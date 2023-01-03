package ru.intervale.smev.service;

import ru.intervale.smev.model.response.ResponseOfPenalty;

public interface LegalQueueResponseService {

    ResponseOfPenalty getResponse(String id);

    void deleteResponse(String taxpayerIdentificationNumber);
}
