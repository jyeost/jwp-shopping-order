package cart.domain;

public class Member {
    private final Long id;
    private final String email;
    private final String password;
    private final Point point;

    public Member(Long id, String email, String password, Point point) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void checkPoint(Point otherPoint) {
        point.checkPoint(otherPoint);
    }

    public Point getPoint() {
        return point;
    }
}
