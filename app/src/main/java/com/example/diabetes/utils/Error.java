package com.example.diabetes.utils;

public class Error {
    Boolean validate;
    String message;

    public Error(Boolean validate, String message) {
        this.validate = validate;
        this.message = message;
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
