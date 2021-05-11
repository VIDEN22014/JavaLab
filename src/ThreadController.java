public class ThreadController {
    public double  ParallelIntegrate(int countOfDivides, double leftBorder, double rightBorder, double step) {
        double shift = rightBorder / (double) countOfDivides;
        SimpsonMethod.area = 0;
        for (int i = 0; i < countOfDivides; i++) {
            SimpsonMethod method = new SimpsonMethod(leftBorder, leftBorder + shift, step);
            Thread t = new Thread(new MyThread(method));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            leftBorder += shift;
        }
        return SimpsonMethod.area;
    }
}
