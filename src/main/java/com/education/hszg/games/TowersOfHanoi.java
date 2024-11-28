package com.education.hszg.games;

public class TowersOfHanoi {

    Hanoi hanoi;

    public TowersOfHanoi(int size) {
        this.hanoi = new Hanoi(size);
    }

    public void play(int number, int from, int to) {

        if (number == 0) return;
        System.out.println("Going into Game " + number + ":" + hanoi.toString());

        play(number-1, from, hanoi.otherTower(from, to));
        hanoi.add(to, hanoi.removeTop(from));
        play(number-1, hanoi.otherTower(from, to), to);

    }

    public static void main(String[] args) {
        TowersOfHanoi towersOfHanoi = new TowersOfHanoi(8);
        System.out.println("before");
        System.out.println(towersOfHanoi.hanoi.toString());
        towersOfHanoi.play(8,0,2);
        System.out.println("after");
        System.out.println(towersOfHanoi.hanoi.toString());

    }

}
