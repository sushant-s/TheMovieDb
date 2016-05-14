package com.example.sushant.themoviedb.PlainJavaObject;

import com.google.gson.annotations.Expose;


/**
 * Created by sushant on 14/5/16.
 */
public class Token {

    @Expose
    public String request_token;

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}
