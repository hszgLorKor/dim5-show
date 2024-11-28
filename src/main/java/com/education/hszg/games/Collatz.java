package com.education.hszg.games;

public class Collatz {
    public static void main(String[] args) {
        Collatz myCollatz = new Collatz();
        System.out.println("Collatz 5 ->" + myCollatz.getCollatzNumber(5));
        System.out.println("Collatz 15 ->" + myCollatz.getCollatzNumber(15));
        System.out.println("Collatz 100 ->" + myCollatz.getCollatzNumber(100));
        System.out.println("Collatz 300 ->" + myCollatz.getCollatzNumber(300));
        System.out.println("Collatz 287323234 ->" + myCollatz.getCollatzNumber(287323234));
    }

    public int getCollatzNumber(int n){
        System.out.print(n + " ");
        if (n == 1) return 1;
        if (n % 2 == 0) return getCollatzNumber(n / 2);
        else return getCollatzNumber((n * 3) + 1);
    }
}
