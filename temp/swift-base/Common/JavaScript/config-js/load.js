function loadClassManually(className,tab,main)
{
	var loadtask = new Ext.util.DelayedTask(function(){
		//console.log("enter delay");
	   // Check for success
	   if (!Ext.Ajax.isLoading()) {
		   //console.log("success true");
	      // Callback
	      //doCallback();
	   } else {
		  //console.log("delaying");
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						////Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
	else if(className.indexOf('respondentotherinfoPanel')>-1 && p==null)
	{
		//className.indexOf('respondentotherinfoPanel')>-1 && p==null
		//alert('in other info');
		var div=document.getElementById('docs-respondentotherinfoPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Respondentotherinfo.js'],
                callback : function() {
                	p=getRespondentotherinfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}	
	else if(className.indexOf('transporttomainbuyerPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-transporttomainbuyerPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Transporttomainbuyer.js'],
                callback : function() {
                	p=getTransporttomainbuyerPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('postharvestproblemratingPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-postharvestproblemratingPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Postharvestproblemrating.js'],
                callback : function() {
                	p=getPostharvestproblemratingPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('quantativelossesinphsystemPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-quantativelossesinphsystemPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Quantativelossesinphsystem.js'],
                callback : function() {
                	p=getQuantativelossesinphsystemPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('priceintakaperkgPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-priceintakaperkgPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Priceintakaperkg.js'],
                callback : function() {
                	p=getPriceintakaperkgPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('postharvestconstraintsPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-postharvestconstraintsPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Postharvestconstraints.js'],
                callback : function() {
                	p=getPostharvestconstraintsPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('expectationofsupplychainactorPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-expectationofsupplychainactorPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Expectationofsupplychainactor.js'],
                callback : function() {
                	p=getExpectationofsupplychainactorPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('coordinatingorganizationPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-coordinatingorganizationPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Coordinatingorganization.js'],
                callback : function() {
                	p=getCoordinatingorganizationPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('expectationotherquestionsPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-expectationotherquestionsPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Expectationotherquestions.js'],
                callback : function() {
                	p=getExpectationotherquestionsPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('commonlygrownvegetablePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-commonlygrownvegetablePanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Commonlygrownvegetable.js'],
                callback : function() {
                	p=getCommonlygrownvegetablePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	//add load script here
	else if(className.indexOf('customerinfoPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-customerinfoPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Customerinfo.js'],
                callback : function() {
                	p=getCustomerinfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	
	else if(className.indexOf('dcrReportPanel')>-1 && p==null){
		//alert("aaaa");
		var div=document.getElementById('docs-dcrReportPanel');
		if(div!=null)
		{
			var callBack = function() {
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
					
					//Ext.Msg.hide();
					tab.remove('header');
					tab.remove('loading');
					tab.remove('loading-mask');
            	}
            };
			
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/dcrReport.js'],
                callback : callBack
			});
		}	
	}	
	else if(className.indexOf('accountPanel')>-1 && p==null){
		//alert("aaaa");
		var div=document.getElementById('docs-accountPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
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
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
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
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('itemPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-itemPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Item.js'],
                callback : function() {
                	p=getItemPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('batchPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-batchPanel');
		if(div!=null)
		{
			var callBack = function() {
            	p=getBatchPanel();
            	if(tab!=null)
            	{
                	tab.add(p);
					tab.doLayout();
					if(main!=null)
					{
						main.setActiveTab(tab);
					}
					
					//Ext.Msg.hide();
					tab.remove('header');
					tab.remove('loading');
					tab.remove('loading-mask');
            	}
            };
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Batch.js'],
                callback : callBack
			});
		}	
	}
	else if(className.indexOf('purchaseTransactionPanel')>-1 && p==null)
	{
		//alert('in trans');
		var div=document.getElementById('docs-purchaseTransactionPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/PurchaseTransaction.js'],
                callback : function() {
                	p=getPurchaseTransactionPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('salesTransactionPanel')>-1 && p==null)
	{
		//alert('in trans');
		var div=document.getElementById('docs-salesTransactionPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/SalesTransaction.js'],
                callback : function() {
                	p=getSalesTransactionPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	/*else if(className.indexOf('schedulePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-schedulePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Schedule.js'],
                callback : function() {
                	p=getSchedulePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}*/
	else if(className.indexOf('travelschedulePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-travelschedulePanel');
		
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Travelschedule.js'],
                callback : function() {
                	p=getTravelschedulePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('vehiclePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-vehiclePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Vehicle.js'],
                callback : function() {
                	p=getVehiclePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('routePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-routePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Route.js'],
                callback : function() {
                	p=getRoutePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('buyerPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-buyerPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Buyer.js'],
                callback : function() {
                	p=getBuyerPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('reservationPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-reservationPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Reservation.js'],
                callback : function() {
                	p=getReservationPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('reservationUpdatePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-reservationUpdatePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/ReservationUpdate.js'],
                callback : function() {
                	p=getReservationUpdatePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('tripPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-tripPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Trip.js'],
                callback : function() {
                	p=getTripPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('seatBookingPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-seatBookingPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/SeatBooking.js'],
                callback : function() {
                	p=getSeatBookingPanel();
                	/*var tabpanel = Ext.get('docs-body');
                	tabpanel.on('resize',function(){
                		alert('eee');
                		p.doLayout();                		
                	});*/
                	//p=new Ext.Panel({width:100,height:200,title:'test'});
                	if(tab!=null)
                	{
                    	tab.add(p);
    					tab.doLayout();
    					if(main!=null)
    					{    						
    						main.setActiveTab(0);
    						main.setActiveTab(tab);
    						//p.doLayout();
    					}
    					
    					//Ext.Msg.hide();
    					tab.remove('header');
    					tab.remove('loading');
    					tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
			
		}	
	}
	
	else if(className.indexOf('stationPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-stationPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Station.js'],
                callback : function() {
                	p=getStationPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('saleReportPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-saleReportPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/SaleReport.js'],
                callback : function() {
                	p=getSaleReportPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('storePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-storePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Store.js'],
                callback : function() {
                	p=getStorePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('coveringPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-coveringPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Covering.js'],
                callback : function() {
                	p=getCoveringPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('transportationPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-transportationPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Transportation.js'],
                callback : function() {
                	p=getTransportationPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('paymentPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-paymentPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Payment.js'],
                callback : function() {
                	p=getPaymentPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('credittermPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-credittermPanel');
		if(div!=null)
		{			
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Creditterm.js'],
                callback : function() {
                	p=getCredittermPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('unitPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-unitPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Unit.js'],
                callback : function() {
                	p=getUnitPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('productionstepPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-productionstepPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Productionstep.js'],
                callback : function() {
                	p=getProductionstepPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('currencyPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-currencyPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Currency.js'],
                callback : function() {
                	p=getCurrencyPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('bankPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-bankPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Bank.js'],
                callback : function() {
                	p=getBankPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('cityPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-cityPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/City.js'],
                callback : function() {
                	p=getCityPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('countryPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-countryPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Country.js'],
                callback : function() {
                	p=getCountryPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('modulePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-modulePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Module.js'],
                callback : function() {
                	p=getModulePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('hscodePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-hscodePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Hscode.js'],
                callback : function() {
                	p=getHscodePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('materialtypePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-materialtypePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Materialtype.js'],
                callback : function() {
                	p=getMaterialtypePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('materialcategoryPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-materialcategoryPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Materialcategory.js'],
                callback : function() {
                	p=getMaterialcategoryPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('warehousePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-warehousePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Warehouse.js'],
                callback : function() {
                	p=getWarehousePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('productionsectionPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-productionsectionPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Productionsection.js'],
                callback : function() {
                	p=getProductionsectionPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('inktypePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-inktypePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Inktype.js'],
                callback : function() {
                	p=getInktypePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('machinePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-machinePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Machine.js'],
                callback : function() {
                	p=getMachinePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('machinetypePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-machinetypePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Machinetype.js'],
                callback : function() {
                	p=getMachinetypePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('brandPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-brandPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Brand.js'],
                callback : function() {
                	p=getBrandPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('leavemanagementPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-leavemanagementPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Leavemanagement.js'],
                callback : function() {
                	p=getLeavemanagementPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('salaryPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-salaryPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Salary.js'],
                callback : function() {
                	p=getSalaryPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('attendencePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-attendencePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Attendence.js'],
                callback : function() {
                	p=getAttendencePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('departmentPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-departmentPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Department.js'],
                callback : function() {
                	p=getDepartmentPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('designationPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-designationPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Designation.js'],
                callback : function() {
                	p=getDesignationPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('leavepackagePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-leavepackagePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Leavepackage.js'],
                callback : function() {
                	p=getLeavepackagePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('holidayPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-holidayPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Holiday.js'],
                callback : function() {
                	p=getHolidayPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('employeePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-employeePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Employee.js'],
                callback : function() {
                	p=getEmployeePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('employeepersonalinfoPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-employeepersonalinfoPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Employeepersonalinfo.js'],
                callback : function() {
                	p=getEmployeepersonalinfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('academicinfoPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-academicinfoPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Academicinfo.js'],
                callback : function() {
                	p=getAcademicinfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('careerhistoryPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-careerhistoryPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Careerhistory.js'],
                callback : function() {
                	p=getCareerhistoryPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('supplierPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-supplierPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Supplier.js'],
                callback : function() {
                	p=getSupplierPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('supplierbankinfoPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-supplierbankinfoPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Supplierbankinfo.js'],
                callback : function() {
                	p=getSupplierbankinfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('inventoryinPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-inventoryinPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Inventoryin.js'],
                callback : function() {
                	p=getInventoryinPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('inventoryoutPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-inventoryoutPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Inventoryout.js'],
                callback : function() {
                	p=getInventoryoutPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('reportPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-reportPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Report.js'],
                callback : function() {
                	p=getReportPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						var reportFrame = Ext.getCmp('reportIFrame');
						reportFrame.load('report/CrystalReport1-viewer.jsp?rpt=CustomerReport.rpt');
						//updateBoxes();
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
						
                	}
                }
			});
		}	
	}
	else if(className.indexOf('empReportPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-empReportPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Report2.js'],
                callback : function() {
                	p=getEmpReportPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						var reportFrame = Ext.getCmp('empReportIFrame');
						reportFrame.load('report/EmpReport-viewer.jsp?rpt=Report2.rpt');
						//updateBoxes();
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
						
                	}
                }
			});
		}	
	}
	else if(className.indexOf('generalSetupReportPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-generalSetupReportPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/GeneralSetupReport.js'],
                callback : function() {
                	p=getEmpReportPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						var reportFrame = Ext.getCmp('generalSetupReportIFrame');
						reportFrame.load('report/GeneralSetup.jsp?rpt=Report2.rpt');
						//updateBoxes();
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
						
                	}
                }
			});
		}	
	}
	else if(className.indexOf('sapSaleReportPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-sapSaleReportPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/SaleReportSAP.js'],
                callback : function() {
                	p=getSAPSaleReportPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						var reportFrame = Ext.getCmp('sapSaleReportIFrame');
						reportFrame.load('report/SaleTest.jsp?rpt=SaleReport.rpt');
						//updateBoxes();
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
						
                	}
                }
			});
		}	
	}
	else if(className.indexOf('customerOrderReportPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-customerOrderReportPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/CustomerOrderReport.js'],
                callback : function() {
                	p=getCustomerOrderReportPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						var reportFrame = Ext.getCmp('customerOrderReportIFrame');
						reportFrame.load('report/CustomerOrderReport-viewer.jsp?rpt=CustomerOrderReport.rpt');
						//updateBoxes();
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
						
                	}
                }
			});
		}	
	}
	else if(className.indexOf('mailinfoPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-mailinfoPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Mailinfo.js'],
                callback : function() {
                	p=getMailinfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
		
	else if(className.indexOf('emailPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-emailPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Email.js'],
                callback : function() {
                	p=getEmailPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('emaildtlPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-emaildtlPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Emaildtl.js'],
                callback : function() {
                	p=getEmaildtlPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('emailgroupPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-emailgroupPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Emailgroup.js'],
                callback : function() {
                	p=getEmailgroupPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('emailreferancePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-emailreferancePanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Emailreferance.js'],
                callback : function() {
                	p=getEmailreferancePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('emailrecipientsPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-emailrecipientsPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Emailrecipients.js'],
                callback : function() {
                	p=getEmailrecipientsPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('emailrecvPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-emailrecvPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Emailrecv.js'],
                callback : function() {
                	p=getEmailrecvPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('shippinginfoPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-shippinginfoPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Shippinginfo.js'],
                callback : function() {
                	p=getShippinginfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('bankinfoPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-bankinfoPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Bankinfo.js'],
                callback : function() {
                	p=getBankinfoPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('inquiryPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-inquiryPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Inquiry.js'],
                callback : function() {
                	p=getInquiryPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('inquiryPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-inquiryPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Inquiry.js'],
                callback : function() {
                	p=getInquiryPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('itemmasterPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-itemmasterPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Itemmaster.js'],
                callback : function() {
                	p=getItemmasterPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('materialsPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-materialsPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Materials.js'],
                callback : function() {
                	p=getMaterialsPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('pricingPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-pricingPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Pricing.js'],
                callback : function() {
                	p=getPricingPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('paymentcollectionPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-paymentcollectionPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Paymentcollection.js'],
                callback : function() {
                	p=getPaymentcollectionPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('paymentcolldocsPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-paymentcolldocsPanel');
		if(div!=null)
		{
			// Basic mask:
			var myMask = new Ext.LoadMask(div, {msg:"Please wait..."});
			myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Paymentcolldocs.js'],
                callback : function() {
                	p=getPaymentcolldocsPanel();
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
						myMask.hide();
                	}
                }
			});
		}	
	}
	
	else if(className.indexOf('generalprofilePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-generalprofilePanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Generalprofile.js'],
                callback : function() {
                	p=getGeneralprofilePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('respondentaddressPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-respondentaddressPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Respondentaddress.js'],
                callback : function() {
                	p=getRespondentaddressPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('upazillaPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-upazillaPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Upazilla.js'],
                callback : function() {
                	p=getUpazillaPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	
	else if(className.indexOf('cropdetailPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-cropdetailPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Cropdetail.js'],
                callback : function() {
                	p=getCropdetailPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('mostimpvegetablePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-mostimpvegetablePanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Mostimpvegetable.js'],
                callback : function() {
                	p=getMostimpvegetablePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('vegetableratingPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-vegetableratingPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Vegetablerating.js'],
                callback : function() {
                	p=getVegetableratingPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('vegetablesituationPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-vegetablesituationPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Vegetablesituation.js'],
                callback : function() {
                	p=getVegetablesituationPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('targetvegetablePanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-targetvegetablePanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Targetvegetable.js'],
                callback : function() {
                	p=getTargetvegetablePanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('postharveststagesPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-postharveststagesPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Postharveststages.js'],
                callback : function() {
                	p=getPostharveststagesPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	else if(className.indexOf('transporttomainbuyerPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-transporttomainbuyerPanel');
		if(div!=null)
		{
			// Basic mask:
	    	var myMask = new Ext.LoadMask(div, {msg:"Please wait! Loading window..."});
	    	myMask.show();
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Transporttomainbuyer.js'],
                callback : function() {
                	p=getTransporttomainbuyerPanel();
                	if(tab!=null)
                	{
	                	tab.add(p);
						tab.doLayout();
						if(main!=null)
						{
							main.setActiveTab(tab);
						}
						
						//Ext.Msg.hide();
						tab.remove('header');
						tab.remove('loading');
						tab.remove('loading-mask');
						myMask.hide();
                	}
                }
			});
		}	
	}
	
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
