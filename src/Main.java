import org.w3c.dom.Node;

import java.sql.*;
import java.util.*;

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
        //
        SQLCommand.createTableIfNotExist();
        SQLCommand.clearTable();
        int depth = 20;
        SQLCommand.parseFromFile("10.gpx", "10", depth);
        SQLCommand.parseFromFile("71.gpx", "71", depth);
        SQLCommand.parseFromFile("73.gpx", "73", depth);

        SQLCommand.fillTempTable("10", "71");

        SQLCommand.innerJoin();
    }
}
