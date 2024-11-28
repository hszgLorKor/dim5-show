package com.education.hszg.games;

import java.util.Arrays;

public class Hanoi {

    int size = 0;
    int[][] towers;

    public Hanoi(int size) {
        this.size = size;
        towers = new int[3][size];
        for (int i = 0; i < size; i++) {
            towers[0][i] = i+1;
            towers[1][i] = 0;
            towers[2][i] = 0;
        }
    }

    @Override
    public String toString() {
        return "Hanoi{" +
                "size=" + size +
                ", towers=" + Arrays.toString(towers[0]) + "--" + Arrays.toString(towers[1]) + "--" + Arrays.toString(towers[2])
                +
                '}';
    }

    public int removeTop(int tower) {
        int top = 0;
        int i = 0;
        while (i < size && towers[tower][i] == 0) i++;
        if (i < size) {
            top = towers[tower][i];
            towers[tower][i] = 0;
        }
        return top;
    }

    public void add(int tower, int size) {
        int top = 0;
        int i = 0;
        while (i < this.size && towers[tower][i] == 0) i++;
        if ((i-1) < this.size && i > 0) towers[tower][i-1] = size;
    }

    public int otherTower(int a, int b) {
        switch (a) {
        case 0: if (b == 1) return 2;
                else return 1;
        case 1: if (b == 0) return 2;
                else return 0;
        case 2: if (b == 1) return 0;
                else return 1;
        }
        return 0;
    }

}
