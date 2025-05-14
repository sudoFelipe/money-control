package dev.programing.moneycontrol;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;


public class MockUnitTest {

    public static final String CATEGORIA_JSON_PATH = "json/categoria.json";
    public static final String CATEGORIA_RESPONSE_JSON_PATH = "json/categoria_response.json";
    public static final String CATEGORIA_REQUEST_JSON_PATH = "json/categoria_request.json";

    @SneakyThrows
    public static <T> T obterDadosJsonPath(String jsonFilePath, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(jsonFilePath);

        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, targetClass);
        }
    }

    @SneakyThrows
    public static <T> List<T> obterListaDadosJsonPath(String jsonFilePath, Class<T> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(jsonFilePath);

        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, targetClass));
        }
    }
}
