package exo4;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Client connecté " + client.getInetAddress().getHostAddress() + ':' + client.getPort());

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            while (true) {
                String line = br.readLine();
                System.out.println(line);

                if (line.equalsIgnoreCase("quit"))
                    break;

                bw.write(line.toUpperCase());
                bw.newLine();
                bw.flush();
            }

            client.close();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
