package ru.stqa.java_course.sandbox;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class Exercise3Tests {

    @Test
    public void testExercise2 () {
        Point p1 = new Point(5, 7);
        Point p2 = new Point(8, 9);
        assertEquals(p1.distance(p2),3.605551275463989);
        System.out.println("Расстояние между двумя точками на плоскости " + "p1 = " +
                "(" + p1.x + ", " + p1.y + ")" + " и " + "p2 = " +
                "(" + p2.x + ", " + p2.y + ")" + " должно быть равно " + 3.605551275463989 +
                " ,а получили "+ p1.distance(p2));
    }
}
