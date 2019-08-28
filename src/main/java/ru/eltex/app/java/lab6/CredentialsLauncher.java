package ru.eltex.app.java.lab6;

public class CredentialsLauncher {

    private static final long CREDENTIALS_WORKING_TIME = 5000;

    public static void main(String[] args) {
        Server server = new Server();
        Credentials user1, user2;
        Thread threadUser1, threadUser2;

        user1 = new Credentials(server, 1);
        user2 = new Credentials(server, 2);

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