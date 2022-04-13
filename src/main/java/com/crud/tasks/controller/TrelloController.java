package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream()
                .filter(trelloBoardDto -> trelloBoardDto.getId() != null)
                .filter(trelloBoardDto -> trelloBoardDto.getName() != null)
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> {
                    System.out.println("Board id: " + trelloBoardDto.getId() + ", board name: " + trelloBoardDto.getName() + ". \n" +
                            "This board consists of: ");
                    trelloBoardDto.getLists().forEach(System.out::println);
                });
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard (@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }

    }