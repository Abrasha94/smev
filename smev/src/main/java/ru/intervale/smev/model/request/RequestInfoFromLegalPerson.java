package ru.intervale.smev.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestInfoFromLegalPerson {

    private String taxpayerIdentificationNumber;
}
