package exo5;

public class Main {
    public static void main(String[] args) {
        ClientSmtp clientSmtp = new ClientSmtp("139.124.187.23", 25,"pluton.aix.univ-amu.fr");
        clientSmtp.sendMail("garcia", "garcia", "bonjour", "au revoir");
    }
}
