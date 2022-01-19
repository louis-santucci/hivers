package com.mti.hivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hivers {

    private final List<Scope> scopes = new ArrayList<>();
    private final List<Extension> extensions = new ArrayList<>();

    public Hivers() {
        scopes.add(new Scope());
    }

    public void pop() {
        if (scopes.size() > 1) {
            scopes.remove(scopes.size() - 1);
        } else {
            throw new RuntimeException("Cannot delete last scope");
        }
    }

    public void push(Scope scope) {
        this.scopes.add(scope);
    }

    public <PROVIDED_T> Provider<PROVIDED_T> provider(final Provider<PROVIDED_T> provider) {
        var topStack = scopes.get(scopes.size() - 1);
        topStack.register(provider);
        return provider;
    }

    public <REQ_T> Optional<? extends REQ_T> instanceOf(Class<REQ_T> boundClass) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            var scope = scopes.get(i);

            var optional = scope.instanceOf(boundClass);
            if (!optional.isEmpty()) {
                return optional;
            }
        }
        return Optional.empty();
    }

    public <REQ_T> REQ_T instanceOfOrThrow(Class<REQ_T> boundClass) {
        for (int i = this.scopes.size() - 1; i >= 0; i--) {
            var scope = scopes.get(i);

            var optional = scope.instanceOf(boundClass);
            if (!optional.isEmpty()) {
                return optional.get();
            }
        }
        throw new RuntimeException("No instance of boundClass");
    }

    public void register(Extension extension) {
        // SI aucune extension, création serveur HTTP

        // Ensuite, ajout de l'extension
        this.extensions.add(extension);
    }

    public <EXTENSION_T extends Extension> EXTENSION_T extension(Class<EXTENSION_T> extensionClass) {
        for (var extension : this.extensions) {
            if (extension.getClass().equals(extensionClass)) {
                return (EXTENSION_T) extension;
            }
        }
        return null;
    }
}
