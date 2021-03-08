import com.data.Data;
import com.google.gson.Gson;

import java.io.*;

import java.io.FileReader;
import java.io.FileWriter;

public class JSON {
    public static void serializeToJsonFile(Data data, String path) throws IOException {
        Gson gson = new Gson();
        FileWriter file = new FileWriter(path);
        file.write(gson.toJson(data));
        file.flush();
        file.close();
    }

    public static Data deserializeFromJsonFile(String path) throws IOException {
        Gson gson = new Gson();
        Reader reader = new FileReader(path);
        Data data = gson.fromJson(reader, Data.class);
        return data;
    }
}
