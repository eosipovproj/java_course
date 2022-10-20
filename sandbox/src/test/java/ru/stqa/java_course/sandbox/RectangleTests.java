package ru.stqa.java_course.sandbox;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RectangleTests {

    @Test
    public void rectangleTest(){
        Rectangle r = new Rectangle(7, 10);
        assertEquals(r.area(), 70);
        System.out.println("Площадь прямругольника со сторонами " + "a = " + r.a + " и "
                + "b = " + r.b + " должна быть равна " + 70 + " , а получили " + r.area());
    }
}
