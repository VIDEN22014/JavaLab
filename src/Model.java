import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {
    List<Person> modelList = new ArrayList<>();

    Model() {
    }

    Model(String fileName) {
        readFromFile(fileName);
    }

    public void add(int Id, String firstName, String lastName, int age, int height) {
        Person tempPerson = new Person(Id, firstName, lastName, age, height);
        modelList.add(tempPerson);
    }

    public void clear() {
        modelList.clear();
    }

    public void display() {
        System.out.println(modelToString());
    }

    public String modelToString() {
        StringBuilder tempStringBuilder = new StringBuilder();
        String borderString = "_".repeat(58) + "\n";
        //Формування Верхньої межі
        //
        tempStringBuilder.append(String.format("%6s | %15s | %15s | %3s | %5s |%n", "ID", "Ім'я", "Прізвище", "Вік", "Зріст"));
        for (Person i : modelList) {
            tempStringBuilder.append(borderString);
            String tempFirstName = i.firstName;
            String tempLastName = i.lastName;
            if (tempFirstName.length() > 15) {
                tempFirstName = tempFirstName.substring(0, 15);
            }
            if (tempLastName.length() > 15) {
                tempLastName = tempLastName.substring(0, 15);
            }
            tempStringBuilder.append(String.format("%6s | %15s | %15s | %3s | %5s |%n", i.Id, tempFirstName, tempLastName, i.age, i.height));
        }
        return tempStringBuilder.toString();

    }

    public void sortById() {
        for (int i = 0; i < modelList.size(); i++) {
            for (int j = i; j < modelList.size(); j++) {
                if (modelList.get(i).Id > modelList.get(j).Id) {
                    Person tempPerson = modelList.get(i);
                    modelList.set(i, modelList.get(j));
                    modelList.set(j, tempPerson);
                }
            }
        }
    }

    public void writeToFile(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(modelToString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String fileName) {
        clear();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            StringBuilder tempStringBuilder = new StringBuilder();
            String tempLine;

            while ((tempLine = bufferedReader.readLine()) != null) {
                tempStringBuilder.append(tempLine);
                tempStringBuilder.append("\n");
            }
            tempLine = tempStringBuilder.toString().replaceAll("_\\n|_", "");
            Scanner scanner = new Scanner(tempLine);
            scanner.nextLine();
            scanner.useDelimiter(" \\|");

            try {
                while (true) {
                    tempLine = scanner.next();
                    if (tempLine == null || tempLine.equals("") || tempLine.equals("\n")) {
                        break;
                    }
                    int Id = Integer.parseInt(tempLine.replaceAll("[\\n ]", ""));

                    String firstName = scanner.next().replaceAll("^ +", "");
                    String lastname = scanner.next().replaceAll("^ +", "");

                    int age = Integer.parseInt(scanner.next().replaceAll(" ", ""));

                    int height = Integer.parseInt(scanner.next().replaceAll(" ", ""));

                    add(Id, firstName, lastname, age, height);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            bufferedReader.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
