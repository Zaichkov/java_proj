package ru.intervale.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance() {
        Point p = new Point(3, 4);
        Assert.assertEquals(p.distance(new Point(6, 8)), 5.0);
        Assert.assertEquals(new Point(12, 20).distance(new Point(4, 5)), 17.0);
    }

    @Test
    public void testDistanceNull() {
        Assert.assertEquals(new Point(0, 0).distance(new Point(0, 0)), 0.0);

    }

    @Test
    public void testDistanceNegativeNumbers() {
        Assert.assertEquals(new Point(-5, -5).distance(new Point(-7, -5)), 2.0);
    }
}
