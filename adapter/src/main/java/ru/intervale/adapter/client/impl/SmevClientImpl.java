package ru.intervale.adapter.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.intervale.adapter.client.SmevClient;
import ru.intervale.adapter.model.request.RequestPenaltyFromLegalPerson;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;

@Component
public class SmevClientImpl implements SmevClient {

    private static final String SMEV_INFO_URL_NATURAL = "http://localhost:8081/api/v1/info/natural/";
    private static final String SMEV_RESULT_URL_NATURAL = "http://localhost:8081/api/v1/result/natural/";
    private static final String SMEV_INFO_URL_LEGAL = "http://localhost:8081/api/v1/info/legal/";
    private static final String SMEV_RESULT_URL_LEGAL = "http://localhost:8081/api/v1/result/legal/";
    private static final String SMEV_ACKNOWLEDGE_URL_NATURAL = "http://localhost:8081/api/v1/result/natural/acknowledge/";
    private static final String SMEV_ACKNOWLEDGE_URL_LEGAL = "http://localhost:8081/api/v1/result/legal/acknowledge/";

    private final RestTemplate restTemplate;

    @Autowired
    public SmevClientImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

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

    @Override
    public ResponseEntity<HttpStatus> sendAcknowledgeForNaturalPerson(String vehicleCertificate) {
        return restTemplate.getForEntity(SMEV_ACKNOWLEDGE_URL_NATURAL + vehicleCertificate, HttpStatus.class);
    }

    @Override
    public ResponseEntity<HttpStatus> sendAcknowledgeForLegalPerson(String taxpayerIdentificationNumber) {
        return restTemplate.getForEntity(SMEV_ACKNOWLEDGE_URL_LEGAL + taxpayerIdentificationNumber, HttpStatus.class);
    }
}
