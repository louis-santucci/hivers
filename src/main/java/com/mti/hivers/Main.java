package com.mti.hivers;

public class Main {
    public static void main(String[] args) {
        final var hivers = new Hivers();

        hivers.push(new DefaultScope());
        hivers.register(new RestHivers());
        hivers.extension(RestHivers.class)
                .register(RestHivers.Method.GET, "/hello", context -> context.response(200, "Hello, world!"))
                .register(RestHivers.Method.DELETE, "/", context -> {
                    hivers.extension(RestHivers.class).shutdown();
                    context.response(204); })
                .register(RestHivers.Method.POST, "/post", context -> context.response(205, "Received Something"))
                .start();
    }
}
