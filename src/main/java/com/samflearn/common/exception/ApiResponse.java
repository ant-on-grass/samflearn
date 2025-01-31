package com.samflearn.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponse {

    private HttpStatus status;

    private String message;

    public static ApiResponse error(HttpStatus status, String message) {
        return new ApiResponse(status, message);
    }

}
