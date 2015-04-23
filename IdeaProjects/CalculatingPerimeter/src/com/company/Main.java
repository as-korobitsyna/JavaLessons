package com.company;

public class Main {

    public static void main(String[] args) {
	    String figure;
        double value;

        value = Double.parseDouble(args[0]);
        if (value <= 0)
        {
            System.out.println("Invalid input");
            System.exit(-1);
        }

        figure = args[1];
        if (figure.compareTo("circle") == 0) System.out.println(circle.perimeter(value));
        else if (figure.compareTo("square") == 0) System.out.println(square.perimeter(value));
        else
         {
            System.out.println("Invalid input");
            System.exit(-1);
        }

    }

}


