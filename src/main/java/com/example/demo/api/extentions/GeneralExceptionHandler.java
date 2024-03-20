package com.example.demo.api.extentions;

import com.example.demo.domain.models.exceptions.AuthException;
import com.example.demo.domain.models.exceptions.ClientFaultException;
import com.example.demo.domain.models.exceptions.ServerFaultException;
import com.example.demo.domain.models.responses.general.CustomError;
import com.example.demo.domain.models.responses.general.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GeneralExceptionHandler {
    @Autowired
    private RequestContextAccessor requestContextAccessor;

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex){
        return requestContextAccessor.getTraceId().map( traceId -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        null,
                        500,
                        "Error interno de servicio.",
                        traceId)
                ));
    }

    @ExceptionHandler(ClientFaultException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleClientFaultException(ClientFaultException ex){
        return requestContextAccessor.getTraceId().map( traceId -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        new CustomError(
                                ex.getCode(),
                                ex.getErrorMessage()
                        ),
                        400,
                        "Revise los datos de la peticiòn.",
                        traceId)
                ));
    }

    @ExceptionHandler(ServerFaultException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleServerFaultException(ServerFaultException ex){
        return requestContextAccessor.getTraceId().map( traceId -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        new CustomError(
                                ex.getCode(),
                                ex.getErrorMessage()
                        ),
                        500,
                        "Error interno.",
                        traceId)
                ));
    }

    @ExceptionHandler(AuthException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleAuthException(AuthException ex){
        return requestContextAccessor.getTraceId().map( traceId -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(
                        new CustomError(
                                ex.getCode(),
                                ex.getErrorMessage()
                        ),
                        401,
                        "Error de autenticaciòn",
                        traceId)
                ));
    }
}
