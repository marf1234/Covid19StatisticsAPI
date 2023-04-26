import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название страны: ");
        String country = scanner.nextLine();

        String apiUrl = "https://api.covid19api.com/dayone/country/" + country + "/status/deaths";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(apiUrl);
        HttpResponse response = client.execute(request);

        String responseJson = EntityUtils.toString(response.getEntity());
        JSONArray dataArray = new JSONArray(responseJson);

        int maxDeaths = 0;
        String maxDeathsDate = "";
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject = dataArray.getJSONObject(i);
            int deaths = dataObject.getInt("Cases");
            String date = dataObject.getString("Date").substring(0, 10);

            if (deaths > maxDeaths) {
                maxDeaths = deaths;
                maxDeathsDate = date;
            }
        }

        System.out.println("Максимальное количество умерших: " + maxDeaths);
        System.out.println("Дата: " + maxDeathsDate);

        scanner.close();
    }
}
