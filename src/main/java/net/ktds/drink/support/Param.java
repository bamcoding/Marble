package net.ktds.drink.support;

import javax.servlet.http.HttpServletRequest;

public class Param {
	
	public static String getStringParam( HttpServletRequest request, String paramName ) {
		
		return getStringParam(request, paramName, "");
		
	}
	
	public static String getStringParam( HttpServletRequest request, String paramName, String defaultValue ) {
		
		String value = request.getParameter(paramName);
		
		//paramName?´ null ?˜?Š” ê¸¸ì´ê°? 0 ?´?¼ë©? defaultValue?¸ ""ë¥? ë¦¬í„´?•œ?‹¤.
		if( value == null || value.length() == 0 ) {
			value = defaultValue;
		}
		//paramName?˜ ê°’ì´ ?ˆ?‹¤ë©? ê·? ê°’ì„ ë¦¬í„´?•œ?‹¤. 
		return value;
				
	}
	
	public static int getIntParam( HttpServletRequest request, String paramName ) {
		//defaultValue?— 0?„ ?ŒŒ?¼ë¯¸í„°ë¡? ?„£?–´ ë¦¬í„´?•œ?‹¤. 
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
