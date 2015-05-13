/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getModulePanel(){
	var store = getModuleStore();
	var moduleGrid = getModuleGrid(store);
	var search = getModuleStore(store);
	containerPanel = new Ext.Panel({
		id:'moduleDetail',
		//frame: true,
		title: 'module List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			moduleGrid
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
					var tabpanel = Ext.get('docs-modulePanel');
					var moduleAddForm = getModuleAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(moduleAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-modulePanel');
				tabpanel.close();
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-modulePanel');
	tabpanel.on('resize',function(){
		moduleGrid.setSize(tabpanel.getSize());
		moduleGrid.doLayout();
	});
        
	return containerPanel;
}
function getModuleAddForm(tabpanel,store)
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
	
	var moduleAddForm = Ext.create('Ext.FormPanel',{
        url:    'moduleAction.csmp?method=addModule',
        id:     'moduleAddForm',
        //layout: 'form',
        //renderTo:Ext.getBody(),
        frame: false,
        title: 'Add Module',
        closable: false,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Module Information',
                defaultType: 'textfield',
                items: getModuleFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: moduleAddForm,
            handler: function() {
            	moduleAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getModulePanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: moduleAddForm,
            handler: function() {
                Ext.getCmp('moduleAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: moduleAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getModulePanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(moduleAddForm);
	extjsWindow.show();
	return moduleAddForm;
}
function getModuleFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'textfield',name:'moduleCode', fieldLabel: 'moduleCode' }, 
			{ xtype: 'textfield',name:'moduleName', fieldLabel: 'moduleName' }];
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
function getModuleGrid(store)
{
	// create the grid
    var moduleGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'moduleGrid',
    	layout: 'fit',
        store: store,
        columns: getModuleColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    moduleGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-modulePanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var moduleAddForm = getModuleAddForm(tabpanel,store);
		var form = Ext.getCmp('moduleAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'moduleAction.csmp?method=modifyModule';
		loadModuleFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	moduleGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-modulePanel');
		var moduleAddForm = getModuleAddForm(tabpanel,store);
		var form = Ext.getCmp('moduleAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'moduleAction.csmp?method=modifyModule';
		loadModuleFormData(form,rowData);
	});
    
    store.load();
    return moduleGrid;
}
function getModuleColumns()
{
	var columns = [{header: 'moduleCode', width: 120, dataIndex: 'modulecode', sortable: true}, 
			{header: 'moduleName', width: 120, dataIndex: 'modulename', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadModuleFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('moduleCode').setValue(row.data.modulecode);
	form.findField('moduleName').setValue(row.data.modulename);
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
function getModuleSearchField(store)
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
function getModuleStore()
{
	var url = 'moduleAction.csmp?method=promptExtModuleSearchSystemLevel';
	Ext.define('ModuleModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId','modulecode','modulename']
	});
	var modulestore = Ext.create('Ext.data.Store', {
	    model: 'ModuleModel',
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
	modulestore.load();
	return modulestore;
}
