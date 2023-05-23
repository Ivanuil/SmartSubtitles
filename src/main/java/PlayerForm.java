import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PlayerForm extends JFrame{
    private JButton backButton;
    private JButton playPauseButton;
    private JButton forwardButton;
    private JPanel playerPanel;
    private JPanel panel;

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
