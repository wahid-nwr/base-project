<%@ page import="java.util.Set"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.io.*" %>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.common.ApplicationConstants"%>
<%@ page import="com.swiftcorp.portal.common.dto.FunctionDTO"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.module.dto.ModuleDTO"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO"%>
<%@ page import="com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO"%>
<%@ page import="com.swiftcorp.portal.module.service.IModuleService"%>
<%@ page import="com.swiftcorp.portal.module.service.ModuleServiceImpl"%>
<%!
String getFunctionItem(int functionId,String children)
{
	if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_ROLE_FUNCTION)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Role Functions\",\"id\":\"rolePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Role Functions\",\"id\":\"rolePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_QUESTION)
	{
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_QUESTIONNAIRE)
	{
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_BENEFICIARY)
	{
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_ALERTS)
	{
	}	
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_WORK_SCHEDULE)
	{
	}	
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_HOUSEHOLD)
	{
	}	
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_REPORT_MOTHER)
	{
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_REPORT_CHILD)
	{
	}	
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_REPORT_MPR)
	{
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_ALGORITHM)
	{
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_GEOGRAPHICAL_INFO)
	{
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_GEOGRAPHICAL_INFO_VIEW)
	{
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_DCR_INFO)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"DCR Info\",\"id\":\"dcrinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"DCR Info\",\"id\":\"dcrinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_DCR_REPORT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"DCR Report\",\"id\":\"dcrReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"DCR Report\",\"id\":\"dcrReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}		
	
	if(functionId == ApplicationConstants.ROLE_FUNCTION_ACCOUNT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Account\",\"id\":\"accountPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Account\",\"id\":\"accountPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}		
	if(functionId == ApplicationConstants.ROLE_FUNCTION_DCR_REPORT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Patient\",\"id\":\"patientPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Patient\",\"id\":\"patientPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}		
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_USERS)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"User\",\"id\":\"userPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"User\",\"id\":\"userPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	
	if(functionId == ApplicationConstants.ROLE_FUNCTION_DCR_REPORT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Hello\",\"id\":\"helloPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Hello\",\"id\":\"helloPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}		
	if(functionId == ApplicationConstants.ROLE_FUNCTION_DCR_REPORT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Home\",\"id\":\"homePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Home\",\"id\":\"homePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	if(functionId == ApplicationConstants.ROLE_FUNCTION_DCR_REPORT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Info\",\"id\":\"infoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Info\",\"id\":\"infoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	if(functionId == ApplicationConstants.ROLE_FUNCTION_ITEM)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Item\",\"id\":\"itemPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Item\",\"id\":\"itemPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	if(functionId == ApplicationConstants.ROLE_FUNCTION_BATCH)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Batch\",\"id\":\"batchPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Batch\",\"id\":\"batchPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}		
	if(functionId == ApplicationConstants.ROLE_FUNCTION_TRANSACTION)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Purchase Voucher List\",\"id\":\"purchaseTransactionPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Purchase Voucher List\",\"id\":\"purchaseTransactionPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	if(functionId == ApplicationConstants.ROLE_FUNCTION_TRANSACTION)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Sales Voucher List\",\"id\":\"salesTransactionPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Sales Voucher List\",\"id\":\"salesTransactionPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	//else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_SCHEDULE)
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_SCHEDULE)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Travelschedule\",\"id\":\"travelschedulePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Travelschedule\",\"id\":\"travelschedulePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	//else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_VEHICLE)
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_VEHICLE)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Vehicle\",\"id\":\"vehiclePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Vehicle\",\"id\":\"vehiclePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	//else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_ROUTE)
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_ROUTE)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Route\",\"id\":\"routePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Route\",\"id\":\"routePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	//else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_BUYER)
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_BUYER)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Buyer\",\"id\":\"buyerPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Buyer\",\"id\":\"buyerPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	//else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_RESERVATION)
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_RESERVATION)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Reservation\",\"id\":\"reservationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Reservation\",\"id\":\"reservationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_TRIP)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Trip\",\"id\":\"tripPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Trip\",\"id\":\"tripPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_RESERVATION)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Reservation Update\",\"id\":\"reservationUpdatePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Reservation Update\",\"id\":\"reservationUpdatePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_RESERVATION)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Ticket Issue\",\"id\":\"seatBookingPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Ticket Issue\",\"id\":\"seatBookingPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	
	if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_RESERVATION)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Sale Report\",\"id\":\"saleReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Sale Report\",\"id\":\"saleReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	//else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRAVEL_TRIP)
	
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_STATION)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Station\",\"id\":\"stationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Station\",\"id\":\"stationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_STORE)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Store\",\"id\":\"storePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Store\",\"id\":\"storePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_COVERING)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Covering\",\"id\":\"coveringPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Covering\",\"id\":\"coveringPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRANSPORT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Transportation\",\"id\":\"transportationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Transportation\",\"id\":\"transportationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_PAYMENT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Payment\",\"id\":\"paymentPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Payment\",\"id\":\"paymentPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_CREDITTERM)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Creditterm\",\"id\":\"credittermPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Creditterm\",\"id\":\"credittermPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_UNIT)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Unit\",\"id\":\"unitPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Unit\",\"id\":\"unitPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_PRODSTEP)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Productionstep\",\"id\":\"productionstepPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Productionstep\",\"id\":\"productionstepPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_CURRENCY)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Currency\",\"id\":\"currencyPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Currency\",\"id\":\"currencyPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_BANK)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Bank\",\"id\":\"bankPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Bank\",\"id\":\"bankPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_CITY)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"City\",\"id\":\"cityPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"City\",\"id\":\"cityPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_COUNTRY)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Country\",\"id\":\"countryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Country\",\"id\":\"countryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
	else if(functionId == ApplicationConstants.ROLE_FUNCTION_MODULE)
	{
		if(children!=null && !children.equals("null") && children.length()>0)
		{
			children += ",{\"text\":\"Module\",\"id\":\"modulePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
		else
		{
			children = "{\"text\":\"Module\",\"id\":\"modulePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
		}
	}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_HSCODE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Hscode\",\"id\":\"hscodePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Hscode\",\"id\":\"hscodePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_MATERIALTYPE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Materialtype\",\"id\":\"materialtypePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Materialtype\",\"id\":\"materialtypePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_MATERIALCATEGORY)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Materialcategory\",\"id\":\"materialcategoryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Materialcategory\",\"id\":\"materialcategoryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_WAREHOUSE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Warehouse\",\"id\":\"warehousePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Warehouse\",\"id\":\"warehousePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_PRODUCTIONSECTION)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Productionsection\",\"id\":\"productionsectionPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Productionsection\",\"id\":\"productionsectionPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_INKTYPE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Inktype\",\"id\":\"inktypePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Inktype\",\"id\":\"inktypePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_MACHINE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Machine\",\"id\":\"machinePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Machine\",\"id\":\"machinePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_MACHINETYPE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Machinetype\",\"id\":\"machinetypePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Machinetype\",\"id\":\"machinetypePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_BRAND)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Brand\",\"id\":\"brandPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Brand\",\"id\":\"brandPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_LEAVEMGNT)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Leavemanagement\",\"id\":\"leavemanagementPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Leavemanagement\",\"id\":\"leavemanagementPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_SALARY)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Salary\",\"id\":\"salaryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Salary\",\"id\":\"salaryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_ATTENDENCE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Attendence\",\"id\":\"attendencePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Attendence\",\"id\":\"attendencePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_DEPARTMENT)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Department\",\"id\":\"departmentPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Department\",\"id\":\"departmentPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_DESIGNATION)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Designation\",\"id\":\"designationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Designation\",\"id\":\"designationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_LEAVEPACKAGE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Leavepackage\",\"id\":\"leavepackagePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Leavepackage\",\"id\":\"leavepackagePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_HOLIDAY)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Holiday\",\"id\":\"holidayPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Holiday\",\"id\":\"holidayPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMPLOYEE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Employee\",\"id\":\"employeePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Employee\",\"id\":\"employeePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMPLOYEEINFO)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Employeepersonalinfo\",\"id\":\"employeepersonalinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Employeepersonalinfo\",\"id\":\"employeepersonalinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_ACADEMICINFO)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Academicinfo\",\"id\":\"academicinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Academicinfo\",\"id\":\"academicinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_CAREERHISTORY)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Careerhistory\",\"id\":\"careerhistoryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Careerhistory\",\"id\":\"careerhistoryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_SUPPLIER)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Supplier\",\"id\":\"supplierPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Supplier\",\"id\":\"supplierPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_SUPPLIERBANKINFO)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Supplierbankinfo\",\"id\":\"supplierbankinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Supplierbankinfo\",\"id\":\"supplierbankinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_INVENTORYIN)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Inventoryin\",\"id\":\"inventoryinPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Inventoryin\",\"id\":\"inventoryinPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_INVENTORYOUT)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Inventoryout\",\"id\":\"inventoryoutPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Inventoryout\",\"id\":\"inventoryoutPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_MAILBOX)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Customer Report\",\"id\":\"reportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Customer Report\",\"id\":\"reportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMP_REPORT)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Employee Report\",\"id\":\"empReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Employee Report\",\"id\":\"empReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_GENERAL_SETUP_REPORT)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"General Setup Report\",\"id\":\"generalSetupReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"General Setup Report\",\"id\":\"generalSetupReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_SALE_REPORT)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Sale Report\",\"id\":\"sapSaleReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Sale Report\",\"id\":\"sapSaleReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_CUSTOMER_ORDER_REPORT)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Customer Order Report\",\"id\":\"customerOrderReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Customer Order Report\",\"id\":\"customerOrderReportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_MAILBOX)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Report\",\"id\":\"reportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Report\",\"id\":\"reportPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
	
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMAIL)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Email\",\"id\":\"emailPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Email\",\"id\":\"emailPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMAILDTL)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Emaildtl\",\"id\":\"emaildtlPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Emaildtl\",\"id\":\"emaildtlPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMAILGROUP)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Emailgroup\",\"id\":\"emailgroupPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Emailgroup\",\"id\":\"emailgroupPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMAILREF)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Emailreferance\",\"id\":\"emailreferancePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Emailreferance\",\"id\":\"emailreferancePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMAILRECIPIENT)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Emailrecipients\",\"id\":\"emailrecipientsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Emailrecipients\",\"id\":\"emailrecipientsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EMAILRECV)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Emailrecv\",\"id\":\"emailrecvPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Emailrecv\",\"id\":\"emailrecvPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_CUSTOMERINFO)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Customerinfo\",\"id\":\"customerinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Customerinfo\",\"id\":\"customerinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_SHIPPINGINFO)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Shippinginfo\",\"id\":\"shippinginfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Shippinginfo\",\"id\":\"shippinginfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_BANKINFO)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Bankinfo\",\"id\":\"bankinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Bankinfo\",\"id\":\"bankinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_INQUIRY)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Inquiry\",\"id\":\"inquiryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Inquiry\",\"id\":\"inquiryPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_ITEMMASTER)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Itemmaster\",\"id\":\"itemmasterPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Itemmaster\",\"id\":\"itemmasterPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_MATERIALS)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Materials\",\"id\":\"materialsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Materials\",\"id\":\"materialsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_PRICING)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Pricing\",\"id\":\"pricingPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Pricing\",\"id\":\"pricingPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_PAYMENT_COLL)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Paymentcollection\",\"id\":\"paymentcollectionPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Paymentcollection\",\"id\":\"paymentcollectionPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		/* else if(functionId == ApplicationConstants.ROLE_FUNCTION_SAMPLECOM)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Paymentcolldocs\",\"id\":\"paymentcolldocsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Paymentcolldocs\",\"id\":\"paymentcolldocsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		} */
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_GENERAL_PROFILE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Generalprofile\",\"id\":\"generalprofilePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Generalprofile\",\"id\":\"generalprofilePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_RESPONDENT_DETAILS)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Respondent Details\",\"id\":\"respondentaddressPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Respondent Details\",\"id\":\"respondentaddressPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_UPAZILLA)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Upazilla\",\"id\":\"upazillaPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Upazilla\",\"id\":\"upazillaPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_RESPONDENT_OTHER_INFO)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Respondentotherinfo\",\"id\":\"respondentotherinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Respondentotherinfo\",\"id\":\"respondentotherinfoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_CROP_DETAIL)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Cropdetail\",\"id\":\"cropdetailPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Cropdetail\",\"id\":\"cropdetailPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_MOST_IMP_VEG)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Mostimpvegetable\",\"id\":\"mostimpvegetablePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Mostimpvegetable\",\"id\":\"mostimpvegetablePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VEGETABLE_RATING)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Vegetablerating\",\"id\":\"vegetableratingPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Vegetablerating\",\"id\":\"vegetableratingPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VEGETABLE_SITUATION)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Vegetablesituation\",\"id\":\"vegetablesituationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Vegetablesituation\",\"id\":\"vegetablesituationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_TARGET_VEGETABLES)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Targetvegetable\",\"id\":\"targetvegetablePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Targetvegetable\",\"id\":\"targetvegetablePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_COMMONLY_GROWN_VEGETABLE)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Commonlygrownvegetable\",\"id\":\"commonlygrownvegetablePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Commonlygrownvegetable\",\"id\":\"commonlygrownvegetablePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_POST_HARVEST_STAGES)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Postharveststages\",\"id\":\"postharveststagesPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Postharveststages\",\"id\":\"postharveststagesPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_TRANSPORT_TO_MAIN_BUYER)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Transporttomainbuyer\",\"id\":\"transporttomainbuyerPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Transporttomainbuyer\",\"id\":\"transporttomainbuyerPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_RATING_OF_POST_HARVEST_PROBLEMS)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Postharvestproblemrating\",\"id\":\"postharvestproblemratingPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Postharvestproblemrating\",\"id\":\"postharvestproblemratingPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_QUANTATIVE_LOSSES_IN_PH_SYSTEM)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Quantativelossesinphsystem\",\"id\":\"quantativelossesinphsystemPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Quantativelossesinphsystem\",\"id\":\"quantativelossesinphsystemPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_PRICE_IN_TAKA_PER_KG)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Priceintakaperkg\",\"id\":\"priceintakaperkgPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Priceintakaperkg\",\"id\":\"priceintakaperkgPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_POST_HARVEST_CONSTRAINTS)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Postharvestconstraints\",\"id\":\"postharvestconstraintsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Postharvestconstraints\",\"id\":\"postharvestconstraintsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EXPECTATION_OF_SUPPLY_CHAIN_ACTOR)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Expectationofsupplychainactor\",\"id\":\"expectationofsupplychainactorPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Expectationofsupplychainactor\",\"id\":\"expectationofsupplychainactorPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_COORDINATING_ORGANIZATION)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Coordinatingorganization\",\"id\":\"coordinatingorganizationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Coordinatingorganization\",\"id\":\"coordinatingorganizationPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_EXPECTATION_OTHER_QUESTION)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Expectationotherquestions\",\"id\":\"expectationotherquestionsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Expectationotherquestions\",\"id\":\"expectationotherquestionsPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		
	//add new functions here
	
	return children;
}
%>
<%
HttpSession thissession = request.getSession ();
LoginDetailInfoDTO loginInfo = null;
UserDTO user = null;
RoleDTO roleDTO = null;
ModuleDTO moduleDTO = null;
long roleId = -1;
String add_label = "";
String children = "",childrenWithoutModule = "";
long moduleId = -1;
String moduleItemList= "";
Hashtable<String,String> functionMap = null;
Hashtable<String,String> moduleFirstFunction = null;
Enumeration en = null;
Enumeration enFirst = null;
List<ModuleDTO> moduleList = null;
String moduleCode = "", moduleItem = "",function="",firstModuleCode = "";
boolean firstModuleItem = true;

