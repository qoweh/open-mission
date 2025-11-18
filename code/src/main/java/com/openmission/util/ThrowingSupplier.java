package com.openmission.util;

import jakarta.mail.MessagingException;

@FunctionalInterface
public interface ThrowingSupplier<T> {
    T get() throws MessagingException;
}
