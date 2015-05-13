package com.swiftcorp.portal.mailinfo.util;

import org.json.simple.JSONObject;

public class StringUtil {

	public static String removeEscapeChar(String value)
	{
		//value = value.replaceAll("\"", "\\\"");
		return JSONObject.escape(value);				
	}
}
