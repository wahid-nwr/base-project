/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getTransportationPanel(){
	var store = getTransportationStore();
	var transportationGrid = getTransportationGrid(store);
	//var search = getTransportationStore(store);
	containerPanel = new Ext.Panel({
		id:'transportationDetail',
		//frame: true,
		title: 'transportation List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			transportationGrid
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
					var tabpanel = Ext.get('docs-transportationPanel');
					var transportationAddForm = getTransportationAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(transportationAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-transportationPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-transportationPanel');
	tabpanel.on('resize',function(){
		transportationGrid.setSize(tabpanel.getSize());
		transportationGrid.doLayout();
	});
        
	return containerPanel;
}
function getTransportationAddForm(tabpanel,store)
{
	
	var extjsWindow = new Ext.Window({
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
	
	var transportationAddForm = Ext.create('Ext.FormPanel',{
        url:    'transportationAction.csmp?method=addTransportation',
        id:     'transportationAddForm',
        //layout: 'form',
        //renderTo:Ext.getBody(),
        frame: false,
        title: 'Add Transportation',
        closable: false,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Transportation Information',
                defaultType: 'textfield',
                items: getTransportationFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: transportationAddForm,
            handler: function() {
            	transportationAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getTransportationPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: transportationAddForm,
            handler: function() {
                Ext.getCmp('transportationAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: transportationAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getTransportationPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(transportationAddForm);
	extjsWindow.show();
	return transportationAddForm;
}
function getTransportationFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'textfield',name:'transportationType', fieldLabel: 'transportationType' }];
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
function getTransportationGrid(store)
{
	// create the grid
    var transportationGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'transportationGrid',
        store: store,
        columns: getTransportationColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    transportationGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-transportationPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var transportationAddForm = getTransportationAddForm(tabpanel,store);
		var form = Ext.getCmp('transportationAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'transportationAction.csmp?method=modifyTransportation';
		loadTransportationFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	transportationGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-transportationPanel');
		var transportationAddForm = getTransportationAddForm(tabpanel,store);
		var form = Ext.getCmp('transportationAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'transportationAction.csmp?method=modifyTransportation';
		loadTransportationFormData(form,rowData);
	});
    
    store.load();
    return transportationGrid;
}
function getTransportationColumns()
{
	var columns = [{header: 'transportationType', width: 120, dataIndex: 'transportationtype', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadTransportationFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('transportationType').setValue(row.data.transportationtype);
	/* sample field load
	form.findField('code').setValue(row.data.code);
	form.findField('desc').setValue(row.data.description);
	form.findField('date').setValue('aa');
	form.findField('session').setValue('aa');
	form.findField('location').setValue('aa');
	form.findField('doctor').setValue('aa');
	*/
}
// Search Field for DCR Info
/*
function getTransportationSearchField(store)
{
	Ext.app.SearchField = Ext.extend(Ext.form.TwinTriggerField, {
	    initComponent : function(){
	        Ext.app.SearchField.superclass.initComponent.call(this);
	        this.on('specialkey', function(f, e){
	            if(e.getKey() == e.ENTER){
	                this.onTrigger2Click();
	            }
	        }, this);
	    },
	    validationEvent:false,
	    validateOnBlur:false,
	    trigger1Class:'x-form-clear-trigger',
	    trigger2Class:'x-form-search-trigger',
	    hideTrigger1:true,
	    width:180,
	    hasSearch : false,
	    paramName : 'searchInput',
	    onTrigger1Click : function(){
	        if(this.hasSearch){
	            this.el.dom.value = '';
	            var o = {start: 0};
	            this.store.baseParams = this.store.baseParams || {};
	            this.store.baseParams[this.paramName] = '';
	            this.store.reload({params:o});
	            this.triggers[0].hide();
	            this.hasSearch = false;
	        }
	    },
	    onTrigger2Click : function(){
	        var v = this.getRawValue();
	        if(v.length < 1){
	            this.onTrigger1Click();
	            return;
	        }
	        var o = {start: 0};
	        this.store.baseParams = this.store.baseParams || {};
	        this.store.baseParams[this.paramName] = v;
	        this.store.reload({params:o});
	        this.hasSearch = true;
	        this.triggers[0].show();
	    }
	});
		
	// A reusable error reader class for XML forms
	Ext.form.XmlErrorReader = function(){
	    Ext.form.XmlErrorReader.superclass.constructor.call(this, {
	            record : 'field',
	            success: '@success'
	        }, [
	            'id', 'msg'
	        ]
	    );
	};
	Ext.extend(Ext.form.XmlErrorReader, Ext.data.XmlReader);
	var SearchField = new Ext.app.SearchField({
        store: store,
        width:320
    })
	return SearchField;
}
*/	
function getTransportationStore()
{
	var url = 'transportationAction.csmp?method=promptExtTransportationSearchSystemLevel';
	Ext.define('TransportationModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId','transportationType']
	});
	var transportationstore = Ext.create('Ext.data.Store', {
	    model: 'TransportationModel',
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
	transportationstore.load();
	return transportationstore;
}
