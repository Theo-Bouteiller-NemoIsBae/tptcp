package exo3;

import java.io.*;
import java.net.Socket;

public class ThreadServerEcho extends Thread {
    Socket s;

    public ThreadServerEcho(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println("Client connecté " + s.getInetAddress().getHostAddress() + ':' + s.getPort());

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            while (true) {
                String line = br.readLine().toUpperCase();
                System.out.println(line);

                if (line == "QUIT")
                    break;

                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            s.close();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }
}