if(thissession!=null)
{
	loginInfo = (LoginDetailInfoDTO)thissession.getAttribute ( SESSION_KEYS.LOGIN_INFO );
	if(loginInfo!=null)
	{
		user = (UserDTO) loginInfo.getUser ();
		if(user!=null)
		{
			roleDTO = user.getRole ();
			if(roleDTO!=null)
			{
				roleId = roleDTO.getComponentId ();
			}
		}
	}
}

if(roleId == ApplicationConstants.ROLE_SYSTEM_ADMIN)
{
	add_label = "Manage ";
}
moduleList = (List<ModuleDTO>)thissession.getAttribute(SESSION_KEYS.MODULEDTO_LIST);

functionMap = new Hashtable<String,String>();
moduleFirstFunction = new Hashtable<String,String>();
for(int i=0;moduleList!=null && i<moduleList.size();i++)
{
	moduleDTO = moduleList.get(i);
	System.out.println("module::"+moduleList.get(i).getModuleName());
	if(moduleDTO!=null)
	{
		
	}
}

Set<FunctionDTO> functionDTOList = (Set<FunctionDTO>)session.getAttribute(SESSION_KEYS.FUNCTIONDTO_SET);
String result = "[{"
	+"\"id\":\"apidocs\","
	+"\"iconCls\":\"icon-docs\","
	+"\"text\":\"Demo Application Root\","
	+"\"singleClickExpand\":true,"
	+"\"children\":[";
	                

