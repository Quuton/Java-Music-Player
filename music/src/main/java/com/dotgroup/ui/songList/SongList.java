package com.dotgroup.ui.songList;
import javax.swing.*;

import java.awt.Component;

import com.dotgroup.classes.Song;
import com.dotgroup.event.SongChosenEvent;
import com.dotgroup.util.IO;

import java.util.List;
import java.awt.Dimension;
import java.util.ArrayList;
import com.dotgroup.Listeners.SongChosenListener;
public class SongList extends JPanel{
    public JPanel contentPane;
    public JScrollPane scrollPane;
    // The hell is this for
    public SongChosenListener dedicatedListener = null;

    public SongList(SongChosenListener listener) {
        this.dedicatedListener = listener;
        resetPanels();
    }
    public SongList() {
        resetPanels();
    }

    public SongList(List<Song> songList, SongChosenListener listener) {
        this.dedicatedListener = listener;
        resetPanels();

        for (Song song : songList) {
            addSong(song);
        }
    }

    public void resetPanels(){
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(420, 600));
        add(scrollPane);
    }

    public void changeSongList(List<Song> songList) {
        resetPanels();

        for (Song song : songList) {
            addSong(song);
        }
        
    }

    public void bindListener(SongChosenListener listener) {
        this.dedicatedListener = listener;
        for (Component c : contentPane.getComponents()) {
            if (c instanceof SongSummary) { 
            ((SongSummary)c).bindListener(this.dedicatedListener);
            }
        }
    }
    
    public void addSong(Song song) {
        SongSummary tempSongSummary = new SongSummary(song.getSongFileName(), song.getSongTitle(), song.getSongDuration(), song.getSongID());
        if (this.dedicatedListener != null) {
            tempSongSummary.bindListener(this.dedicatedListener);
        }
        contentPane.add(tempSongSummary);
    }

}
