package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String firstPath = Files.readString(Path.of(filepath1).toAbsolutePath().normalize());
        String secondPath = Files.readString(Path.of(filepath2).toAbsolutePath().normalize());

        TreeMap<String, Object> valueFilepath1 = Parser.parse(firstPath);
        TreeMap<String, Object> valueFilepath2 = Parser.parse(secondPath);

        if (valueFilepath1.isEmpty() || valueFilepath2.isEmpty()) {
            return "one of the files is empty";
        }
        return Formatter.choiceFormat(format, GenerateDifferences.genDiff(valueFilepath1, valueFilepath2));
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
}
