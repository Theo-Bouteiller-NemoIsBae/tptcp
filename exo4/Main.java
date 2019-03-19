package exo4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static int nbThreads = 3;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(nbThreads);

        try {

            ServerSocket s = new ServerSocket();
            s.bind(new InetSocketAddress("0.0.0.0", 50007));

            while (true) {
                Socket client = s.accept();
                ClientHandler clienthandler = new ClientHandler(client);
                executorService.execute(clienthandler);
            }

        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
