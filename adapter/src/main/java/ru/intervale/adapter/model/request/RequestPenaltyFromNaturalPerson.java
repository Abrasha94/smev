package ru.intervale.adapter.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestPenaltyFromNaturalPerson {

    private String vehicleCertificate;
}
