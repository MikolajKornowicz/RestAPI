package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Spy
    private TaskRepository taskRepository;

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
        when(taskRepository.findById(any())).thenReturn(Optional.of(task));
        Long id = 1L;
        //when
        Task retrievedTask = dbService.getTask(task.getId());

        //then
        assertEquals("namedTask",retrievedTask.getTitle());
    }

    @Test
    void testSaveTask(){
        //given
        Task task = new Task(1L, "Test","Test");
        //when
        dbService.saveTask(task);
        //then
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask(){
        //given
        Task task = new Task(1L, "Test","Test");
        //when
        dbService.deleteTaskById(task.getId());
        //then
        verify(taskRepository, times(1)).deleteById(task.getId());
    }
}