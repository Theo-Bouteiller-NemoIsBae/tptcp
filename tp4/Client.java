package tp4;

import java.io.IOException;
import java.net.Socket;

public class Client {
    String serveur = "";
    int port = 0;
    public Client(String serveur, int port){
        this.serveur = serveur;
        this.port = port;
        try {
            Socket client = new Socket(this.serveur, this.port);
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
