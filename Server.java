import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    // Thread-safe set of all connected clients
    static Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        int port = 6000;
        System.out.println("Chat Server started on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);

                new Thread(handler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast message to all clients
    public static void broadcast(String message, ClientHandler excludeUser) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != excludeUser) {
                    client.sendMessage(message);
                }
            }
        }
    }

    // Remove client when disconnected
    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}