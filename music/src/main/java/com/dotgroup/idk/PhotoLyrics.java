package com.dotgroup.idk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PhotoLyrics extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;

    public PhotoLyrics() {
        setTitle("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Image Panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("path/to/your/image.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setBackground(Color.BLACK);
        imagePanel.addMouseListener(new MouseClickListener());

        // Lyrics Panel
        JPanel lyricsPanel = new JPanel(new BorderLayout());
        JTextArea lyricsTextArea = new JTextArea("This is the lyrics area.\nYou can integrate your lyrics here.");
        lyricsTextArea.setEditable(false);
        lyricsPanel.add(lyricsTextArea, BorderLayout.CENTER);

        cardsPanel.add(imagePanel, "IMAGE");
        cardsPanel.add(lyricsPanel, "LYRICS");

        add(cardsPanel);
    }

    private class MouseClickListener extends MouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            cardLayout.next(cardsPanel); // Switch to the next card (lyricsPanel)
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PhotoLyrics PhotoLyrics = new PhotoLyrics();
            PhotoLyrics.setVisible(true);
        });
    }
}
