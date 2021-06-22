package com.simple.dto.create.response;

import com.simple.entity.Status;
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
public class CardAccountCreateResponse {
    private long id;
    private String billNumber;
    private String currency;
    private Status status;
}
