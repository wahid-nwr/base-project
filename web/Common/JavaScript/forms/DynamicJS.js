function GetHttpRequest()
{
	if ( window.XMLHttpRequest ) // Gecko
		return new XMLHttpRequest() ;
	else if ( window.ActiveXObject ) // IE
	return new ActiveXObject("MsXml2.XmlHttp") ;
}
function AjaxPage(sId, url){
	request(sId,url);
	/*
	var oXmlHttp = GetHttpRequest() ;
	oXmlHttp.OnReadyStateChange = function()
	{
		alert('oXmlHttp.readyState::'+oXmlHttp.readyState)
		if ( oXmlHttp.readyState == 4 )
		{
			alert('oXmlHttp.status::'+oXmlHttp.status)
			if ( oXmlHttp.status == 200 || oXmlHttp.status == 304 )
			{
				IncludeJS( sId, url, oXmlHttp.responseText );
			}
			else
			{
				alert( 'XML request error: ' + oXmlHttp.statusText + ' (' + oXmlHttp.status + ')' ) ;
			}
		}
	}
	oXmlHttp.open('GET', url, true);
	oXmlHttp.send(null);
	*/
}
function request(sId,url,jsloaded,loadtask)
{
	loadtask.delay(50);
	var self = arguments.callee ;
	Ext.Ajax.request({
		   url: url,
		   success: function(resp) {
		       //Ext.Msg.alert('Success', 'Role Functions Added');
		       var source = resp.responseText;
		       
		       jsloaded = IncludeJS( sId, url, source,jsloaded );
		       alert('jsloaded::'+self);
		       setTimeout(self,10);
		   },
		   failure: function(f,a){
		       Ext.Msg.alert('Warnning', 'Error occured in previous action');
		   },
		   headers: {
		       'my-header': 'foo'
		   },
		   async:true
		   /*,
		   params: { foo: 'bar' }
		   */
		});
	if(Ext.Ajax.isLoading())		
	{
		loadtask.delay(10);
	}
	
	return jsloaded;
}
function IncludeJS(sId, fileUrl, source,jsloaded)
{
	//alert('source:::::::'+source);
	if ( ( source != null ) && ( !document.getElementById( sId ) ) )
	{
		var oHead = document.getElementsByTagName('BODY').item(0);
		var oScript = document.createElement( "script" );
		oScript.language = "javascript";
		oScript.type = "text/javascript";
		oScript.id = sId;
		oScript.defer = true;
		//alert('source::'+source);
		oScript.text = source;
		oHead.appendChild( oScript );
		alert('done');
		jsloaded = true;
	}
	else
	{
		jsloaded = false;
	}
	return jsloaded;
}