package ru.intervale.smev.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.intervale.smev.entity.NaturalQueueResponse;
import ru.intervale.smev.exception.NaturalPersonResponseNotFoundException;
import ru.intervale.smev.model.response.ResponseOfPenalty;
import ru.intervale.smev.repository.NaturalQueueResponseRepository;
import ru.intervale.smev.service.NaturalQueueResponseService;

@Service
@RequiredArgsConstructor
public class NaturalQueueResponseServiceImpl implements NaturalQueueResponseService {

    private final NaturalQueueResponseRepository naturalQueueResponseRepository;

    @Override
    public ResponseOfPenalty getResponse(String id) {

        final NaturalQueueResponse naturalQueueResponse = naturalQueueResponseRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NaturalPersonResponseNotFoundException("Response not found!"));

        return createResponseOfPenalty(naturalQueueResponse);
    }

    @Override
    public void deleteResponse(String vehicleCertificate) {

        final NaturalQueueResponse naturalQueueResponse =
                naturalQueueResponseRepository.findByVehicleCertificate(vehicleCertificate)
                        .orElseThrow(() -> new NaturalPersonResponseNotFoundException("Response not found!"));

        naturalQueueResponseRepository.delete(naturalQueueResponse);
    }

    private ResponseOfPenalty createResponseOfPenalty(NaturalQueueResponse naturalQueueResponse) {
        final ResponseOfPenalty responseOfPenalty = new ResponseOfPenalty();
        responseOfPenalty.setAccrualAmount(naturalQueueResponse.getAccrualAmount());
        responseOfPenalty.setAmountToBePaid(naturalQueueResponse.getAmountToBePaid());
        responseOfPenalty.setVehicleCertificate(naturalQueueResponse.getVehicleCertificate());
        responseOfPenalty.setResolutionNumber(naturalQueueResponse.getResolutionNumber());
        responseOfPenalty.setDateOfTheResolution(naturalQueueResponse.getDateOfTheResolution());
        responseOfPenalty.setArticleOfTheAdministrativeCode(naturalQueueResponse.getArticleOfTheAdministrativeCode());

        return responseOfPenalty;
    }
}
