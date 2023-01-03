package ru.intervale.smev.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.intervale.smev.entity.LegalQueueRequest;
import ru.intervale.smev.entity.LegalQueueResponse;
import ru.intervale.smev.entity.Penalty;
import ru.intervale.smev.exception.NaturalPersonRequestNotFoundException;
import ru.intervale.smev.exception.PenaltyNotFoundException;
import ru.intervale.smev.repository.LegalQueueRequestRepository;
import ru.intervale.smev.repository.LegalQueueResponseRepository;
import ru.intervale.smev.repository.PenaltyRepository;

@Service
@RequiredArgsConstructor
public class WorkerForLegalPerson {

    private final LegalQueueRequestRepository legalQueueRequestRepository;
    private final LegalQueueResponseRepository legalQueueResponseRepository;
    private final PenaltyRepository penaltyRepository;

    @Async
    public void emulateGisgmp(Long requestId) {

        final LegalQueueRequest legalQueueRequest =
                legalQueueRequestRepository.findById(requestId)
                        .orElseThrow(() -> new NaturalPersonRequestNotFoundException("Request not found!"));

        final Penalty penalty =
                penaltyRepository.findByTaxpayerIdentificationNumber(legalQueueRequest.getTaxpayerIdentificationNumber())
                        .orElseThrow(() -> new PenaltyNotFoundException("Penalty not found!"));

        final LegalQueueResponse legalQueueResponse = createLegalQueueResponse(penalty, legalQueueRequest);

        legalQueueResponseRepository.save(legalQueueResponse);

        legalQueueRequestRepository.delete(legalQueueRequest);
    }

    private LegalQueueResponse createLegalQueueResponse(Penalty penalty, LegalQueueRequest legalQueueRequest) {
        final LegalQueueResponse legalQueueResponse = new LegalQueueResponse();
        legalQueueResponse.setId(legalQueueRequest.getId());
        legalQueueResponse.setAccrualAmount(penalty.getAccrualAmount());
        legalQueueResponse.setAmountToBePaid(penalty.getAmountToBePaid());
        legalQueueResponse.setResolutionNumber(penalty.getResolutionNumber());
        legalQueueResponse.setDateOfTheResolution(penalty.getDateOfTheResolution());
        legalQueueResponse.setArticleOfTheAdministrativeCode(penalty.getArticleOfTheAdministrativeCode());
        legalQueueResponse.setVehicleCertificate(penalty.getVehicleCertificate());
        legalQueueResponse.setTaxpayerIdentificationNumber(penalty.getTaxpayerIdentificationNumber());

        return legalQueueResponse;
    }
}
