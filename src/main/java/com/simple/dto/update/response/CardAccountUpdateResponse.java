package com.simple.dto.update.response;

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
public class CardAccountUpdateResponse {
    private long id;
    private String bill_number;
    private String currency;
    private Status status;
}
