package Zavd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zavd2 {
    /*Знайдіть у телефонному довідникові номери телефонів та імена контактних осіб.
    1_Виведіть у таблицю знайдені імена та телефони.
    2_Виведіть імена та телефони тільки факультету математики та інформатики.*/
    public static void start(String fileName) {
        //Зчитування Файлу
        String inputString = MyBufferedFileReader.readFile(fileName);

        //Регекс для першої частини
        final String regex1 = "<br />\\s*\\n(.*)<br />\\s*\\n(.*)</td>\\s*\\n<td.*>(.*)</td>";
        final String regex2 = "<br />\\s*\\n(.*)<br />\\s*\\n(.*)</td>\\s*\\n<td.*width: 26.2136%(.*)</td>";
        final Pattern pattern1 = Pattern.compile(regex1, Pattern.MULTILINE);
        final Pattern pattern2 = Pattern.compile(regex2, Pattern.MULTILINE);
        final Matcher matcher1 = pattern1.matcher(inputString);
        final Matcher matcher2 = pattern2.matcher(inputString);
        //
        List<Person> personList = new ArrayList<>();
        String tempName;
        Person tempPerson;

        while (matcher1.find()) {
            tempName = matcher1.group(1).charAt(0) + matcher1.group(1).toLowerCase(Locale.ROOT).substring(1) + " " + matcher1.group(2);
            tempPerson = new Person(tempName, matcher1.group(3));
            personList.add(tempPerson);
        }
        //Виклик функції для побудови Html таблиці
        HtmlTableCreator.createHtmlTable(personList, "Zavd/Zavd2/out2_1.html");
        personList.clear();

        while (matcher2.find()) {
            tempName = matcher2.group(1).charAt(0) + matcher2.group(1).toLowerCase(Locale.ROOT).substring(1) + " " + matcher2.group(2);
            tempPerson = new Person(tempName, matcher2.group(3));
            personList.add(tempPerson);
        }
        //Виклик функції для побудови Html таблиці
        HtmlTableCreator.createHtmlTable(personList, "Zavd/Zavd2/out2_2.html");
        personList.clear();
    }
}
