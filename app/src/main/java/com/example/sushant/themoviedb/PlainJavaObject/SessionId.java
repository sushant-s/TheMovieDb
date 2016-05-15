package com.example.sushant.themoviedb.PlainJavaObject;

import com.google.gson.annotations.Expose;


/**
 * Created by sushant on 14/5/16.
 */
public class SessionId {

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    @Expose
    public String session_id;


}
