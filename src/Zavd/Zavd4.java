package Zavd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zavd4 {
    public static void start(String fileName) {
        //Зчитування Файлу
        String inputString = MyBufferedFileReader.readFile(fileName);

        //Регекс для пошуку місяців
        final String regex = "(3[0-1]|[1-2][0-9]|0?[1-9])-((1[0-2]|0?[1-9])|(лютого|березня|квітня|травня|червня|липня|серпня|вересня|жовтня|листопада|грудня))(-\\d+)?";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(inputString);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group(0));
            stringBuilder.append("\n");
        }
        //Запис в файл
        try {
            FileWriter fileWriter = new FileWriter("Zavd/Zavd4/out4.txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
