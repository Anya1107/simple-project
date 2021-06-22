package com.simple.mapper;

import com.simple.dto.create.response.CardCreateResponse;
import com.simple.dto.get.response.CardGetResponse;
import com.simple.dto.update.request.CardUpdateRequest;
import com.simple.dto.update.response.CardUpdateResponse;
import com.simple.properties.LogicStatusProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class LogicStatusMapper {

    @Autowired
    private LogicStatusProperties logicStatusProperties;

    public List<CardGetResponse> convertLogicStatuses(List<CardGetResponse> cardDtos){
        for (CardGetResponse cardDto : cardDtos) {
            cardDto.setLogicStatus(convertStatusField(cardDto.getLogicStatus()));
        }
        return cardDtos;
    }

    public String convertStatusField(String logicStatus) {
        String defaultMessage = logicStatusProperties.getDefaultMessage();
        String status = logicStatusProperties.getLogicStatuses().get(logicStatus);

        return status != null ? status : defaultMessage;
    }
}
