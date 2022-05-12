package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTestsuite {

    @InjectMocks
    private EmailScheduler emailScheduler;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;


    @Test
    void testOneMail (){
        //given
        when(taskRepository.count()).thenReturn(1L);
        //when
        emailScheduler.sendInformationEmail();
        //then

    }

    @Test
    void testManyMail (){
        //given
        when(taskRepository.count()).thenReturn(25L);
        //when
        emailScheduler.sendInformationEmail();
        //then

    }
}