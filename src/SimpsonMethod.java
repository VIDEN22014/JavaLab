public class SimpsonMethod {
    public SimpsonMethod(double leftBorder, double rightBorder, double step) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.step = step;
    }

    double leftBorder;
    double rightBorder;
    double step;

    public double Integrate() {
        double area=0;
        double tempSum = 0;
        double numOfPartition = (rightBorder - leftBorder) / step, x;
        area += getY(leftBorder) + getY(rightBorder);
        for (x = leftBorder + step / 2; x < rightBorder; x += step) {
            tempSum += getY(x);
        }
        tempSum *= 4.0;
        area += tempSum;
        tempSum = 0;
        x = leftBorder + step;
        for (double i = 1; i < numOfPartition; i++) {
            tempSum += getY(x);
            x += step;
        }
        tempSum *= 2.0;
        area += tempSum;
        area *= (step / 6);
        return area;
    }

    public double getY(double x) {
        return Math.sin(x);
    }
}
