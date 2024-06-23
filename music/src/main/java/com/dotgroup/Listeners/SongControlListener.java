package com.dotgroup.Listeners;

import java.util.EventListener;
import com.dotgroup.event.SongControlEvent;

public interface SongControlListener extends EventListener{
    public void songControlOccured(SongControlEvent e);
}
