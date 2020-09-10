package com.cwj.ssm.template.constant;

public enum Result {
    
    SUCCESS("00000", "Success"),
    // system 
    SYSTEM_INTERNAL_ERROR("00001", "System internal error"),
    DATABASE_ERROR("00002", "Database error"),
    // parameters
    PARAMETER_REQUIRE("00003", "Parameter require"),
    PARAMETER_FORMAT_ERROR("00004", "Parameter format error"),
    // login
    LOGIN_NOT_MATCH("00005", "Username or password not match"),
    USER_LOCKED("00006", "User is locked"),
    NOT_LOGIN("00007", "Cannot found login information");

    private String errorCode;
    private String errorMessage;
    
    Result(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String code() {
        return errorCode;
    }
    
    public String msg() {
        return errorMessage;
    }
}
