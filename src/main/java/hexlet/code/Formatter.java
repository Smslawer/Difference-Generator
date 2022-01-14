package hexlet.code;

import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;


public class Formatter {
    public static String choiceFormat(String format, List<TreeMap<String, Object>> differences) throws IOException {
        return switch (format) {
            case "plain" -> PlainFormat.plain(differences);

            case "json" -> JsonFormat.json(differences);

            case "stylish" -> StylishFormat.stylish(differences);

            default -> throw new IOException(format + " is wrong format! Available formats: stylish, plain, json.");
        };
    }
}
