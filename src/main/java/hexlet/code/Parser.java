package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.TreeMap;

public class Parser {
    public static TreeMap<String, Object> parse(String data) throws IOException {
        return data.endsWith(".json")
                ? new ObjectMapper().readValue(data, new TypeReference<>() { })
                : new ObjectMapper(new YAMLFactory()).readValue(data, new TypeReference<>() { });
    }
}
