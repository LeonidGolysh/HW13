package org.example.commentsuser;

import static org.example.commentsuser.UserComments.*;

public class Main {
    public static void main(String[] args) {
        int userId = 1;
        int postId = 10;
        downloadComments(userId, postId);
    }
}
