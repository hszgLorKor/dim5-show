package com.education.hszg.sort.impl;

public class BubbleSort {

    public void swap(int[] A, int index1, int index2) {
        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;
    }

    public int[] sort(int[] A) {
        for(int j = 1; j < A.length ; j++)
            for (int i = 0; i < A.length - 1; i++)
                if (A[i] > A[i + 1]) swap(A, i, i + 1);
        return
                A;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] myNumbers= {3,7,4,1,8};

        System.out.println("My numbers before sorting:");
        for(int i = 0; i < myNumbers.length; i++)
            System.out.println(i + ": " + myNumbers[i]);

        System.out.println("My numbers after sorting:");
        myNumbers = bubbleSort.sort(myNumbers);
        for(int i = 0; i < myNumbers.length; i++)
            System.out.println(i + ": " + myNumbers[i]);

    }

}
