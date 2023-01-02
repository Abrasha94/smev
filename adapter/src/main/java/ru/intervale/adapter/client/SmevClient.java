package ru.intervale.adapter.client;

import org.springframework.http.ResponseEntity;
import ru.intervale.adapter.model.request.RequestPenaltyFromLegalPerson;
import ru.intervale.adapter.model.request.RequestPenaltyFromNaturalPerson;
import ru.intervale.adapter.model.response.ResponseOfPenalty;

public interface SmevClient {

    ResponseEntity<String> gettingInformationForNaturalPerson(RequestPenaltyFromNaturalPerson request);

    ResponseEntity<ResponseOfPenalty> gettingResultForNaturelPerson(String id);

    ResponseEntity<String> gettingInformationForLegalPerson(RequestPenaltyFromLegalPerson request);

    ResponseEntity<ResponseOfPenalty> gettingResultForLegalPerson(String id);

}
