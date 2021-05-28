package Zavd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zavd4 {
    /*Знайдіть у файлі багаторядкові коментарі С-стилю, котрі містять дату у форматі ДД-ММ-РРРР
    або ДД-ММ. При цьому можливий варіант, коли місяць задано словом, а не цифрами
    (наприклад, 12-жовтня-2019). Виведіть дати.
    */
    public static void start(String fileName) {
        //Зчитування Файлу
        String inputString = MyBufferedFileReader.readFile(fileName);

        //Регекс для пошуку місяців
        final String regex = "(3[0-1]|[1-2][0-9]|0?[1-9])-((1[0-2]|0?[1-9])|(лютого|березня|квітня|травня|червня|липня|серпня|вересня|жовтня|листопада|грудня))(-\\d+)?";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(inputString);
        //
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
