package com.education.hszg.games;

public class Fibonacci {

    private int[] myFibNumberCache = new int[50];

    public int getRecursiveFibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return
                getRecursiveFibonacci(n-1) + getRecursiveFibonacci(n-2);
    }
    public int getDynamicFibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        // Mini-Tabelle
        // 1 - 2 - 3
        // 1 - 1 - 2

        // 2 - 3 - 4
        // 1 - 2 - 3

        int[][] fibTableWindow = new int[3][2];
        fibTableWindow[0][0] = 0;
        fibTableWindow[0][1] = 0;
        fibTableWindow[1][0] = 1;
        fibTableWindow[1][1] = 1;
        fibTableWindow[2][0] = 2;
        fibTableWindow[2][1] = 1;

        while (fibTableWindow[2][0] < n) {
            fibTableWindow[0][0] = fibTableWindow[1][0];
            fibTableWindow[0][1] = fibTableWindow[1][1];
            fibTableWindow[1][0] = fibTableWindow[2][0];
            fibTableWindow[1][1] = fibTableWindow[2][1];
            fibTableWindow[2][0] = fibTableWindow[1][0] + 1;
            fibTableWindow[2][1] = fibTableWindow[0][1] + fibTableWindow[1][1];
        }

        return fibTableWindow[2][1];
    }

    public int getRecursiveOptimizedFibonacci(int n) {
        if (myFibNumberCache[n] != -1) {
            return myFibNumberCache[n];
        }
        if (n == 0){
            myFibNumberCache[0] = 0;
            return 0;
        }
        if (n == 1){
            myFibNumberCache[1] = 1;
            return 1;
        }
        myFibNumberCache[n] = getRecursiveOptimizedFibonacci(n-1) + getRecursiveOptimizedFibonacci(n-2);
        return myFibNumberCache[n];
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for(int i=0; i<50; i++) {
            fibonacci.myFibNumberCache[i] = -1;
        }
        System.out.println("Play with Fibonacci Numbers");
        System.out.println("1 -> " + fibonacci.getRecursiveFibonacci(1));
        System.out.println("10 -> " + fibonacci.getRecursiveFibonacci(10));
        System.out.println("46 -> " + fibonacci.getRecursiveFibonacci(46));

        System.out.println("Play with Fibonacci Numbers Dynamic Version");
        System.out.println("1 -> " + fibonacci.getDynamicFibonacci(1));
        System.out.println("10 -> " + fibonacci.getDynamicFibonacci(10));
        System.out.println("46 -> " + fibonacci.getDynamicFibonacci(46));

        System.out.println("Play with Fibonacci Numbers recursive optimized Version");
        System.out.println("1 -> " + fibonacci.getRecursiveOptimizedFibonacci(1));
        System.out.println("10 -> " + fibonacci.getRecursiveOptimizedFibonacci(10));
        System.out.println("46 -> " + fibonacci.getRecursiveOptimizedFibonacci(46));
    }
}
