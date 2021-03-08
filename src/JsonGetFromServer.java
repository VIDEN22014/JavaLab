
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonGetFromServer {

    private static final String USER_AGENT = "Mozilla/5.0";



    public static boolean sendGET(String cityName) throws IOException {
        String GET_URL = "http://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&APPID=f75c35058cf73360f1ae084bd6097aa5";
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            FileWriter file = new FileWriter("C://Users/dxbog/IdeaProjects/JavaLab/src/Forecast.json");
            file.write(response.toString());
            file.flush();
            file.close();
            return true;
        } else {
            return false;
        }

    }
}