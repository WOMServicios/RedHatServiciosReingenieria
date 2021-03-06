package cl.wom.exception.services;

/**
 * Esta clase maneja los errores y devuelve codigos customizados
 * 
 * @author Alejandro Fernandez
 * @date 08/2018
 */

public class ServiceError extends Exception{

   private static final long serialVersionUID = 1L;
   private int codigoError;

   /**
    * Constructor de la clase que invoca al metodo getMessage()
    * @param msg Mensaje de error que es parseado para retornar el error customizado
    */
   public ServiceError(String msg) {
      super(msg);
      this.codigoError=Integer.parseInt(msg);
      getMessage();
   }
   
   /**
    * Metodo que retorna el mensaje de error customizado dependiendo del tipo
    * @return un mensaje de error
    */
   @Override
   public String getMessage(){
   String mensaje="";
	   
	   switch(codigoError){
	   case 400:
		   mensaje="bad request";
		   break;
	   case 453:
		   mensaje="No Data Found";
		   break;
	   case 452 :
		   mensaje= "userId exceeds the maximum length";
		   break;
	 
	   }
	   return mensaje;
   }
   
   /**
    * Recibe el codigo de error y lo retorna
    * @return un entero que especifica el codigo de error
    */
   public int getCodigoError() {
		 return codigoError;
   }
}