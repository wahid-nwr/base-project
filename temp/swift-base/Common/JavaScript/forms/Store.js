/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getStorePanel(){
	var store = getStoreStore();
	var storeGrid = getStoreGrid(store);
	var search = getStoreStore(store);
	containerPanel = new Ext.Panel({
		id:'storeDetail',
		//frame: true,
		title: 'store List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			storeGrid
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
					var tabpanel = Ext.get('docs-storePanel');
					var storeAddForm = getStoreAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(storeAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-storePanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-storePanel');
	tabpanel.on('resize',function(){
		storeGrid.setSize(tabpanel.getSize());
		storeGrid.doLayout();
	});
        
	return containerPanel;
}
function getStoreAddForm(tabpanel,store)
{
	/*var extDateField = new Ext.form.DateField({
		"id" : 'extDateFieldId',
		"width" : 200,
		"format" : 'd M Y'
	});*/
	var extjsWindow = extjsWindow = new Ext.Window({
		layout:'card',
		activeItem: 0,
		width:400,
		height:300,
		modal:true,
		closable:true,
		minimizable:true,
		maximizable:true,
		autoScroll:true
	});
	
	var storeAddForm = Ext.create('Ext.FormPanel',{
        url:    'storeAction.csmp?method=addStore',
        id:     'storeAddForm',
        //layout: 'form',
        //renderTo:Ext.getBody(),
        frame: false,
        title: 'Add Store',
        closable: false,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Store Information',
                defaultType: 'textfield',
                items: getStoreFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: storeAddForm,
            handler: function() {
            	storeAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getStorePanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: storeAddForm,
            handler: function() {
                Ext.getCmp('storeAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: storeAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getStorePanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(storeAddForm);
	
	extjsWindow.show();
	return storeAddForm;
}
function getStoreFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'numberfield',name:'itemId',fieldLabel: 'itemId',emptyText:'0'}, 
			{ xtype: 'textfield',name:'itemName', fieldLabel: 'itemName' },
			{ xtype: 'textfield',name:'description',fieldLabel: 'description'},
			{ xtype: 'numberfield',name:'maxItemNum',fieldLabel: 'maxItemNum',emptyText:'0'}, 
			{ xtype: 'numberfield',name:'minItemNum',fieldLabel: 'minItemNum',emptyText:'0'}, 
			{ xtype: 'numberfield',name:'itemInStore',fieldLabel: 'itemInStore',emptyText:'0'}];
	/* example set
	[{ xtype: 'datefield',name:'date',fieldLabel: 'Date',emptyText:'Enter Date',readOnly:true},
     { xtype: 'textfield',name:'code', fieldLabel: 'Code' },
     { xtype: 'textarea',name:'desc', fieldLabel: 'Description',width:100 },
     { xtype: 'textfield',name:'session', fieldLabel: 'Session' },
     { xtype: 'textfield',name:'location', fieldLabel: 'Location' },
     { xtype: 'textfield',name:'doctor', fieldLabel: 'Doctor Name' }]
	*/
	return fieldSet;
}
function getStoreGrid(store)
{
	// create the grid
    var storeGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'storeGrid',
        store: store,
        columns: getStoreColumns(),
        
		viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    /*var rowData = null;
    storeGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
		//get row data
		rowData  = r;
	});
    */
    var rowData = null;
    storeGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-storePanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var storeAddForm = getStoreAddForm(tabpanel,store);
		var form = Ext.getCmp('storeAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'itemAction.csmp?method=modifyItem';
		loadStoreFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	storeGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-storePanel');
		var storeAddForm = getStoreAddForm(tabpanel,store);
		var form = Ext.getCmp('storeAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'storeAction.csmp?method=modifyStore';
		loadStoreFormData(form,rowData);
	});
    
    store.load();
    return storeGrid;
}
function getStoreColumns()
{
	var columns = [{header: 'itemId', width: 120, dataIndex: 'itemid', sortable: true}, 
			{header: 'itemName', width: 120, dataIndex: 'itemname', sortable: true}, 
			{header: 'maxItemNum', width: 120, dataIndex: 'maxitemnum', sortable: true}, 
			{header: 'minItemNum', width: 120, dataIndex: 'minitemnum', sortable: true}, 
			{header: 'itemInStore', width: 120, dataIndex: 'iteminstore', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadStoreFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('itemId').setValue(row.data.itemid);
	form.findField('itemName').setValue(row.data.itemname);
	form.findField('description').setValue(row.data.description);
	form.findField('maxItemNum').setValue(row.data.maxitemnum);
	form.findField('minItemNum').setValue(row.data.minitemnum);
	form.findField('itemInStore').setValue(row.data.iteminstore);
	/* sample field load
	form.findField('code').setValue(row.data.code);
	form.findField('desc').setValue(row.data.description);
	form.findField('date').setValue('aa');
	form.findField('session').setValue('aa');
	form.findField('location').setValue('aa');
	form.findField('doctor').setValue('aa');
	*/
}
function getStoreStore()
{	
	var url = 'storeAction.csmp?method=promptExtStoreSearchSystemLevel';
	Ext.define('StoreModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId', 'itemid', 'itemname','description','maxitemnum','minitemnum','iteminstore']
	});
	var inventorystore = Ext.create('Ext.data.Store', {
	    model: 'StoreModel',
	    pageSize: 20,
	    proxy: {
	        type: 'ajax',
	        url: url,
	        reader: {
	            type: 'xml',
	            record: 'Item',
	            root: 'Items',
	            totalProperty: 'TotalResults'
	        }
	    }
	});
	inventorystore.load();
	return inventorystore;
}
