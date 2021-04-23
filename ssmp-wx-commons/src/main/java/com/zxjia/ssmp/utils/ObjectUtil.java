package com.zxjia.ssmp.utils;

import java.util.function.Consumer;

public class ObjectUtil {

    public ObjectUtil() {
    }

    public static <T> InitObject<T> initObject(T obj) {
        return new InitObject(obj);
    }

    public static class InitObject<T> {
        private T ref;

        public InitObject(T obj) {
            this.ref = obj;
        }

        public InitObject<T> init(Consumer<T> init) {
            if (init != null) {
                init.accept(this.ref);
            }
            return this;
        }

        public T getObject() {
            return this.ref;
        }
    }
}
