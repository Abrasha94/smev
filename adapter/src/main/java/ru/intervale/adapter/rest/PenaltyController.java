package ru.intervale.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.intervale.adapter.exception.SmevClientException;
import ru.intervale.adapter.model.request.RequestPenaltyFromLegalPerson;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;
import ru.intervale.adapter.service.PenaltyService;

@RestController
@RequestMapping("/api/v1/penalties/")
@RequiredArgsConstructor
public class PenaltyController {

    private final PenaltyService penaltyService;

    @PostMapping("natural")
    public ResponseEntity<ResponseOfPenalty> requestPenaltyFromNaturalPerson(@RequestBody RequestPenaltyFromNaturalPerson requestPenalty) {

        try {
            final ResponseOfPenalty penalty = penaltyService.getInfoFromSmevForNaturalPerson(requestPenalty);
            penaltyService.sendAcknowledgeForNaturalPerson(requestPenalty);
            return new ResponseEntity<>(penalty, HttpStatus.OK);
        } catch (InterruptedException | SmevClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something wrong with SMEV, try later!", e);
        }
    }

    @PostMapping("legal")
    public ResponseEntity<ResponseOfPenalty> requestPenaltyFromLegalPerson(@RequestBody RequestPenaltyFromLegalPerson requestPenalty) {

        try {
            final ResponseOfPenalty penalty = penaltyService.getInfoFromSmevForLegalPerson(requestPenalty);
            penaltyService.sendAcknowledgeForLegalPerson(requestPenalty);
            return new ResponseEntity<>(penalty, HttpStatus.OK);
        } catch (InterruptedException | SmevClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something wrong with SMEV, try later!", e);
        }
    }
}
