package ru.intervale.adapter.service;

import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.intervale.adapter.client.SmevClient;
import ru.intervale.adapter.exception.SmevClientException;
import ru.intervale.adapter.model.request.RequestPenaltyFromLegalPerson;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;
import ru.intervale.adapter.service.impl.PenaltyServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PenaltyServiceTest {

    @InjectMocks
    private PenaltyServiceImpl penaltyService;

    @Mock
    private SmevClient smevClient;

    @Test
    void whenGettingInfoForPerson_thenReturnDto() throws InterruptedException {
        final ResponseOfPenalty penalty = getResponseOfPenalty();

        when(smevClient.gettingInformationForNaturalPerson(any(RequestPenaltyFromNaturalPerson.class)))
                .thenReturn(new ResponseEntity<>("test", HttpStatus.OK));
        when(smevClient.gettingResultForNaturelPerson("test"))
                .thenReturn(new ResponseEntity<>(penalty, HttpStatus.OK));

        final ResponseOfPenalty response = penaltyService.getInfoFromSmevForNaturalPerson(new RequestPenaltyFromNaturalPerson());

        assertThat(response.getDateOfTheResolution()).isEqualTo(penalty.getDateOfTheResolution());
    }

    @Test
    void whenNoAnswerFromSvem_thenThrowException() {

        when(smevClient.gettingInformationForLegalPerson(any(RequestPenaltyFromLegalPerson.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        Assertions.assertThatThrownBy(() ->
                        penaltyService.getInfoFromSmevForLegalPerson(new RequestPenaltyFromLegalPerson()))
                .isInstanceOf(SmevClientException.class);
    }

    @NotNull
    private static ResponseOfPenalty getResponseOfPenalty() {
        final ResponseOfPenalty penalty = new ResponseOfPenalty();
        penalty.setAccrualAmount(BigDecimal.TEN);
        penalty.setAmountToBePaid(BigDecimal.TEN);
        penalty.setArticleOfTheAdministrativeCode("test");
        penalty.setDateOfTheResolution(LocalDate.now());
        penalty.setResolutionNumber("test");
        penalty.setVehicleCertificate("test");
        return penalty;
    }
}
