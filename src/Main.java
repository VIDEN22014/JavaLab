
import java.sql.*;
import java.util.*;

public class Main {

    static Connection connection;


    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "mysqlgovno");
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;
        }
        //
        SQLCommand.createTableIfNotExist();
        SQLCommand.clearTable();
        Scanner in = new Scanner(System.in);
        System.out.println("Введіть довжину рядка : ");
        int depth = in.nextInt();
        System.out.println("Запис даних в таблицю");
        SQLCommand.parseFromFile("10.gpx", "10", depth);
        SQLCommand.parseFromFile("71.gpx", "71", depth);
        SQLCommand.parseFromFile("73.gpx", "73", depth);
        System.out.println("Запис даних завершено");
        System.out.println("Розмір прямокутинка : " + 180.0 / Math.pow(2, (depth - 1)) + " x " + 90.0 / Math.pow(2, (depth - 1)) + " градусів");
        System.out.println("Розмір прямокутинка : " + 180.0 / Math.pow(2, (depth - 1)) / 360.0 * 40000000 + " x " + 90.0 / Math.pow(2, (depth - 1)) / 180.0 * 20000000 + " метрів");
        while (true) {
            MyTrack.trackCompare();
        }
    }
}
