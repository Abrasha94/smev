package ru.intervale.smev.service;

import ru.intervale.smev.entity.LegalQueueRequest;
import ru.intervale.smev.model.request.RequestInfoFromLegalPerson;

public interface LegalQueueRequestService {

    LegalQueueRequest createInfoRequestForNatural(RequestInfoFromLegalPerson request);
}
