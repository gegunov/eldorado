package com.getjavajob.egunov.eldorado.kettle;

import com.getjavajob.egunov.eldorado.kettle.exceptions.NoWaterException;
import com.getjavajob.egunov.eldorado.kettle.exceptions.NotEnoughWaterException;
import com.getjavajob.egunov.eldorado.kettle.exceptions.TooMuchWaterException;

public class Kettle {

    public static final int MAX_TEMPERATURE = 100; //Максимальная температура

    private int volume; //вместимость чайника
    private boolean turnedOn; //Состояние
    private int currentTemperature; //текущая температура (получается с помощью метода measureWaterTemperature)
    private int waterQuantity; //количество воды в миллилитрах
    private int requiredTemperature = MAX_TEMPERATURE; //температура до которой требуется подогреть воду (по умолчанию - вскипятить)
    private int minimumOfWater = 100; //минимальное количество воды

    public Kettle(int volume) { //При создании чайника нужно указать его объём
        this.volume = volume;
    }

    public void heatWater() throws NoWaterException { //греем воду, пока не закипит или не отключится кнопка
        if (getWaterQuantity() > getMinimumOfWater()) {
            while (isTurnedOn() && currentTemperature < requiredTemperature) {
                currentTemperature += 1;
                System.out.println("Warmed to " + getCurrentTemperature());
            }
            turnedOn = false;
        } else {
            throw new NoWaterException();
        }
    }

    public void pourWater(int waterQuantity) throws TooMuchWaterException { //Заполнить чайник
        if (waterQuantity + getWaterQuantity() > volume) {
            throw new TooMuchWaterException(waterQuantity + getWaterQuantity(), volume);
        }
        this.waterQuantity += waterQuantity;
    }

    public void pourOutWater(int waterQuantity) throws NotEnoughWaterException { // Вылить воду из чайника
        if (waterQuantity > getWaterQuantity()) {
            throw new NotEnoughWaterException(waterQuantity, getWaterQuantity());
        }
        this.waterQuantity -= waterQuantity;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public void setTurnedOn(boolean turnedOn) {
        this.turnedOn = turnedOn;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public int getRequiredTemperature() {
        return requiredTemperature;
    }

    public void setRequiredTemperature(int requiredTemperature) {
        this.requiredTemperature = requiredTemperature;
    }

    public int getMinimumOfWater() {
        return minimumOfWater;
    }

    public void setMinimumOfWater(int minimumOfWater) {
        this.minimumOfWater = minimumOfWater;
    }
}