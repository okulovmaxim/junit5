package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonConverter {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String jsonPath, Class <T> out) {
        try {
            return objectMapper.readValue(new File(jsonPath), out);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
