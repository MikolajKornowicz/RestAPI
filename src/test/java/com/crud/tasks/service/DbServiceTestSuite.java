package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private Task task;




    @Test
    void testGetAllTasks (){
        //given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test", "test"));
        taskList.add(new Task(2L, "test", "test"));
        taskList.add(new Task(3L, "test", "test"));
        when(taskRepository.findAll()).thenReturn(taskList);
        //when
        List<Task> retrievedTasks = dbService.getAllTasks();
        //then
        assertEquals(3, retrievedTasks.size());
    }


    @Test
    void testGetTask() throws TaskNotFoundException {
        //given

        Task task =new Task(1L, "namedTask", "test");
        Task task1 =new Task(2L, "test", "test");
        Task task2 = new Task(3L, "test", "test");
        //when
        dbService.saveTask(task);
        dbService.saveTask(task1);
        dbService.saveTask(task2);
        Task retrievedTask = dbService.getTask(2L);

        //then
        assertEquals("namedTask",retrievedTask.getTitle());
    }


}