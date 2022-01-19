package com.mti.hivers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Scope {
    private final Map<Class<?>, Provider<?>> providerMap = new HashMap<>();

    public void register(final Provider<?> provider) {
        providerMap.put(provider.getProvidedClass(), provider);
    }

    public <REQ_T> Optional<? extends REQ_T> instanceOf(final Class<REQ_T> boundClass) {
        var provider = providerMap.get(boundClass);
        return provider != null
                ? Optional.of((REQ_T) provider.getValue())
                : Optional.empty();
    }
}
