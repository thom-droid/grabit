package com.cabbage.grabit.exception;

import lombok.Getter;

@Getter
public enum ApiStatus implements ApiStatusInterface{

    SUCCESS(0, "success"),

    /** giver code : -1 ~ -100 */
    GIVER_NOT_FOUND(-1, "giver not found"),



    /** taker : -101 ~ -200 */
    TAKER_NOT_FOUND(-101, "taker not found"),



    /** product : -201 ~ -300 */
    PRODUCT_NOT_FOUND(-201, "product not found"),



    /** review and reply : -301 ~ -400 */
    ONE_REPLY_PER_REVIEW(-10, "A review has to have only one reply"),
    REVIEW_NOT_FOUND(-11, "no review found"),
    DUPLICATED_REVIEW(-12, "a product cannot have more than two reviews from one taker"),

    /** subscription : -401 ~ -500 */

    /** region : -501 ~ 600 */
    REGION_NOT_FOUND(-501, "no region found"),

    /** others : -5000 ~ */
    FAILED(-5000, "something went wrong"),
    REQUEST_IS_NOT_VALID(-5001, "given request it not valid");


    Integer code;
    String message;

    ApiStatus(Integer code, String message){
        this.code = code;
        this.message = message;
    }


}
