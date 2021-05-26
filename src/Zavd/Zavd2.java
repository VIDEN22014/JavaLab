package Zavd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zavd2 {
    public static void start(String fileName) {
        //Зчитування Файлу
        String inputString = MyBufferedFileReader.readFile(fileName);

        //Регекс
        final String regex = "<td[^>]+>(|\\n|.)+<\\/td>";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(inputString);
        List<String> hrefList = new ArrayList<>();

        while (matcher.find()) {
            hrefList.add(matcher.group(0));
        }
        //Виклик функції для побудови Html таблиці
        HtmlTableCreator.createHtmlTable(hrefList, "Zavd/Zavd2/out2.html");
    }
}
