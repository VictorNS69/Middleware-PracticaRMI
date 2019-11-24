package es.upm.middleware;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class LogisticaImpl extends UnicastRemoteObject
                         implements LogisticaInterfaz, Serializable {

	private static final long serialVersionUID = 1L;
	
	private final int precio = 10;
	private int producto;

	// K = id tienda, V = cantidad producto
	private HashMap <Integer, Integer> tiendas;
	
	protected LogisticaImpl() throws RemoteException {
		super();
		this.tiendas = new HashMap<Integer, Integer>();
		this.producto = 0;
	}

	@Override
	public String addProducto(int producto) throws RemoteException {
		this.producto = this.producto + producto;
		return "El producto total actual es: " + this.producto;
	}
	
	@Override
	public String registrarTienda(int idTienda) throws RemoteException, TiendaRegistradaException {
		if (this.tiendas.containsKey(idTienda))
			throw new TiendaRegistradaException();
		this.tiendas.put(idTienda, 0);
		return "Tienda " + idTienda + " registrada\nMap: " + this.tiendas.toString();
	}

	@Override
	public String realizarVenta(int idTienda, int cantidad) throws RemoteException, RoturaStockException {
		if (!this.tiendas.containsKey(idTienda))
			return "Esta tienda no esta registrada";
		if (this.producto-cantidad < 0)
			throw new RoturaStockException();
		this.producto = this.producto - cantidad;
		int acumulado = this.tiendas.get(idTienda) + cantidad;
		this.tiendas.put(idTienda, acumulado);
		return "Se ha realizado una venta de " + cantidad + " producto/s\nMap: " + this.tiendas.toString() + "\nproducto total: " +this.producto;
	}

	@Override
	public int totalizarVentas(int idTienda) throws RemoteException {
		if (!this.tiendas.containsKey(idTienda))
			return 0;
		return this.tiendas.get(idTienda);		
	}

	@Override
	public int totalizarIngresos(int idTienda) throws RemoteException {
		if (!this.tiendas.containsKey(idTienda))
			return 0;
		return this.tiendas.get(idTienda) * this.precio;
	}
}
