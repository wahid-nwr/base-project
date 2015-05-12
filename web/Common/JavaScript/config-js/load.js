function loadClassManually(className,tab,main)
{
	var loadtask = new Ext.util.DelayedTask(function(){
		console.log("enter delay");
	   // Check for success
	   if (!Ext.Ajax.isLoading()) {
		   console.log("success true");
	      // Callback
	      //doCallback();
	   } else {
		  console.log("delaying");
		   loadtask.delay(50);
	      
	   }
	});
	
	//air.trace("panel className::::::::::"+className);
	Ext.Ajax.on('beforerequest', function(connection,options){
		Ext.getBody().mask('Loading...');
		});

		Ext.Ajax.on('requestcomplete', function(connection,options){
		Ext.getBody().unmask();
		});

		loadtask.delay(10);
		var jsloaded = false;
				
	var p=null;
	if(className.indexOf('dcrinfoPanel')>-1 && p==null)
	{		
		var div=document.getElementById('docs-dcrinfoPanel');
		p=new Ext.Panel({id:'replace'});
		if(div!=null)
		{
			//Ext.ux.ScriptMgr = new Ext.ux.ScriptLoaderMgr();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/dcrinfo.js'],
                callback : function() {
                	//alert('loaded');
                	p=getDcrinfoPanel();
                	if(tab!=null)
                	{
                		//tab.remove('replace');
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
			/*
			jsloaded=request( "scrA", "./Common/JavaScript/forms/dcrinfo.js",jsloaded,loadtask );
			if(Ext.Ajax.isLoading())		
			{
				loadtask.delay(10);
			}
			var self = arguments.callee ;
			setTimeout(self,10);
			//alert('jsloadedjsloadedjsloadedjsloadedjsloaded::::'+jsloaded);
			//console.log('1. before loading ajax script');
			//loadViewViaAjax('./Common/JavaScript/forms/test.js');
			//console.log('3. after loading ajax script');
			//alert( "dynamically load javascript");
			//Ext.Ajax.defer(1000);
			//alert( "dynamically load a.js and get the variable：" + str );
			alert('now load panel')
			 
			Ext.Ajax.on('requestexception', function(connection,options){
				Ext.getBody().unmask();
				
			});
			*/
			
			
		}		
	}
	else if(className.indexOf('dcrReportPanel')>-1 && p==null){
		//alert("aaaa");
		var div=document.getElementById('docs-dcrReportPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/dcrReport.js'],
                callback : function() {
                	//alert('loaded');
                	p=getDcrReportPanel();
                	if(tab!=null)
                	{
                		//tab.remove('replace');
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}	
	else if(className.indexOf('accountPanel')>-1 && p==null){
		//alert("aaaa");
		var div=document.getElementById('docs-accountPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Account.js'],
                callback : function() {
                	//alert('loaded');
                	p=getAccountPanel();
                	if(tab!=null)
                	{
                		//tab.remove('replace');
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});			
		}	
	}
	else if(className.indexOf('patientPanel')>-1 && p==null){
		//alert("aaaa");
		var div=document.getElementById('docs-patientPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Patient.js'],
                callback : function() {
                	//alert('loaded');
                	p=getPatientPanel();
                	if(tab!=null)
                	{
                		//tab.remove('replace');
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}	
	else if(className.indexOf('userPanel')>-1){
		//alert("aaaa");
		var div=document.getElementById('docs-userPanel');
		if(div!=null)
		{			
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/User.js'],
                callback : function() {
                	//alert('loaded');
                	p=getUserPanel();
                	if(tab!=null)
                	{
                		//tab.remove('replace');
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	else if(className.indexOf('helloPanel')>-1){
		//alert("aaaa");
		var div=document.getElementById('docs-helloPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Hello.js'],
                callback : function() {
                	//alert('loaded');
                	p=getHelloPanel();
                	if(tab!=null)
                	{
                		//tab.remove('replace');
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	
	else if(className.indexOf('beneficiaryPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-beneficiaryPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Beneficiary.js'],
                callback : function() {
                	p=getBeneficiaryPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	else if(className.indexOf('beneficiaryPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-beneficiaryPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Beneficiary.js'],
                callback : function() {
                	p=getBeneficiaryPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	else if(className.indexOf('homePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-homePanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Home.js'],
                callback : function() {
                	p=getHomePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	else if(className.indexOf('rolePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-rolePanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/RoleFunction.js'],
                callback : function() {
                	p=getRolePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	else if(className.indexOf('infoPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-infoPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Info.js'],
                callback : function() {
                	p=getInfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	else if(className.indexOf('paitenprofilePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-paitenprofilePanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Paitenprofile.js'],
                callback : function() {
                	p=getPaitenprofilePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	
	else if(className.indexOf('ajanPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-ajanPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Ajan.js'],
                callback : function() {
                	p=getAjanPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
                	}
                }
			});
		}	
	}
	//add load script here
	else if(className.indexOf('branchPanel')>-1){
		p=getBranchPanel();
	}
	else if(className.indexOf('centerPanel')>-1){
		p=getCenterPanel();
	}
	else if(className.indexOf('loaneePanel')>-1){
		p=getLoaneePanel();
	}
	return p;
}
function loadViewViaAjax(url) {
    Ext.Ajax.request({
        url: url,
        success: function(objServerResponse) {
            var responseText = objServerResponse.responseText;
            var scripts, scriptsFinder=/<script[^>]*>([\s\S]+)<\/script>/gi;
            while(scripts=scriptsFinder.exec(responseText)) {
                eval.call(window,scripts[1]);
            }
        }
    });
}
