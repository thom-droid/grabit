package com.cabbage.grabit.exception;

import lombok.Getter;

@Getter
public enum ApiStatus implements ApiStatusInterface{
    SUCCESS(0, "success"),
    GIVER_NOT_FOUND(-1, "giver not found"),
    TAKER_NOT_FOUND(-2, "taker not found"),
    REQUEST_IS_NOT_VALID(-3, "given request it not valid"),
    FAILED(-99, "something went wrong");

    Integer code;
    String message;

    ApiStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }


}
