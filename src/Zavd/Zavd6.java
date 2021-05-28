package Zavd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zavd6 {
    /*Напишіть регулярний вираз для валідації імені та прізвища, де кожне слово повинно
    починатися з великої літери, між словами — пробіл, слова можуть містити тільки літери. Не
    можуть змішуватися латинські та кириличні літери.*/
    public static void start(String fileName) {
        //Зчитування Файлу
        String inputString = MyBufferedFileReader.readFile(fileName);
        //Регекс для пошуку
        final String regex = "(^[А-ЯІЇЄ][а-яіїє]* [А-ЯІЇЄ][а-яіїє]*)|(^[A-Z][a-z]* [A-Z][a-z]*)";
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
            FileWriter fileWriter = new FileWriter("Zavd/Zavd6/out6.txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
