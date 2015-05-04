/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getAccountPanel(){
	var store = getAccountStore();
	var accountGrid = getAccountGrid(store);
	//var search = getAccountStore(store);
	containerPanel = new Ext.Panel({
		id:'accountDetail',
		//frame: true,
		title: 'account List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			accountGrid
		],
		tbar: [
	            'Search: ', ' ',
	            getAccountSearchField(store)
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
					var tabpanel = Ext.getCmp('doc-body').findById('docs-accountPanel');
					var accountAddForm = getAccountAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(accountAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.getCmp('doc-body').findById('docs-accountPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.getCmp('doc-body').findById('docs-accountPanel');
	tabpanel.on('resize',function(){
		accountGrid.setSize(tabpanel.getSize());
		accountGrid.doLayout();
	});
        
	return containerPanel;
}
function getAccountAddForm(tabpanel,store)
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
	
	var accountAddForm = new Ext.FormPanel
	({
        url:    'accountAction.csmp?method=addAccount',
        id:     'accountAddForm',
        layout: 'form',
        //renderTo:Ext.getBody(),
        frame: true,
        title: 'Add Account',
        closable: true,
        autoScroll:true,
        items: [
            new Ext.form.FieldSet({
                title: 'Account Information',
                defaultType: 'textfield',
                items: getAccountFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: accountAddForm,
            handler: function() {
            	accountAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getAccountPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: accountAddForm,
            handler: function() {
                Ext.getCmp('accountAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: accountAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		//store.load();                
                //tabpanel.add(getAccountPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(accountAddForm);
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
	return accountAddForm;
}
function getAccountFieldSet()
{
	var fieldSet = [{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
	        { xtype: 'textfield',name:'code', fieldLabel: 'code' }, 
			{ xtype: 'textfield',name:'name', fieldLabel: 'name' },
			{ xtype: 'textfield',name:'parent', fieldLabel: 'parent' }
			];
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
function getAccountGrid(store)
{
	var itemDeleter = new Extensive.grid.ItemDeleter();
	var columns = [];
	var getColumn = getAccountColumns(itemDeleter);
	for(var i=0;i<getColumn.length;i++)		
	{
		columns.push(getColumn[i]);
	}
    // create the grid
    var accountGrid = new Ext.grid.GridPanel({
    	id:'accountGrid',
        store: store,
        columns: getAccountColumns(itemDeleter),
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
    accountGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
		//get row data
		rowData  = r;
	});
	
	accountGrid.on('dblClick',function() {
		var tabpanel = Ext.getCmp('doc-body').findById('docs-accountPanel');
		var accountAddForm = getAccountAddForm(tabpanel,store);
		var form = Ext.getCmp('accountAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'accountAction.csmp?method=modifyAccount';
		loadAccountFormData(form,rowData);
	});
    
    store.load();
    return accountGrid;
}
function getAccountColumns(itemDeleter)
{
	var columns = [{header: 'code', width: 120, dataIndex: 'code', sortable: true}, 
			{header: 'name', width: 120, dataIndex: 'name', sortable: true}, 
			{header: 'parent', width: 120, dataIndex: 'parent', sortable: true}, 
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
function loadAccountFormData(form,row)
{
	form.findField('code').setValue(row.data.code);
	form.findField('name').setValue(row.data.name);
	form.findField('parent').setValue(row.data.parent);
	form.findField('componentId').setValue(row.data.componentId);
	//alert(row.data.componentId);
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
function getAccountSearchField(store)
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
function getAccountStore()
{
	var store = new Ext.data.Store({
        // load using HTTP
        url: 'accountAction.csmp?method=promptExtAccountSearchSystemLevel',
        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'componentId',
               totalRecords: 'TotalResults'
           }, [
               // set up the fields mapping into the xml doc
               // The first needs mapping, the others are very basic
               {name: 'code', mapping: 'ItemAttributes > code'},
               'componentId',
               'name',
			   'parent'
           ])
    });
	return store;
}
