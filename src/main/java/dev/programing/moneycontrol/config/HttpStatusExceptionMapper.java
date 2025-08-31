package dev.programing.moneycontrol.config;

import dev.programing.moneycontrol.exception.AtivoNotFoundException;
import dev.programing.moneycontrol.exception.CategoriaNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@Component
public class HttpStatusExceptionMapper {

    private Map<Class<? extends Exception>, HttpStatus> exceptionHttpStatusMap;

    @PostConstruct
    public void postConstruct() {
        this.exceptionHttpStatusMap = new HashMap<>();
        this.exceptionHttpStatusMap.put(ConversionNotSupportedException.class, INTERNAL_SERVER_ERROR);
        this.exceptionHttpStatusMap.put(TypeMismatchException.class, BAD_REQUEST);
        this.exceptionHttpStatusMap.put(WebExchangeBindException.class, BAD_REQUEST);
        this.exceptionHttpStatusMap.put(HttpMessageNotReadableException.class, BAD_REQUEST);
        this.exceptionHttpStatusMap.put(HttpMessageNotWritableException.class, INTERNAL_SERVER_ERROR);
        this.exceptionHttpStatusMap.put(MethodArgumentNotValidException.class, BAD_REQUEST);
        this.exceptionHttpStatusMap.put(BindException.class, BAD_REQUEST);
        this.exceptionHttpStatusMap.put(AsyncRequestTimeoutException.class, SERVICE_UNAVAILABLE);
        this.exceptionHttpStatusMap.put(MethodArgumentTypeMismatchException.class, BAD_REQUEST);
        this.exceptionHttpStatusMap.put(NoResourceFoundException.class, NOT_FOUND);
        this.exceptionHttpStatusMap.put(CategoriaNotFoundException.class, NOT_FOUND);
        this.exceptionHttpStatusMap.put(AtivoNotFoundException.class, NOT_FOUND);
    }

    public HttpStatus getHttStatus(Throwable ex) {
        return this.exceptionHttpStatusMap.entrySet()
                .stream()
                .filter(e -> e.getKey().isAssignableFrom(ex.getClass()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }
}
