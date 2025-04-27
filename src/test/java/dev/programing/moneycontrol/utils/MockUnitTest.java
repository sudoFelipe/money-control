package dev.programing.moneycontrol.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;


public class MockUnitTest {

    public final String TEST_JSON_PATH = "json/test.json";

    @SneakyThrows
    public static <T> T convertJsonFileToObject(String jsonFilePath, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(jsonFilePath);

        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, targetClass);
        }
    }
}
