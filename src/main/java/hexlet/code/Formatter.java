package hexlet.code;

import formatters.PlainFormat;
import formatters.StylishFormat;

import java.util.TreeMap;

import static hexlet.code.Differ.genDiff;

public class Formatter {
    public static String choiceFormat(String format, TreeMap<String, Object> valueFilepath1,
                                      TreeMap<String, Object> valueFilepath2) {
        switch (format) {
            case "plain" -> {
                return PlainFormat.plain(genDiff(valueFilepath1, valueFilepath2));
            }
            case "456" -> {
                return "456";
            }
            default -> {
                return StylishFormat.stylish(genDiff(valueFilepath1, valueFilepath2));
            }
        }
    }
}
