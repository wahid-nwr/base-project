/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getEmaildtlPanel(){
	var store = getEmaildtlStore();
	var emaildtlGrid = getEmaildtlGrid(store);
	var search = getEmaildtlStore(store);
	containerPanel = new Ext.Panel({
		id:'emaildtlDetail',
		//frame: true,
		title: 'emaildtl List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			emaildtlGrid
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
					var tabpanel = Ext.get('docs-emaildtlPanel');
					var emaildtlAddForm = getEmaildtlAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(emaildtlAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-emaildtlPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-emaildtlPanel');
	tabpanel.on('resize',function(){
		emaildtlGrid.setSize(tabpanel.getSize());
		emaildtlGrid.doLayout();
	});
        
	return containerPanel;
}
function getEmaildtlAddForm(tabpanel,store)
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
	
	var emaildtlAddForm = Ext.create('Ext.FormPanel',{
        url:    'emaildtlAction.csmp?method=addEmaildtl',
        id:     'emaildtlAddForm',
        layout: 'form',
        //renderTo:Ext.getBody(),
        frame: true,
        title: 'Add Emaildtl',
        closable: true,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Emaildtl Information',
                defaultType: 'textfield',
                items: getEmaildtlFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: emaildtlAddForm,
            handler: function() {
            	emaildtlAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getEmaildtlPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: emaildtlAddForm,
            handler: function() {
                Ext.getCmp('emaildtlAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: emaildtlAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getEmaildtlPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(emaildtlAddForm);
	extjsWindow.show();
	return emaildtlAddForm;
}
function getEmaildtlFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'numberfield',name:'emailId',fieldLabel: 'emailId',emptyText:'0'}, 
			{ xtype: 'textfield',name:'contentType', fieldLabel: 'contentType' }, 
			{ xtype: 'numberfield',name:'content',fieldLabel: 'content',emptyText:'0'}];
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
function getEmaildtlGrid(store)
{
	// create the grid
    var emaildtlGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'emaildtlGrid',
        store: store,
        columns: getEmaildtlColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    emaildtlGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-emaildtlPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var emaildtlAddForm = getEmaildtlAddForm(tabpanel,store);
		var form = Ext.getCmp('emaildtlAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'emaildtlAction.csmp?method=modifyEmaildtl';
		loadEmaildtlFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	emaildtlGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-emaildtlPanel');
		var emaildtlAddForm = getEmaildtlAddForm(tabpanel,store);
		var form = Ext.getCmp('emaildtlAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'emaildtlAction.csmp?method=modifyEmaildtl';
		loadEmaildtlFormData(form,rowData);
	});
    
    store.load();
    return emaildtlGrid;
}
function getEmaildtlColumns()
{
	var columns = [{header: 'emailId', width: 120, dataIndex: 'emailid', sortable: true}, 
			{header: 'contentType', width: 120, dataIndex: 'contenttype', sortable: true}, 
			{header: 'content', width: 120, dataIndex: 'content', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadEmaildtlFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('emailId').setValue(row.data.emailid);
	form.findField('contentType').setValue(row.data.contenttype);
	form.findField('content').setValue(row.data.content);
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
function getEmaildtlSearchField(store)
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
function getEmaildtlStore()
{
	var url = 'emaildtlAction.csmp?method=promptExtEmaildtlSearchSystemLevel';
	Ext.define('EmaildtlModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId'
,'emailid'
,'contenttype'
,'content'
]
	});
	var emaildtlstore = Ext.create('Ext.data.Store', {
	    model: 'EmaildtlModel',
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
	emaildtlstore.load();
	return emaildtlstore;
}
