package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *" )
    public void sendInformationEmail() {
        log.info("Preparing scheduled mail");
        long size = taskRepository.count();
        if (size == 1) {
            log.info("Singular form mail prepared");
            simpleEmailService.send(
                          new Mail(
                                    adminConfig.getAdminMail(),
                            SUBJECT,
                            "Currently in database you got: " + size + " task",
                            null
                    )
            );
        } else {
            log.info("Plural form mail prepared");
            simpleEmailService.send(
                    new Mail(
                            adminConfig.getAdminMail(),
                            SUBJECT,
                            "Currently in database you got: " + size + " tasks",
                            null
                    )
            );
        }
        log.info("Scheduled mail has been sent");
    }
}
