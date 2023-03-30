package com.mitocode.exception;

import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex, WebRequest req){
        CustomErrorResponse res = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }*/

    /*@ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Model Not Found");
        pd.setType(URI.create(req.getContextPath()));
        //pd.setProperty("test", "value test");
        //pd.setProperty("age", 32);
        return pd;
    }*/

    @ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Model not found")
                .type(URI.create(req.getContextPath()))
                .property("test", "value test")
                .property("age", 32)
                .build();
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<CustomErrorResponse> handleSQLException(SQLException ex, WebRequest req){
        CustomErrorResponse res = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    /*@Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }*/

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("URL Not Found");
        pd.setType(URI.create(req.getContextPath()));
        //pd.setProperty("test", "value test");
        //pd.setProperty("age", 32);
        return new ResponseEntity<>(pd, HttpStatus.NOT_FOUND);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(" "));

        /*String message = "";
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            message += error.getField() + ": " + error.getDefaultMessage() + " ";
        }*/

        CustomErrorResponse res = new CustomErrorResponse(LocalDateTime.now(), message, req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

    }
}
