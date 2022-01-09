package formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class JsonFormat {
    public static String json(List<TreeMap<String, Object>> differences) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(differences);
    }
}
