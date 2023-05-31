package cart.domain;

public class Point {
    int point;

    public Point(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void checkPoint(Point other) {
        if (this.point < other.point) {
            throw new IllegalArgumentException("사용불가능한 포인트입니다.");
        }
    }

    public Point subtract(Point other) {
        return new Point(this.point - other.point);
    }
}
