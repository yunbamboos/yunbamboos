package io.github.yunbamboos.rest.proxy;

public class InvokeServiceClassLoader extends ClassLoader {

    public InvokeServiceClassLoader() {
        super(InvokeServiceClassLoader.class.getClassLoader());
    }

    public synchronized Class<?> getClass(String name, byte[] code) {
        if (name == null) {
            throw new IllegalArgumentException("");
        }
        return defineClass(name, code, 0, code.length);
    }
}