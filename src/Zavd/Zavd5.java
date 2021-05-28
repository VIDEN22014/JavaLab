package Zavd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zavd5 {
    /*Знайдіть у тексті комбінації слів “білий” або “чорний” “пес” або “кіт”. Підрахуйте
    кількість входжень кожного з варіантів окремо.*/
    public static void start(String fileName) {
        //Зчитування Файлу
        String inputString = MyBufferedFileReader.readFile(fileName);
        //Регекс для пошуку
        final String regex = "(білий|чорний) (пес|кіт)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(inputString);
        StringBuilder stringBuilder = new StringBuilder();
        MatchGroupCounter matchGroupCounter = new MatchGroupCounter();
        while (matcher.find()) {
            matchGroupCounter.countMatches(matcher.group(1), matcher.group(2));
        }
        stringBuilder.append("білий кіт - " + matchGroupCounter.WhiteCat + "\n");
        stringBuilder.append("білий пес - " + matchGroupCounter.WhiteDog + "\n");
        stringBuilder.append("чорний кіт - " + matchGroupCounter.BlackCat + "\n");
        stringBuilder.append("чорний пес - " + matchGroupCounter.BlackDog + "\n");
        //Запис в файл
        try {
            FileWriter fileWriter = new FileWriter("Zavd/Zavd5/out5.txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MatchGroupCounter {
    public int BlackCat = 0;
    public int BlackDog = 0;
    public int WhiteCat = 0;
    public int WhiteDog = 0;

    public void countMatches(String firstGroup, String secondGroup) {
        if (firstGroup.equals("білий")) {
            if (secondGroup.equals("кіт")) {
                WhiteCat++;
            } else {
                WhiteDog++;
            }
        } else {
            if (secondGroup.equals("кіт")) {
                BlackCat++;
            } else {
                BlackDog++;
            }
        }
    }
}
