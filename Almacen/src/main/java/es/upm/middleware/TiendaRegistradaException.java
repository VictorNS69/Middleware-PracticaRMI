package es.upm.middleware;

public class TiendaRegistradaException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TiendaRegistradaException() {
        super("Esta tienda ya ha sido registrada");
    }
}
