package org.example.test;

import java.util.List;

import static org.example.test.TestApp.getOpenTaskForUser;

public class TestEx {
    public static void main(String[] args) {
        int userId = 1;
        List<TestTaskEntity> openTasks = getOpenTaskForUser(userId);

        if(openTasks != null) {
            System.out.println("Open tasks for user ID " + userId + ":");
            for(TestTaskEntity task : openTasks) {
                System.out.println(task.getId() + ": " + task.getTitle());
            }
        } else {
            System.out.println("Failed to retrieve open tasks for User ID" + userId);
        }
    }
}