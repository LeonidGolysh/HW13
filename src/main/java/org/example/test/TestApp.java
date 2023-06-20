package org.example.test;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestApp {
    private static final String BASE_URL = "ttps://jsonplaceholder.typicode.com";
    private static final Gson gson = new Gson();
    private static final HttpClient httpClient = HttpClients.createDefault();

    public static List<TestTaskEntity> getOpenTaskForUser(int userid) {
        String url = BASE_URL + "/users/" + userid + "/todos?completed=false";

        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity());
                TestTaskEntity[] tasks = gson.fromJson(responseBody, TestTaskEntity[].class);
                return Arrays.asList(tasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
