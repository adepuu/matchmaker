package com.adepuu.matchmaker.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Getter
@Setter
@ToString
public class Response<T> {

    private int status;
    private String message;
    boolean success = false;
    private T data;

    public Response(int statCode, String statusDesc, T data) {
        this.status = statCode;
        this.message = statusDesc;
        this.data = data;
        if (status >= HttpStatus.OK.value() && status < HttpStatus.BAD_REQUEST.value()) {
            success = true;
        }
    }

    public static <T> ResponseEntity<Response<Object>> failedResponse(String message) {
        return failedResponse(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> ResponseEntity<Response<T>> failedResponse(T data) {
        return failedResponse(HttpStatus.BAD_REQUEST.value(), "Bad request", data);
    }

    public static <T> ResponseEntity<Response<T>> failedResponse(int status, String message) {
        return failedResponse(status, message, null);
    }

    public static <T> ResponseEntity<Response<T>> failedResponse(int status, String message, T data) {
        return ResponseEntity.status(status).body(new Response<>(status, message, data));
    }

    public static <T> ResponseEntity<Response<T>> successfulResponse(String message, T data) {
        return successfulResponse(HttpStatus.OK.value(), message, data);
    }

    public static <T> ResponseEntity<Response<T>> successfulResponse(String message) {
        return successfulResponse(HttpStatus.OK.value(), message, null);
    }

    public static <T> ResponseEntity<Response<T>> successfulResponse(int status, String message, T data) {
        return ResponseEntity.status(status).body(new Response<>(status, message, data));
    }
}
