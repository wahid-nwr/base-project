/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
var set_combobox_value_from_store;
function getUserPanel(){
	var store = getUserStore();
	var userGrid = getUserGrid(store);
	//var search = getUserStore(store);
	containerPanel = new Ext.Panel({
		id:'userDetail',
		//frame: true,
		title: 'user List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			userGrid
		],
		tbar: [
	            'Search: ', ' ',
	            getSearchField(store)
	        ],
		/* dockedItems: [{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			store: store,
            pageSize: 20,
            displayInfo: true,
            displayMsg: 'Users {0} - {1} of {2}',
            emptyMsg: "No Users to display"
        }],*/
        buttons:[{
			text:"ADD",
				handler:function() {
					var tabpanel = Ext.get('docs-userPanel');
					var userAddForm = getUserAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(userAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-userPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-userPanel');
	tabpanel.on('resize',function(){
		userGrid.setSize(tabpanel.getSize());
		userGrid.doLayout();
	});
        
	return containerPanel;
}
function getUserAddForm(tabpanel,store)
{
	/*var extDateField = new Ext.form.DateField({
		"id" : 'extDateFieldId',
		"width" : 200,
		"format" : 'd M Y'
	});*/
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
	
	var userAddForm = Ext.create('Ext.FormPanel',{
        url:    'userAction.csmp?method=addUser',
        id:     'userAddForm',
        //layout: 'form',
        //renderTo:Ext.getBody(),
        frame: false,
        title: 'Add User',
        closable: false,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'User Information',
                defaultType: 'textfield',
                items: getUserFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: userAddForm,
            handler: function() {
            	userAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getUserPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: userAddForm,
            handler: function() {
                Ext.getCmp('userAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: userAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();        		             
                //tabpanel.add(getUserPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(userAddForm);
	extjsWindow.show();
	return userAddForm;
}
function getUserFieldSet()
{
	var store = getRoleStore();
	var combo = Ext.create('Ext.form.ComboBox', {
        store: store,
        id:'roleCMB',
        name:'rolename',
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
	var fieldSet = [{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId',anchor:'90%' },
	        { xtype: 'textfield',name:'lastName', fieldLabel: 'lastName' }, 
			{ xtype: 'textfield',name:'firstName', fieldLabel: 'firstName' }, 
			{ xtype: 'textfield',inputType:'password',name:'password', fieldLabel: 'password' }, 
			{ xtype: 'textfield',inputType:'password',name:'confirmPassword', fieldLabel: 'confirmPassword' }, 
			{ xtype: 'textfield',name:'uniqueCode', fieldLabel: 'uniqueCode' },combo];
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
function getUserGrid(store)
{
	// create the grid
    var userGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'userGrid',
        store: store,
        columns: getUserColumns(),
		viewConfig: {
			forceFit: true
		},
		dockedItems: [{
            xtype: 'pagingtoolbar',
            store: store,   // same store GridPanel is using
            dock: 'bottom',
            displayInfo: true
        }],
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    userGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-userPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var userAddForm = getUserAddForm(tabpanel,store);
		var form = Ext.getCmp('userAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'userAction.csmp?method=modifyUser';
		loadUserFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	userGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-userPanel');
		var userAddForm = getUserAddForm(tabpanel,store);
		var form = Ext.getCmp('userAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		form.url= 'userAction.csmp?method=modifyUser';
		var index = loadUserFormData(form,rowData);
		//alert('i::'+index);
		set_combobox_value_from_store(getRoleStore(),form.findField('role'), 'id', index);
	});
    
    store.load();
    return userGrid;
}
function getUserColumns()
{
	var columns = [{header: 'uniqueCode', width: 120, dataIndex: 'uniquecode', sortable: true},
	        {header: 'lastName', width: 120, dataIndex: 'lastname', sortable: true}, 
			{header: 'firstName', width: 120, dataIndex: 'firstname', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadUserFormData(form,row)
{
	//alert('row.data.componentId::'+row.data.lastname);
	form.findField('componentId').setValue(row.data.componentId);
	form.findField('lastName').setValue(row.data.lastname);
	form.findField('firstName').setValue(row.data.firstname);
	form.findField('password').setValue(row.data.password);
	form.findField('confirmPassword').setValue(row.data.password);
	form.findField('uniqueCode').setValue(row.data.uniquecode);
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
	return row.data.userrole;
}

function getUserStore()
{
	var url = 'userAction.csmp?method=promptExtUserSearchSystemLevel';
	Ext.define('UserModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId','uniquecode','firstname',
				   'lastname',
				   'password',
				   'userrole']
	});
	var userstore = Ext.create('Ext.data.Store', {
	    model: 'UserModel',
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
	
	return userstore;
}

function getRoleStore()
{
	var url = 'roleAction.csmp?method=getRoleList';
	Ext.define('RoleFunctionModel', {
	    extend: 'Ext.data.Model',
	    fields: ['id','roleName']
	});
	var rolestore = Ext.create('Ext.data.Store', {
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
	rolestore.load();
	return rolestore;
}