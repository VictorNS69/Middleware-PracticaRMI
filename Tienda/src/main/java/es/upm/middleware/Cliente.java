package es.upm.middleware;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

    public static void main(String[] args) throws RoturaStockException, TiendaRegistradaException {
        Registry registro;
        try{
            registro = LocateRegistry.getRegistry();
            LogisticaInterfaz servicio = (LogisticaInterfaz)registro.lookup("Saludar");
            //System.out.println(servicio.registrarTienda(12));
            System.out.println(servicio.realizarVenta(12,5));
            System.out.println(servicio.totalizarVentas(12));
            System.out.println(servicio.totalizarVentas(1));
            System.out.println(servicio.totalizarIngresos(12));
            System.out.println(servicio.totalizarIngresos(1));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
