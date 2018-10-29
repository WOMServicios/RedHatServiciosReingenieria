package cl.wom.exception.services;

/**
 * Esta clase maneja los errores y devuelve codigos customizados
 * 
 * @author Alejandro Fernandez
 * @date 08/2018
 */

public class ServiceError extends Exception {

	private String mensaje;

	/**
	 * Constructor de la clase que invoca al metodo getMessage()
	 * 
	 * @param msg Mensaje de error que es parseado para retornar el error
	 *            customizado
	 */
	public ServiceError(String msg) {
		super(msg);
		this.mensaje = (msg);
		getMessage();
	}

	/**
	 * Metodo que retorna el mensaje de error customizado dependiendo del tipo
	 * 
	 * @return un mensaje de error
	 */
	@Override
	public String getMessage() {
		
		String mensajeExcetion="";
		
		switch (mensaje) {
		case "ALREADY_REFUNDED"	:
			mensajeExcetion = "Already Refunded";
			break;
		case "USER_NOT_ENABLED"	:
			mensajeExcetion = "The user is not eligible";
			break;
		case "USER_NOT_FUND"	:
			mensajeExcetion = "The user credential cannot be found";
			break;
			
		case "USER_SUSPENDED"	:
			mensajeExcetion = "the user is suspended from using the service (permanent block)";
			break;
		
		case "RETRIABLE_ERROR"	:
			mensajeExcetion = "API errored but request can be re-tried";
			break;
			
		case "454"	:
			mensajeExcetion = "Invalid data type";
			break;
			
		 case "452" :
			 mensajeExcetion= "userId exceeds the maximum length";
			   break;
			
		}
		return  mensajeExcetion;

	}
	
	 public String getCodigoError() {
		 return this.mensaje;
   }
	
}