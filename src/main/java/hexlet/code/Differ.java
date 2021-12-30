package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {
    public String host;
    public String timeout;
    public String proxy;
    public String follow;
    public static void main(String[] args) throws Exception {
        System.out.println(generate());
    }

    public static String generate() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result = "{ \n";
        TreeMap valueFilepath1 = mapper.readValue(new File("filepath1.json"), TreeMap.class);
        TreeMap valueFilepath2 = mapper.readValue(new File("filepath2.json"), TreeMap.class);
        for (Object key: valueFilepath1.keySet()) {
            if (valueFilepath2.containsKey(key)) {
                if (valueFilepath1.get(key).equals(valueFilepath2.get(key))) {
                    result = result + "    " + key + ": " + valueFilepath1.get(key).toString() + "\n";
                } else {
                    result = result + "  - " + key + ": " + valueFilepath1.get(key).toString() + "\n";
                    result = result + "  + " + key + ": " + valueFilepath2.get(key).toString() + "\n";
                }
            } else {
                result = result + "  - " + key + ": " + valueFilepath1.get(key).toString() + "\n";
            }
        }
        for (Object key: valueFilepath2.keySet()) {
            if (!valueFilepath1.containsKey(key)) {
                result = result + "  + " + key + ": " + valueFilepath2.get(key).toString() + "\n";
            }
        }
        result = result + "}";
        return result;
    }
}
