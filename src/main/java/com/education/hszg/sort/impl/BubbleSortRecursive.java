package com.education.hszg.sort.impl;

public class BubbleSortRecursive {

    public void swap(int[] A, int index1, int index2) {
        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;
    }

    public int[] sort(int[] A, int lastElementIndex) {
        if (lastElementIndex == 0) return A;

        for (int i = 0; i < lastElementIndex; i++)
            if (A[i] > A[i + 1]) swap(A, i, i + 1);

        return
                sort(A, lastElementIndex-1);
    }

}
