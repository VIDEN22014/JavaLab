package Zavd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zavd3 {
    /*Знайдіть у текстовому файлі усі входження дати, що складається з назви місяця та
    числа, і поміняйте місяць на січень. Наприклад: 23 грудня було холодно - > 23 січня було
    холодно*/
    public static void start(String fileName) {
        //Зчитування Файлу
        String inputString = MyBufferedFileReader.readFile(fileName);

        //Регекс для пошуку місяців
        final String regex = "(3[0-1]|[1-2][0-9]|[1-9]) +(лютого|березня|квітня|травня|червня|липня|серпня|вересня|жовтня|листопада|грудня)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(inputString);
        //
        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            String str = matcher.group(0);
            str = str.replaceAll("лютого|березня|квітня|травня|червня|липня|серпня|вересня|жовтня|листопада|грудня", "січня");
            matcher.appendReplacement(stringBuilder, str);
        }
        //Запис в файл
        try {
            FileWriter fileWriter = new FileWriter("Zavd/Zavd3/out3.txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
