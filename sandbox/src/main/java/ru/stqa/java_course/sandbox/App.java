package ru.stqa.java_course.sandbox;

public class App{
	public static void main(String[] args){
		System.out.println("Hello, world!");
		Square s = new Square(5);
//		s.l = 5;
		System.out.println("площадь квадрата со стороной " + s.l + " = " + area(s));
		Rectangle r = new Rectangle(5, 7);
//		r.a = 5;
//		r.b = 7;
		System.out.println("площадь прямоугольника со сторонами " + r.a + " и " + r.b +  " = " + area(r));
	}
	public static double area(Square s) {
		return s.l * s.l;
	}
	public static double area(Rectangle r) {
		return r.a * r.b;
	}
}