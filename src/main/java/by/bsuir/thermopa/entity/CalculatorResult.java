package by.bsuir.thermopa.entity;

public class CalculatorResult {
    private double y;
    private double x;

    public CalculatorResult(double y) {
        this.y = y;
    }

    public CalculatorResult(double y, double x) {
        this.y = y;
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
