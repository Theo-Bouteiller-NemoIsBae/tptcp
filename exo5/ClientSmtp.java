package exo5;

import java.io.*;
import java.net.Socket;

public class ClientSmtp {
    Socket s;
    BufferedWriter bw;
    BufferedReader br;

    public ClientSmtp(String serveurSmtp, int port, String hostname) {
        try {
            s = new Socket(serveurSmtp, port);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            System.out.println(br.readLine());
            bw.write("EHLO " + hostname);
            bw.newLine();
            bw.flush();

            for (int i=0; i < 9; ++i)
                System.out.println(br.readLine());

        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public boolean sendMail(String from, String to, String subject, String body) {
        try {

            String received = new String();

            bw.write("MAIL FROM: " + from);
            bw.newLine();
            bw.flush();

            received = br.readLine();
            System.out.println(received);
            if (!received.equals("250 2.1.0 Ok"))
                return false;


            bw.write("RCPT TO: " + to);
            bw.newLine();
            bw.flush();

            received = br.readLine();
            System.out.println(received);
            if (!received.equals("250 2.1.5 Ok"))
                return false;

            bw.write("DATA");
            bw.newLine();
            bw.flush();

            received = br.readLine();
            System.out.println(received);
            if (!received.startsWith("354"))
                return false;

            bw.write("FROM: " + from);
            bw.newLine();
            bw.write("TO: " + to);
            bw.newLine();
            bw.write("SUBJECT: " + subject);
            bw.newLine();
            bw.write(body);
            bw.newLine();
            bw.write(".");
            bw.newLine();
            bw.flush();

            received = br.readLine();
            System.out.println(received);
            if (!received.startsWith("250 2.0.0 Ok"))
                return false;

            bw.write("QUIT");
            bw.newLine();
            bw.flush();

            received = br.readLine();
            System.out.println(received);
            if (!received.equals("221 2.0.0 Bye"))
                return false;

            return true;
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        return false;
    }
}
