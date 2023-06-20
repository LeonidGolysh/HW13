package org.example.commentsuser;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserComments {

    public static void downloadComments(int userId, int postId) {
        try {
            String postUrl = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";
            JSONArray postsArray = getJsonArrayFromUrl(postUrl);
            JSONObject lastPost = postsArray.getJSONObject(postsArray.length() - 1);

            int lastPostId = lastPost.getInt("id");
            String commentsUrl = "https://jsonplaceholder.typicode.com/posts/" + lastPostId + "/comments";
            JSONArray commentsArray = getJsonArrayFromUrl(commentsUrl);

            String fileName = "user-" + userId + "-post-" +  postId + "-comments.json";
            writeJsonArrayToFile(commentsArray, fileName);
            System.out.println("Comments downloaded and saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeJsonArrayToFile(JSONArray jsonArray, String fileName) throws IOException{
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(jsonArray.toString(2));
            fileWriter.close();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONArray getJsonArrayFromUrl(String urlString) throws IOException {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            StringBuilder response = new StringBuilder();
            int data;
            while ((data = inputStream.read()) != -1) {
                response.append((char) data);
            }
            return new JSONArray(response.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
