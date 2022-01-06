package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Parser {
    public static TreeMap<String, Object> parse(File filepath) throws IOException {
        if (new BufferedReader(new FileReader(filepath)).readLine() == null) {
            return new TreeMap<>();
        }
        ObjectMapper mapper = filepath.toString().endsWith(".json")
                ? new ObjectMapper() : new ObjectMapper(new YAMLFactory());
        return mapper.readValue(filepath, new TypeReference<>() { });
    }
}
