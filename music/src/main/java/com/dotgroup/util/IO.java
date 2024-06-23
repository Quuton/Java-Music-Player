package com.dotgroup.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class IO {
    public static ImageIcon loadImageIcon(String filename) {
        return loadImageIcon(filename, -1, -1);
    }

    public static ImageIcon loadImageIcon(String filename, int width, int height) {
        try {
            // Get the URL of the resource file using the class loader
            ClassLoader classLoader = IO.class.getClassLoader();
            URL resourceUrl = classLoader.getResource("images/" + filename + ".png");

            if (resourceUrl != null) {
                BufferedImage image = ImageIO.read(resourceUrl);

                // If dimensions are provided and valid, resize the image
                if (width > 0 && height > 0) {
                    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImage);
                } else {
                    return new ImageIcon(image);
                }

            } else {
                System.err.println("Image file not found: " + filename);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error loading image: " + filename);
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon loadSongIcon(String filename, int width, int height) {
        try {
            // Get the URL of the resource file using the class loader
            ClassLoader classLoader = IO.class.getClassLoader();
            URL resourceUrl = classLoader.getResource("songs/" + filename + ".png");

            if (resourceUrl != null) {
                BufferedImage image = ImageIO.read(resourceUrl);

                // If dimensions are provided and valid, resize the image
                if (width > 0 && height > 0) {
                    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImage);
                } else {
                    return new ImageIcon(image);
                }

            } else {
                System.err.println("Image file not found: " + filename);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error loading image: " + filename);
            e.printStackTrace();
            return null;
        }
    }

    public static String loadLyrics(String filename) {
        String fileContent = "";
        try {
            // Get the URL of the resource file using the class loader
            ClassLoader classLoader = IO.class.getClassLoader();
            URL resourceUrl = classLoader.getResource("songs/" + filename + ".txt");

            if (resourceUrl != null) {
                InputStream inputStream = resourceUrl.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                fileContent = stringBuilder.toString();
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent;

    }
    public static InputStream loadMusicPath(String filename) {
        try {
            ClassLoader classLoader = IO.class.getClassLoader();
            URL resourceURL = classLoader.getResource("songs/" + filename + ".mp3");
            return resourceURL.openStream();
        } catch (Exception e) {
            System.out.println("Could not find music file :(");
        }
        return null;
    }
}
