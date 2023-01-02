package ru.intervale.adapter.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;

import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest(SmevClient.class)
public class SmevClientTest {

    @Autowired
    private SmevClient smevClient;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    void whenGettingInfo_thenReturnId() {
        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8081/api/v1/info/natural/"))
                .andRespond(MockRestResponseCreators.withSuccess("test", MediaType.TEXT_PLAIN));

        final ResponseEntity<String> response = smevClient.gettingInformationForNaturalPerson(new RequestPenaltyFromNaturalPerson());

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo("test");
    }

    @Test
    void whenGettingResult_thenReturnResponse() {
        String json = "{\n" +
                "    \"accrualAmount\":12.345,\n" +
                "    \"amountToBePaid\":67.891,\n" +
                "    \"resolutionNumber\":\"test\",\n" +
                "    \"vehicleCertificate\":\"test\",\n" +
                "    \"dateOfTheResolution\":\"2023-01-02\",\n" +
                "    \"articleOfTheAdministrativeCode\":\"test\"\n" +
                "}";

        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8081/api/v1/result/legal/"))
                .andRespond(MockRestResponseCreators.withSuccess(json, MediaType.APPLICATION_JSON));

        final ResponseEntity<ResponseOfPenalty> response = smevClient.gettingResultForLegalPerson("1");

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResolutionNumber()).isEqualTo("test");
    }
}
