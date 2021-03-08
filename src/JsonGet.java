
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonGet {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://api.openweathermap.org/data/2.5/forecast?lat=48.9215&lon=24.7097&APPID=f75c35058cf73360f1ae084bd6097aa5";

    public static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            FileWriter file = new FileWriter("C://Users/dxbog/IdeaProjects/JavaLab/src/json.json");
            file.write(response.toString());
            file.flush();
            file.close();
        } else {
            System.out.println("GET request not worked");
        }

    }
}