package com.example.sushant.themoviedb;

/**
 * Created by sushant on 15/5/16.
 */
public class EventBusContext {
    public int ActionCode;

    public EventBusContext(int actionCode)
    {
        this.ActionCode=actionCode;
    }

    public int getActionCode() {
        return this.ActionCode;
    }


}
