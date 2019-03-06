package pack;

public enum HopCoordinates {
    A(2, 1),
    B(2, -1),
    C(-2, 1),
    D(-2, -1),
    E(1, 2),
    F(1, -2),
    G(-1, 2),
    H(-1, -2);

    private final int x;
    private final int y;

    HopCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}