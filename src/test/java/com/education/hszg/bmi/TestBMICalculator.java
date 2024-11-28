package com.education.hszg.bmi;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class TestBMICalculator {

    private BMICalculator bmiCalculator;

    @Before
    public void before() {
        bmiCalculator = new BMICalculator();
    }

    @After
    public void after() {
        bmiCalculator = null;
    }

    @Test
    @Parameters({
            "85.123,        1.78,     26.86",
            "-5.5,          1.24,     0.0",
            "233,           1.24,     0.0",
            "85.7,          0.78,     0.0",
            "66.0,          820.7,    0.0"
    })
    public void testCalculateBMI(double weightInKG, double heightInMetres, double bmi) {
        assertEquals(
                        bmi,
                        bmiCalculator.calculateBMI(weightInKG, heightInMetres),
                    0.1);
    }

    @Test
    @Parameters({
            "85.123,        1.78,     Please do join a gym sports club.",
            "-5.5,          1.24,     Please eat more often at McDonalds.",
            "233,           1.24,     Please eat more often at McDonalds.",
            "85.7,          0.78,     Please eat more often at McDonalds.",
            "66.0,          820.7,    Please eat more often at McDonalds.",
            "67.72,         1.80,     Please eat more often at McDonalds.",
            "77.44,         1.80,     You are having a healthy body.",
            "78.08,         1.80,     Please do join a gym sports club."
    })
    public void testGetBMIRecommendation(double weightInKG, double heightInMetres, String recommendation) {
        assertEquals(
                recommendation,
                bmiCalculator.getBMIRecommendation(weightInKG, heightInMetres)
        );
    }

}
