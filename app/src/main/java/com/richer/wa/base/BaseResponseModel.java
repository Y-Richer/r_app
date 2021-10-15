package com.richer.wa.base;

/**
 * create by richer on 2021/10/13
 * 所有response类都要继承BaseResponseModel
 */
public class BaseResponseModel {

    private int errorCode;

    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
