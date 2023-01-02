package ru.intervale.adapter.model.request.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.intervale.adapter.model.request.RequestPenalty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestPenaltyFromLegalPersonPenalty implements RequestPenalty {

    private String taxpayerIdentificationNumber;
}
