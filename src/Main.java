import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.data.Data;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonGetFromServer jsonGetFromServer = new JsonGetFromServer();
        java.util.List<String> cityNames = new ArrayList<String>();
        File file = new File("C://Users/dxbog/IdeaProjects/JavaLab/src/CityNames.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null) {
            cityNames.add(line);
            line = reader.readLine();
        }
        JSON json = new JSON();
        Data data = json.deserializeFromJsonFile("C://Users/dxbog/IdeaProjects/JavaLab/src/Forecast.json");
        for (String cityName : cityNames) {
            if (jsonGetFromServer.sendGET(cityName)) {
                data = json.deserializeFromJsonFile("C://Users/dxbog/IdeaProjects/JavaLab/src/Forecast.json");
                System.out.println("Прогноз температури для міста : " + cityName);
                for (com.data.List i : data.list) {
                    System.out.println(i.dtTxt + " Температура: " + (int) (i.main.temp - 273.15) + "°C");
                }
            } else {
                System.out.println("Введіть правильну назву міста");
            }
            System.out.println("");
        }
    }
}
