package com.openmission.domain;

import jakarta.mail.Address;
import jakarta.mail.internet.InternetAddress;
import java.util.List;

public class Receivers {
    private final List<Receiver> receivers;
    private final List<Receiver> ccReceivers;

    private Receivers(List<Receiver> receivers, List<Receiver> ccReceivers) {
        this.receivers = receivers;
        this.ccReceivers = ccReceivers;
    }

    public static Receivers of(List<Receiver> receivers, List<Receiver> ccReceivers) {
        return new Receivers(receivers, ccReceivers);
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
