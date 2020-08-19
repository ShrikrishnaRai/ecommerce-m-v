package com.shree.ecommerce_m_v.utils.exceptionHandling;

import com.fasterxml.jackson.core.JsonParseException;
import com.shree.ecommerce_m_v.utils.exceptionHandling.model.CustomErrorResponse;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RESTGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation failed")
                .timeStamp(LocalDateTime.now())
                .build();
        customErrorResponse.addValidationErrors(ex.getConstraintViolations());

        return buildResponseEntity(customErrorResponse);
    }

    @ExceptionHandler(value = {JsonParseException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleJsonParseException(JsonParseException ex, HttpServletRequest request) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .message("Invalid json")
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Invalid Json Input")
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Error writing json input")
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Unable to find the record you are looking fot")
                .timeStamp(LocalDateTime.now())
                .build());
    }


    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return  buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .message("HttpMedia type not supported")
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()))
                .timeStamp(LocalDateTime.now())
                .build());
    }


    @ExceptionHandler(value = {MalformedJwtException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> malformedException(MalformedJwtException ex) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .message("Invalid token")
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> unknownException(Exception ex) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Something went wrong")
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> emptyResult(EmptyResultDataAccessException ex) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Unable to find the data")
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> clientError(HttpClientErrorException ex) {
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Bad request")
                .timeStamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(CustomErrorResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Database error")
                    .timeStamp(LocalDateTime.now())
                    .build());
        }
        return buildResponseEntity(CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build());
    }

    private ResponseEntity<Object> buildResponseEntity(CustomErrorResponse customErrorResponse) {
        return new ResponseEntity<>(customErrorResponse, customErrorResponse.getStatus());
    }
}
