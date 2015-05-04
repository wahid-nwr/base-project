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
	            getSamplecomSearchField(store)
	        ],
		bbar: new Ext.PagingToolbar({
            store: store,
            pageSize: 20,
            displayInfo: true,
            displayMsg: 'Items {0} - {1} of {2}',
            emptyMsg: "No topics to display"
        }),
        buttons:[{
			text:"ADD",
				handler:function() {
					var tabpanel = Ext.getCmp('doc-body').findById('docs-samplecomPanel');
					var samplecomAddForm = getSamplecomAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(samplecomAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.getCmp('doc-body').findById('docs-samplecomPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.getCmp('doc-body').findById('docs-samplecomPanel');
	tabpanel.on('resize',function(){
		samplecomGrid.setSize(tabpanel.getSize());
		samplecomGrid.doLayout();
	});
        
	return containerPanel;
}