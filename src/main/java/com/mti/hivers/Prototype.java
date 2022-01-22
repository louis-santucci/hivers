package com.mti.hivers;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Prototype<PROVIDED_T, VALUE_T extends PROVIDED_T> extends AbstractProvider<PROVIDED_T> {
    private final Supplier<? extends PROVIDED_T> valueSupplier;

    public Prototype(Class<PROVIDED_T> boundClass, Supplier<VALUE_T> valueSupplier) {
        super(boundClass);
        this.valueSupplier = valueSupplier;
    }

    @Override public Class<PROVIDED_T> getProvidedClass() {
        return boundClass;
    }

    @SuppressWarnings("unchecked")
    @Override public PROVIDED_T getValue() {
        var value = valueSupplier.get();
        return getProxy(value);
    }
}
