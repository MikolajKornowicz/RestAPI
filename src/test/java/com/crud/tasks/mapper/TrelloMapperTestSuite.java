package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TrelloMapperTestSuite {

@InjectMocks
private TrelloMapper trelloMapper;

@Mock
private TrelloBoard trelloBoard;

@Mock
private TrelloBoardDto trelloBoardDto;

@Mock
private TrelloListDto trelloListDto;

@Mock
private TrelloList trelloList;

@Mock
private TrelloCard trelloCard;

@Mock
private TrelloCardDto trelloCardDto;


    @Test
    void testMapToList(){
        //given
        List<TrelloList> testList = new ArrayList<>();
        testList.add(trelloList);
        //when
        List<TrelloListDto> testListDto = trelloMapper.mapToListDto(testList);
        //then
        assertEquals(1, testListDto.size());
    }
    @Test
    void testMapToListDto(){
        //given
        List<TrelloListDto> testListDto = new ArrayList<>();
        testListDto.add(trelloListDto);
        //when
        List<TrelloList> testList = trelloMapper.mapToList(testListDto);
        //then
        assertEquals(1, testList.size());
    }

@Test
    void testMapToBoard(){
    //given
    List<TrelloListDto> testList = new ArrayList<>();
    testList.add(trelloListDto);
    trelloBoardDto = new TrelloBoardDto("test", "test",testList);
    List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
    trelloBoardDtos.add(trelloBoardDto);
    //when
    List<TrelloBoard> testBoard = trelloMapper.mapToBoard(trelloBoardDtos);
    //then
    assertEquals(1, testBoard.size());
    assertEquals("test", testBoard.get(0).getName());
}

    @Test
    void testMapToBoardDto(){
        //given
        List<TrelloList> testList = new ArrayList<>();
        testList.add(trelloList);
        trelloBoard = new TrelloBoard("test", "testDTO", testList);
        List<TrelloBoard> trelloBoards =  new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //when
        List<TrelloBoardDto> testTrelloBoardDtos = trelloMapper.mapToBoardDto(trelloBoards);
        //then
        assertEquals(1, testTrelloBoardDtos.size());
        assertEquals("testDTO", testTrelloBoardDtos.get(0).getName());
    }

    @Test
    void mapToCard(){
        //given
        trelloCardDto = new TrelloCardDto("testName", "testDesc", "testPos", "testId");
        //when
        TrelloCard testCard = trelloMapper.mapToCard(trelloCardDto);
        //then
        assertEquals("testName", testCard.getName());
        assertEquals("testDesc", testCard.getDescription());
        assertEquals("testPos", testCard.getPos());
        assertEquals("testId", testCard.getListId());
    }
    @Test
    void mapToCardDto() {
        //given
        trelloCard = new TrelloCard("testName", "testDesc", "testPos", "testId");
        //when
        TrelloCardDto testCardDto = trelloMapper.mapToCardDto(trelloCard);
        //then
        assertEquals("testName", testCardDto.getName());
        assertEquals("testDesc", testCardDto.getDescription());
        assertEquals("testPos", testCardDto.getPos());
        assertEquals("testId", testCardDto.getListId());
    }
}