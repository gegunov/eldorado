package com.getjavajob.egunov.eldorado.kettle;

import com.getjavajob.egunov.eldorado.kettle.exceptions.NoWaterException;
import com.getjavajob.egunov.eldorado.kettle.exceptions.NotEnoughWaterException;
import com.getjavajob.egunov.eldorado.kettle.exceptions.TooMuchWaterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KettleTest {

    private Kettle kettle;

    @Before
    public void setup() {
        kettle = new Kettle(1500);
        try {
            kettle.pourWater(500);
        } catch (TooMuchWaterException e) {
            e.printStackTrace();
        }
        kettle.setTurnedOn(true);
    }

    @Test
    public void testBoil() {
        try {
            kettle.heatWater();
            Assert.assertEquals(100, kettle.getCurrentTemperature());
        } catch (NoWaterException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testPour() {
        try {
            kettle.pourWater(500);
            Assert.assertTrue(kettle.getWaterQuantity() == 1000);
        } catch (TooMuchWaterException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testPourOut() {
        try {
            kettle.pourOutWater(500);
            Assert.assertTrue(kettle.getWaterQuantity() == 0);
        } catch (NotEnoughWaterException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testTurnedOff() {
        kettle.setTurnedOn(false);
        try {
            kettle.heatWater();
            Assert.assertTrue(kettle.getCurrentTemperature() == 0);
        } catch (NoWaterException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test(expected = NotEnoughWaterException.class)
    public void testException() throws NotEnoughWaterException {
        kettle.pourOutWater(10000);
    }

    @Test(expected = NoWaterException.class)
    public void testNoWaterDefence() throws NoWaterException {
        try {
            kettle.pourOutWater(500);
            kettle.heatWater();
        } catch (NotEnoughWaterException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = TooMuchWaterException.class)
    public void testTooMuchWaterException() throws TooMuchWaterException {
        kettle.pourWater(2000);
    }
}
