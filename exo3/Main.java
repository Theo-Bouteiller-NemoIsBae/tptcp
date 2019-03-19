package exo3;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static int nbClients = 10;

    public static void main(String[] args) {

        try {

            ServerSocket s = new ServerSocket();
            s.bind(new InetSocketAddress("0.0.0.0", 50007));

            while (true) {
                Socket client = s.accept();
                ThreadServerEcho th = new ThreadServerEcho(client);
                th.start();
            }


        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}