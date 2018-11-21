package cl.wom.exception.services;

/**
 * Esta clase maneja los errores y devuelve codigos customizados
 * 
 * @author Alejandro Fernandez
 * @date 08/2018
 */

public class ServiceError extends Exception {

	private String codigoError;

	/**
	 * Constructor de la clase que invoca al metodo getMessage()
	 * 
	 * @param msg Mensaje de error que es parseado para retornar el error
	 *            customizado
	 */
	public ServiceError(String msg) {
		super(msg);
		this.codigoError = msg;
		getMessage();
	}

	/**
	 * Metodo que retorna el mensaje de error customizado dependiendo del tipo
	 * 
	 * @return un mensaje de error
	 */
	@Override
	public String getMessage() {
		String mensajeExeption = "";

		switch (codigoError) {
		case "400":
			mensajeExeption = "bad request";
			break;
		case "453":
			mensajeExeption = "No Data Found";
			break;
		case "452":
			mensajeExeption = "userId exceeds the maximum length";
			break;
		case "MSISDN no disponible":
			mensajeExeption = "MSISDN no disponible";
			break;

		case "Cliente ya posse un subscripcion activa":
			mensajeExeption = "Cliente ya posse un subscripcion activa";
			break;

		case "MISISDN no coincide con el responsable de pago":

			mensajeExeption = "MISISDN no coincide con el responsable de pago";
			break;

		case "No existe responsable de pago":
			mensajeExeption = "No existe responsable de pago";
			break;
			
		 case "No existe oferta carrier billing":
	            mensajeExeption ="No existe oferta carrier billing";
	            break;
	     case "455":
	            mensajeExeption = "DataBase Error SQL Exception";
	            break;

		}

		return mensajeExeption;
	}

	/**
	 * Recibe el codigo de error y lo retorna
	 * 
	 * @return un entero que especifica el codigo de error
	 */
	public String getCodigoError() {
		return codigoError;
	}
}