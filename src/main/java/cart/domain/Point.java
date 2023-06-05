package cart.domain;

import cart.domain.Product.Price;
import cart.exception.InvalidRequestException;

import java.util.Objects;

public class Point {
    private static final double POINT_RATE = 2.5/100;

    private final int point;

    public Point(int point) {
        validatePoint(point);
        this.point = point;
    }

    public static Point makePointFrom(Price price){
        return price.makePointFrom(POINT_RATE);
    }

    private void validatePoint(int point) {
        if (point < 0) {
            throw new InvalidRequestException.MinusInvalidRequest();
        }
    }

    public boolean isSmallerThan(Point other){
        return this.point < other.point;
    }

    public boolean isBiggerThan(Price price) {
        return this.point > price.price();
    }

    public Point getNewPoint(Point savePoint, Point usePoint) {
        int newPoint = this.point + savePoint.point - usePoint.point;
        return new Point(newPoint);
    }

    public int point() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point1 = (Point) o;
        return point == point1.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
