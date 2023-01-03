package ru.intervale.smev.service;

import ru.intervale.smev.model.response.ResponseOfPenalty;

public interface NaturalQueueResponseService {

    ResponseOfPenalty getResponse(String id);

    void deleteResponse(String vehicleCertificate);
}
