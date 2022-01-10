package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Objects;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String firstPath = Files.readString(Path.of(filepath1).toAbsolutePath().normalize());
        String secondPath = Files.readString(Path.of(filepath2).toAbsolutePath().normalize());

        TreeMap<String, Object> valueFilepath1 = Parser.parse(firstPath);
        TreeMap<String, Object> valueFilepath2 = Parser.parse(secondPath);

        if (valueFilepath1.isEmpty() || valueFilepath2.isEmpty()) {
            return "one of the files is empty";
        }
        return Formatter.choiceFormat(format, genDiff(valueFilepath1, valueFilepath2));
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static List<TreeMap<String, Object>> genDiff(TreeMap<String, Object> valueFilepath1,
                                                        TreeMap<String, Object> valueFilepath2) {
        Set<String> keys = new TreeSet<>(valueFilepath1.keySet());
        keys.addAll(valueFilepath2.keySet());
        List<TreeMap<String, Object>> differences = new ArrayList<>();
        for (String key : keys) {
            TreeMap<String, Object> combinedMap = new TreeMap<>();
            if (!valueFilepath2.containsKey(key)) {
                combinedMap.put("diff", "removed");
                combinedMap.put("name", key);
                combinedMap.put("value", valueFilepath1.get(key));
            } else if (!valueFilepath1.containsKey(key)) {
                combinedMap.put("diff", "added");
                combinedMap.put("name", key);
                combinedMap.put("value", valueFilepath2.get(key));
            } else if (!Objects.equals(valueFilepath1.get(key), valueFilepath2.get(key))) {
                combinedMap.put("diff", "updated");
                combinedMap.put("name", key);
                combinedMap.put("value", valueFilepath1.get(key));
                combinedMap.put("newValue", valueFilepath2.get(key));
            } else {
                combinedMap.put("diff", "unchanged");
                combinedMap.put("name", key);
                combinedMap.put("value", valueFilepath1.get(key));
            }
            differences.add(combinedMap);
        }
        return differences;
    }
}
