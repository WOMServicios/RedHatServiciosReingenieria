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
	                mensaje="Error, 400 prueba";
	                break;
	            case 500:
	                mensaje="Error, 500 prueba";
	                break;
	            case 503:
	                mensaje="Error, prueba";
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
