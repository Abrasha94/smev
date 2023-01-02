package ru.intervale.adapter.model.request.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.intervale.adapter.model.request.RequestPenalty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestPenaltyFromNaturalPersonPenalty implements RequestPenalty {

    private String vehicleCertificate;
}
