package com.smaz.ms.model;

import lombok.Data;

@Data
public class ResponseModel {

    private String status;

    private String message;

    public ResponseModel(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
