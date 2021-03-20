import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLCommand {
    public static void createTableIfNotExist() {
        try {
            Main.connection.createStatement().execute("CREATE TABLE if not exists waypoints "
                    + "( id INT NOT NULL AUTO_INCREMENT, "
                    + "name CHAR(100) NOT NULL, "
                    + "lat double NOT NULL, "
                    + "lon double NOT NULL,"
                    + "PRIMARY KEY (id))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void display() {
        try {
            ResultSet rs = Main.connection.createStatement().executeQuery("SELECT * FROM waypoints");
            display(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayByRange() {
        try {
            ResultSet rs = Main.connection.createStatement().executeQuery("SELECT * FROM waypoints WHERE (lat between 47.746465 AND 48.764625) AND ( lon between 25.031869 AND 25.19399)");
            display(rs);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void display(ResultSet rs) {
        try {
            System.out.printf("%-3s%-53s%-10s%-10s%n", "id", "name", "lon", "lat");
            while (rs.next()) {
                String name = rs.getString(2);
                if (name.length() > 50) {
                    name = name.substring(0, 50);
                }
                System.out.printf("%-3s%-53s%-10s%-10s%n", rs.getInt(1), name, rs.getString(3), rs.getString(4));
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteByID(int ID) {
        try {
            Main.connection.createStatement().execute("DELETE  FROM waypoints WHERE id= " + ID);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void clearTable() {
        try {
            Main.connection.createStatement().execute("TRUNCATE TABLE waypoints");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateTable() {
        try {
            int id;
            String description;
            String query;
            System.out.println("Введіть id:\n");
            Scanner in1 = new Scanner(System.in);
            id = Integer.parseInt(in1.nextLine());
            System.out.println("Введіть новий текст мітки:");
            description = in1.nextLine();
            query = "UPDATE waypoints SET name =? WHERE id=?";
            PreparedStatement stmt = Main.connection.prepareStatement(query);
            stmt.setString(1, description);
            stmt.setInt(2, id);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void insert(String name, double latitute, double longitude) {
        try {
            String query = "insert into waypoints (name,lat,lon)" + "values(?,?,?)";
            PreparedStatement prepareStatement = Main.connection.prepareStatement(query);
            prepareStatement.setString(1, name);
            prepareStatement.setDouble(2, latitute);
            prepareStatement.setDouble(3, longitude);
            prepareStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void ParseFromFile(Node node) {
        clearTable();
        String name;
        double latitute;
        double longitude;
        NodeList nodesList = node.getChildNodes();
        for (int i = 0; i < nodesList.getLength(); i++) {
            longitude = Double.parseDouble(nodesList.item(i).getAttributes().getNamedItem("lon").getNodeValue());
            latitute = Double.parseDouble(nodesList.item(i).getAttributes().getNamedItem("lat").getNodeValue());
            name = MyNode.getChildNodeByName(nodesList.item(i), "desc").getChildNodes().item(0).getNodeValue();
            insert(name, latitute, longitude);
        }
    }
}
