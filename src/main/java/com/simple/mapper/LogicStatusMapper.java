package com.simple.mapper;

import com.simple.dto.CardDto;
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

    public CardDto convertLogicStatus(CardDto cardDto){
        convertStatusField(cardDto);
        return cardDto;
    }

    public List<CardDto> convertLogicStatuses(List<CardDto> cardDtos){
        for (CardDto cardDto : cardDtos) {
            convertStatusField(cardDto);
        }
        return cardDtos;
    }

    private void convertStatusField(CardDto cardDto) {
        String logicStatus = cardDto.getLogicStatus();
        String defaultMessage = logicStatusProperties.getDefaultMessage();
        String status = logicStatusProperties.getLogicStatuses().get(logicStatus);

        cardDto.setLogicStatus(status != null ? status : defaultMessage);
    }
}
