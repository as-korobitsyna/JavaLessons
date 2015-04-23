package com.company;

public class Main {

    public static void main(String[] args) {
        int day;
        day = Integer.parseInt(args[0]);

        System.out.println(chooseDay(day));

    }

    public static String chooseDay(int DayNumber) {
        switch(DayNumber) {
            case 1:  return("Monday");
            case 2:  return("Tuesday");
            case 3:  return("Wednesday");
            case 4:  return("Thursday");
            case 5:  return("Friday");
            case 6:  return("Saturday");
            case 7:  return("Sunday");
            default: return("Invalid input");
        }
    }
}
