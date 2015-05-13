/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */


function getSamplecomPanel(){
	var store = getSamplecomStore();
	var samplecomGrid = getSamplecomGrid(store);
	var search = getSamplecomStore(store);
	containerPanel = new Ext.Panel({
		id:'samplecomDetail',
		//frame: true,
		title: 'samplecom List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			samplecomGrid
		],
		tbar: [
	            'Search: ', ' ',
	            getSearchField(store)
	        ],
		 dockedItems: [{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
            store: store,
           displayInfo: true
        }],
        buttons:[{
			text:"ADD",
				handler:function() {
					var tabpanel = Ext.get('docs-samplecomPanel');
					var samplecomAddForm = getSamplecomAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(samplecomAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-samplecomPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-samplecomPanel');
	tabpanel.on('resize',function(){
		samplecomGrid.setSize(tabpanel.getSize());
		samplecomGrid.doLayout();
	});
        
	return containerPanel;
}