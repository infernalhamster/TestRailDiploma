package com.bercut.pltest.mashup.controller;

import com.bercut.pltest.mashup.client.MsTeamsClient;
import com.bercut.pltest.mashup.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ScheduledFuture;

@Controller
@RequestMapping("/group")
public class WebGroupController {
    private final MsTeamsClient msTeamsClient;
    TaskScheduler scheduler = new DefaultManagedTaskScheduler();
    private ScheduledFuture nowSchedule;

    public WebGroupController(MsTeamsClient msTeamsClient) {
        this.msTeamsClient = msTeamsClient;
    }

    @GetMapping
    public String group(Model model) {
        model.addAttribute("group", new Group());
        return "group";
    }

    @PostMapping
    public String group(@ModelAttribute Group group) {
        if (nowSchedule != null) {
            nowSchedule.cancel(true);
        }
        nowSchedule = scheduler.schedule(
                new Notification(group.getWebUrl(), group.getProjects(), msTeamsClient),
                new CronTrigger(group.getCron())
        );
        return "group";
    }

    @Data
    @AllArgsConstructor
    static class Notification implements Runnable {

        private final String webURL;
        private final String project;
        private final MsTeamsClient msTeamsClient;

        @Override
        public void run() {
            msTeamsClient.sendRequest(webURL, project);
        }

    }


}
