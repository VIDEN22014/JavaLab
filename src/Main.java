import java.sql.*;
import java.util.Scanner;

public class Main {

    static Connection connection;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(CoordinatesConvertor.recursionConvert(24, 48, 4));
    }
}
