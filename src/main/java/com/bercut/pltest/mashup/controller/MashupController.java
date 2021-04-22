package com.bercut.pltest.mashup.controller;

import com.bercut.pltest.mashup.client.ApiName;
import com.bercut.pltest.mashup.client.TestRailClient;
import com.bercut.pltest.mashup.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Controller
public class MashupController {
    private final TestRailClient client;

    @GetMapping("/user")
    public String users(Model model) {
        User[] user = client.sendGet(ApiName.GET_USERS, User[].class);
        model.addAttribute("users", user);
        return "usersList";
    }

    @GetMapping("/user/runs/{id}")
    public String getUserRuns(@PathVariable("id") int id, Model model) throws JsonProcessingException {
        User user = client.sendGet(ApiName.GET_USER, id, User.class);
        model.addAttribute("user", user);
        Project[] projects = client.sendGet(ApiName.GET_PROJECTS, Project[].class); // Получение проектов
        Map<Project, Map<Plan, List<TestRun>>> yourMap = new HashMap<>(projects.length);
        for (Project project : projects) { // Получим из каждого проекта свои TestRun
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
        Project[] projects = client.sendGet(ApiName.GET_PROJECTS, Project[].class);               // Получение проектов
        Map<Project, List<Case>> casesMap = new HashMap<>(projects.length);
        for (Project project : projects) {                                   // Получим из каждого проекта свои TestRun
            Case[] cases = client.sendGet(ApiName.GET_CASES, project.getId(), Case[].class);
            List<Case> collect = Arrays.stream(cases)
                    .filter(aCase -> aCase.getCreatedBy() == id || aCase.getUpdatedBy() == id)
                    .collect(Collectors.toList());
            casesMap.put(project, collect);
        }
        model.addAttribute("casesMap", casesMap);
        return "userProfileCases";
    }

    @GetMapping("runs")
    public String newRuns(@RequestParam(name = "name", required = false, defaultValue = "newRun") String name, Model model) {
        model.addAttribute("name", name);
        return "newRun";
    }

    @GetMapping("runsscp")
    public String newRunsScp(@RequestParam(name = "name", required = false, defaultValue = "newRunScp") String name, Model model) {
        model.addAttribute("name", name);
        return "newRunScp";
    }

}
