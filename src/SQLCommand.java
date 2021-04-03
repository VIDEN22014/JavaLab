import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class SQLCommand {
    public static void createTableIfNotExist() {
        try {
            Main.connection.createStatement().execute("CREATE TABLE if not exists Waypoints "
                    + "( id INT NOT NULL AUTO_INCREMENT, "
                    + "trackName CHAR(100) NOT NULL, "
                    + "point CHAR(100) NOT NULL, "
                    + "PRIMARY KEY (id))");
            Main.connection.createStatement().execute("CREATE TABLE if not exists TempTable1 "
                    + "( id INT NOT NULL AUTO_INCREMENT, "
                    + "trackName CHAR(100) NOT NULL, "
                    + "point CHAR(100) NOT NULL, "
                    + "PRIMARY KEY (id))");
            Main.connection.createStatement().execute("CREATE TABLE if not exists TempTable2 "
                    + "( id INT NOT NULL AUTO_INCREMENT, "
                    + "trackName CHAR(100) NOT NULL, "
                    + "point CHAR(100) NOT NULL, "
                    + "PRIMARY KEY (id))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void clearTable() {
        try {
            Main.connection.createStatement().execute("TRUNCATE TABLE Waypoints");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void clearTempTable() {
        try {
            Main.connection.createStatement().execute("TRUNCATE TABLE TempTable1");
            Main.connection.createStatement().execute("TRUNCATE TABLE TempTable2");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public static void insert(String trackName, String point) {
        try {
            String query = "insert into Waypoints (trackName,point)" + "values(?,?)";
            PreparedStatement prepareStatement = Main.connection.prepareStatement(query);
            prepareStatement.setString(1, trackName);
            prepareStatement.setString(2, point);
            prepareStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public static void fillTempTable(String trkName1, String trkName2) {
        try {
            clearTempTable();
            ResultSet rs = Main.connection.createStatement().executeQuery("SELECT * FROM waypoints WHERE trackName = " + trkName1);
            while (rs.next()) {
                String query = "insert into TempTable1 (trackName,point)" + "values(?,?)";
                PreparedStatement prepareStatement = Main.connection.prepareStatement(query);
                prepareStatement.setString(1, rs.getString(2));
                prepareStatement.setString(2, rs.getString(3));
                prepareStatement.execute();
            }
            rs = Main.connection.createStatement().executeQuery("SELECT * FROM waypoints WHERE trackName = " + trkName2);
            while (rs.next()) {
                String query = "insert into TempTable2 (trackName,point)" + "values(?,?)";
                PreparedStatement prepareStatement = Main.connection.prepareStatement(query);
                prepareStatement.setString(1, rs.getString(2));
                prepareStatement.setString(2, rs.getString(3));
                prepareStatement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void innerJoin() {
        try {
            int counter = 0;
            ResultSet rs = Main.connection.createStatement().executeQuery("SELECT *\n" +
                    "FROM mydatabase.temptable1\n" +
                    "INNER JOIN mydatabase.temptable2\n" +
                    "ON mydatabase.temptable1.point = mydatabase.temptable2.point ");
            while (rs.next()) {
                counter++;
            }
            System.out.println(counter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseFromFile(String fileName, String trackName, int depth) {
        Node node = GPXParser.parseGPX(fileName);
        HashSet<String> hashSet = new HashSet<>();

        double latitute;
        double longitude;
        MyNode.clearChildrenNodes(node);
        NodeList nodesList = node.getChildNodes();
        for (int i = 0; i < nodesList.getLength(); i++) {
            longitude = Double.parseDouble(nodesList.item(i).getAttributes().getNamedItem("lon").getNodeValue());
            latitute = Double.parseDouble(nodesList.item(i).getAttributes().getNamedItem("lat").getNodeValue());
            String point = CoordinatesConvertor.recursionConvert(longitude, latitute, depth);
            hashSet.add(point);
//
        }
        for (String str : hashSet) {
            insert(trackName, str);
        }
    }
}
