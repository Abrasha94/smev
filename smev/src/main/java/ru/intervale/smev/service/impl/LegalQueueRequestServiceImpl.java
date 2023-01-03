package ru.intervale.smev.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.intervale.smev.entity.LegalQueueRequest;
import ru.intervale.smev.model.request.RequestInfoFromLegalPerson;
import ru.intervale.smev.repository.LegalQueueRequestRepository;
import ru.intervale.smev.service.LegalQueueRequestService;

@Service
@RequiredArgsConstructor
public class LegalQueueRequestServiceImpl implements LegalQueueRequestService {

    private final LegalQueueRequestRepository legalQueueRequestRepository;

    @Override
    public LegalQueueRequest createInfoRequestForNatural(RequestInfoFromLegalPerson request) {

        final LegalQueueRequest legalQueueRequest = new LegalQueueRequest();
        legalQueueRequest.setTaxpayerIdentificationNumber(request.getTaxpayerIdentificationNumber());

        return legalQueueRequestRepository.save(legalQueueRequest);
    }
}
