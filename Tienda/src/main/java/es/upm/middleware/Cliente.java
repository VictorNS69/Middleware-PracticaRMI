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
				System.out.println("\t4. Realizar pedido (10 unidades)");
				System.out.println("\t5. Salir");
				@SuppressWarnings("resource")
				Scanner optionsc = new Scanner(System.in);
				int option = optionsc.nextInt();
				switch (option) {
				case 1:
					try {
						System.out.println("Elige cantidad");
						@SuppressWarnings("resource")
						Scanner cantidadsc = new Scanner(System.in);
						int cantidad = cantidadsc.nextInt();
						if (cantidad <= 0) {
							System.out.println("Cantidad no valida");
							break;
						}
						int actual = servicio.getProducto();
						if (cantidad > actual) {
							System.out.println("No hay suficiente stock");
							System.out.println("La cantidad de producto actual es " 
									+ actual + " producto/s y has elegido " + cantidad);	
							break;
						}
						for (int i = 0; i < cantidad; i++) {
							System.out.println(servicio.realizarVenta(id));
						}
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
					System.out.println(servicio.addProducto(10));
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
			System.exit(0);
		} catch (NotBoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch (InputMismatchException e) {
			System.out.println("Opcion no valida. Saliendo.");
			System.exit(1);
		}
	}
}