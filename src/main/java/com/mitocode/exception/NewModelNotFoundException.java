package com.mitocode.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;

//https://www.sivalabs.in/spring-boot-3-error-reporting-using-problem-details/
public class NewModelNotFoundException extends ErrorResponseException {

    public NewModelNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, asProblemDetail(message), null);
    }

    private static ProblemDetail asProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
        problemDetail.setTitle("Model Not Found");
        problemDetail.setType(URI.create("/not-found"));
        problemDetail.setProperty("new value", "value test");
        problemDetail.setProperty("age", 32);
        return problemDetail;
    }
}
