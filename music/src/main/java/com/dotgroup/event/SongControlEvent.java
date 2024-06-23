package com.dotgroup.event;

import java.util.EventObject;

public class SongControlEvent extends EventObject{
    public static final Integer PLAY = 0;
    public static final Integer PAUSE = 1;
    public static final Integer NEXT = 2;
    public static final Integer PREVIOUS = 3;
    public static final Integer SKIM = 4;
    public static final Integer RESUME = 5;

    public Integer eventType;
    public Integer variable = 0;

    public Integer getEventType() {
        return this.eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getVariable() {
        return this.variable;
    }

    public void setVariable(Integer variable) {
        this.variable = variable;
    }

    public SongControlEvent(Object source, Integer eventType, Integer variable) {
        super(source);
        this.eventType = eventType;
        this.variable = variable;
    }
}
