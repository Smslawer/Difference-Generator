package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.TreeMap;

public class Parser {
    public static TreeMap<String, Object> parse(String filepath) throws IOException {
        if (filepath.isEmpty()) {
            return new TreeMap<>();
        }
        ObjectMapper mapper = filepath.endsWith(".json")
                ? new ObjectMapper() : new ObjectMapper(new YAMLFactory());
        return mapper.readValue(filepath, new TypeReference<>() { });
    }
}
