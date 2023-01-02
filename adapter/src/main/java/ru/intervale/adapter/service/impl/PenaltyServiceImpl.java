package ru.intervale.adapter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.intervale.adapter.client.SmevClient;
import ru.intervale.adapter.exception.SmevClientException;
import ru.intervale.adapter.model.request.RequestPenaltyFromLegalPerson;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;
import ru.intervale.adapter.service.PenaltyService;

@Service
@RequiredArgsConstructor
public class PenaltyServiceImpl implements PenaltyService {

    private final SmevClient smevClient;

    @Override
    public ResponseOfPenalty getInfoFromSmevForNaturalPerson(RequestPenaltyFromNaturalPerson request) throws InterruptedException {
        final ResponseOfPenalty penalty = new ResponseOfPenalty();

        final ResponseEntity<String> responseWithId = smevClient.gettingInformationForNaturalPerson(request);

        if (responseWithId.getStatusCode().is2xxSuccessful()) {
            boolean penaltyIsReceived = false;

            //TODO: make normal checker, maybe circuit breaker
            while (!penaltyIsReceived) {
                final ResponseEntity<ResponseOfPenalty> responseWithPenalty = smevClient.gettingResultForNaturelPerson(responseWithId.getBody());

                penaltyIsReceived = isPenaltyReceived(penalty, penaltyIsReceived, responseWithPenalty);
            }

            return penalty;
        } else {
            throw new SmevClientException("Something wrong in SMEV, try later!");
        }
    }

    @Override
    public ResponseOfPenalty getInfoFromSmevForLegalPerson(RequestPenaltyFromLegalPerson request) throws InterruptedException {
        final ResponseOfPenalty penalty = new ResponseOfPenalty();

        final ResponseEntity<String> responseWithId = smevClient.gettingInformationForLegalPerson(request);

        if (responseWithId.getStatusCode().is2xxSuccessful()) {
            boolean penaltyIsReceived = false;

            //TODO: make normal checker, maybe circuit breaker
            while (!penaltyIsReceived) {
                final ResponseEntity<ResponseOfPenalty> responseWithPenalty = smevClient.gettingResultForLegalPerson(responseWithId.getBody());

                penaltyIsReceived = isPenaltyReceived(penalty, penaltyIsReceived, responseWithPenalty);
            }

            return penalty;
        } else {
            throw new SmevClientException("Something wrong in SMEV, try later!");
        }
    }

    private boolean isPenaltyReceived(ResponseOfPenalty penalty,
                                      boolean penaltyIsReceived,
                                      ResponseEntity<ResponseOfPenalty> responseWithPenalty) throws InterruptedException {

        if (responseWithPenalty.getStatusCode().is2xxSuccessful() && responseWithPenalty.getBody() != null) {
            penaltyIsReceived = true;

            penalty.setAccrualAmount(responseWithPenalty.getBody().getAccrualAmount());
            penalty.setAmountToBePaid(responseWithPenalty.getBody().getAmountToBePaid());
            penalty.setArticleOfTheAdministrativeCode(responseWithPenalty.getBody().getArticleOfTheAdministrativeCode());
            penalty.setDateOfTheResolution(responseWithPenalty.getBody().getDateOfTheResolution());
            penalty.setResolutionNumber(responseWithPenalty.getBody().getResolutionNumber());
            penalty.setVehicleCertificate(responseWithPenalty.getBody().getVehicleCertificate());
        } else {
            Thread.sleep(1000);
        }

        return penaltyIsReceived;
    }
}
