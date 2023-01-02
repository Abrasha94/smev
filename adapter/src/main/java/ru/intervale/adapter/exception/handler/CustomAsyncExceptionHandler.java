package ru.intervale.adapter.exception.handler;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Method;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "SMEV service can't delete the message!", ex);
    }
}