for(int i=0;moduleList!=null && i<moduleList.size();i++)
{
	moduleDTO = moduleList.get(i);
	
		moduleItemList = "{"
			+"\"id\":\""+moduleDTO.getModuleCode()+"\","
			+"\"iconCls\":\"icon-config\","
			+"\"text\":\""+moduleDTO.getModuleName()+"\","
			+"\"singleClickExpand\":true,"
			+"\"children\":[";
	functionMap.put(""+moduleDTO.getModuleCode(),moduleItemList);
}

	if ( functionDTOList != null && functionDTOList.size() != 0 )
	{
		//out.println("Function dto list size is "+functionDTOList.size());
		for ( FunctionDTO functionDTO : functionDTOList )
		{
			en = functionMap.keys();
			enFirst = moduleFirstFunction.keys();
			String functionName = functionDTO.getFunctionName ();
			int functionId = functionDTO.getFunctionId();
			moduleDTO = functionDTO.getModuleId();
			if(moduleDTO!=null)
			{
				moduleId = moduleDTO.getComponentId();
				System.out.println("moduleId::"+moduleId);
			}
			if(moduleDTO!=null)
			{
				while(en.hasMoreElements())
				{
					moduleCode = (String)en.nextElement();
					
					
					if(moduleDTO.getModuleCode()!=null && moduleCode.equals(moduleDTO.getModuleCode()))
					{
						firstModuleItem = true;
						System.out.println("moduleCode::"+moduleCode+" moduleDTO.getModuleCode()::"+moduleDTO.getModuleCode());
						moduleItem = functionMap.get(moduleCode);
						System.out.println("functionName::"+functionName+" functionId::"+functionDTO.getFunctionId());	
						
						children = getFunctionItem(functionId,children);
						moduleId = -1;
						while(enFirst.hasMoreElements())
						{
							firstModuleCode = (String)enFirst.nextElement();
							if(firstModuleCode!=null && firstModuleCode.equals(moduleCode))
							{
								firstModuleItem = false;
								break;
							}
						}
						if(firstModuleItem)
						{
							moduleItem += children;
							moduleFirstFunction.put(moduleCode,"");
						}
						else
						{
							moduleItem += ","+children;
						}
						
						functionMap.put(moduleCode,moduleItem);						
					}
					
					children = "";					
				}
			}
			else
			{
				function = getFunctionItem(functionId,children);
				if(function!=null && !function.equals("null") && function.length()>0)
				{
					if(childrenWithoutModule!=null && !childrenWithoutModule.equals("null") && childrenWithoutModule.length()>0)
					{
						childrenWithoutModule += ","+function;
					}
					else
					{
						childrenWithoutModule += function;	
					}
				}				
			}
			
		}
	}
	en = functionMap.keys();
	while(en.hasMoreElements())
	{
		moduleCode = (String)en.nextElement();
		result += functionMap.get(moduleCode)+"]}";
		if(childrenWithoutModule!=null && !childrenWithoutModule.equals("null") && childrenWithoutModule.length()>0)
		{
			result += ",";
		}
	}
	result += childrenWithoutModule;
//result += moduleItemList;
//result += children;
result += "]}]";
response.setContentType("text/javascript");
PrintWriter xmlout = response.getWriter();
xmlout.println(result);
System.out.println("result::"+result);

%>
