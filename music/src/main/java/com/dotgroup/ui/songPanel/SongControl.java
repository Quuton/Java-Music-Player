package com.dotgroup.ui.songPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.dotgroup.Listeners.SongControlListener;
import com.dotgroup.event.SongControlEvent;
import com.dotgroup.util.IO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SongControl extends JPanel{
    private JButton previousButton;
    private JButton playPauseButton;
    private JButton nextButton;
    private JSlider durationSlider;
    public ArrayList<SongControlListener> listeners = new ArrayList<SongControlListener>();
    private boolean isPlaying = false;

    public SongControl() {
        setLayout(new GridBagLayout());

        previousButton = new JButton(IO.loadImageIcon("previous", 50, 50));
        GridBagConstraints previousButtonConstraints = new GridBagConstraints(); 
        previousButtonConstraints.gridx = 0;
        previousButtonConstraints.gridy = 1;
        previousButtonConstraints.gridwidth = 1;
        previousButtonConstraints.anchor = GridBagConstraints.CENTER;
        previousButtonConstraints.insets = new Insets(10, 10, 10, 10);

        playPauseButton = new JButton(IO.loadImageIcon("pause", 50, 50));
        GridBagConstraints playPauseConstraints = new GridBagConstraints(); 
        playPauseConstraints.gridx = 1;
        playPauseConstraints.gridy = 1;
        playPauseConstraints.gridwidth = 1;
        playPauseConstraints.anchor = GridBagConstraints.CENTER;
        playPauseConstraints.insets = new Insets(10, 10, 10, 10);

        nextButton = new JButton(IO.loadImageIcon("next", 50, 50));
        GridBagConstraints nextButtonConstraints = new GridBagConstraints(); 
        nextButtonConstraints.gridx = 2;
        nextButtonConstraints.gridy = 1;
        nextButtonConstraints.gridwidth = 1;
        nextButtonConstraints.anchor = GridBagConstraints.CENTER;
        nextButtonConstraints.insets = new Insets(10, 10, 10, 10);

        durationSlider = new JSlider();
        durationSlider.setMinimum(0);
        durationSlider.setMaximum(100); 
        durationSlider.setValue(0);
        durationSlider.addChangeListener(e -> {
        });

        GridBagConstraints durationSliderConstraints = new GridBagConstraints(); 
        durationSliderConstraints.gridx = 0;
        durationSliderConstraints.gridy = 0;
        durationSliderConstraints.gridwidth = 3;
        durationSliderConstraints.anchor = GridBagConstraints.CENTER;
        durationSliderConstraints.insets = new Insets(10, 10, 10, 0);

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invokeListeners(new SongControlEvent(this, SongControlEvent.PREVIOUS, 0));
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invokeListeners(new SongControlEvent(this, SongControlEvent.NEXT, 0));
            }
        });

        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlaying) {
                    playPauseButton.setIcon(IO.loadImageIcon("play", 50, 50));
                    invokeListeners(new SongControlEvent(this, SongControlEvent.PAUSE, 0));
                } else {
                    playPauseButton.setIcon(IO.loadImageIcon("pause", 50, 50));
                    invokeListeners(new SongControlEvent(this, SongControlEvent.PLAY, 0));
                }
                isPlaying = !isPlaying;
            }
        });

        durationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                invokeListeners(new SongControlEvent(this, SongControlEvent.SKIM, value));
            }
        });



        add(previousButton, previousButtonConstraints);
        add(playPauseButton, playPauseConstraints);
        add(nextButton, nextButtonConstraints);
        add(durationSlider, durationSliderConstraints);


    }
    public void invokeListeners(SongControlEvent event) {
        for (SongControlListener listener : listeners) {
            listener.songControlOccured(event);
        }
    }

    public void bindListsener(SongControlListener listener) {
        listeners.add(listener);
    }

    public void reset() {
        this.isPlaying = true;
        this.durationSlider.setValue(0);
        playPauseButton.setIcon(IO.loadImageIcon("pause", 50, 50));
    }

    public static void main(String[] args) {
        JFrame temp = new JFrame();
        temp.add(new SongControl());
        temp.pack();
        temp.show();
        
    }
}
