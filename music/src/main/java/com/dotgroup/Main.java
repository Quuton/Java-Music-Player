// This is the Controller
package com.dotgroup;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import com.dotgroup.classes.Song;
import com.dotgroup.ui.SongInterface;
import com.formdev.flatlaf.*;
import com.dotgroup.Listeners.SongChosenListener;
import com.dotgroup.Listeners.SongControlListener;
import com.dotgroup.event.SongChosenEvent;
import com.dotgroup.event.SongControlEvent;
import com.dotgroup.util.MusicPlayer;
import com.dotgroup.SongDB;

public class Main {
    public SongInterface songInterface = new SongInterface();
    public ArrayList<Song> songs = new ArrayList<Song>();
    public MusicPlayer player;
    public Integer lastPlayed = 0;
    public Main() {
        loadSongs();
        for (Song song : songs) {
            songInterface.addSong(song);
        }
        
        songInterface.setVisible(true);

        songInterface.songList.bindListener(new SongChosenListener() {
            public void songChosenOccured(SongChosenEvent e) {
                playSong(e.getSongID());
            }
        });

        songInterface.songPanel.songControlPanel.bindListsener(new SongControlListener() {
            public void songControlOccured(SongControlEvent e) {
                switch (e.getEventType()) {
                    case 0: {
                        resumeSong();
                        break;
                    }
                    case 1:{
                        pauseSong();
                        break;
                    }
                    case 2: {
                        playSong(nextSong());
                        break;
                    }
                    case 3: {
                        playSong(previousSong());
                        break;
                    }
                    case 4:{
                        jumpTo(e.getVariable());
                        break;
                    }
                }
            }
        });
    }

    public void loadSongs() {
        SongDB db = new SongDB();
        List<Song> temp = db.getSongList();
        for (Song song : temp) {
            songs.add(song);
        }
    }

    public void playSong(Integer songID) {
        stopSong();
        for (Song song : songs) {
            if (song.getSongID() == songID) {
                this.songInterface.changeSong(song);
                this.player = new MusicPlayer(song.getSongFileName());
                player.start();
                lastPlayed = songID;
            }
        }
    }
    public void stopSong(){
        if (player != null) {
            player.stop();
        }
        player = null;
    }

    public void resumeSong() {
        if (player != null) {
            player.start();
        }
    }

    public void pauseSong() {
        if (player != null) {
            player.pause();
        }
    }

    public int nextSong() {
        if (lastPlayed + 1 > songs.size()) {
            return 1;
        }
        return lastPlayed + 1;
    }

    public int previousSong() {
        if (lastPlayed - 1 <= 0) {
            return songs.size();
        }
        return lastPlayed - 1;
    }

    public void jumpTo(int percentage) {
        if (this.player != null) {
            this.player.jumpTo(percentage);
        }
    }

	public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            System.out.println("OH NO!");
        }
        SwingUtilities.invokeLater(() -> {
             Main c = new Main();
        });
    }
}

