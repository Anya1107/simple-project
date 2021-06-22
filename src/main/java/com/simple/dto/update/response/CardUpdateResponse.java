package com.simple.dto.update.response;

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
public class CardUpdateResponse {
    private long id;
    private String logicStatus;
    private String number;
    private String cardFirstName;
    private String cardLastName;
}
