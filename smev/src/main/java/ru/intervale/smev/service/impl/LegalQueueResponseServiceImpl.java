package ru.intervale.smev.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.intervale.smev.entity.LegalQueueResponse;
import ru.intervale.smev.exception.LegalPersonResponseNotFoundException;
import ru.intervale.smev.model.response.ResponseOfPenalty;
import ru.intervale.smev.repository.LegalQueueResponseRepository;
import ru.intervale.smev.service.LegalQueueResponseService;

@Service
@RequiredArgsConstructor
public class LegalQueueResponseServiceImpl implements LegalQueueResponseService {

    private final LegalQueueResponseRepository legalQueueResponseRepository;

    @Override
    public ResponseOfPenalty getResponse(String id) {

        final LegalQueueResponse legalQueueResponse = legalQueueResponseRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new LegalPersonResponseNotFoundException("Response not found!"));

        return createResponseOfPenalty(legalQueueResponse);
    }

    @Override
    public void deleteResponse(String taxpayerIdentificationNumber) {
        final LegalQueueResponse legalQueueResponse =
                legalQueueResponseRepository.findByTaxpayerIdentificationNumber(taxpayerIdentificationNumber)
                        .orElseThrow(() -> new LegalPersonResponseNotFoundException("Response not found!"));

        legalQueueResponseRepository.delete(legalQueueResponse);
    }

    private ResponseOfPenalty createResponseOfPenalty(LegalQueueResponse legalQueueResponse) {
        final ResponseOfPenalty responseOfPenalty = new ResponseOfPenalty();
        responseOfPenalty.setAccrualAmount(legalQueueResponse.getAccrualAmount());
        responseOfPenalty.setAmountToBePaid(legalQueueResponse.getAmountToBePaid());
        responseOfPenalty.setVehicleCertificate(legalQueueResponse.getVehicleCertificate());
        responseOfPenalty.setResolutionNumber(legalQueueResponse.getResolutionNumber());
        responseOfPenalty.setDateOfTheResolution(legalQueueResponse.getDateOfTheResolution());
        responseOfPenalty.setArticleOfTheAdministrativeCode(legalQueueResponse.getArticleOfTheAdministrativeCode());

        return responseOfPenalty;
    }
}
