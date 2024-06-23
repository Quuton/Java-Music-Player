package com.dotgroup.ui.songPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dotgroup.classes.Song;
import com.dotgroup.util.IO;

public class SongPanel extends JPanel {
    public SongControl songControlPanel = new SongControl();
    public SongInfo songInfoPanel = new SongInfo();
    public SongPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints songControlPanelConstraints = new GridBagConstraints(0,1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10,10,10,10), 0, 0);
        GridBagConstraints songInfoPanelConstraints = new GridBagConstraints(0,0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10,10,10,10), 0, 0);


        this.add(songControlPanel, songControlPanelConstraints);
        
        this.add(songInfoPanel, songInfoPanelConstraints);
        
    }

    public void changeSong(Song song) {
        this.songControlPanel.reset();
        this.songInfoPanel.updateData(song.getSongFileName(), song.getSongTitle());
    }


    public static void main(String[] args) {
        JFrame temp = new JFrame();
        temp.add(new SongPanel());
        temp.setVisible(true);
    }
    
}
