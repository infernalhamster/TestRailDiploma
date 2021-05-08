package com.bercut.pltest.mashup.controller;

import com.bercut.pltest.mashup.client.ApiName;
import com.bercut.pltest.mashup.client.TestRailClient;
import com.bercut.pltest.mashup.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class WebController {
    private final TestRailClient client;

    @GetMapping(value = {"/", "/user", "/index"})
    public String users(Model model) {
        User[] user = client.sendGet(ApiName.GET_USERS, User[].class);
        model.addAttribute("users", user);
        return "usersList";
    }

    @GetMapping("/user/runs/{id}")
    public String getUserRuns(@PathVariable("id") int id, Model model) {
        User user = client.sendGet(ApiName.GET_USER, id, User.class);
        model.addAttribute("user", user);
        Project[] projects = client.sendGet(ApiName.GET_PROJECTS, Project[].class);
        Map<Project, Map<Plan, List<TestRun>>> yourMap = new HashMap<>(projects.length);
        for (Project project : projects) {
            TestRun[] testRuns = client.sendGet(ApiName.GET_RUNS, project.getId(), TestRun[].class);
            Plans[] plans = client.sendGet(ApiName.GET_PLANS, project.getId(), Plans[].class);
            List<TestRun> collect =
                    Arrays.stream(testRuns)
                            .filter(testRun -> testRun.getAssignedtoId() == id)
                            .collect(Collectors.toList());
            Map<Plan, List<TestRun>> map = new HashMap<>();
            map.put(null, collect);
            for (Plans planId : plans) {
                Plan plan = client.sendGet(ApiName.GET_PLAN, planId.getId(), Plan.class);
                List<TestRun> planTest =
                        Arrays.stream(plan.getEntries())
                                .map(Plan.Entries::getRuns)
                                .flatMap(Arrays::stream)
                                .filter(testRuns1 -> testRuns1.getAssignedtoId() == id)
                                .collect(Collectors.toList());
                map.put(plan, planTest);
            }
            yourMap.put(project, map);
        }
        model.addAttribute("yourMap", yourMap);
        return "userProfileRuns";
    }

    @GetMapping("/user/cases/{id}")
    public String getUserCases(@PathVariable("id") int id, Model model) {
        User user = client.sendGet(ApiName.GET_USER, id, User.class);
        model.addAttribute("user", user);
        Project[] projects = client.sendGet(ApiName.GET_PROJECTS, Project[].class);
        Map<Project, List<Case>> casesMap = new HashMap<>(projects.length);
        for (Project project : projects) {
            Case[] cases = client.sendGet(ApiName.GET_CASES, project.getId(), Case[].class);
            List<Case> collect = Arrays.stream(cases)
                    .filter(aCase -> aCase.getCreatedBy() == id || aCase.getUpdatedBy() == id)
                    .collect(Collectors.toList());
            casesMap.put(project, collect);
        }
        model.addAttribute("casesMap", casesMap);
        return "userProfileCases";
    }

    @GetMapping("/search")
    public String search(Model model) {
        Project[] projects = client.sendGet(ApiName.GET_PROJECTS, Project[].class);
        Map<Project, List<Case>> map = new HashMap<>(projects.length);
        CaseType[] caseTypes = client.sendGet(ApiName.GET_CASE_TYPES, CaseType[].class);
        Map<Integer, String> caseTypesMap =
                Arrays.stream(caseTypes).collect(Collectors.toMap(CaseType::getId, CaseType::getName));
        User[] users = client.sendGet(ApiName.GET_USERS, User[].class);
        Map<Integer, String> userMap = Arrays.stream(users).collect(Collectors.toMap(User::getId, User::getEmail));
        for (Project project : projects) {
            Case[] cases = client.sendGet(ApiName.GET_CASES, project.getId(), Case[].class);
            for (Case aCase : cases) {
                aCase.setType(caseTypesMap.get(aCase.getTypeId()));
                aCase.setEmailCreatedBy(userMap.get(aCase.getCreatedBy()));
                aCase.setEmailUpdatedBy(userMap.get(aCase.getUpdatedBy()));
            }
            map.put(project, Arrays.stream(cases).collect(Collectors.toList()));
        }
        model.addAttribute("mapCases", map);
        model.addAttribute("mapAllCases", null);
        Search attributeValue = new Search();
        attributeValue.setDateUntil(new Date());
        model.addAttribute("search", attributeValue);
        return "search";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Search search, Model model) {
        List<Project> projects =
                Arrays.stream(client.sendGet(ApiName.GET_PROJECTS, Project[].class)).collect(Collectors.toList());
        projects = filterProject(projects, search.getProjects());
        CaseType[] caseTypes = client.sendGet(ApiName.GET_CASE_TYPES, CaseType[].class);
        Map<Integer, String> caseTypesMap =
                Arrays.stream(caseTypes).collect(Collectors.toMap(CaseType::getId, CaseType::getName));
        List<User> users = Arrays.stream(client.sendGet(ApiName.GET_USERS, User[].class)).collect(Collectors.toList());
        Map<Integer, String> collect = filterUserEmail(users, search.getEmails()).stream()
                .collect(Collectors.toMap(User::getId, User::getEmail));
        Map<Integer, String> userMap = users.stream().collect(Collectors.toMap(User::getId, User::getEmail));
        Map<Project, List<Case >> map = new HashMap<>(projects.size());
        Map<Project, List<Case >> mapAllCases = new HashMap<>();
        if (search.getDateFrom() == null) {
            search.setDateFrom(new Date(0L));
        }
        for (Project project : projects) {
            List<Case> cases = Arrays.stream(client.sendGet(ApiName.GET_CASES, project.getId(), Case[].class))
                    .filter(aCase -> {
                        if (
                                (
                                        (!search.getIsModified() && collect.containsKey(aCase.getCreatedBy()))
                                                || (search.getIsModified() && collect.containsKey(aCase.getUpdatedBy()))
                                )
                                        && aCase.getCreatedOn().getTime() >= search.getDateFrom().getTime()
                                        && aCase.getCreatedOn().getTime() <= search.getModifiedDataUntil()
                        ) {
                            aCase.setType(caseTypesMap.get(aCase.getTypeId()));
                            aCase.setEmailCreatedBy(userMap.get(aCase.getCreatedBy()));
                            aCase.setEmailUpdatedBy(userMap.get(aCase.getUpdatedBy()));
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList());
            List<Case> allCases = new ArrayList<>();
            if (search.getOnlyAutomated()) {
                allCases = cases.stream()
                        .filter(aCase -> !aCase.getType().equals("Automated")).collect(Collectors.toList());
                cases = cases.stream()
                        .filter(aCase -> aCase.getType().equals("Automated")).collect(Collectors.toList());
            }
            mapAllCases.put(project, allCases);
            map.put(project, cases);
        }
        model.addAttribute("mapCases", map);
        model.addAttribute("mapAllCases", mapAllCases);
        return "search";
    }

    private List<User> filterUserEmail(List<User> users, String emails) {
        if (emails.contains(",")) {
            String[] split = emails.split(", ");
            return users.stream()
                    .filter(user -> Arrays.stream(split).anyMatch(s -> s.equals(user.getEmail())))
                    .collect(Collectors.toList());
        } else {
            return users.stream().filter(user -> user.getEmail().equals(emails)).collect(Collectors.toList());
        }
    }

    private List<Project> filterProject(List<Project> projects, String filter) {
        if (filter.contains(",")) {
            String[] split = filter.split(", ");
            return projects.stream()
                    .filter(project -> Arrays.stream(split).anyMatch(s -> s.equals(project.getName())))
                    .collect(Collectors.toList());
        } else if (filter.contains("*")) {
            filter = filter.replace("*", ".*"); // space or char
            filter = "^" + filter;
            filter += "$";
            Pattern pattern = Pattern.compile(filter);
            return projects.stream()
                    .filter(project -> pattern.matcher(project.getName()).find())
                    .collect(Collectors.toList());
        } else {
            String finalFilter = filter;
            return projects.stream().filter(project -> project.getName().equals(finalFilter)).collect(Collectors.toList());
        }
    }

}
