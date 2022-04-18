package com.crud.tasks.domain;

import lombok.*;

import java.util.Optional;

@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Mail {

    private final String sendTo;
    private final String subject;
    private final String message;
    private String toCC;


}
