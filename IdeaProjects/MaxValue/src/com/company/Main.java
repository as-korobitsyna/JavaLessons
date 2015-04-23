package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

       System.out.println(maxValue(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }

    public static int maxValue(int a, int b) {

        return((a >= b)? a : b);

    }
}
