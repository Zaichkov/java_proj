package ru.intervale.pft.sandbox;
import static java.lang.Math.sqrt;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double distance(Point p) {
        return sqrt(Math.pow((this.x - p.x), 2.0) + Math.pow((this.y - p.y), 2.0));
    }
}

