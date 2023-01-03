package ru.intervale.smev.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.intervale.smev.exception.LegalPersonResponseNotFoundException;
import ru.intervale.smev.exception.NaturalPersonResponseNotFoundException;
import ru.intervale.smev.model.response.ResponseOfPenalty;
import ru.intervale.smev.service.LegalQueueResponseService;
import ru.intervale.smev.service.NaturalQueueResponseService;

@RestController
@RequestMapping("/api/v1/result/")
@RequiredArgsConstructor
public class ResultController {

    private final NaturalQueueResponseService naturalQueueResponseService;
    private final LegalQueueResponseService legalQueueResponseService;

    @GetMapping("natural/{id}")
    public ResponseEntity<ResponseOfPenalty> getResultForNatural(@PathVariable("id") String id) {

        try {
            final ResponseOfPenalty response = naturalQueueResponseService.getResponse(id);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NaturalPersonResponseNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No penalty found!", e);
        }
    }

    @GetMapping("legal/{id}")
    public ResponseEntity<ResponseOfPenalty> getResultForLegal(@PathVariable("id") String id) {

        try {
            final ResponseOfPenalty response = legalQueueResponseService.getResponse(id);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (LegalPersonResponseNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No penalty found!", e);
        }
    }

    @GetMapping("natural/acknowledge/{vehicleCertificate}")
    public ResponseEntity<HttpStatus> getAcknowledgeForNatural(@PathVariable("vehicleCertificate") String vehicleCertificate) {

        try {
            naturalQueueResponseService.deleteResponse(vehicleCertificate);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NaturalPersonResponseNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No penalty found!", e);
        }

    }

    @GetMapping("legal/acknowledge/{taxpayerIdentificationNumber}")
    public ResponseEntity<HttpStatus> getAcknowledgeForLegal(@PathVariable("taxpayerIdentificationNumber") String taxpayerIdentificationNumber) {

        try {
            legalQueueResponseService.deleteResponse(taxpayerIdentificationNumber);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (LegalPersonResponseNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No penalty found!", e);
        }
    }
}
