import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.data.Data;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonGet jsonGet = new JsonGet();
        jsonGet.sendGET();
        JSON json = new JSON();
        Data data = json.deserializeFromJsonFile("C://Users/dxbog/IdeaProjects/JavaLab/src/json.json");
        for (com.data.List i : data.list) {
            System.out.println(i.dtTxt + " Температура: " + (int) (i.main.temp - 273.15) + "°C");
        }
    }
}
