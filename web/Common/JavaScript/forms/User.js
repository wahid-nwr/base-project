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
	var search = getUserStore(store);
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
	            getUserSearchField(store)
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
					var tabpanel = Ext.getCmp('doc-body').findById('docs-userPanel');
					var userAddForm = getUserAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(userAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.getCmp('doc-body').findById('docs-userPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.getCmp('doc-body').findById('docs-userPanel');
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
	var extjsWindow = null;
	var navHandler = function(direction){
		
		var active = extjsWindow.getLayout().activeItem;
		var itemToActivate = 0;
		for(var i=0;i<extjsWindow.items.length;i++)
		{
			var item = extjsWindow.getComponent(i);
			if(item.id==active.id)
			{
				var index = i+direction;
				if(index>=0 && index<extjsWindow.items.length)
				{
					itemToActivate = (index);
				}
				else
				{					
					itemToActivate = i;
				}				
			}
		}
		
		extjsWindow.getLayout().setActiveItem(itemToActivate);
		
		
		Ext.get('move-prev').disabled = false;
		extjsWindow.doLayout();
		
	    // This routine could contain business logic required to manage the navigation steps.
	    // It would call setActiveItem as needed, manage navigation button state, handle any
	    // branching logic that might be required, handle alternate actions like cancellation
	    // or finalization, etc.  A complete wizard implementation could get pretty
	    // sophisticated depending on the complexity required, and should probably be
	    // done as a subclass of CardLayout in a real-world implementation.
	};
	
	extjsWindow = new Ext.Window({
		layout:'card',
		activeItem: 0,
		width:400,
		height:300,
		modal:true,
		closable:true,
		minimizable:true,
		maximizable:true,
		bbar: [
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
	    ],
		autoScroll:true
	});
	
	var userAddForm = new Ext.FormPanel
	({
        url:    'userAction.csmp?method=addUser',
        id:     'userAddForm',
        layout: 'form',
        //renderTo:Ext.getBody(),
        frame: true,
        title: 'Add User',
        closable: true,
        autoScroll:true,
        items: [
            new Ext.form.FieldSet({
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
	var item = new Ext.Panel({
		id: 'card-0',
        html: '<h1>Add Info Wizard!</h1><p>Step 2 of 3</p>'
	});
	extjsWindow.add(item);
	item = new Ext.Panel({
		id: 'card-1',
        html: '<h1>Welcome to the Wizard!</h1><p>Step 3 of 3</p>'
	});
	extjsWindow.add(item);
	extjsWindow.show();
	return userAddForm;
}
function getUserFieldSet()
{
	var store = getRoleStore();
	var combo = new Ext.form.ComboBox({
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
	var itemDeleter = new Extensive.grid.ItemDeleter();
    // create the grid
    var userGrid = new Ext.grid.GridPanel({
    	id:'userGrid',
        store: store,
        columns: getUserColumns(itemDeleter),
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
    userGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
		//get row data
		rowData  = r;
	});
	
	userGrid.on('dblClick',function() {
		var tabpanel = Ext.getCmp('doc-body').findById('docs-userPanel');
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
function getUserColumns(itemDeleter)
{
	var columns = [{header: 'uniqueCode', width: 120, dataIndex: 'uniquecode', sortable: true},
	        {header: 'lastName', width: 120, dataIndex: 'lastname', sortable: true}, 
			{header: 'firstName', width: 120, dataIndex: 'firstname', sortable: true},						 
			itemDeleter];
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
// Search Field for DCR Info
function getUserSearchField(store)
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
function getUserStore()
{
	var store = new Ext.data.Store({
        // load using HTTP
        url: 'userAction.csmp?method=promptExtUserSearchSystemLevel',
        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'componentId',
               totalRecords: 'TotalResults'
           }, [
               // set up the fields mapping into the xml doc
               // The first needs mapping, the others are very basic
               {name: 'uniquecode', mapping: 'ItemAttributes > uniquecode'},
               'firstname',
			   'lastname',
			   'password',
			   'userrole',
			   'componentId'
           ])
    });
	return store;
}

function getRoleStore()
{
	var rolestore = new Ext.data.Store({
        // load using HTTP
        url: 'roleAction.csmp?method=getRoleList',
        // the return will be XML, so lets set up a reader
        /*
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'id',
               totalRecords: 'itemCount'
           },//[
               // set up the fields mapping into the xml doc
               // The first needs mapping, the others are very basic
               //{name: 'componentId', mapping: 'ItemAttributes > roleName'},
           //]
           [
               {
                   name: 'roleName',
                   mapping: 'roleName'
               },
               {
                   name: 'id',
                   mapping: 'ItemAttributes > roleName'
               }
           ]
        )
		*/
        reader: new Ext.data.XmlReader(
                { record: 'Item', id: 'id'},
                [ { name: 'id', type: 'string' },{ name: 'roleName', type: 'string' } ]
        )

    });
	rolestore.load();
	return rolestore;
}