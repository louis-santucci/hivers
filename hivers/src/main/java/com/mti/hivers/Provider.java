package com.mti.hivers;

public interface Provider<PROVIDED_T> {

    Class<PROVIDED_T> getProvidedClass();

    <VALUE_T extends PROVIDED_T> VALUE_T getValue();

    Provider<PROVIDED_T> withProxies(ProxyDefinition... proxies);

}
