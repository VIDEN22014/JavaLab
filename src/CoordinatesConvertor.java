public class CoordinatesConvertor {
    public static String recursionConvert(double latitude, double longitude, int depth) {
        StringBuilder temp = new StringBuilder("0");
        temp = recursionConvert(latitude, longitude, depth, 0, temp, -180, 90);
        return temp.toString();
    }

    public static StringBuilder recursionConvert(double latitude, double longitude, int maxDepth, int depth, StringBuilder previous, double borderX, double borderY) {
        if (depth == maxDepth) {
            return previous;
        }
        if (latitude >= borderX && latitude <= borderX + 180.0 / Math.pow(2, depth) && longitude <= borderY && longitude >= borderY - 90.0 / Math.pow(2, depth)) {
            previous.append('1');
            previous = recursionConvert(latitude, longitude, maxDepth, depth + 1, previous, borderX, borderY);
        } else if (latitude >= borderX + 180.0 / Math.pow(2, depth) && latitude <= borderX + 2 * 180.0 / Math.pow(2, depth) && longitude <= borderY && longitude >= borderY - 90.0 / Math.pow(2, depth)) {
            previous.append('2');
            previous = recursionConvert(latitude, longitude, maxDepth, depth + 1, previous, borderX + 180 / Math.pow(2, depth), borderY);
        } else if (latitude >= borderX && latitude <= borderX + 180.0 / Math.pow(2, depth) && longitude - 90.0 / Math.pow(2, depth) <= borderY && longitude >= borderY - 2 * 90.0 / Math.pow(2, depth)) {
            previous.append('3');
            previous = recursionConvert(latitude, longitude, maxDepth, depth + 1, previous, borderX, borderY - 90 / Math.pow(2, depth));
        } else if (latitude >= borderX + 180.0 / Math.pow(2, depth) && latitude <= borderX + 2 * 180.0 / Math.pow(2, depth) && longitude - 90.0 / Math.pow(2, depth) <= borderY && longitude >= borderY - 2 * 90.0 / Math.pow(2, depth)) {
            previous.append('4');
            previous = recursionConvert(latitude, longitude, maxDepth, depth + 1, previous, borderX + 180 / Math.pow(2, depth), borderY - 90 / Math.pow(2, depth));
        }
        return previous;
    }
}
