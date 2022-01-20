package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

public class Parser {
    public static TreeMap<String, Object> parse(String filepath) throws IOException {
        String firstPath = Files.readString(Path.of(filepath).toAbsolutePath().normalize());

        if (!filepath.endsWith(".json") && !filepath.endsWith(".yaml") && !filepath.endsWith(".yml")) {
            throw new IOException("Wrong file format! Available formats: .json, .yaml, .yml");
        }

        if (firstPath.replaceAll("\\{", "").replaceAll("}", "")
                .trim().isEmpty()) {
            throw new IOException("one of the files is empty");
        }

        return filepath.endsWith(".json")
                ? new ObjectMapper().readValue(firstPath, new TypeReference<>() { })
                : new ObjectMapper(new YAMLFactory()).readValue(firstPath, new TypeReference<>() { });
    }
}
