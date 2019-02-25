package com.abhijith.test.entity;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseObject {

    private HttpStatus status;

    private String statusDescription;

    private List<ResponseElement> data;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(final String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public List<ResponseElement> getData() {
        return data;
    }

    public void setData(final List<ResponseElement> data) {
        this.data = data;
    }
}
