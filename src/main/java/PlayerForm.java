import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PlayerForm extends JFrame{
    private JButton backButton;
    private JButton playPauseButton;
    private JButton forwardButton;
    private JPanel playerPanel;
    private JPanel panel;
    private JSlider timeSlider;

    boolean running = true;

    EmbeddedMediaPlayerComponent mediaPlayer = new EmbeddedMediaPlayerComponent();

    public PlayerForm() {
        playerPanel.setLayout(new BorderLayout());
        playerPanel.add(mediaPlayer, BorderLayout.CENTER);
        playPauseButton.addActionListener(e -> {
            if (running)
                mediaPlayer.mediaPlayer().controls().pause();
            else
                mediaPlayer.mediaPlayer().controls().play();
        });
        backButton.addActionListener(e -> mediaPlayer.mediaPlayer().controls().skipTime(-1000));
        forwardButton.addActionListener(e -> mediaPlayer.mediaPlayer().controls().skipTime(1000));
        timeSlider.addChangeListener(e -> {
            double sliderPosition = (double) (timeSlider.getValue() - timeSlider.getMinimum()) /
                    (timeSlider.getMaximum() - timeSlider.getMinimum());
            long videoLength = mediaPlayer.mediaPlayer().status().length();
            long time = (long) (videoLength * sliderPosition);
            mediaPlayer.mediaPlayer().controls().setTime(time);
        });

        this.setContentPane(panel);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayer.release();
                System.exit(0);
            }
        });
        pack();
        setVisible(true);
    }

    public void playMedia(String filename) {
        mediaPlayer.mediaPlayer().media().play(filename);
        pack();
    }

}
