package ru.intervale.pft.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args) {
		System.out.println("Hello, TEST AUTOMATION!");
/*		Point p1 = new Point();
		p1.x = 3;
		p1.y = 4;
		Point p2 = new Point();
		p2.x = 6;
		p2.y = 8;
		System.out.println(distance(p1, p2));*/

		Point p1 = new Point(3, 4);
		System.out.println(p1.distance(new Point(6, 8)));

	}

/*	public static double distance(Point p1, Point p2) {
		double s = Math.sqrt(Math.pow((p2.x - p1.x), 2.0) + Math.pow((p2.y - p1.y), 2.0));
		return s;
	}*/
}