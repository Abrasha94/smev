package ru.intervale.adapter.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ResponseOfPenalty {

    private BigDecimal accrualAmount;
    private BigDecimal amountToBePaid;
    private String resolutionNumber;
    private String vehicleCertificate;
    private LocalDate dateOfTheResolution;
    private String articleOfTheAdministrativeCode;

}
