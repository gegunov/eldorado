package com.getjavajob.egunov.eldorado.kettle.exceptions;

/**
 * Created by Администратор on 14.01.2015.
 */
public class NotEnoughWaterException extends Exception {

    public NotEnoughWaterException(int quantity, int remains) {
        super("Вы пытаетесь вылить " + quantity + " мл воды" + " , однако, остаток всего " + remains + "мл");
    }

}
