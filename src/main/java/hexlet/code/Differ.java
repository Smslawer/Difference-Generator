package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String data1 = Files.readString(Path.of(checkFileFormat(filepath1)).toAbsolutePath().normalize());
        TreeMap<String, Object> parsedData1 = Parser.parse(checkEmptyFile(data1));

        String data2 = Files.readString(Path.of(checkFileFormat(filepath2)).toAbsolutePath().normalize());
        TreeMap<String, Object> parsedData2 = Parser.parse(checkEmptyFile(data2));

        return Formatter.choiceFormat(format, GenerateDifferences.genDiff(parsedData1, parsedData2));
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String checkFileFormat(String filepath) throws IOException {
        if (!filepath.endsWith(".json") && !filepath.endsWith(".yaml") && !filepath.endsWith(".yml")) {
            throw new IOException("Wrong file format! Available formats: .json, .yaml, .yml");
        }
        return filepath;
    }

    public static String checkEmptyFile(String data) throws IOException {
        if (data.replaceAll("\\{", "").replaceAll("}", "")
                .trim().isEmpty()) {
            throw new IOException("one of the files is empty");
        }
        return data;
    }
}
