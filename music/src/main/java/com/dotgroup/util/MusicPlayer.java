package com.dotgroup.util;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.SwingUtilities;
import com.dotgroup.util.IO;
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import java.io.IOException;

public class MusicPlayer {
    private Player player;
    private InputStream file;
    private int length;
    private String originalPath;
    private Thread thread;
    private long pausedPosition; // Store the position when paused

    public MusicPlayer(String filePath) {
        originalPath = filePath;
        try {
            file = IO.loadMusicPath(filePath);
            length = file.available();
            this.player = new Player(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error initializing music player.");
        }
    }

    public void stop() {
        System.out.println("DEBUG: Attempted to stop()");
        if (player != null) {
            this.thread.interrupt(); // Interrupt the thread gracefully
            player.close();
            try {
                // Reset the file input stream to the beginning
                file.close();
                file = IO.loadMusicPath(originalPath);
            } catch (Exception e) {
                System.out.println("Error closing the music file.");
            }
        }
    }

    public void start() {
        System.out.println("DEBUG: Attempted to start()");

        thread = new Thread(() -> {
            try {
                // If there's a position where the music was paused, skip to that position
                if (pausedPosition > 0) {
                    file = IO.loadMusicPath(originalPath);
                    file.skip(file.available() - pausedPosition);
                    pausedPosition = 0;
                }
                player = new Player(file);
                player.play();
            } catch (Exception e) {
                // Catch specific exceptions and display meaningful messages
                if (e instanceof javazoom.jl.decoder.JavaLayerException) {
                    e.printStackTrace();
                    System.out.println("Error playing the music: JavaLayerException.");
                } else if (e instanceof java.io.IOException) {
                    System.out.println("Error playing the music: IOException.");
                } else {
                    System.out.println("Error playing the music.");
                }
            }
        });
        thread.start();
    }
    

    public void pause() {
        System.out.println("DEBUG: Attempted to pause()");

        if (player != null) {
            this.thread.interrupt();
            try {
                pausedPosition = file.available(); // Store the current position
                player.close();
            } catch (Exception e) {
                System.out.println("Error pausing the music.");
            }
        }
    }

    public void jumpTo(int percent) {
        System.out.println("DEBUG: Attempted to jumpTo()");
        if (player != null) {
            // Calculate the position to jump to based on the percentage
            long position = (long) (length * percent / 100.0);

            // Close the current player and create a new one at the desired position
            player.close();
            this.thread.stop();
            pausedPosition = length - position;
            start();

        }
    }
}
