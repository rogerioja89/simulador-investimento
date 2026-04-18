package com.github.rogerioja89.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ApiErrorResponse {

    @JsonProperty("message")
    private String message;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

