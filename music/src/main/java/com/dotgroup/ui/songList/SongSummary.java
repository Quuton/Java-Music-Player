package com.dotgroup.ui.songList;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import com.dotgroup.Listeners.SongChosenListener;
import com.dotgroup.util.Formatter;
import com.dotgroup.util.IO;
import com.dotgroup.event.*;

public class SongSummary extends JPanel implements MouseListener{
    private int songID;
    private ArrayList<SongChosenListener> listeners = new ArrayList<SongChosenListener>();
    private JLabel thumbnail;
    private JLabel songTitleLabel;
    private JLabel songDurationLabel;

    
    public SongSummary(String imagePath, String songTitle, int duration, int songID) {
        addMouseListener(this);
        this.songID = songID;
        setLayout(new GridBagLayout());
        thumbnail = new JLabel(IO.loadSongIcon(imagePath, 100 , 100));

        songTitleLabel = new JLabel(songTitle);
        songDurationLabel = new JLabel(Formatter.durationFormatter(duration));
        
        GridBagConstraints thumbnailConstraints = new GridBagConstraints();
        thumbnailConstraints.gridx = 0;
        thumbnailConstraints.gridy = 0;
        thumbnailConstraints.gridwidth = 1;
        thumbnailConstraints.anchor = GridBagConstraints.LINE_START;
        thumbnailConstraints.insets = new Insets(10, 0, 10, 10);

        GridBagConstraints songTitleLabelConstraints = new GridBagConstraints();
        songTitleLabelConstraints.gridx = 1;
        songTitleLabelConstraints.gridy = 0;
        songTitleLabelConstraints.gridwidth = 1;
        songTitleLabelConstraints.anchor = GridBagConstraints.CENTER;
        songTitleLabelConstraints.insets = new Insets(10, 10, 10, 10);

        GridBagConstraints songDurationLabelConstraints = new GridBagConstraints();
        songDurationLabelConstraints.gridx = 2;
        songDurationLabelConstraints.gridy = 0;
        songDurationLabelConstraints.gridwidth = 1;
        songDurationLabelConstraints.anchor = GridBagConstraints.LINE_END;
        songDurationLabelConstraints.insets = new Insets(10, 10, 10, 0);
        
        add(thumbnail, thumbnailConstraints);
        add(songTitleLabel, songTitleLabelConstraints);
        add(songDurationLabel, songDurationLabelConstraints);
    }

    public void bindListener(SongChosenListener listener) {
        listeners.add(listener);
    }


    public void invokeListeners(Integer songID) {
        for (SongChosenListener listener : listeners) {
            listener.songChosenOccured(new SongChosenEvent(this, songID));
        }
    }

        public void mouseClicked(MouseEvent e) {
            invokeListeners(this.songID);
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}

}
