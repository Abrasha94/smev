package ru.intervale.smev.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.intervale.smev.entity.NaturalQueueRequest;
import ru.intervale.smev.entity.NaturalQueueResponse;
import ru.intervale.smev.entity.Penalty;
import ru.intervale.smev.exception.NaturalPersonRequestNotFoundException;
import ru.intervale.smev.exception.PenaltyNotFoundException;
import ru.intervale.smev.repository.NaturalQueueRequestRepository;
import ru.intervale.smev.repository.NaturalQueueResponseRepository;
import ru.intervale.smev.repository.PenaltyRepository;

@Service
@RequiredArgsConstructor
public class WorkerForNaturalPerson {

    private final NaturalQueueRequestRepository naturalQueueRequestRepository;
    private final NaturalQueueResponseRepository naturalQueueResponseRepository;
    private final PenaltyRepository penaltyRepository;

    @Async
    public void emulateGisgmp(Long requestId) {

        final NaturalQueueRequest naturalQueueRequest =
                naturalQueueRequestRepository.findById(requestId)
                        .orElseThrow(() -> new NaturalPersonRequestNotFoundException("Request not found!"));

        final Penalty penalty =
                penaltyRepository.findByVehicleCertificate(naturalQueueRequest.getVehicleCertificate())
                        .orElseThrow(() -> new PenaltyNotFoundException("Penalty not found!"));

        final NaturalQueueResponse naturalQueueResponse = createNaturalQueueResponse(penalty, naturalQueueRequest);

        naturalQueueResponseRepository.save(naturalQueueResponse);

        naturalQueueRequestRepository.delete(naturalQueueRequest);
    }

    private NaturalQueueResponse createNaturalQueueResponse(Penalty penalty, NaturalQueueRequest naturalQueueRequest) {
        final NaturalQueueResponse naturalQueueResponse = new NaturalQueueResponse();
        naturalQueueResponse.setId(naturalQueueRequest.getId());
        naturalQueueResponse.setAccrualAmount(penalty.getAccrualAmount());
        naturalQueueResponse.setAmountToBePaid(penalty.getAmountToBePaid());
        naturalQueueResponse.setResolutionNumber(penalty.getResolutionNumber());
        naturalQueueResponse.setDateOfTheResolution(penalty.getDateOfTheResolution());
        naturalQueueResponse.setArticleOfTheAdministrativeCode(penalty.getArticleOfTheAdministrativeCode());
        naturalQueueResponse.setVehicleCertificate(penalty.getVehicleCertificate());

        return naturalQueueResponse;
    }
}
