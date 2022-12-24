package com.example.wizard_info_micro.exception;

import brave.Span;
import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionsHandler {

    @Autowired
    private Tracer tracer;

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionsHandler.class);

    public String generateTraceId() {
        String traceId = null;
        Span span = tracer.currentSpan();
        if (span != null) {
            traceId = span.context().traceIdString();
        }
        return traceId;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> message = new HashMap<>();
        Map<String, String> errorMap = new HashMap<>();
        String methodArgumentNotValidExceptionTraceId = generateTraceId();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        message.put("code", HttpStatus.BAD_REQUEST.toString());
        message.put("message", errorMap);
        ExceptionFormat exceptionFormat = new ExceptionFormat("NOK", 1, LocalDateTime.now(), methodArgumentNotValidExceptionTraceId, message);
        logger.info("MethodArgumentNotValidExceptionTraceId: {}", methodArgumentNotValidExceptionTraceId);
        logger.info(String.valueOf(exceptionFormat.toFormat()));
        return exceptionFormat.toFormat();
    }

    @ExceptionHandler(WizardInfoExistException.class)
    public Map<String, Object> handleWizardInfoExistException(WizardInfoExistException ex) {
        Map<String, Object> message = new HashMap<>();
        String wizardInfoExistException = generateTraceId();
        message.put("code", HttpStatus.CONFLICT.toString());
        message.put("message", ex.getLocalizedMessage());
        ExceptionFormat exceptionFormat = new ExceptionFormat("NOK", 1, LocalDateTime.now(), wizardInfoExistException, message);
        logger.info("WizardInfoExistExceptionTraceId: {}", wizardInfoExistException);
        logger.info(String.valueOf(exceptionFormat.toFormat()));
        return exceptionFormat.toFormat();
    }

    @ExceptionHandler(NoWizardInfoFoundException.class)
    public Map<String, Object> handleNoWizardInfoFoundException(NoWizardInfoFoundException ex) {
        Map<String, Object> message = new HashMap<>();
        String noWizardInfoFoundExceptionTraceId = generateTraceId();
        message.put("code", HttpStatus.NO_CONTENT.toString());
        message.put("message", ex.getLocalizedMessage());
        ExceptionFormat exceptionFormat = new ExceptionFormat("NOK", 1, LocalDateTime.now(), noWizardInfoFoundExceptionTraceId, message);
        logger.info("NoWizardInfoFoundExceptionTraceId: {}", noWizardInfoFoundExceptionTraceId);
        logger.info(String.valueOf(exceptionFormat.toFormat()));
        return exceptionFormat.toFormat();
    }

    @ExceptionHandler(WizardIdNotFoundException.class)
    public Map<String, Object> handleWizardIdNotFoundException(WizardIdNotFoundException ex) {
        Map<String, Object> message = new HashMap<>();
        String wizardIdNotFoundExceptionTraceId = generateTraceId();
        message.put("code", HttpStatus.NOT_FOUND.toString());
        message.put("message", ex.getLocalizedMessage());
        ExceptionFormat exceptionFormat = new ExceptionFormat("NOK", 1, LocalDateTime.now(), wizardIdNotFoundExceptionTraceId, message);
        logger.info("WizardIdNotFoundExceptionTraceId: {}", wizardIdNotFoundExceptionTraceId);
        logger.info(String.valueOf(exceptionFormat.toFormat()));
        return exceptionFormat.toFormat();
    }
}
