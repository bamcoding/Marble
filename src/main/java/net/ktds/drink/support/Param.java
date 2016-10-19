package net.ktds.drink.support;

import javax.servlet.http.HttpServletRequest;

public class Param {
	
	public static String getStringParam( HttpServletRequest request, String paramName ) {
		
		return getStringParam(request, paramName, "");
		
	}
	
	public static String getStringParam( HttpServletRequest request, String paramName, String defaultValue ) {
		
		String value = request.getParameter(paramName);
		
		if( value == null || value.length() == 0 ) {
			value = defaultValue;
		}
		return value;
				
	}
	
	public static int getIntParam( HttpServletRequest request, String paramName ) {
		return getIntParam(request, paramName, 0);
	}
	
	public static int getIntParam( HttpServletRequest request, String paramName, int defaultValue ) {
		
		String value = getStringParam(request, paramName);
		
		try {
			int intValue = Integer.parseInt(value);
			return intValue;
		} catch (NumberFormatException nfe) {
			return defaultValue;
		}
		
		
	}

}
