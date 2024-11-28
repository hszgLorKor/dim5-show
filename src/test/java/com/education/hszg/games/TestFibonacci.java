package com.education.hszg.games;

import com.education.hszg.util.IntArrayCalculator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestFibonacci {

    private Fibonacci myFibonacci;

    @Before
    public void before() {
        myFibonacci = new myFibonacci();
    }

    @After
    public void after() {
        myFibonacci = null;
    }

    @Test
    @Parameters({
            "1,                 1",
            "2,                 1",
            "3,                 2",
            "4,                 3",
            "5,                 5",
            "6,                 8",
            "11,                89"
    })
    public void testGetRecursiveFibonacci(int number, int result) {
        Assert.assertEquals(
                result,
                myFibonacci.getRecursiveFibonacci(number),
                0.0001
        );


    }





}
