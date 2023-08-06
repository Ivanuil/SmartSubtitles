import me.bush.translator.Language;
import me.bush.translator.Translation;
import me.bush.translator.Translator;

public class SubtitlesTranslator {
    Translator translator = new Translator();
    Language source;
    Language target;

    public SubtitlesTranslator(Language source, Language target) {
        this.source = source;
        this.target = target;
    }

    public SubtitlesParser.SubtitlesLine translate(SubtitlesParser.SubtitlesLine line) {
        Translation translation = translator.translateBlocking(line.line(), target, source);
        return new SubtitlesParser.SubtitlesLine(line.number(), line.startTime(),
                        line.endTime(), translation.getTranslatedText());
    }

    public record WordTranslation(String word, String translation) {}

    public WordTranslation translate(String word) {
        Translation translation = translator.translateBlocking(word, target, source);
        return new WordTranslation(word, translation.getTranslatedText());
    }

}
