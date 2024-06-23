package com.dotgroup.ui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import com.dotgroup.classes.Song;
import com.dotgroup.ui.songList.SongList;
import com.dotgroup.ui.songPanel.SongPanel;
import com.dotgroup.util.IO;

import java.awt.Insets;
public class SongInterface extends JFrame {
    public SongList songList = new SongList();
    public SongPanel songPanel = new SongPanel();

    public SongInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints songPanelConstraints = new GridBagConstraints();
        songPanelConstraints.gridx = 0;
        songPanelConstraints.gridy = 0;
        songPanelConstraints.gridwidth = 3;
        songPanelConstraints.gridheight = 0;
        songPanelConstraints.insets = new Insets(0, 10, 0, 10);

        GridBagConstraints songListConstraints = new GridBagConstraints();
        songListConstraints.gridx = 3;
        songListConstraints.gridy = 0;
        songListConstraints.gridwidth = 2;
        songListConstraints.gridheight = 0;
        songListConstraints.insets = new Insets(0, 10, 0, 10);

        this.add(songPanel, songPanelConstraints);
        this.add(songList, songListConstraints);


        this.pack();
        this.setVisible(true);

    } 

    public void addSong(Song song) {
        this.songList.addSong(song);
    }

    public void changeSong(Song song) {
        this.songPanel.changeSong(song);
        this.pack();
    }

}
