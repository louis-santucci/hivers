package com.mti.hivers;

import java.lang.reflect.Method;

public class ProxyDefinition {

    Runnable onInit;
    String methodName;
    AroundAspect aspect;

    public Runnable onInit() {
        return onInit;
    }

    public void setOnInit(Runnable onInit) {
        this.onInit = onInit;
    }

    public String MethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public AroundAspect Aspect() {
        return aspect;
    }

    public void setAspect(AroundAspect aspect) {
        this.aspect = aspect;
    }

    public ProxyDefinition(Runnable onInit, String methodName, AroundAspect aspect) {
        this.onInit = onInit;
        this.methodName = methodName;
        this.aspect = aspect;
    }

    public static ProxyDefinition init(final Runnable onInit) {
        return new ProxyDefinition(onInit, null, null);
    }

    public static ProxyDefinition around(final String methodName, final AroundAspect aspect) {
        return new ProxyDefinition(() -> {}, methodName, aspect);
    }

    public static record Context(
            Object proxy,
            Method method,
            Object[] args) {}

    public interface AroundAspect {
        Object invokeProxy(final Context ctx) throws Throwable;
    }
}
