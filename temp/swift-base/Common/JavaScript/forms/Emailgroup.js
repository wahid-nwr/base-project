/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getEmailgroupPanel(){
	var store = getEmailgroupStore();
	var emailgroupGrid = getEmailgroupGrid(store);
	var search = getEmailgroupStore(store);
	containerPanel = new Ext.Panel({
		id:'emailgroupDetail',
		//frame: true,
		title: 'emailgroup List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			emailgroupGrid
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
					var tabpanel = Ext.get('docs-emailgroupPanel');
					var emailgroupAddForm = getEmailgroupAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(emailgroupAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-emailgroupPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-emailgroupPanel');
	tabpanel.on('resize',function(){
		emailgroupGrid.setSize(tabpanel.getSize());
		emailgroupGrid.doLayout();
	});
        
	return containerPanel;
}
function getEmailgroupAddForm(tabpanel,store)
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
	
	var emailgroupAddForm = Ext.create('Ext.FormPanel',{
        url:    'emailgroupAction.csmp?method=addEmailgroup',
        id:     'emailgroupAddForm',
        layout: 'form',
        //renderTo:Ext.getBody(),
        frame: true,
        title: 'Add Emailgroup',
        closable: true,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Emailgroup Information',
                defaultType: 'textfield',
                items: getEmailgroupFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: emailgroupAddForm,
            handler: function() {
            	emailgroupAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getEmailgroupPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: emailgroupAddForm,
            handler: function() {
                Ext.getCmp('emailgroupAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: emailgroupAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getEmailgroupPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(emailgroupAddForm);
	extjsWindow.show();
	return emailgroupAddForm;
}
function getEmailgroupFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'textfield',name:'groupName', fieldLabel: 'groupName' }];
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
function getEmailgroupGrid(store)
{
	// create the grid
    var emailgroupGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'emailgroupGrid',
        store: store,
        columns: getEmailgroupColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    emailgroupGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-emailgroupPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var emailgroupAddForm = getEmailgroupAddForm(tabpanel,store);
		var form = Ext.getCmp('emailgroupAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'emailgroupAction.csmp?method=modifyEmailgroup';
		loadEmailgroupFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	emailgroupGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-emailgroupPanel');
		var emailgroupAddForm = getEmailgroupAddForm(tabpanel,store);
		var form = Ext.getCmp('emailgroupAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'emailgroupAction.csmp?method=modifyEmailgroup';
		loadEmailgroupFormData(form,rowData);
	});
    
    store.load();
    return emailgroupGrid;
}
function getEmailgroupColumns()
{
	var columns = [{header: 'groupName', width: 120, dataIndex: 'groupname', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadEmailgroupFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('groupName').setValue(row.data.groupname);
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
function getEmailgroupSearchField(store)
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
function getEmailgroupStore()
{
	var url = 'emailgroupAction.csmp?method=promptExtEmailgroupSearchSystemLevel';
	Ext.define('EmailgroupModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId'
,'groupname'
]
	});
	var emailgroupstore = Ext.create('Ext.data.Store', {
	    model: 'EmailgroupModel',
	    pageSize: 20,
	    proxy: {
	        type: 'ajax',
	        url: url,
	        reader: {
	            type: 'xml',
	            record: 'Item',
	            root: 'Items'
	        }
	    }
	});
	emailgroupstore.load();
	return emailgroupstore;
}
