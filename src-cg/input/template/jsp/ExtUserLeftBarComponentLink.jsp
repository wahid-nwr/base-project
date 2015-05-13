		else if(functionId == ApplicationConstants.ROLE_FUNCTION_SAMPLECOM)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Samplecom\",\"id\":\"samplecomPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Samplecom\",\"id\":\"samplecomPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}