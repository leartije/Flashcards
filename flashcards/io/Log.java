package flashcards.io;

import flashcards.services.Services;

import java.util.ArrayList;
import java.util.List;

public class Log {

    public final static List<String> log = new ArrayList<>();

    public static String nextLine() {
        final String nextLine = Services.SCANNER.nextLine();
        log.add(nextLine);
        return nextLine;
    }

    public static void println(String line) {
        System.out.println(line);
        log.add(line);
    }

    public static void printf(String format, Object... args) {
        final String line = String.format(format, args);
        println(line);
    }

}
