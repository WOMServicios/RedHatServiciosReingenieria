package cl.wom.middleware.util;

public class ValidationUtil {
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	public static boolean isLong(String cadena){
		try {
			Long.parseLong(cadena.trim());
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}