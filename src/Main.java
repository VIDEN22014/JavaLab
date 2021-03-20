import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;
import java.util.Scanner;
import java.sql.SQLException;

public class Main {

    static String lonqitute = "";
    static String latitute = "";
    static String Name = "";
    static String somedescription = "";
    static String query=" ";
    static Connection connection;

    static String command;
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "zae1rf4Mak");
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;
        }
        //
        KMLCreator.kmlCreate(GPXParser.parseGPX("L-35-003-points.gpx"));
        //
        System.out.println("Виберіть яку дію потрібно виконати:\n1.Вивід таблиці у консоль\n2.Видалити з таблиці запис із id\n3.Вивід записів координати яких лежать у діапазоні\n4.Видалення вмісту таблиці\n5.Модифікація за певним id\n6.Парсинг, та початковий запис таблиці \n7.Закінчення виконання\n");
        Parsekml();

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while(Integer.parseInt(command)!=7 ) {   choose(Integer.parseInt(command));   command = in.next();}
        System.out.println("END");


    }
    private static void choose(int nmb){
        switch (nmb) {
            case 1:
                System.out.println("Вивід таблиці:\n");
                write_info();
                break;

            case 2:
                System.out.println("Введіть id за яким потрібно видалити:\n");
                Scanner in0 = new Scanner(System.in);
                delete_id_table(in0.nextInt());

                break;
            case 3:
                System.out.println("Діапазон по ширині: 47.743213 по 47.764625\n");
                System.out.println("Діапазон по довготі: 25.033465 по 25.067227\n");
                Range_info();
                break;
            case 4:
                clear_table();
                System.out.println("Вміст був видалений\n");
                break;
            case 5:
                Scanner in2 = new Scanner(System.in);
                Scanner in3 = new Scanner(System.in);
                int numb;
                String text;

                System.out.println("Введіть id:\n");
                command = in2.next();
                numb = Integer.parseInt(command);
                System.out.println("Введіть новий текст мітки:");
                command = in3.next();
                text = command;
                modification(numb, text);
                break;
            case 6:
                clear_table();
                Parsekml();
                break;
            default:
                System.out.println("Невірний вибір команди\n");

        }
    }

    private static void Parsekml() {
        File file = new File("input.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document xml = dBuilder.parse(file);
            xml.getDocumentElement().normalize();

            NodeList edesc = xml.getDocumentElement().getElementsByTagName("desc");
            NodeList elink = xml.getDocumentElement().getElementsByTagName("link");
            NodeList ewpt = xml.getDocumentElement().getElementsByTagName("wpt");


            for (int i = 0; i < edesc.getLength(); i++) {

                Name = edesc.item(i).getTextContent();

                somedescription = elink.item(i).getAttributes().item(0).getTextContent();
                latitute = ewpt.item(i).getAttributes().item(0).getTextContent();
                lonqitute = ewpt.item(i).getAttributes().item(1).getTextContent();
                set_info();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void set_info(){
        try {
            query = "insert into information (name,lan,lon)" + "values(?,?,?)";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, Name);
            prepareStatement.setString(2, latitute);
            prepareStatement.setString(3, lonqitute);
            prepareStatement.execute();

        }
        catch (SQLException e)
        { System.out.println(e); }

    }
    private static void write_info(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM information");
            while (rs.next()){
                System.out.println(
                        rs.getInt(1) + " " +
                                rs.getString(2) + " " +
                                rs.getString(3)+ " " +
                                rs.getString(4));
            } ;
        }
        catch (SQLException e)
        { System.out.println(e); }

    }

    private static void Range_info(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM information WHERE (lan between 47.743213 AND 47.764625) AND ( lon between 25.033465 AND 25.067227)");

            while (rs.next()){
                System.out.println(
                        rs.getInt(1) + " " +
                                rs.getString(2) + " " +
                                rs.getString(3)+ " " +
                                rs.getString(4));
            } ;
        }
        catch (SQLException e)
        { System.out.println(e); }
    }
    private static void delete_id_table(int ID){
        try {

            connection.createStatement().executeUpdate("DELETE  FROM information WHERE id= "+ID);
        }
        catch (SQLException e)
        { System.out.println(e); }
    }
    private static void clear_table() {
        try {
            connection.createStatement().executeUpdate("TRUNCATE TABLE information");
        }catch (SQLException e)
        { System.out.println(e); }
    }
    private static void modification(int number, String Desc){
        try {
            query="UPDATE information SET name =? WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,' '+Desc);
            stmt.setString(2, String.valueOf(number));
            stmt.executeUpdate();
        }catch (SQLException e)
        { System.out.println(e); }

    }
}
