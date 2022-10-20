package ru.stqa.java_course.sandbox;

public class Exercise2 {
    public static void  main(String[] args){
        //пункт 3
/*      Point p1 = new Point(4.0, 5.0);
        Point p2 = new Point(7.0, 9.0);
        System.out.println("Расстояние между двумя точками на плоскости " + "p1 = " +
                "(" + p1.x + ", " + p2.y + ")"  + " и " + "p2 = " +
                "(" + p2.x + ", " + p2.y + ")" + " = " + distance(p1, p2));
*/
        //пункт 4
        Point p1 = new Point(5, 7);
        Point p2 = new Point(8, 9);
        System.out.println("Расстояние между двумя точками на плоскости " + "p1 = " +
                "(" + p1.x + ", " + p1.y + ")"  + " и " + "p2 = " +
                "(" + p2.x + ", " + p2.y + ")" + " = " + p1.distance(p2));
    }
/*
 Пункт 2
    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
*/
}
