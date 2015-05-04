function getSamplecomGrid(store)
{
	var itemDeleter = new Extensive.grid.ItemDeleter();
    // create the grid
    var samplecomGrid = new Ext.grid.GridPanel({
    	id:'samplecomGrid',
        store: store,
        columns: getSamplecomColumns(itemDeleter),
        selModel: itemDeleter,
        sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
        
		viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    samplecomGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
		//get row data
		rowData  = r;
	});
	
	samplecomGrid.on('dblClick',function() {
		var tabpanel = Ext.getCmp('doc-body').findById('docs-samplecomPanel');
		var samplecomAddForm = getSamplecomAddForm(tabpanel,store);
		var form = Ext.getCmp('samplecomAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'samplecomAction.csmp?method=modifySamplecom';
		loadSamplecomFormData(form,rowData);
	});
    
    store.load();
    return samplecomGrid;
}