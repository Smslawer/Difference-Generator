package hexlet.code;

import formatters.JsonFormat;
import formatters.PlainFormat;
import formatters.StylishFormat;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;


public class Formatter {
    public static String choiceFormat(String format, List<TreeMap<String, Object>> differences) throws IOException {
        switch (format) {
            case "plain" -> {
                return PlainFormat.plain(differences);
            }
            case "json" -> {
                return JsonFormat.json(differences);
            }
            default -> {
                return StylishFormat.stylish(differences);
            }
        }
    }
}
