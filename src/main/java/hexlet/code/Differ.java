package hexlet.code;

import java.io.IOException;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        TreeMap<String, Object> valueFilepath1 = Parser.parse(filepath1);
        TreeMap<String, Object> valueFilepath2 = Parser.parse(filepath2);

        return Formatter.choiceFormat(format, GenerateDifferences.genDiff(valueFilepath1, valueFilepath2));
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
}
