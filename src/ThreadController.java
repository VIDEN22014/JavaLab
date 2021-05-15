import java.util.ArrayList;
import java.util.List;

public class ThreadController {
    public synchronized double ParallelIntegrate(int countOfDivides, double leftBorder, double rightBorder, double step,AreaAdder adder) {
        double shift = rightBorder / (double) countOfDivides;
        List<Thread> threadList=new ArrayList<>();
        for (int i = 0; i < countOfDivides; i++) {
            SimpsonMethod method = new SimpsonMethod(leftBorder, leftBorder + shift, step);
            Thread t = new Thread(new MyThread(method,adder));
            t.start();
            threadList.add(t);
            leftBorder += shift;
        }
        //Завершує виконання всіх потоків
        for (int i = 0; i < countOfDivides; i++) {
            try {
                threadList.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return adder.area;
    }
}
