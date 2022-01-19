package com.mti.hivers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.function.Consumer;

public class DeleteHandler implements HttpHandler {

    private final Consumer<HttpObject> lambda;

    public DeleteHandler(Consumer<HttpObject> lambda) {
        this.lambda = lambda;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            var httpObj = new HttpObject(httpExchange);
            lambda.accept(httpObj);
            httpExchange.sendResponseHeaders(httpObj.statusCode, 0);
        } catch (IOException e) {
            System.err.println("Error Writing on response body");
        }
    }
}
