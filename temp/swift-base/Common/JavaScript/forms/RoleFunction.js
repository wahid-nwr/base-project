/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
var set_combobox_value_from_store;

function getRolePanel(){
//alert('in get role panel');
	var store = getRoleStore();
	var roleGrid = getRoleGrid(store);
	var search = getRoleStore(store);
	var containerPanel = Ext.create('Ext.panel.Panel', {
		id:'roleDetail',
		//frame: true,
		title: 'role List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			roleGrid
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
					var tabpanel = Ext.get('docs-rolePanel');
					var roleAddForm = getRoleAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(userAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-rolePanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-rolePanel');
	tabpanel.on('resize',function(){
		roleGrid.setSize(tabpanel.getSize());
		roleGrid.doLayout();
	});
        
	return containerPanel;
}
function getRoleAddForm(tabpanel,store)
{
	/*var extDateField = new Ext.form.DateField({
		"id" : 'extDateFieldId',
		"width" : 200,
		"format" : 'd M Y'
	});*/
	var extjsWindow = Ext.create('Ext.window.Window', {
		layout:'card',
		//activeItem: 0,
		id:'roleWindow',
		width:400,
		height:300,
		modal:true,
		closable:true,
		minimizable:true,
		maximizable:true,
		/*bbar: [
	        {
	            id: 'move-prev',
	            text: 'Back',
	            handler: navHandler.createDelegate(this, [-1]),
	            disabled: false
	        },
	        '->', // greedy spacer so that the buttons are aligned to each side
	        {
	            id: 'move-next',
	            text: 'Next',
	            handler: navHandler.createDelegate(this, [1])
	        }
	    ],*/
		autoScroll:true
	});
	
	var roleAddForm = Ext.create('Ext.form.Panel',{
        url:    'roleFunctionAction.csmp?method=addRoleFunctions',
        id:     'roleAddForm',
        //layout: 'form',
        //renderTo:Ext.getBody(),
        autoScroll:true,
        frame: false,
        title: 'Add Role',
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Role Information',
                defaultType: 'textfield',
                items: getRoleFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: roleAddForm,
            handler: function() {
            	roleAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getRolePanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: roleAddForm,
            handler: function() {
                Ext.getCmp('roleAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: roleAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();        		             
                //tabpanel.add(getRolePanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(roleAddForm);
	var roleFunctionstore = getRoleFunctionStore();
	roleFunctionstore.load();
	var store = getRoleStore();
	roleFunctionstore.on('load', function(store, records, options)
	{
		//alert('loading');
		var checkbox = null;
		var record = null;
		var form = Ext.getCmp('roleAddForm');
		
		var individual = {
			        xtype: 'container',
			        layout: 'hbox',
			        margin: '0 0 10',
			        items: [{
			            xtype: 'fieldset',
			            flex: 1,
			            title: 'Individual Checkboxes',
			            defaultType: 'checkbox', // each item will be a checkbox
			            layout: 'anchor',
			            defaults: {
			                anchor: '100%',
			                hideEmptyLabel: false
			            },
			            items: [{
			                xtype: 'textfield',
			                name: 'txt-test1',
			                fieldLabel: 'Alignment Test'
			            }, {
			                fieldLabel: 'Favorite Animals',
			                boxLabel: 'Dog',
			                name: 'fav-animal-dog',
			                inputValue: 'dog'
			            }, {
			                boxLabel: 'Cat',
			                name: 'fav-animal-cat',
			                inputValue: 'cat'
			            }, {
			                checked: true,
			                boxLabel: 'Monkey',
			                name: 'fav-animal-monkey',
			                inputValue: 'monkey'
			            }]
			        }]
		 };
		//form.add(individual);	        
		for(var i=0;i<records.length;i++)
		{
			record = records[i];
			checkbox = Ext.create('Ext.form.field.Checkbox',{
				hideLabel:true,
				fieldLabel:''+record.get('rolefunctionname'),
				boxLabel: ''+record.get('rolefunctionname'),
				id:record.get('id'),
				name: 'cb-'+record.get('id')
			});
			
			//checkboxdata.push(record);
			form.add(checkbox);
		}
		form.doLayout();
		//alert('loaded::'+records.length);
		/*store.each(function(record)  
		{  
	        //alert(record);
			var checkbox = Ext.create('Ext.form.field.Checkbox',{hideLabel:true,fieldLabel:''+record.get('rolefunctionname'),boxLabel: ''+record.get('rolefunctionname'),id:record.get('id'), name: 'cb-'+record.get('id')});
			
			//checkboxdata.push(record);
			form.add(checkbox);
			//checkboxes.push(checkbox);
			
			
	    },this);*/
		//Ext.getCmp('roleAddForm').doLayout();
		//Ext.getCmp('roleWindow').doLayout();
	    
	});
	/*
	var item = new Ext.Panel({
		id: 'card-0',
        html: '<h1>Add Info Wizard!</h1><p>Step 2 of 3</p>'
	});
	extjsWindow.add(item);
	item = new Ext.Panel({
		id: 'card-1',
        html: '<h1>Welcome to the Wizard!</h1><p>Step 3 of 3</p>'
	});
	extjsWindow.add(item);*/
	extjsWindow.show();
	return roleAddForm;
}
function getRoleFieldSet()
{
	
	var roleFunctionstore = getRoleFunctionStore();
	var checkboxes = new Array();
	var checkboxdata = new Array();
	
	/*roleFunctionstore.on('load', function () {
		roleFunctionstore.data.each(function(item, index, totalItems ) {
	        //alert(item.data ['id']);
			checkbox = new Ext.form.Checkbox({hideLabel:true,fieldLabel:''+item.data['rolefunctionname'],boxLabel: ''+item.data['rolefunctionname'],id:item.data[id], name: 'cb-'+item.data['id']});
			var form = Ext.getCmp('roleAddForm');
			checkboxdata.push(item);
			form.add(checkbox);
			checkboxes.push(checkbox);
			Ext.getCmp('roleAddForm').doLayout();
			Ext.getCmp('roleWindow').doLayout();
			
	    });		
	});*/
	var store = getRoleStore();
	
		
	var combo = Ext.create('Ext.form.ComboBox', {
        store: store,
        id:'roleCMB',
        name:'role',
        fieldLabel:'role',
        valueField:'id',
        displayField:'roleName',
        //valueNotFoundText:'Invalid selection',
        //typeAhead: true,
        lazyInit: false,
        hiddenName:'role',
        forceSelection:true,
        mode: 'local',
        triggerAction: 'all',
        emptyText:'Select a role...',
        msgFx:'under'
        //validateOnBlur:true,
       // selectOnFocus:true        
    });
	/*var val = 7;
	
	store.on("load", function(val) {
		alert(val);
	   combo.setValue(val);
	});
	store.load(val);
*/
	combo.on('select',function(){
		//checkBoxChecked(checkboxes,checkboxdata);
		var mapstore = getRoleFunctionMapStore(combo.getValue());
		mapstore.on('load', function () {
			var form = Ext.getCmp('roleAddForm');
			//alert( 'load::'+roleFunctionstore.getTotalCount()  );
			var boxes = form.getForm().getFields();
			boxes.each(function(item,index,length){
				if(item.isXType('checkbox'))
				{
					form.getForm().findField(item.name).setValue(false);
				}
				//alert(item.name+"::::"+item.isXType('checkbox'));
			});
			
			mapstore.data.each(function(item, index, totalItems ) {
				//alert(item.data ['id']);	
				
				form.getForm().findField('cb-'+item.data['id']).setValue(true);
				
				
		    });	
			form.doLayout();
			Ext.getCmp('roleWindow').doLayout();
		});
	});
	set_combobox_value_from_store = function (store, combobox, valueField, value) {
		//Get a reference to the combobox's underlying store
		store.load({
		    callback: function () {
		        //Find item index in store
		        var index = store.find(valueField, value, false);
		        if (index < 0) return;
		        //Get model data id
		        
		        var dataId = store.getAt(index).data.id;
		        //alert('index::'+index+' dataId::'+dataId);
		        //Set combobox value and fire OnSelect event
		        combobox.setValue(dataId);
		    }
		});
	}
	
	//combo.selectByValue('3',true);
	//combo.setValue(3);
	var fieldSet = [combo,{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId',anchor:'90%' }];
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
function getRoleGrid(store)
{
	// create the grid
    var roleGrid = Ext.create('Ext.grid.Panel', {
    	//storeId:'roleGrid',
        store: store,
        columns: getRoleColumns(),
        /*selModel: itemDeleter,
        sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
        
		viewConfig: {
			forceFit: true
		},*/
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    roleGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-rolePanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var roleAddForm = getRoleAddForm(tabpanel,store);
		var form = Ext.getCmp('roleAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'roleFunctionAction.csmp?method=modifyRoleFunctions';
		var index = loadRoleFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	roleGrid.on('dblClick',function() {
		var tabpanel = Ext.getCmp('doc-body').findById('docs-rolePanel');
		var roleAddForm = getRoleAddForm(tabpanel,store);
		var form = Ext.getCmp('roleAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		form.url= 'roleFunctionAction.csmp?method=modifyRoleFunctions';
		var index = loadRoleFormData(form,rowData);
		//alert('i::'+index);
		set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
    
    store.load();
    return roleGrid;
}
function getRoleColumns()
{
	var columns = [{header: 'roleName', width: 120, dataIndex: 'roleName', sortable: true},
	        {header: 'description', width: 120, dataIndex: 'description', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadRoleFormData(form,row)
{
	//alert('row.data.componentId::'+row.data.lastname);
	form.findField('componentId').setValue(row.data.componentId);
	
	//var rolestore = getRoleStore();
	//form.findField('role').store.clearFilter();
	//form.findField('role').setValue(row.data.userrole);
	//alert(row.data.userrole);
	//alert('id::'+form.findField('componentId').getValue());
	/* sample field load
	form.findField('code').setValue(row.data.code);
	form.findField('desc').setValue(row.data.description);
	form.findField('date').setValue('aa');
	form.findField('session').setValue('aa');
	form.findField('location').setValue('aa');
	form.findField('doctor').setValue('aa');
	*/
	return row.data.id;
}
// Search Field for DCR Info
function getRoleSearchField(store)
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
	/*
	Ext.form.XmlErrorReader = function(){
	    Ext.form.XmlErrorReader.superclass.constructor.call(this, {
	            record : 'field',
	            success: '@success'
	        }, [
	            'id', 'msg'
	        ]
	    );
	};*/
	Ext.extend(Ext.form.XmlErrorReader, Ext.data.XmlReader);
	var SearchField = new Ext.app.SearchField({
        store: store,
        width:320
    })
	return SearchField;
}	
function getRoleFunctionStore()
{
	var url = 'roleFunctionAction.csmp?method=getExtFunctions';
	Ext.define('RoleFunctionMapModel', {
	    extend: 'Ext.data.Model',
	    fields: ['id', 'rolefunctionname']
	});
	var store = Ext.create('Ext.data.Store', {
	    model: 'RoleFunctionMapModel',
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
	
	return store;
}

function getRoleFunctionMapStore(role)
{
	var url = 'roleFunctionAction.csmp?method=getRoleFunctions&role='+role;
	Ext.define('RoleFunctionModel', {
	    extend: 'Ext.data.Model',
	    fields: ['id', 'rolefunctionname']
	});
	var store = Ext.create('Ext.data.Store', {
	    model: 'RoleFunctionModel',
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
	
	store.load();
	return store;
}

function getRoleStore()
{
	var url = 'roleAction.csmp?method=getRoleList';
	Ext.define('RoleModel', {
	    extend: 'Ext.data.Model',
	    fields: ['id', 'roleName', 'description']
	});
	var rolestore = Ext.create('Ext.data.Store', {
	    model: 'RoleModel',
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
	rolestore.load();
	return rolestore;
}