package com.openmission.domain;

import java.util.List;

public class Receivers {
    private final List<Receiver> receivers;

    private Receivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }

    public static Receivers from(List<Receiver> receivers) {
        return new Receivers(receivers);
    }

}
