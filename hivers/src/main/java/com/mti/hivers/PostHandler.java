package com.mti.hivers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.function.Consumer;

public class PostHandler implements HttpHandler {

    private final Consumer<HttpObject> lambda;

    public PostHandler(Consumer<HttpObject> lambda) {
        this.lambda = lambda;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            var httpObj = new HttpObject(httpExchange);
            lambda.accept(httpObj);
            var data = httpExchange.getRequestBody();
            httpExchange.sendResponseHeaders(httpObj.statusCode, httpObj.message.length());
            var os = httpExchange.getResponseBody();
            os.write(httpObj.message.getBytes());
            os.close();
        } catch (Exception e) {
            System.err.println("Error writing on response body");
        }
    }
}
