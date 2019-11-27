package es.upm.middleware;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		Registry registro;
		int id = -1;
		try{
			System.out.println("CLIENTE");
			registro = LocateRegistry.getRegistry();
			LogisticaInterfaz servicio = (LogisticaInterfaz)registro.lookup("Saludar");

			System.out.println("Introduce el ID de tu tienda");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			boolean logged = false;
			while (!logged) {
				id = sc.nextInt();
				try {
					servicio.registrarTienda(id);
					logged = true;
				} catch (TiendaRegistradaException e) {
					System.out.println("Ese ID de tienda ya esta registrado.\nPor favor, elige otro ID");
				}
			}
			while (true) {
				System.out.println("Elige una opcion");
				System.out.println("\t1. Vender productos");
				System.out.println("\t2. Calcular ventas");
				System.out.println("\t3. Calcular ingresos");
				System.out.println("\t4. Realizar pedido");
				System.out.println("\t5. Salir");
				@SuppressWarnings("resource")
				Scanner optionsc = new Scanner(System.in);
				int option = optionsc.nextInt();
				switch (option) {
				case 1:
					try {
						System.out.println(servicio.realizarVenta(id));
	
						} catch (RoturaStockException e) {
							System.out.println("Parece que no hay suficiente stock. "
									+ "\nIntentalo mas tarde");
						}

					
					break;
				case 2:
					System.out.println(servicio.totalizarVentas(id));
					break;
				case 3:
					System.out.println(servicio.totalizarIngresos(id));
					break;
				case 4:
					// TODO: no sé si es esto lo que se pide
					try {	
						for(int i = 0; i<10;i++){
							System.out.println(servicio.realizarVenta(id));
						}
						
					} catch (RoturaStockException e) {
						System.out.println("Parece que no hay suficiente stock. "
								+ "\nIntentalo mas tarde");
					}
					break;
				case 5:
					System.out.println("Saliendo");
					System.exit(1);
					break;
				default:
					System.out.println("Opcion no valida");
					break;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
