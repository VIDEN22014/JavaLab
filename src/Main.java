import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Connection connection;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "mysqlgovno");
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;
        }
        SQLCommand.createTableIfNotExist();
        int commandNumber;
        do {
            System.out.println("Виберіть яку дію потрібно виконати:\n1.Вивід таблиці на екран\n2.Видалити з таблиці запис по id\n3.Вивід записів координати яких лежать у певному діапазоні\n4.Очищення таблиці\n5.Оновлення даних за певним id\n6.Парсинг, таблиці з файлу\n");
            commandNumber = in.nextInt();
            choose(commandNumber);
        } while (commandNumber >= 1 && commandNumber <= 6);
    }

    private static void choose(int number) throws Exception {
        switch (number) {
            case 1 -> {
                System.out.println("Вивід таблиці:\n");
                SQLCommand.display();
            }
            case 2 -> {
                System.out.println("Введіть id за яким потрібно видалити:");
                SQLCommand.deleteByID(in.nextInt());
                System.out.println("Видалено успішно\n");
            }
            case 3 -> {
                System.out.println("Діапазон по ширині: 47.746465 по 48.764625");
                System.out.println("Діапазон по довготі: 25.031869 по 25.19399\n");
                SQLCommand.displayByRange();
            }
            case 4 -> {
                SQLCommand.clearTable();
                System.out.println("Таблиця очищена\n");
            }
            case 5 -> SQLCommand.updateTable();
            case 6 -> {
                if (GPXValidator.checkXMLforXSD("L-35-003-points.gpx", "scheme.xsd")) {
                    SQLCommand.ParseFromFile(GPXParser.parseGPX("L-35-003-points.gpx"));
                }
            }
            default -> System.out.println("Невірний вибір команди\n");
        }
    }
}
