package formatters;

import java.util.List;
import java.util.TreeMap;

public class StylishFormat {
    public static String stylish(List<TreeMap<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");
        for (TreeMap<String, Object> diffs : differences) {
            switch (diffs.get("diff").toString()) {
                case "removed" -> result.append("  - ").append(diffs.get("name")).append(": ")
                        .append(diffs.get("value")).append("\n");
                case "added" -> result.append("  + ").append(diffs.get("name")).append(": ")
                        .append(diffs.get("value")).append("\n");
                case "unchanged" -> result.append("    ").append(diffs.get("name")).append(": ")
                        .append(diffs.get("value")).append("\n");
                default -> {
                    result.append("  - ").append(diffs.get("name")).append(": ")
                            .append(diffs.get("value")).append("\n");
                    result.append("  + ").append(diffs.get("name")).append(": ")
                            .append(diffs.get("newValue")).append("\n");
                }
            }

        }
        result.append("}");
        return result.toString();
    }

}
