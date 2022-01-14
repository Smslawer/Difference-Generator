package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

public class GenerateDifferences {
    public static List<TreeMap<String, Object>> genDiff(TreeMap<String, Object> valueFilepath1,
                                                        TreeMap<String, Object> valueFilepath2) {
        List<TreeMap<String, Object>> differences = new ArrayList<>();
        for (String key : getAllKeys(valueFilepath1, valueFilepath2)) {
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

    private static Set<String> getAllKeys(TreeMap<String, Object> valueFilepath1,
                                          TreeMap<String, Object> valueFilepath2) {
        Set<String> keys = new TreeSet<>(valueFilepath1.keySet());
        keys.addAll(valueFilepath2.keySet());
        return keys;
    }
}
