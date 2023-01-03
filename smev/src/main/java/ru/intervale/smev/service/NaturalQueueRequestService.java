package ru.intervale.smev.service;

import ru.intervale.smev.entity.NaturalQueueRequest;
import ru.intervale.smev.model.request.RequestInfoFromNaturalPerson;

public interface NaturalQueueRequestService {

    NaturalQueueRequest createInfoRequestForNatural(RequestInfoFromNaturalPerson request);
}
