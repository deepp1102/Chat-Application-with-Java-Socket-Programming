import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Ask username
            out.print("Enter your username:");
            username = in.readLine();

            System.out.println(username + " joined the chat");
            Server.broadcast(username + " joined the chat", this);

            String message;
            while ((message = in.readLine()) != null) {

                // Broadcast to all
                System.out.println(username + ": " + message);
                if (message.startsWith("@")) {
    // Private message
    int space = message.indexOf(" ");
    if (space != -1) {
        String targetUser = message.substring(1, space);
        String privateMsg = message.substring(space + 1);

        boolean found = false;
        synchronized (Server.clients) {
            for (ClientHandler client : Server.clients) {
                if (client.username.equals(targetUser)) {
                    client.sendMessage("(Private) " + username + ": " + privateMsg);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            out.println("User not found: " + targetUser);
        }
    }
} else {
    Server.broadcast(username + ": " + message, this);
}
            }

        } catch (IOException e) {
            System.out.println(username + " disconnected");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}

            Server.removeClient(this);
            Server.broadcast(username + " left the chat", this);
        }
    }
}