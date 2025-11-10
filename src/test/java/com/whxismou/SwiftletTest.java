package com.whxismou;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SwiftletTest {

    @Test
    void testGetRequest() throws Exception {
        String response = Swiftlet.get("https://httpbin.org/get")
                .header("Accept", "application/json")
                .asString();

        assertNotNull(response);
        assertTrue(response.contains("\"url\": \"https://httpbin.org/get\""));
    }

    @Test
    void testPostRequest() throws Exception {
        String response = Swiftlet.post("https://httpbin.org/post")
                .header("Content-Type", "text/plain")
                .body("hola swiftlet")
                .asString();

        assertNotNull(response);
        assertTrue(response.contains("hola swiftlet"));
    }
}
