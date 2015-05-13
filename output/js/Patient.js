/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getPatientPanel(){
	var store = getPatientStore();
	var patientGrid = getPatientGrid(store);
	var search = getPatientStore(store);
	containerPanel = new Ext.Panel({
		id:'patientDetail',
		//frame: true,
		title: 'patient List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			patientGrid
		],
		tbar: [
	            'Search: ', ' ',
	            getPatientSearchField(store)
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
					var tabpanel = Ext.getCmp('doc-body').findById('docs-patientPanel');
					var patientAddForm = getPatientAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(patientAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.getCmp('doc-body').findById('docs-patientPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.getCmp('doc-body').findById('docs-patientPanel');
	tabpanel.on('resize',function(){
		patientGrid.setSize(tabpanel.getSize());
		patientGrid.doLayout();
	});
        
	return containerPanel;
}
function getPatientAddForm(tabpanel,store)
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
	
	var patientAddForm = new Ext.FormPanel
	({
        url:    'patientAction.csmp?method=addSpatient',
        id:     'patientAddForm',
        layout: 'form',
        //renderTo:Ext.getBody(),
        frame: true,
        title: 'Add Patient',
        closable: true,
        autoScroll:true,
        items: [
            new Ext.form.FieldSet({
                title: 'Patient Information',
                defaultType: 'textfield',
                items: getFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: patientAddForm,
            handler: function() {
            	patientAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getPatientPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: patientAddForm,
            handler: function() {
                Ext.getCmp('patientAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: patientAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getPatientPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(patientAddForm);
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
	return patientAddForm;
}
function getFieldSet()
{
	var fieldSet = [{ xtype: 'textfield',name:'patientId', fieldLabel: 'patientId' }, 
			{ xtype: 'textfield',name:'name', fieldLabel: 'name' }, 
			{ xtype: 'textfield',name:'birthDate', fieldLabel: 'birthDate' }, 
			{ xtype: 'textfield',name:'age', fieldLabel: 'age' }, 
			{ xtype: 'textfield',name:'mobileNo', fieldLabel: 'mobileNo' }, 
			{ xtype: 'textfield',name:'email', fieldLabel: 'email' }, 
			{ xtype: 'textfield',name:'address', fieldLabel: 'address' }];
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
function getPatientGrid(store)
{
	var itemDeleter = new Extensive.grid.ItemDeleter();
    // create the grid
    var patientGrid = new Ext.grid.GridPanel({
    	id:'patientGrid',
        store: store,
        columns: getColumns(itemDeleter),
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
    patientGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
		//get row data
		rowData  = r;
	});
	
	patientGrid.on('dblClick',function() {
		var tabpanel = Ext.getCmp('doc-body').findById('docs-patientPanel');
		var patientAddForm = getPatientAddForm(tabpanel,store);
		var form = Ext.getCmp('patientAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'patientAction.csmp?method=modifyPatient';
		loadFormData(form,rowData);
	});
    
    store.load();
    return patientGrid;
}
function getColumns(itemDeleter)
{
	var columns = [{header: 'patientId', width: 120, dataIndex: 'patientId', sortable: true}, 
			{header: 'name', width: 120, dataIndex: 'name', sortable: true}, 
			{header: 'birthDate', width: 120, dataIndex: 'birthDate', sortable: true}, 
			{header: 'age', width: 120, dataIndex: 'age', sortable: true}, 
			{header: 'mobileNo', width: 120, dataIndex: 'mobileNo', sortable: true}, 
			{header: 'email', width: 120, dataIndex: 'email', sortable: true}, 
			{header: 'address', width: 120, dataIndex: 'address', sortable: true}, 
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
function loadFormData(form,row)
{
	form.findField('patientId').setValue(row.data.patientId);
	form.findField('name').setValue(row.data.name);
	form.findField('birthDate').setValue(row.data.birthDate);
	form.findField('age').setValue(row.data.age);
	form.findField('mobileNo').setValue(row.data.mobileNo);
	form.findField('email').setValue(row.data.email);
	form.findField('address').setValue(row.data.address);
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
function getPatientSearchField(store)
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
function getPatientStore()
{
	var store = new Ext.data.Store({
        // load using HTTP
        url: 'patientAction.csmp?method=promptExtPatientSearchSystemLevel',
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
               'description',
			   'updatedby'
           ])
    });
	return store;
}
