package com.openmission.util;

import jakarta.mail.MessagingException;

@FunctionalInterface
public interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v) throws MessagingException;
}
