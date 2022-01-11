package formatters;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class PlainFormat {
    public static String plain(List<TreeMap<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (TreeMap<String, Object> diffs : differences) {
            switch (diffs.get("diff").toString()) {
                case "removed" -> result.append("Property ").append("'")
                        .append(diffs.get("name")).append("'").append(" was removed").append("\n");
                case "added" -> result.append("Property ").append(isArrayOrObject(diffs.get("name")))
                        .append(" was added with value: ")
                        .append(isArrayOrObject(diffs.get("value")))
                        .append("\n");
                case "updated" ->
                    result.append("Property ").append(isArrayOrObject(diffs.get("name")))
                            .append(" was updated. From ")
                            .append(isArrayOrObject(diffs.get("value"))).append(" to ")
                            .append(isArrayOrObject(diffs.get("newValue")))
                            .append("\n");

                default -> result.append("");
            }

        }
        return result.toString().trim();
    }

    public static String isArrayOrObject(Object data) {
        if (data instanceof Object[] || data instanceof Collections || data instanceof Map
                || data instanceof ArrayList<?>) {
            return "[complex value]";
        } else if (data instanceof String) {
            return "'" + data + "'";
        } else if (data == null) {
            return null;
        }
        return data.toString();
    }
}
