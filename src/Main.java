import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long threadTime;
        StringBuilder csvStringBuilder = new StringBuilder("ThreadCount;Time\n");
        double tempArea;
        double tempTime;

        for (int i = 1; i <= 250; i++) {
            AreaAdder adder=new AreaAdder();
            ThreadController threadController = new ThreadController();
            threadTime = System.currentTimeMillis();
            tempArea=threadController.ParallelIntegrate(i, 0, Math.PI / 2.0, 0.0000001,adder);
            tempTime=(double) (System.currentTimeMillis() - threadTime);
            //Вивід даних
            System.out.println("Кількість потоків : " + i);
            System.out.println("Площа = " + tempArea);
            System.out.println("Час виконання : " + (int)tempTime + "\n");
            csvStringBuilder.append(i);
            csvStringBuilder.append(";");
            csvStringBuilder.append((int)tempTime);
            csvStringBuilder.append("\n");
        }
        //Створення файлу csv
        FileWriter fileWriter = new FileWriter("data.csv");
        fileWriter.write(csvStringBuilder.toString());
        fileWriter.close();

    }
}
