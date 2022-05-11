package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TrelloValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloCard trelloCard;

    @Mock
    private TrelloBoard trelloBoard;

    @Test
    void testCardValidation () {
        //given
        trelloCard = new TrelloCard("test", "test", "test", "test");
        //when
        trelloValidator.validateCard(trelloCard);
        String name = trelloCard.getName();
        //then
        assertEquals("test", name);
    }

    @Test
    void testBoardValidation(){
        //given
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(new TrelloBoard("test", "test", new ArrayList<>()));
        boards.add(new TrelloBoard("board", "board", new ArrayList<>()));
        //when
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(boards);
        //then
        assertEquals(1, validatedBoards.size());
    }

}