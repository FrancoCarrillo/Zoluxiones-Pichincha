package com.zoluxiones.pichincha.shared.controllers;

import com.zoluxiones.pichincha.shared.exceptions.NotFoundException;
import com.zoluxiones.pichincha.shared.models.BaseErrorResponse;
import com.zoluxiones.pichincha.shared.models.ErrorListResponse;
import com.zoluxiones.pichincha.shared.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundController {
    @ExceptionHandler(NotFoundException.class)
    public BaseErrorResponse handleIdNotFound(NotFoundException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .errorCode(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleIdNotFound(MethodArgumentNotValidException exception) {

        var listErrors = new ArrayList<String>();
        exception.getAllErrors()
                .forEach(error -> listErrors.add(error.getDefaultMessage()));

        return ErrorListResponse.builder()
                .listErrors(listErrors)
                .status(HttpStatus.NOT_FOUND.name())
                .errorCode(HttpStatus.NOT_FOUND.value())
                .build();
    }
}
