package ru.intervale.adapter.rest;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.intervale.adapter.exception.SmevClientException;
import ru.intervale.adapter.model.request.RequestPenaltyFromLegalPerson;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;
import ru.intervale.adapter.service.PenaltyService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PenaltyController.class)
public class PenaltyControllerTest {

    @MockBean
    PenaltyService penaltyService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenRequestPenalty_thenReturnPenalty() throws Exception {
        final ResponseOfPenalty penalty = getResponseOfPenalty();

        when(penaltyService.getInfoFromSmevForNaturalPerson(any(RequestPenaltyFromNaturalPerson.class)))
                .thenReturn(penalty);

        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/penalties/natural")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"vehicleCertificate\":\"test\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accrualAmount").value(BigDecimal.TEN));
    }

    @Test
    void whenException_thenStatusError() throws Exception {

        when(penaltyService.getInfoFromSmevForLegalPerson(any(RequestPenaltyFromLegalPerson.class)))
                .thenThrow(SmevClientException.class);

        final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/penalties/legal")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"taxpayerIdentificationNumber\":\"test\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isInternalServerError());
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
