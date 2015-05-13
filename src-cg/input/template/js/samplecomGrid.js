function getSamplecomGrid(store)
{
	// create the grid
    var samplecomGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'samplecomGrid',
        store: store,
        columns: getSamplecomColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    samplecomGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-samplecomPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var samplecomAddForm = getSamplecomAddForm(tabpanel,store);
		var form = Ext.getCmp('samplecomAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'samplecomAction.csmp?method=modifySamplecom';
		loadSamplecomFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	samplecomGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-samplecomPanel');
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