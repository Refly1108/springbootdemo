package com.refly.spring_boot_test.model;

public class SignatureResponse {

    private String signature;
    private Integer status;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
