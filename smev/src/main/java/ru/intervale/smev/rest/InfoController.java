package ru.intervale.smev.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.intervale.smev.entity.LegalQueueRequest;
import ru.intervale.smev.entity.NaturalQueueRequest;
import ru.intervale.smev.model.request.RequestInfoFromLegalPerson;
import ru.intervale.smev.model.request.RequestInfoFromNaturalPerson;
import ru.intervale.smev.service.LegalQueueRequestService;
import ru.intervale.smev.service.NaturalQueueRequestService;
import ru.intervale.smev.worker.WorkerForLegalPerson;
import ru.intervale.smev.worker.WorkerForNaturalPerson;

@RestController
@RequestMapping("/api/v1/info/")
@RequiredArgsConstructor
public class InfoController {

    private final NaturalQueueRequestService naturalQueueRequestService;
    private final LegalQueueRequestService legalQueueRequestService;
    private final WorkerForNaturalPerson workerForNaturalPerson;
    private final WorkerForLegalPerson workerForLegalPerson;

    @PostMapping("natural")
    public ResponseEntity<String> requestInfoForNaturalPerson(@RequestBody RequestInfoFromNaturalPerson request) {

        try {
            final NaturalQueueRequest naturalQueueRequest = naturalQueueRequestService.createInfoRequestForNatural(request);

            workerForNaturalPerson.emulateGisgmp(naturalQueueRequest.getId());

            return new ResponseEntity<>(String.valueOf(naturalQueueRequest.getId()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "SMEV can't create info request!", e);
        }
    }

    @PostMapping("legal")
    public ResponseEntity<String> requestInfoForLegalPerson(@RequestBody RequestInfoFromLegalPerson request) {

        try {
            final LegalQueueRequest legalQueueRequest = legalQueueRequestService.createInfoRequestForNatural(request);

            workerForLegalPerson.emulateGisgmp(legalQueueRequest.getId());

            return new ResponseEntity<>(String.valueOf(legalQueueRequest.getId()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "SMEV can't create info request!", e);
        }
    }
}
