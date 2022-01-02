package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class Differ {
    private String host;
    private String timeout;
    private String proxy;
    private String follow;

    public static String generate(File filepath1, File filepath2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder result = new StringBuilder("{ \n");
        TreeMap<String, Object> valueFilepath1 = mapper.readValue(filepath1, new TypeReference<>() { });
        TreeMap<String, Object> valueFilepath2 = mapper.readValue(filepath2, new TypeReference<>() { });
        if (valueFilepath1.isEmpty() || valueFilepath2.isEmpty()) {
            return "one of the files is empty";
        }
        for (String key : valueFilepath1.keySet()) {
            if (valueFilepath2.containsKey(key)) {
                if (valueFilepath1.get(key).equals(valueFilepath2.get(key))) {
                    result.append("    ").append(key).append(": ")
                            .append(valueFilepath1.get(key).toString()).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ")
                            .append(valueFilepath1.get(key).toString()).append("\n");
                    result.append("  + ").append(key).append(": ")
                            .append(valueFilepath2.get(key).toString()).append("\n");
                }
            } else {
                result.append("  - ").append(key).append(": ")
                        .append(valueFilepath1.get(key).toString()).append("\n");
            }
        }
        for (String key : valueFilepath2.keySet()) {
            if (!valueFilepath1.containsKey(key)) {
                result.append("  + ").append(key).append(": ")
                        .append(valueFilepath2.get(key).toString()).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
