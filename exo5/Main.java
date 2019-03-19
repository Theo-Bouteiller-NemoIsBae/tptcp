package exo5;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Socket socket = new Socket();

        socket.connect(new InetSocketAddress("10.203.9.145", 13));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.in));


    }
}
