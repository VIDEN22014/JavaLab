public class Main {
    public static void main(String[] args) {
        ThreadController threadController = new ThreadController();
        long threadTime;
        for (int i = 1; i < 300; i++) {
            System.out.println("Кількість потоків : " + i);
            threadTime = System.currentTimeMillis();
            System.out.println("Площа = " + threadController.ParallelIntegrate(i, 0, Math.PI / 2.0, 0.0000001));
            System.out.println("Час виконання : " + (double) (System.currentTimeMillis() - threadTime) + "\n");
        }
    }
}
