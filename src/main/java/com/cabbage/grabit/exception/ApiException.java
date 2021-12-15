package com.cabbage.grabit.exception;

import lombok.Getter;

public class ApiException extends RuntimeException {

    @Getter
    private ApiStatusInterface apiStatus;

    public ApiException (ApiStatusInterface apiStatus) {
        super(apiStatus.getMessage());
        this.apiStatus = apiStatus;
    }

}
