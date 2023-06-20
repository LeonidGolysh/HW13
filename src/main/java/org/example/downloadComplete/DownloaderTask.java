package org.example.downloadComplete;

import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class DownloaderTask {
    public static void downloadOpenTasks(int userId) {
        try {
            String tasksUrl = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
            String tasksJson = getJsonFormUrl(tasksUrl);

            String[] tasksArray = parseJsonArray(tasksJson);
            String[] openTasksArray = filterOpenTasks(tasksArray);

            String fileName = "user-" + userId + "-open-tasks.json";
            writeJsonArrayToFile(openTasksArray, fileName);
            System.out.println("Open tasks downloaded and save to: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getJsonFormUrl(String urlString) throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()){
            byte[] responseByte = inputStream.readAllBytes();
            return new String(responseByte);
        }
    }

    private static String[] parseJsonArray(String jsonArray) {
        jsonArray = jsonArray.substring(1, jsonArray.length() - 1);
        return jsonArray.split(",");
    }

    private static String[] filterOpenTasks(String[] tasksArray) {
        return Stream.of(tasksArray)
                .filter(task -> task.contains("\"completed\": false"))
                .toArray(String[] :: new);
    }

    private static void writeJsonArrayToFile(String[] jsonArray, String fileName) throws IOException {
        String jsonArrayWithIndentation = String.join(", ", jsonArray);
        String fileContent = "[" + jsonArrayWithIndentation + "]";

        Path filePath = Path.of(fileName);
        Files.writeString(filePath, fileContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
