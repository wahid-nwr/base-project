/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getInfoPanel(){
	var store = getInfoStore();
	var infoGrid = getInfoGrid(store);
	var search = getInfoStore(store);
	containerPanel = new Ext.Panel({
		id:'infoDetail',
		//frame: true,
		title: 'info List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			infoGrid
		],
		tbar: [
	            'Search: ', ' ',
	            getInfoSearchField(store)
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
					var tabpanel = Ext.getCmp('doc-body').findById('docs-infoPanel');
					var infoAddForm = getInfoAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(infoAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.getCmp('doc-body').findById('docs-infoPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.getCmp('doc-body').findById('docs-infoPanel');
	tabpanel.on('resize',function(){
		infoGrid.setSize(tabpanel.getSize());
		infoGrid.doLayout();
	});
        
	return containerPanel;
}
function getInfoAddForm(tabpanel,store)
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
	
	var infoAddForm = Ext.create('Ext.FormPanel',{
        url:    'infoAction.csmp?method=addInfo',
        id:     'infoAddForm',
        //layout: 'form',
        //renderTo:Ext.getBody(),
        frame: false,
        title: 'Add Info',
        closable: false,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Info Information',
                defaultType: 'textfield',
                items: getInfoFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: infoAddForm,
            handler: function() {
            	infoAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getInfoPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: infoAddForm,
            handler: function() {
                Ext.getCmp('infoAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: infoAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getInfoPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(infoAddForm);
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
	return infoAddForm;
}
function getInfoFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'textfield',name:'infoId', fieldLabel: 'infoId' }, 
			{ xtype: 'textfield',name:'infoDetail', fieldLabel: 'infoDetail' }];
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
function getInfoGrid(store)
{
	var itemDeleter = new Extensive.grid.ItemDeleter();
    // create the grid
    var infoGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'infoGrid',
        store: store,
        columns: getInfoColumns(itemDeleter),
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
    infoGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
		//get row data
		rowData  = r;
	});
	
	infoGrid.on('dblClick',function() {
		var tabpanel = Ext.getCmp('doc-body').findById('docs-infoPanel');
		var infoAddForm = getInfoAddForm(tabpanel,store);
		var form = Ext.getCmp('infoAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'infoAction.csmp?method=modifyInfo';
		loadInfoFormData(form,rowData);
	});
    
    store.load();
    return infoGrid;
}
function getInfoColumns(itemDeleter)
{
	var columns = [{header: 'infoId', width: 120, dataIndex: 'infoid', sortable: true}, 
			{header: 'infoDetail', width: 120, dataIndex: 'infodetail', sortable: true}, 
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
function loadInfoFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('infoId').setValue(row.data.infoid);
	form.findField('infoDetail').setValue(row.data.infodetail);
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
function getInfoSearchField(store)
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
function getInfoStore()
{
	var store = new Ext.data.Store({
        // load using HTTP
        url: 'infoAction.csmp?method=promptExtInfoSearchSystemLevel',
        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'componentId',
               totalRecords: 'TotalResults'
           }, [
               // set up the fields mapping into the xml doc
               // The first needs mapping, the others are very basic
               {name: 'infoid', mapping: 'ItemAttributes > infoid'},
               'infodetail',
			   'componentId'
           ])
    });
	return store;
}
