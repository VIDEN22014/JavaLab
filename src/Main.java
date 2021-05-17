import java.io.BufferedWriter;
import java.io.Writer;

public class Main {
    public static void main(String[] args) {
        long threadTime;

        for (int i = 1; i <= 100; i++) {
            threadTime = System.currentTimeMillis();
            System.out.println("Кількість потоків : " + i);
            AreaAdder adder=new AreaAdder();
            ThreadController threadController = new ThreadController();
            System.out.println("Площа = " + threadController.ParallelIntegrate(i, 0, Math.PI / 2.0, 0.0000001,adder));
            System.out.println("Час виконання : " + (double) (System.currentTimeMillis() - threadTime) + "\n");
        }
    }
}
