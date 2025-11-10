package com.whxismou;

import java.net.URI;

/**
 * Provides convenient static methods for creating HTTP requests.
 * <p>
 * This class simplifies the creation of {@link Request} objects for
 * common HTTP methods such as {@code GET} and {@code POST}.
 * </p>
 *
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * String result = Swiftlet.get("https://api.example.com/users")
 *         .header("Accept", "application/json")
 *         .asString();
 * }</pre>
 * </p>
 *
 * @author
 *         Whxismou
 * @since 1.0
 */
public class Swiftlet {

    /**
     * Creates a new HTTP POST request.
     *
     * @param url the target URL
     * @return a new {@link Request} instance configured for POST
     */
    public static Request post(String url) {
        return new Request(URI.create(url), RequestTypes.POST);
    }

    /**
     * Creates a new HTTP GET request.
     *
     * @param url the target URL
     * @return a new {@link Request} instance configured for GET
     */
    public static Request get(String url) {
        return new Request(URI.create(url), RequestTypes.GET);
    }
}
