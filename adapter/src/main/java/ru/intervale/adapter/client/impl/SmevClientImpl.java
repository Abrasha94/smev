package ru.intervale.adapter.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.intervale.adapter.client.SmevClient;
import ru.intervale.adapter.model.request.RequestPenalty;

@Service
@RequiredArgsConstructor
public class SmevClientImpl implements SmevClient {

    private static final String SMEV_URL = "http://localhost:8081/api/v1/info";
    private final RestTemplate restTemplate;

    @Override
    public HttpStatus gettingInformation(RequestPenalty request) {

        return restTemplate.postForObject(SMEV_URL, request, HttpStatus.class);
    }
}
