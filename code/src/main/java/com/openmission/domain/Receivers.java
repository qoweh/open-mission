package com.openmission.domain;

import jakarta.mail.Address;
import jakarta.mail.internet.InternetAddress;
import java.util.List;

public class Receivers {
    private final List<Receiver> receivers;

    private Receivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }

    public static Receivers from(List<Receiver> receivers) {
        return new Receivers(receivers);
    }

    public List<String> getMails() {
        return this.receivers
                .stream()
                .map(Receiver::getMail)
                .toList();
    }

    public Address[] getAddresses() {
        return getMails()
                .stream()
                .map(s -> {
                    try { return new InternetAddress(s); }
                    catch (Exception e) { throw new RuntimeException(e); }
                })
                .toArray(Address[]::new);
    }
}
