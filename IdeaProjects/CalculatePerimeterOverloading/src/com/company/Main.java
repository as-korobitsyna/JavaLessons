package com.company;

public class Main {

    public static void main(String[] args) {
	    String name;
        double singleValue;
        double triangleValues[] = new double[3];

        name = args[0];
        if (name.compareTo("circle") == 0)
        {
            singleValue = Double.parseDouble(args[1]);
            if (singleValue <= 0)
            {
                System.out.println("Invalid input");
                System.exit(-1);
            }
            System.out.println(circle.perimeter(singleValue));

        }
        else if (name.compareTo("square") == 0)
        {
            singleValue = Double.parseDouble(args[1]);
            if (singleValue <= 0)
            {
                System.out.println("Invalid input");
                System.exit(-1);
            }
            System.out.println(square.perimeter(singleValue));
        }
        else if (name.compareTo("triangle") == 0)
        {
           for(int i = 0; i < 3; i++)
           {
               singleValue = Double.parseDouble(args[i + 1]);
               if (singleValue > 0) triangleValues[i] = singleValue;
               else {
                   System.out.println("Invalid input");
                   System.exit(-1);
               }
           }

            System.out.println(triangle.perimeter(triangleValues[0], triangleValues[1], triangleValues[2]));

        }
        else
         {
            System.out.println("Invalid input");
            System.exit(-1);
        }


    }

}


