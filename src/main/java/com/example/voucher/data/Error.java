package com.example.voucher.data;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class Error {
    String status;
    String message;
    Error.Data data;

    public Error(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error.Data getData() {
        return this.data;
    }

    public static class Data {
        boolean is_error;
        String code;

        public Data() {
        }

        public String getCode() {
            return this.code;
        }
    }
}
