package com.mti.hivers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AspectInvocationHandler implements InvocationHandler {
    private final ProxyDefinition proxyDefinition;
    private final Object target;

    public AspectInvocationHandler(Object target,
                                   ProxyDefinition definitions) {
        this.target = target;
        this.proxyDefinition = definitions;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals(proxyDefinition.methodName)) {
            var context = new ProxyDefinition.Context(target, method, args);
            return proxyDefinition.aspect.invokeProxy(context);
        } else {
            return method.invoke(proxy, args);
        }
    }
}
