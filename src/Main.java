public class Main {
    public static void main(String[] args) {
        SimpsonMethod method=new SimpsonMethod();
        System.out.println(method.Integrate(0,Math.PI / 3.0, 0.001));
    }
}

class SimpsonMethod {
    public SimpsonMethod() {}
    public double Integrate(double leftBorder,double rightBorder,double step) {
        double area = 0;
        double sum = 0;
        double numOfPartition = (rightBorder - leftBorder) / step, x;
        area += getY(leftBorder) + getY(rightBorder);
        for (x = leftBorder + step / 2; x < rightBorder; x += step) {
            sum += getY(x);
        }
        sum *= 4.0;
        area += sum;
        sum = 0;
        x = leftBorder + step;
        for (double i = 1; i < numOfPartition; i++) {
            sum += getY(x);
            x += step;
        }
        sum *= 2.0;
        area += sum;
        area *= (step / 6);
        return area;
    }
    public double getY(double x) {
        return Math.sin(x);
    }
}
