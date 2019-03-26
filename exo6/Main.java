package exo6;

public class Main {
    public static void main(String[] args) {
        ClientPOP client = new ClientPOP("139.124.187.23", 110,"garcia", "garcia");
        if (!client.printMail(1))
            System.out.println("Impossible de récupérer le mail");
    }
}
