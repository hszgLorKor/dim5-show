package com.education.hszg.util;

import com.education.hszg.sort.impl.BubbleSort;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class TestIntArrayCalculator {

    private IntArrayCalculator intArrayCalculator;

    @Before
    public void before() {
        intArrayCalculator = new IntArrayCalculator();
    }

    @After
    public void after() {
        intArrayCalculator = null;
    }

    @Test
    @Parameters({
            "1,                 1.0",
            "1-1-1-1-1-1,       1.0",
            "1-2-3-4-5-6,       3.5",
            "1-2-3-4-5-6-7,     4.0",
            "1-7-3-4-2-6-5,     4.0",
            "7-6-5-4-3-2-1,     4.0"
    })
    public void testCalculateAverage(String arrayNumbers, double average) {

        final String[] split = arrayNumbers.split("-");
        int[] numbers = new int[split.length];
        for(int i = 0; i < split.length; i++)
            numbers[i] = Integer.parseInt(split[i]);

        Assert.assertEquals(
                average,
                intArrayCalculator.calculateAverage(numbers),
                0.0001
        );


    }





}
