package com.getjavajob.egunov.eldorado.kettle.exceptions;

public class TooMuchWaterException extends Exception {

    public TooMuchWaterException(int quantity, int volume) {
        super("Вы пытаетесь налить " + quantity + " мл воды. Однако, объём чайника " + volume + " мл");
    }

}
