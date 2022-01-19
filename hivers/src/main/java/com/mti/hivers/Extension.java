package com.mti.hivers;

import java.util.function.Consumer;

public interface Extension {

    public Extension register(RestHivers.Method method, String endpoint, Consumer<HttpObject> Handler);

    public void shutdown();// Stops webserver, Stop listening

    public void start();// Runs webserver, begins listening
}
