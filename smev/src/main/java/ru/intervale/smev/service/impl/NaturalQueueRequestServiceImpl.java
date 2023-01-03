package ru.intervale.smev.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.intervale.smev.entity.NaturalQueueRequest;
import ru.intervale.smev.model.request.RequestInfoFromNaturalPerson;
import ru.intervale.smev.repository.NaturalQueueRequestRepository;
import ru.intervale.smev.service.NaturalQueueRequestService;

@Service
@RequiredArgsConstructor
public class NaturalQueueRequestServiceImpl implements NaturalQueueRequestService {

    private final NaturalQueueRequestRepository naturalQueueRequestRepository;

    @Override
    public NaturalQueueRequest createInfoRequestForNatural(RequestInfoFromNaturalPerson request) {

        final NaturalQueueRequest naturalQueueRequest = new NaturalQueueRequest();
        naturalQueueRequest.setVehicleCertificate(request.getVehicleCertificate());

        return naturalQueueRequestRepository.save(naturalQueueRequest);
    }
}
