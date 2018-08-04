package project.chess960.implementation;

public class PiecePosition {
    private int x;
    private int y;

    public  PiecePosition() {

    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
