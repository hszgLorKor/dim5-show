package com.education.hszg.util;

public class NumberUtilCalculator {
    public static void main(String[] args) {
        int ergebnis = getRoundedSquareRoot(160000,0);
        System.out.println(ergebnis);
        String ergebnis2 = getBinaryOfDecimal(29);
        System.out.println(ergebnis2);
    }
    public static int getRoundedSquareRoot(int n, int k){
        if(k*k > n){
            return(k-1);
        }
        return getRoundedSquareRoot(n,k+1);
    }
    public static String getBinaryOfDecimal(int n) {
        if (n < 2) return "" + n;
        int digit = n % 2;
        return
                getBinaryOfDecimal(n / 2) + digit;
    }
}
