package com.education.hszg.sort.impl;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestBubbleSortRecursive {

    private BubbleSortRecursive bubbleSortRecursive;

    @Before
    public void before() {
        bubbleSortRecursive = new BubbleSortRecursive();
    }

    @After
    public void after() {
        bubbleSortRecursive = null;
    }


    @Test
    @Parameters({
            "7-7-7-7-7-7,       7-7-7-7-7-7",
            "9-8-7-6-5-4,       4-5-6-7-8-9",
            "1-2-3-4-5-6,       1-2-3-4-5-6",
            "7-7-7-1234567-7,   7-7-7-7-1234567",
            "7-7-7-4-7,         4-7-7-7-7",
            "7,                 7"
    })
    public void testSort(String unsorted, String sorted) {

        String[] split = unsorted.split("-");
        int[] unsortedNumbers = new int[split.length];
        for(int i = 0; i < split.length; i++)
            unsortedNumbers[i] = Integer.parseInt(split[i]);

        split = sorted.split("-");
        int[] sortedNumbers = new int[split.length];
        for(int i = 0; i < split.length; i++)
            sortedNumbers[i] = Integer.parseInt(split[i]);

        Assert.assertArrayEquals(
                sortedNumbers,
                bubbleSortRecursive.sort(unsortedNumbers, unsortedNumbers.length-1)
        );


    }

}
