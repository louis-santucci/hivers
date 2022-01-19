package com.mti.hivers;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.function.Consumer;

public class RestHivers implements Extension {

    private final int port = 8000;
    private final String host = "localhost";
    private HttpServer server = null;

    public enum Method {
        GET,
        POST,
        DELETE,
        PUT
    }

    public RestHivers() {
        // Config for web server, containing ports etc...
        try {
            this.server = HttpServer.create(new InetSocketAddress(host, port), 0);
            this.server.setExecutor(null); // Executor by default
        } catch (IOException e) {
            System.err.println("Cannot create Server");
        }
    }

    @Override
    public Extension register(Method method, String endpoint, Consumer<HttpObject> lambda) {
        if (method.equals(Method.GET)) {
            this.server.createContext(endpoint, new GetHandler(lambda));
        } else if (method.equals(Method.DELETE)) {
            this.server.createContext(endpoint, new DeleteHandler(lambda));
        }
        return this;
    }

    @Override
    public void shutdown() {
        this.server.stop(1);
    }

    @Override
    public void start() {
        this.server.start();
        System.out.println("Listening");
    }
}
