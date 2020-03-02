package com.example.myapplicationdemo;

import com.squareup.otto.Bus;

public class BusProvider {
    private BusProvider(){
    }
    private static final Bus BUS = new Bus();

    public static Bus getBUS() {
        return BUS;
    }
}
