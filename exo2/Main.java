package exo2;

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

            for (int i=0; i < nbClients; ++i) {
                Socket client = s.accept();

                System.out.println("Client connecté " + client.getInetAddress().getHostAddress() + ':' + client.getPort());
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                String line = br.readLine();
                System.out.println(line);

                bw.write(line.toUpperCase());
                bw.newLine();
                bw.flush();

            }
            s.close();

        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}