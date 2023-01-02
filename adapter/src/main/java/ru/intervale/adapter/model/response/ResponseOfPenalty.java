package ru.intervale.adapter.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ResponseOfPenalty {

    private BigDecimal accrualAmount;
    private BigDecimal amountToBePaid;
    private String resolutionNumber;
    private String vehicleCertificate;
    private Date dateOfTheResolution;
    private String articleOfTheAdministrativeCode;

}
