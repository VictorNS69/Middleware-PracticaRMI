package es.upm.middleware;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Servidor {

	public static void main(String[] args) throws NotBoundException {
		Registry registro;
		try{
			System.out.println("SERVIDOR");
			registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			registro.rebind("Saludar", new LogisticaImpl());

			registro = LocateRegistry.getRegistry();
			LogisticaInterfaz servicio = (LogisticaInterfaz)registro.lookup("Saludar");
			while (true) {
				System.out.println("Elige una opción:");
				System.out.println("\t1. Añadir producto");
				System.out.println("\t2. Ver producto actual");
				System.out.println("\t3. Ver tiendas actuales");
				System.out.println("\t4. Salir");
				@SuppressWarnings("resource")
				Scanner optionsc = new Scanner(System.in);
				int option = optionsc.nextInt();
				switch (option) {
				case 1:
					System.out.println("Introduce la cantidad de producto a añadir:");
					Scanner sc = new Scanner(System.in);
					int producto = sc.nextInt();
					if (producto > 0)
						System.out.println(servicio.addProducto(producto));
					else
						System.out.println("Cantidad no valida");	
					break;
				case 2: 
					System.out.println(servicio.addProducto(0));
					break;
				case 3:
					System.out.println(servicio.getTiendas());
					break;
				case 4:
					System.out.println("Saliendo");
					System.exit(1);
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}

			}
		} catch (RemoteException e){
			e.printStackTrace();
		}
		catch (InputMismatchException e) {
			System.out.println("Opcion no valida. Saliendo.");
			System.exit(1);
		}
	}
}
