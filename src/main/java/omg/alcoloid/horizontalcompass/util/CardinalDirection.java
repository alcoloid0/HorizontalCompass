package omg.alcoloid.horizontalcompass.util;

public enum CardinalDirection {
    N(0),
    NE(45),
    E(90),
    SE(135),
    S(180),
    SW(225),
    W(270),
    NW(315);

    private final int angle;

    CardinalDirection(int angle) {
        this.angle = angle;
    }

    public int getRotationAngle() {
        return this.angle;
    }
}
