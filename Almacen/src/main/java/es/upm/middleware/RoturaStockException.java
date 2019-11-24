package es.upm.middleware;

public class RoturaStockException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoturaStockException() {
        super("No hay suficiente cantidad de producto");
    }
}
