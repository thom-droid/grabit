package com.cabbage.grabit.exception;

public class ApiResult<T>  {

    public Integer code;
    public String message;
    public T data;

    public static <T> ApiResult<T> of(ApiStatusInterface apiStatus, T data){
        return new ApiResult<>(apiStatus, data);
    }

    public ApiResult(ApiStatusInterface apiStatus, T data) {
        this.code = apiStatus.getCode();
        this.message = apiStatus.getMessage();
        this.data = data;
    }
}
