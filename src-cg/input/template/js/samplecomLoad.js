	else if(className.indexOf('samplecomPanel')>-1 && p==null)
	{
		var div=document.getElementById('docs-samplecomPanel');
		if(div!=null)
		{
			Ext.ux.ScriptMgr.load({
                scripts : ['./Common/JavaScript/forms/Samplecom.js'],
                callback : function() {
                	p=getSamplecomPanel();
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