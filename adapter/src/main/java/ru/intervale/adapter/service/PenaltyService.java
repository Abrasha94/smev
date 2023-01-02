package ru.intervale.adapter.service;

import ru.intervale.adapter.model.request.RequestPenaltyFromLegalPerson;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;

public interface PenaltyService {

    ResponseOfPenalty getInfoFromSmevForNaturalPerson(RequestPenaltyFromNaturalPerson request) throws InterruptedException;

    ResponseOfPenalty getInfoFromSmevForLegalPerson(RequestPenaltyFromLegalPerson request) throws InterruptedException;
}
