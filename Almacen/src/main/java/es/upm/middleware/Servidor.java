package es.upm.middleware;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) {
        Registry registro;
        try{
            registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registro.rebind("Saludar", new SaludarImpl());
System.out.println("El servidor hace algo");
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
