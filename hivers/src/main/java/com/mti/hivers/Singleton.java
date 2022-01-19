package com.mti.hivers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Singleton<PROVIDED_T, VALUE_T extends PROVIDED_T> extends AbstractProvider<PROVIDED_T> {

    private final Supplier<? extends PROVIDED_T> valueSupplier;
    private PROVIDED_T value;

    public Singleton(Class<PROVIDED_T> boundClass, Supplier<VALUE_T> valueSupplier) {
        super(boundClass);
        this.valueSupplier = valueSupplier;
    }

    public Singleton(Class<PROVIDED_T> boundClass, VALUE_T value) {
        this(boundClass, () -> value);
    }

    @Override public Class<PROVIDED_T> getProvidedClass() {
        return boundClass;
    }

    @Override public PROVIDED_T getValue() {
        if (value == null) {
            value = valueSupplier.get();
        }
        return getProxy(value);
    }
}