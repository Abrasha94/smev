package ru.intervale.smev.exception.handler;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.intervale.smev.exception.PenaltyNotFoundException;

import java.lang.reflect.Method;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {

        if (ex instanceof PenaltyNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No penalty for you!", ex);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "GISGMP service have some problem!", ex);
        }
    }
}
