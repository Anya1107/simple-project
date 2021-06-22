package com.simple.dto.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardCreateRequest {
    private String logicStatus;
    private String number;
    private String cardFirstName;
    private String cardLastName;
}
