package ru.intervale.adapter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.intervale.adapter.model.request.RequestPenalty;
import ru.intervale.adapter.model.response.ResponseOfPenalty;

@RestController
@RequestMapping("/api/v1/penalties/")
public class PenaltyController {

    @PostMapping("natural")
    public ResponseEntity<ResponseOfPenalty> requestPenaltyFromNaturalPerson(@RequestBody RequestPenalty requestPenalty) {

        return null;
    }
}
