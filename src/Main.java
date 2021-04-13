import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Model modelToWrite = new Model();
        Random rand = new Random();
        //Початкова ініціалізація
        for (int i = 0; i < 10; i++) {
            modelToWrite.add(rand.nextInt(100000), "Bohdan", Character.toString('A' + i), rand.nextInt(100), rand.nextInt(300));
        }

        System.out.println("Невідсортована Модель : ");
        modelToWrite.display();
        modelToWrite.sortById();
        System.out.println("Відсортована Модель : ");
        modelToWrite.display();
        modelToWrite.writeToFile("output.txt");
        Model modelToRead = new Model("output.txt");
        System.out.println("Модель із файлу : ");
        modelToRead.display();
    }
}
