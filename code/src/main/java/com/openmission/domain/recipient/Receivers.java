package com.openmission.domain.recipient;

import jakarta.mail.Address;
import jakarta.mail.internet.InternetAddress;
import java.util.List;

public class Receivers {
    private final List<Receiver> _receivers;
    private final List<Receiver> _ccReceivers;

    private Receivers(List<Receiver> receivers, List<Receiver> ccReceivers) {
        _receivers = receivers;
        _ccReceivers = ccReceivers;
    }

    public static Receivers of(List<Receiver> receivers, List<Receiver> ccReceivers) {
        return new Receivers(receivers, ccReceivers);
    }

    public List<String> getReceiversMails() {
        return _receivers
                .stream()
                .map(Receiver::getMail)
                .toList();
    }

    public List<String> getCcReceiversMails() {
        return _ccReceivers
                .stream()
                .map(Receiver::getMail)
                .toList();
    }

    public Address[] getReceiversAddress() {
        return getAddresses(getReceiversMails());
    }

    public Address[] getCcReceiversAddress() {
        return getAddresses(getCcReceiversMails());
    }

    private Address[] getAddresses(List<String> mails) {
        return mails
                .stream()
                .map(s -> {
                    try { return new InternetAddress(s); }
                    catch (Exception e) { throw new RuntimeException(e); }
                })
                .toArray(Address[]::new);
    }
}
