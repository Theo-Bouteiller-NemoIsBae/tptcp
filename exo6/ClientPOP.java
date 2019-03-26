package exo6;

import java.io.*;
import java.net.Socket;

public class ClientPOP {
    Socket s;
    BufferedReader br;
    BufferedWriter bw;
    boolean ready = false;

    public ClientPOP(String serveurPOP, int port, String user, String pass) {
        try {
            s = new Socket(serveurPOP, port);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            String received = br.readLine();
            System.out.println(received);
            if (!received.startsWith("+OK"))
                return;

            bw.write("USER " + user);
            bw.newLine();
            bw.flush();

            received = br.readLine();
            System.out.println(received);
            if (!received.startsWith("+OK"))
                return;
            if (!received.equalsIgnoreCase("+OK Password required.")) {
                ready = true;
                return;
            }

            bw.write("PASS " + pass);
            bw.newLine();
            bw.flush();

            received = br.readLine();
            System.out.println(received);
            if (!received.equalsIgnoreCase("+OK logged in."))
                return;

            ready = true;

        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public boolean printMail(int num) {
        if (!ready)
            return false;

        try {
            /*bw.write("LIST");
            bw.newLine();
            bw.flush();*/

            String response;
            /*response = br.readLine();
            System.out.println(response);*/

            try {
                /*int numberOfMails = Integer.parseUnsignedInt(response.substring(4, 5));
                if (numberOfMails < num)
                    return false;*/

                bw.write("RETR " + num);
                bw.newLine();
                bw.flush();

                while (true) {
                    response = br.readLine();
                    if (response == null)
                        break;
                    System.out.println(response);
                }
            } catch (NumberFormatException eex) {
                System.out.println(eex.getLocalizedMessage());
                return false;
            }

        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }

        return true;
    }
}
