package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.servlet.FetchBasket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public final class ServletJsonMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(ServletJsonMapper.class);


    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void objectToJsonResponse(Object object, HttpServletResponse response) {
        response.setContentType("application/json");
        try {
            String json = objectMapper.writeValueAsString(object);

            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> T objectFromJsonRequest(HttpServletRequest request, Class<T> aClass) {
        try {
            String string = request.getReader().lines().collect(Collectors.joining());
            System.err.println("Request: " + string);
            return objectMapper.readValue(string, aClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
