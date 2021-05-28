package Zavd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyBufferedFileReader {
    public static String readFile(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder tempStringBuilder = new StringBuilder();
        String tempString = "";
        while (true) {
            try {
                if (!((tempString = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempStringBuilder.append(tempString);
            tempStringBuilder.append("\n");
        }
        return tempStringBuilder.toString();
    }
}
