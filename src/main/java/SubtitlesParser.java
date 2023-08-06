import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SubtitlesParser {
    private final Scanner scanner;
    private final ArrayList<SubtitlesLine> lines = new ArrayList<>();

    public SubtitlesParser(String filename) throws FileNotFoundException {
        File file = new File(filename);
        scanner = new Scanner(file);
    }

    public SubtitlesLine getLine(long time) {
        for (int i = lines.size() - 1; i >= 0; i--) {
            SubtitlesLine line = lines.get(i);
            if (line.endTime() >= time)
                return line.startTime() <= time ? line : null;
        }

        SubtitlesLine line = getNextLine();
        lines.add(line);
        if (line.startTime <= time)
            return line.endTime >= time ? line : null;
        return null;
    }

    public SubtitlesLine getNextLine() {
        if (!scanner.hasNext())
            return null;
        int number = scanner.nextInt();
        String startTimeString = scanner.next();
        scanner.next();
        String endTimeString = scanner.next();
        scanner.nextLine();
        StringBuilder line = new StringBuilder(scanner.nextLine());
        String nextLine;
        while (!Objects.equals(nextLine = scanner.nextLine(), ""))
            line.append("\n").append(nextLine);
        return new SubtitlesLine(number,
                getTimeAsLong(startTimeString),
                getTimeAsLong(endTimeString),
                line.toString());
    }

    private static long getTimeAsLong(String time) {
        Scanner scan = new Scanner(time);
        scan.useDelimiter("[:,\\n]");
        long hours = scan.nextInt();
        long minutes = hours * 24 + scan.nextInt();
        long seconds = minutes * 60 + scan.nextInt();
        return seconds * 1000 + scan.nextInt() * 10L; // Millis
    }

    public record SubtitlesLine(int number, long startTime, long endTime, String line) {
    }
}
