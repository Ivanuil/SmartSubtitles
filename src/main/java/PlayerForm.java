import me.bush.translator.Language;
import me.bush.translator.Translator;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

public class PlayerForm extends JFrame{
    private JButton backButton;
    private JButton playPauseButton;
    private JButton forwardButton;
    private JPanel playerPanel;
    private JPanel panel;
    private JSlider timeSlider;
    private JPanel subtitlesPanel;
    private final JTextPane originSubtitlesPane = new JTextPane();
    private final JTextPane translatedSubtitlesPane = new JTextPane();
    private SubtitlesParser originSubtitlesParser;
    SubtitlesParser.SubtitlesLine originSubtitlesLine;
    private SubtitlesTranslator translator = null;

    EmbeddedMediaPlayerComponent mediaPlayer = new EmbeddedMediaPlayerComponent();

    public PlayerForm() {
        playerPanel.setLayout(new BorderLayout());
        playerPanel.add(mediaPlayer, BorderLayout.CENTER);
        playPauseButton.addActionListener(e -> {
            if (mediaPlayer.mediaPlayer().status().isPlaying())
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
        timeSlider.setValue(0);
        subtitlesPanel.setLayout(new BoxLayout(subtitlesPanel, BoxLayout.Y_AXIS));
        subtitlesPanel.add(originSubtitlesPane);
        subtitlesPanel.add(translatedSubtitlesPane);

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

        translator = new SubtitlesTranslator(Language.AUTO, Language.RUSSIAN);

        mediaPlayer.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                if (originSubtitlesLine != null && originSubtitlesLine.startTime() <= newTime
                        && newTime <= originSubtitlesLine.endTime())
                    return;

                originSubtitlesLine = originSubtitlesParser.getLine(newTime);
                if (originSubtitlesLine != null) {
                    originSubtitlesPane.setVisible(true);
                    originSubtitlesPane.setText(originSubtitlesLine.line());
                    if (translator != null) {
                        translatedSubtitlesPane.setVisible(true);
                        translatedSubtitlesPane.setText(translator.translate(originSubtitlesLine).line());
                    }
                } else
                    originSubtitlesPane.setVisible(true);  // TODO revert to 'false'
            }
        });
    }

    public void playMedia(String filename) {
        mediaPlayer.mediaPlayer().media().play(filename);
    }

    public void playSubtitles(String filename) throws FileNotFoundException {
        originSubtitlesParser = new SubtitlesParser(filename);
    }

    public void addTranslator(SubtitlesTranslator translator) {
        this.translator = translator;
    }

}
