import java.util.Scanner;

public class MyTrack {
    public static void trackCompare() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введіть назви треків для порівняння : ");
        SQLCommand.fillTempTable(in.nextLine());
        SQLCommand.innerJoin(in.nextLine());
    }

    public static String recursionConvert(double longitude, double latitude, int depth) {
        StringBuilder temp = new StringBuilder("0");
        temp = recursionConvert(longitude, latitude, depth, 0, temp, -180, 90);
        return temp.toString();
    }

    public static StringBuilder recursionConvert(double longitude, double latitude, int maxDepth, int depth, StringBuilder previous, double borderX, double borderY) {
        if (depth == maxDepth) {
            return previous;
        }
        if (longitude >= borderX && longitude <= borderX + 180.0 / Math.pow(2, depth) && latitude <= borderY && latitude >= borderY - 90.0 / Math.pow(2, depth)) {
            previous.append('1');
            previous = recursionConvert(longitude, latitude, maxDepth, depth + 1, previous, borderX, borderY);
        } else if (longitude >= borderX + 180.0 / Math.pow(2, depth) && longitude <= borderX + 2 * 180.0 / Math.pow(2, depth) && latitude <= borderY && latitude >= borderY - 90.0 / Math.pow(2, depth)) {
            previous.append('2');
            previous = recursionConvert(longitude, latitude, maxDepth, depth + 1, previous, borderX + 180 / Math.pow(2, depth), borderY);
        } else if (longitude >= borderX && longitude <= borderX + 180.0 / Math.pow(2, depth) && latitude - 90.0 / Math.pow(2, depth) <= borderY && latitude >= borderY - 2 * 90.0 / Math.pow(2, depth)) {
            previous.append('3');
            previous = recursionConvert(longitude, latitude, maxDepth, depth + 1, previous, borderX, borderY - 90 / Math.pow(2, depth));
        } else if (longitude >= borderX + 180.0 / Math.pow(2, depth) && longitude <= borderX + 2 * 180.0 / Math.pow(2, depth) && latitude - 90.0 / Math.pow(2, depth) <= borderY && latitude >= borderY - 2 * 90.0 / Math.pow(2, depth)) {
            previous.append('4');
            previous = recursionConvert(longitude, latitude, maxDepth, depth + 1, previous, borderX + 180 / Math.pow(2, depth), borderY - 90 / Math.pow(2, depth));
        }
        return previous;
    }
}
