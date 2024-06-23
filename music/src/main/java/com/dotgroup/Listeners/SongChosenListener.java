package com.dotgroup.Listeners;
import java.util.EventListener;
import com.dotgroup.event.SongChosenEvent;

public interface SongChosenListener extends EventListener {
    public void songChosenOccured(SongChosenEvent e);
}
