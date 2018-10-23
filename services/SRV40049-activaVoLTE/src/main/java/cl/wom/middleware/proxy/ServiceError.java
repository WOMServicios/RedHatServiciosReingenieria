package cl.wom.middleware.proxy;

public class ServiceError extends Exception{

	private static final long serialVersionUID = 1L;

	 private int codigoError;

	public ServiceError(String msg) {
	
	    super(msg);
		this.codigoError=Integer.parseInt(msg);
		getMessage();
	}
	
	 @Override
	    public String getMessage(){
	         
	        String mensaje="";
	         
	        switch(codigoError){
	 	   case 400:
			   mensaje="bad request";
			   break;
		   case 452:
			   mensaje="exceeds the maximum length";
			   break;
		   case 422:
			   mensaje="XML format invalid";
			   break;
		   case 453:
			   mensaje="No Data Found";
			   break;
	       default:
	           mensaje="Error, sin codigo de error reconocido";
	           break;
	        }
	         
	        return mensaje;
	         
	    }
	 
	 public int getCodigoError() {
		 return codigoError;
	 }
	 
	
}
