package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Mock
    private Task task;

    @Mock
    private TaskDto taskDto;

    @Test
    void mapToTaskDto () {
        //given
        task = new Task(1L, "test name", "test test test");
        //when
        TaskDto dto = taskMapper.mapToTaskDto(task);
        //then
        assertEquals("test name", dto.getTitle());
        assertEquals("test test test", dto.getContent());
        assertEquals(1, dto.getId());
    }

    @Test
    void mapTaskDtoToTask () {
        //given
        taskDto = new TaskDto(1L, "test name", "test test test");
        //when
        Task testTask = taskMapper.mapToTask(taskDto);
        //then
        assertEquals("test name", testTask.getTitle());
        assertEquals(1, testTask.getId());
        assertEquals("test test test", testTask.getContent());
    }

    @Test
    void mapTaskToTaskDtoList () {
        //given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test", "test test test"));
        taskList.add(new Task(2L, "test2", "test test test"));
        taskList.add(new Task(3L, "test3", "test test test"));
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //then
        assertEquals(3, taskDtoList.size());
        assertEquals(3, taskDtoList.get(2).getId());
        assertEquals(2, taskDtoList.get(1).getId());
        assertEquals(1, taskDtoList.get(0).getId());
    }
}