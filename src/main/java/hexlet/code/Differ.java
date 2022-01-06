package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class Differ {

    public static String generate(File filepath1, File filepath2) throws IOException {
        StringBuilder result = new StringBuilder("{ \n");
        TreeMap<String, Object> valueFilepath1 = Parser.parse(filepath1);
        TreeMap<String, Object> valueFilepath2 = Parser.parse(filepath2);
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
