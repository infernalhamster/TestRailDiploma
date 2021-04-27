package com.bercut.pltest.mashup.client;

import com.bercut.pltest.mashup.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
@Component
public class MsTeamsClient {

    private final RestTemplate restTemplate;
    private final TestRailClient testRailClient;

    public String sendRequest(String webUrl, String projectsSearch) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        List<Project> projects =
                Arrays.stream(testRailClient.sendGet(ApiName.GET_PROJECTS, Project[].class)).collect(Collectors.toList());
        projects = filterProject(projects, projectsSearch);
        CaseType[] caseTypes = testRailClient.sendGet(ApiName.GET_CASE_TYPES, CaseType[].class);
        Map<Integer, String> caseTypesMap =
                Arrays.stream(caseTypes).collect(Collectors.toMap(CaseType::getId, CaseType::getName));
        User[] users = testRailClient.sendGet(ApiName.GET_USERS, User[].class);
        Map<Integer, String> userMap = Arrays.stream(users).collect(Collectors.toMap(User::getId, User::getEmail));
        ArrayList<Notification.Section> sections;
        Notification notification;
        for (Project project : projects) {
            Case[] cases = testRailClient.sendGet(ApiName.GET_CASES, project.getId(), Case[].class);
            int end;
            for (int j = 0; j < cases.length; j+=50) {
                sections = new ArrayList<>();
                notification = new Notification(sections);
                List<Notification.Section.Facts> factsProject = new ArrayList<>();
                factsProject.add(new Notification.Section.Facts("Url: ", project.getUrl()));
//                sections.add(new Notification.Section("**" + project.getName() + "**", factsProject));
                end  = j + 50;
                if (j + 50 > cases.length){
                    end = cases.length;
                }
                for (int i = j; i < end; i++) {
                    var str = new StringBuilder();
                    List<Notification.Section.Facts> facts = new ArrayList<>();
                    facts.add(new Notification.Section.Facts("Type: ", caseTypesMap.get(cases[i].getTypeId())));
                    facts.add(new Notification.Section.Facts("Creted by: ", userMap.get(cases[i].getCreatedBy())));
                    facts.add(new Notification.Section.Facts("Title: ", cases[i].getTitle()));
                    sections.add(
                            new Notification.Section(str.append(" [")
                                    .append(cases[i].getId())
                                    .append("](https://test2021test.testrail.io/index.php?/cases/view/")
                                    .append(cases[i].getId())
                                    .append(")").toString(),
                                    facts));
                }
                HttpEntity<?> entity = null;
                try {
                    String s = new ObjectMapper().writeValueAsString(notification);
                    System.out.println(s);
                    entity = new HttpEntity<>(s, headers);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                ResponseEntity<String> exchange = restTemplate.postForEntity(webUrl, entity, String.class);
                if (entity != null && !exchange.getBody().equals("1")) {
                    throw new IllegalArgumentException(exchange.toString());
                }
            }
        }
        return "1";
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
