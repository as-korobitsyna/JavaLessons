package com.company;

/**
 * Created by korobitsyna on 4/23/15.
 */
public class circle extends figure{

    public double radius;

    public static double perimeter(double radius)
    {
        return (radius*radius* Math.PI);
    }
}