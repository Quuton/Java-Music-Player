package com.dotgroup.event;

import java.util.EventObject;

public class SongChosenEvent extends EventObject{
    int songID;
    public SongChosenEvent(Object source, Integer songID) {
        super(source);
        this.songID = songID;
    }

    public Integer getSongID() {
        return this.songID;
    }
}
