package ru.intervale.adapter.client;

import org.springframework.http.HttpStatus;
import ru.intervale.adapter.model.request.RequestPenalty;

public interface SmevClient {

    HttpStatus gettingInformation(RequestPenalty request);

}
