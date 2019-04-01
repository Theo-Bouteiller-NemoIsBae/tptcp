package tp5;

import Interfaces.AnnuaireInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class main {
    public static void main(String[] args) {
        try {
            String adrServeur = "10.203.9.88";
            Registry registre = LocateRegistry.getRegistry(adrServeur);

            AnnuaireInterface stub = (AnnuaireInterface) registre.lookup("annuaire");

            String email = stub.getEmail("garfield");
            String phone = stub.getPhoneNumber("garfield");

            System.out.println(email);
            System.out.println(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

