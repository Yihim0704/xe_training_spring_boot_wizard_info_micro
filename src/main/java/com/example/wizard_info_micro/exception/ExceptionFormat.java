package com.example.wizard_info_micro.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ExceptionFormat {
    private String type;
    private int code;
    private LocalDateTime timestamp;
    private String traceId;
    private Map<String, Object> errors;

    public ExceptionFormat(String type, int code, LocalDateTime timestamp, String traceId, Map<String, Object> errors) {
        setType(type);
        setCode(code);
        setTimestamp(timestamp);
        setTraceId(traceId);
        setErrors(errors);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    public Map<String, Object> toFormat() {
        Map<String, Object> status = new HashMap<>();
        Map<String, Object> details = new HashMap<>();
        details.put("type", getType());
        details.put("code", getCode());
        details.put("timestamp", getTimestamp());
        details.put("traceId", getTraceId());
        details.put("errors", getErrors());
        status.put("status", details);
        return details;
    }
}
