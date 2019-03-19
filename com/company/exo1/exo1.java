package com.company.exo1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class exo1 {
    String              hostname;
    int                 port;
    boolean             init = false;
    BufferedWriter      bw;
    BufferedReader      brclavier;
    BufferedReader      br;
    Socket              s;

    public exo1(String adresseIP, int port) {

        this.hostname = adresseIP;
        this.port = port;

        this.s = new Socket();

        try {
            s.connect(new InetSocketAddress(this.hostname, this.port));

            brclavier = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void lancerBW() {

        try {
            while (true)
            {
                String input = brclavier.readLine();

                bw.write(input);
                bw.newLine();
                bw.flush();

                String line = br.readLine().toUpperCase();

                System.out.println(line);

                if (line == "QUIT")
                    break;
            }

            s.close();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }
}
