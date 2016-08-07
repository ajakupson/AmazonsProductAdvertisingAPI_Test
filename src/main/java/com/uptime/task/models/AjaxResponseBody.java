package com.uptime.task.models;

/**
 * Created by de1mos on 3.08.16.
 */
public class AjaxResponseBody {

    private String message;
    private String code;
    private Object response;

    // getters and setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public Object getResponse() {
        return response;
    }
    public void setResponse(Object response) {
        this.response = response;
    }
}
