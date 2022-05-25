package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private DbService dbService;


    private int numberOfTasks(){
        List<Task> tasks = dbService.getAllTasks();
        return tasks.size();
    }

    private String messageContentGenerator() {
        int amount = numberOfTasks();
        if(amount == 1){
            return "There is only 1 task in the database.";
        } if (amount == 0){
            return "There are no tasks in the database.";
        } else {
            return "There are " + amount + " tasks in the database.";
        }
    }

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Thank you for using our services");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildNumberOfTasksEmail (){
        Context context = new Context();
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("number_of_tasks", numberOfTasks());
        context.setVariable("button", "Visit the website");
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("message_content", messageContentGenerator());
        context.setVariable("goodbye_message", "Thank you for using our services");

        return templateEngine.process("mail/number-of-tasks-mail", context);
    }
}
