package es.upm.middleware;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LogisticaInterfaz extends Remote {
	/** Añade la cantidad de producto al inventario
	 * @param producto: cantidad a añadir
	 * @return: Devuelve el producto total actual
	 * @throws RemoteException
	 */
	public String addProducto(int producto) throws RemoteException;
	
	/** Se registra una tienda
	 * @param id: id de la tienda
	 * @return: mensaje de OK o de error
	 * @throws RemoteException, TiendaRegistradaException
	 */
    public String registrarTienda(int id) throws RemoteException, TiendaRegistradaException;
    
    /** Se realiza la venta de "cantidad" de producto. Esta venta la hace "idTienda"
     * @param idTienda: id de la tienda
     * @param cantidad: cantidad de producto
     * @return: mensaje de OK o de error
     * @throws RemoteException, RoturaStock
     */
    public String realizarVenta(int idTienda, int cantidad) throws RemoteException, RoturaStockException;
    
    /** Se devuelve el total de ventas de producto realizado
     * @param idTienda: id e la tienda
     * @return: valor de producto vendido
     * @throws RemoteException
     */
    public int totalizarVentas(int idTienda) throws RemoteException;
    
    /** Devuelve el total de ingresos obtenidos por una tienda
     * @param idTienda: id de la tienda
     * @return: valor de ingreso obtenido
     * @throws RemoteException
     */
    public int totalizarIngresos(int idTienda) throws RemoteException;
}