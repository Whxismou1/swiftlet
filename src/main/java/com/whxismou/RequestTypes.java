package com.whxismou;

/**
 * Enumeration of supported HTTP request methods.
 * <p>
 * This enum is used by {@link Request} and {@link Swiftlet}
 * to specify the desired HTTP method when building a request.
 * </p>
 *
 * @author
 *         Whxismou
 * @since 0.1.0
 */
public enum RequestTypes {

    /**
     * HTTP POST method — used to send data to a server.
     */
    POST,

    /**
     * HTTP GET method — used to retrieve data from a server.
     */
    GET
}
