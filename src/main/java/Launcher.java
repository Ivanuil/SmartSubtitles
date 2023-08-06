import me.bush.translator.Language;

import java.io.FileNotFoundException;

public class Launcher {
    public static void main(String[] args) throws FileNotFoundException {
        PlayerForm player = new PlayerForm();
        player.setVisible(true);
        player.playMedia("C:\\Users\\ivo-p\\dev\\SmartSubtitles\\src\\main\\java\\video.mkv");
        player.playSubtitles("C:\\Users\\ivo-p\\dev\\SmartSubtitles\\src\\main\\java\\subtitles.srt");
        player.addTranslator(new SubtitlesTranslator(Language.AUTO, Language.RUSSIAN));
    }

}
