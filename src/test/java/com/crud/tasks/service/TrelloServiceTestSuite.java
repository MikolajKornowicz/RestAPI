package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;


    @Test
    void testFetchTrelloBoards() {
        //given
        //when
        List<TrelloBoardDto> dtoList = trelloService.fetchTrelloBoards();
        //then
        verify(trelloClient, times(1)).getTrelloBoards();
        assertEquals(0, dtoList.size());
    }

    @Test
    void testCreateTrelloBoard () {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test", "test", "test");
        //when
        trelloService.createTrelloCard(trelloCardDto);
        //then
        verify(trelloClient, times(1)).createNewCard(trelloCardDto);
    }
}