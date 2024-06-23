package com.dotgroup.ui.songPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.dotgroup.classes.Song;
import com.dotgroup.ui.songPanel.SongControl;
import com.dotgroup.util.*;
public class SongInfo extends JPanel {
    private static Integer COVER_WIDTH = 300;
    private static Integer COVER_HEIGHT = 300;
    CardLayout cardLayout = new CardLayout();
    private JPanel imagePanel = new JPanel(new BorderLayout());
    private JLabel imageLabel = new JLabel();
    private JLabel titleLabel = new JLabel();
    private JTextArea textArea = new JTextArea(20, 30);
    private JScrollPane scrollPane;

    public SongInfo() {
        this.setLayout(cardLayout);

        // lyricsPanel.setLayout(new BoxLayout(lyricsPanel, BoxLayout.Y_AXIS));
        titleLabel.setText("No Song Played");
        imagePanel.add(titleLabel, BorderLayout.NORTH);
        imageLabel.setIcon(IO.loadImageIcon("error", COVER_WIDTH, COVER_HEIGHT));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.addMouseListener(new MouseClickListener());

        textArea = new JTextArea("No Lyrics detected for this song!");
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(COVER_WIDTH, COVER_HEIGHT));
        scrollPane.addMouseListener(new MouseClickListener());
        textArea.addMouseListener(new MouseClickListener());

        this.add(imagePanel, "Image");
        this.add(scrollPane, "Lyrics");
        this.addMouseListener(new MouseClickListener());


    }

    public void updateData(String filePath, String title) {   
        this.resetCard();
        textArea.setText(IO.loadLyrics(filePath));
        this.imageLabel.setIcon(IO.loadSongIcon(filePath, COVER_WIDTH, COVER_HEIGHT));
        this.titleLabel.setText(title);
    }

    public void toggleCard() {
        this.cardLayout.next(this);
    }

    public void resetCard() {
        this.cardLayout.show(this, "Image");
    }

    private class MouseClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            toggleCard();
        }
    }

}
