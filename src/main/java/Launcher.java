import me.bush.translator.Language;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Launcher {
    public static void main(String[] args) throws FileNotFoundException {
        PlayerForm player = new PlayerForm();
        player.setVisible(true);

        JFileChooser videoFileChooser = new JFileChooser();
        int responseCodeVideo = videoFileChooser.showOpenDialog(player);
        JFileChooser subsFileChooser = new JFileChooser();
        int responseCodeSubs = subsFileChooser.showOpenDialog(player);

        if (responseCodeVideo == JFileChooser.APPROVE_OPTION)
            player.playMedia(videoFileChooser.getSelectedFile().getPath());
        else
            player.playMedia("C:\\Users\\ivo-p\\dev\\SmartSubtitles\\src\\main\\java\\video.mkv");

        if (responseCodeSubs == JFileChooser.APPROVE_OPTION)
            player.playSubtitles(subsFileChooser.getSelectedFile().getPath());
        else
            player.playSubtitles("C:\\Users\\ivo-p\\dev\\SmartSubtitles\\src\\main\\java\\subtitles.srt");

        player.addTranslator(new SubtitlesTranslator(Language.AUTO, Language.RUSSIAN));
    }

}
