package com.mti.hivers;

import com.sun.net.httpserver.HttpExchange;

public class HttpObject {

    public int statusCode;
    public String message;
    public HttpExchange ex;

    public HttpObject(HttpExchange ex) {
        this.ex = ex;
    }

    public void response(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public void response(int statusCode) {
        this.statusCode = statusCode;
    }
}
