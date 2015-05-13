package com.swiftcorp.portal.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.property.BasicPropertyAccessor;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;
import org.hibernate.type.CalendarDateType;
import org.hibernate.util.ReflectHelper;

public class DTOObjectReflectionUtil 
{
	public static void setValueToProperty ( Object entityObject, String propertyName, Object propertyValue,Type type )
	throws Exception
	{
		// setter for the reflection
		Setter setterMethod = null;
		// Object entityObject = mhealthDTOAccesor.getEntityClass ().newInstance
		// ();
		
		// get the setter method
		setterMethod = new BasicPropertyAccessor ().getSetter ( entityObject.getClass (), propertyName );
		System.out.println("type::"+type.toString());
		if(type.equals(java.lang.Long.TYPE))
		{
			propertyValue = Long.parseLong((String) propertyValue);
		}
		else if(type.equals(java.lang.Integer.TYPE))
		{
			propertyValue = Integer.parseInt((String) propertyValue);
		}
		else if(type.equals(java.lang.Double.TYPE))
		{
			propertyValue = Double.parseDouble((String) propertyValue);
		} 
		else if(type.equals(java.lang.Float.TYPE))
		{
			propertyValue = Double.parseDouble((String) propertyValue);
		}
		else if(type.equals(java.lang.Boolean.TYPE))
		{
			if(propertyValue.equals("true"))
			{
				propertyValue = true;
			}
			else
			{
				propertyValue = false;
			}
		}
		else if(type.toString().equals("String"))
		{
			
		}
		else if(type.toString().equals("class java.util.Calendar"))
		{
			String mysql_datetime_format = "yyyy-MM-dd";
			String mysql_time_format = "HH:mm:ss";
			String format = "";
			if((""+propertyValue).contains ( " " ))
			{
				format = mysql_datetime_format;
			}
			else
			{
				format = mysql_time_format;
			}
			propertyValue = CalendarUtils.stringToCalendarFormatterFormat(""+propertyValue,format);
		}
		System.out.println("type.toString():::::::::::::::::::::::::::"+type.toString());
		// now set the value to the entity object
		ReflectHelper.getMethod ( entityObject.getClass (), setterMethod.getMethod () ).invoke ( entityObject, propertyValue );
		
	}
	
	public static Object getValueOfProperty ( Object entityObject, String propertyName ) 	throws Exception
	{
		Object propertyValue = null;
		
		// setter for the reflection
		Getter getterMethod = null;
		
		
		// get the setter method
		getterMethod = new BasicPropertyAccessor ().getGetter (  entityObject.getClass (), propertyName );
		
		propertyValue = getterMethod.getMethod ().invoke ( entityObject );
		
		// return the value
		return propertyValue;
		
	}
	private static String getPropertyNameFromMethodName(String methodName)
	{
		String propertyName = methodName.substring(methodName.indexOf("set")+3,methodName.length());
		Character firstLetter = propertyName.charAt(0);
		String lowerCase = ""+firstLetter;
		lowerCase = lowerCase.toLowerCase();
		propertyName = propertyName.substring(1,propertyName.length());
		propertyName = lowerCase+propertyName;
		return propertyName;
		
	}
	public static Object populateDTOFromRequest(HttpServletRequest request,Object obj)
	{
		Enumeration<String> parameters = request.getAttributeNames();
		Object propertyValue = null;
		String propertyName = "";
		obj.getClass().getFields();
		Method[] properties = obj.getClass().getDeclaredMethods();
		Method theSetter = null;
		
		for(int i=0;properties!=null && i<properties.length;i++)
		{
			theSetter = properties[i];
			System.out.println("name::"+properties[i].getName()+" type::"+properties[i].getGenericReturnType());
			System.out.println("theSetter.getModifiers()::"+theSetter.getGenericReturnType().toString());
			if(theSetter.getGenericReturnType().toString().equals("void"))
			{
				propertyName = getPropertyNameFromMethodName(theSetter.getName());
				propertyValue = request.getParameter(propertyName);
				Type[] types = theSetter.getGenericParameterTypes();
				System.out.println("propertyName::"+propertyName+" propertyValue::"+propertyValue);
				if(propertyValue!=null && !propertyValue.equals("null") && propertyValue.toString().length()>0)
				{
					try 
					{
						setValueToProperty(obj, propertyName, propertyValue,types[0]);
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return obj;
	}
}
