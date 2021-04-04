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
            Main.connection.createStatement().execute("CREATE TABLE if not exists TempTable "
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
            e.printStackTrace();
        }
    }

    public static void clearTempTable() {
        try {
            Main.connection.createStatement().execute("TRUNCATE TABLE TempTable");
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }


    public static void fillTempTable(String trkName) {
        try {
            clearTempTable();
            ResultSet rs = Main.connection.createStatement().executeQuery("SELECT * FROM waypoints WHERE trackName = " + trkName);
            while (rs.next()) {
                String query = "insert into TempTable (trackName,point)" + "values(?,?)";
                PreparedStatement prepareStatement = Main.connection.prepareStatement(query);
                prepareStatement.setString(1, rs.getString(2));
                prepareStatement.setString(2, rs.getString(3));
                prepareStatement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void innerJoin(String trkName) {
        try {
            int joinCounter = 0;
            int tempTableCounter = 0;
            ResultSet rs = Main.connection.createStatement().executeQuery("SELECT *\n" +
                    "FROM mydatabase.temptable\n" +
                    "INNER JOIN mydatabase.waypoints\n" +
                    "ON mydatabase.temptable.point = mydatabase.waypoints.point AND mydatabase.waypoints.trackName = " + trkName);
            while (rs.next()) {
                joinCounter++;
            }
            rs = Main.connection.createStatement().executeQuery("SELECT * FROM temptable");
            while (rs.next()) {
                tempTableCounter++;
            }
            System.out.println(joinCounter + "/" + tempTableCounter + " Точок співпадають");
            System.out.println("Процент співпадіння : " + ((double) joinCounter / (double) tempTableCounter) * 100.0);
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
            String point = MyTrack.recursionConvert(longitude, latitute, depth);
            hashSet.add(point);
        }
        for (String str : hashSet) {
            insert(trackName, str);
        }
    }
}
