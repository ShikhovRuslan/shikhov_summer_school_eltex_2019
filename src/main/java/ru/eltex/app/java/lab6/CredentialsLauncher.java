package ru.eltex.app.java.lab6;

public class CredentialsLauncher {

    private static long CREDENTIALS_WORKING_TIME = 5000;

    public static void main(String[] args) {
        Credentials user1, user2;
        Thread threadUser1, threadUser2;

        user1 = new Credentials(1);
        user2 = new Credentials(2);

        threadUser1 = new Thread(user1);
        threadUser2 = new Thread(user2);

        threadUser1.start();
        threadUser2.start();

        try {
            Thread.sleep(CREDENTIALS_WORKING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        user1.stop();
        user2.stop();
    }

}