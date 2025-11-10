package com.whxismou;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a configurable and reusable HTTP request.
 * <p>
 * This class provides a simple abstraction over the
 * {@link java.net.http.HttpClient}
 * API, allowing you to build and send HTTP requests with custom headers, body
 * content,
 * and methods.
 * </p>
 *
 * <p>
 * Example usage:
 * 
 * <pre>{@code
 * String response = Swiftlet.post("https://api.example.com/data")
 *         .json("{\"key\": \"value\"}")
 *         .header("Authorization", "Bearer TOKEN")
 *         .asString();
 * }</pre>
 * </p>
 *
 * @author Whxismou
 * @since 0.1.0
 */
public class Request {
    private String method;
    private URI uri;
    private String body;
    private final Map<String, List<String>> headers = new LinkedHashMap<>();

    /**
     * Constructs a new {@code Request} with the specified URL and HTTP method.
     *
     * @param url    the target URI of the request
     * @param method the HTTP method to use (e.g., {@link RequestTypes#GET} or
     *               {@link RequestTypes#POST})
     */
    public Request(URI url, RequestTypes method) {
        this.uri = url;
        this.method = method.name();
    }

    /**
     * Adds an HTTP header to this request.
     *
     * @param key   the header name
     * @param value the header value
     * @return this {@code Request} instance for fluent chaining
     */
    public Request header(String key, String value) {
        headers.computeIfAbsent(key, d -> new ArrayList<>()).add(value);
        return this;
    }

    /**
     * Sends this HTTP request and returns the response body as a string.
     *
     * @return the response body as a {@link String}
     * @throws IOException          if an I/O error occurs while sending the request
     * @throws InterruptedException if the operation is interrupted while waiting
     *                              for a response
     */
    public String asString() throws IOException, InterruptedException {
        HttpRequest req = this.buildHttpRequest();
        var res = HttpClient.newHttpClient().send(req, BodyHandlers.ofString());

        return res.body();
    }

    /**
     * Sets the request body as JSON content.
     * <p>
     * Automatically adds the header {@code Content-Type: application/json}.
     * </p>
     *
     * @param json the JSON string to send in the body
     * @return this {@code Request} instance for fluent chaining
     */
    public Request json(String json) {
        header("Content-Type", "application/json");
        this.body = json;
        return this;
    }

    /**
     * Sets a plain text body for this request.
     *
     * @param text the text content to send in the body
     * @return this {@code Request} instance for fluent chaining
     */
    public Request body(String text) {
        this.body = text;
        return this;
    }

    /**
     * Builds the underlying {@link HttpRequest} object.
     * <p>
     * This method is used internally before sending the request through
     * the {@link HttpClient}.
     * </p>
     *
     * @return a configured {@link HttpRequest} instance
     */
    private HttpRequest buildHttpRequest() {
        HttpRequest.BodyPublisher publisher = (body == null) ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(body);

        HttpRequest.Builder b = HttpRequest.newBuilder(uri)
                .method(method, publisher);

        headers.forEach((k, vs) -> vs.forEach(v -> b.header(k, v)));

        return b.build();
    }

}
