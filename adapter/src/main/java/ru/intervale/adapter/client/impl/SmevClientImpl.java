package ru.intervale.adapter.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.intervale.adapter.client.SmevClient;
import ru.intervale.adapter.model.request.RequestPenaltyFromLegalPerson;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;

@Service
@RequiredArgsConstructor
public class SmevClientImpl implements SmevClient {

    private static final String SMEV_INFO_URL_NATURAL = "http://localhost:8081/api/v1/info/natural/";
    private static final String SMEV_RESULT_URL_NATURAL = "http://localhost:8081/api/v1/result/natural/";
    private static final String SMEV_INFO_URL_LEGAL = "http://localhost:8081/api/v1/info/legal/";
    private static final String SMEV_RESULT_URL_LEGAL = "http://localhost:8081/api/v1/result/legal/";
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> gettingInformationForNaturalPerson(RequestPenaltyFromNaturalPerson request) {

        return restTemplate.postForEntity(SMEV_INFO_URL_NATURAL, request, String.class);
    }

    @Override
    public ResponseEntity<ResponseOfPenalty> gettingResultForNaturelPerson(String id) {
        return restTemplate.getForEntity(SMEV_RESULT_URL_NATURAL + id, ResponseOfPenalty.class);
    }

    @Override
    public ResponseEntity<String> gettingInformationForLegalPerson(RequestPenaltyFromLegalPerson request) {
        return restTemplate.postForEntity(SMEV_INFO_URL_LEGAL, request, String.class);
    }

    @Override
    public ResponseEntity<ResponseOfPenalty> gettingResultForLegalPerson(String id) {
        return restTemplate.getForEntity(SMEV_RESULT_URL_LEGAL, ResponseOfPenalty.class);
    }
}
