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
	public String getTiendas() throws RemoteException {
		if (this.tiendas.isEmpty())
			return "No hay tiendas actualmente";
		return this.tiendas.toString();
	}

	@Override
	public String registrarTienda(int idTienda) throws RemoteException, TiendaRegistradaException {
		if (this.tiendas.containsKey(idTienda))
			throw new TiendaRegistradaException();
		this.tiendas.put(idTienda, 0);
		return "Tienda " + idTienda + " registrada";
	}

	@Override
	public String realizarVenta(int idTienda) throws RemoteException, RoturaStockException {
		if (!this.tiendas.containsKey(idTienda))
			return "Esta tienda no esta registrada";
		if (this.producto == 0)
			throw new RoturaStockException();
		this.producto = this.producto -1;
		int acumulado = this.tiendas.get(idTienda) + 1;
		this.tiendas.put(idTienda, acumulado);
		return "Se ha realizado una venta de un producto";
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
