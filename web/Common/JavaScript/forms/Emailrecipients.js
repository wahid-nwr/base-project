/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getEmailrecipientsPanel(){
	var store = getEmailrecipientsStore();
	var emailrecipientsGrid = getEmailrecipientsGrid(store);
	var search = getEmailrecipientsStore(store);
	containerPanel = new Ext.Panel({
		id:'emailrecipientsDetail',
		//frame: true,
		title: 'emailrecipients List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			emailrecipientsGrid
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
					var tabpanel = Ext.get('docs-emailrecipientsPanel');
					var emailrecipientsAddForm = getEmailrecipientsAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(emailrecipientsAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-emailrecipientsPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-emailrecipientsPanel');
	tabpanel.on('resize',function(){
		emailrecipientsGrid.setSize(tabpanel.getSize());
		emailrecipientsGrid.doLayout();
	});
        
	return containerPanel;
}
function getEmailrecipientsAddForm(tabpanel,store)
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
	
	var emailrecipientsAddForm = Ext.create('Ext.FormPanel',{
        url:    'emailrecipientsAction.csmp?method=addEmailrecipients',
        id:     'emailrecipientsAddForm',
        layout: 'form',
        //renderTo:Ext.getBody(),
        frame: true,
        title: 'Add Emailrecipients',
        closable: true,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Emailrecipients Information',
                defaultType: 'textfield',
                items: getEmailrecipientsFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: emailrecipientsAddForm,
            handler: function() {
            	emailrecipientsAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getEmailrecipientsPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: emailrecipientsAddForm,
            handler: function() {
                Ext.getCmp('emailrecipientsAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: emailrecipientsAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getEmailrecipientsPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(emailrecipientsAddForm);
	extjsWindow.show();
	return emailrecipientsAddForm;
}
function getEmailrecipientsFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'textfield',name:'companyEmailAddress', fieldLabel: 'companyEmailAddress' }, 
			{ xtype: 'textfield',name:'messageId', fieldLabel: 'messageId' }, 
			{ xtype: 'numberfield',name:'contactId',fieldLabel: 'contactId',emptyText:'0'}, 
			{ xtype: 'textfield',name:'recipientsType', fieldLabel: 'recipientsType' }, 
			{ xtype: 'textfield',name:'emailStatus', fieldLabel: 'emailStatus' }, 
			{ xtype: 'numberfield',name:'emailRecvId',fieldLabel: 'emailRecvId',emptyText:'0'}, 
			{ xtype: 'textfield',name:'messageType', fieldLabel: 'messageType' }];
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
function getEmailrecipientsGrid(store)
{
	// create the grid
    var emailrecipientsGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'emailrecipientsGrid',
        store: store,
        columns: getEmailrecipientsColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    emailrecipientsGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-emailrecipientsPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var emailrecipientsAddForm = getEmailrecipientsAddForm(tabpanel,store);
		var form = Ext.getCmp('emailrecipientsAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'emailrecipientsAction.csmp?method=modifyEmailrecipients';
		loadEmailrecipientsFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	emailrecipientsGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-emailrecipientsPanel');
		var emailrecipientsAddForm = getEmailrecipientsAddForm(tabpanel,store);
		var form = Ext.getCmp('emailrecipientsAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'emailrecipientsAction.csmp?method=modifyEmailrecipients';
		loadEmailrecipientsFormData(form,rowData);
	});
    
    store.load();
    return emailrecipientsGrid;
}
function getEmailrecipientsColumns()
{
	var columns = [{header: 'companyEmailAddress', width: 120, dataIndex: 'companyemailaddress', sortable: true}, 
			{header: 'messageId', width: 120, dataIndex: 'messageid', sortable: true}, 
			{header: 'contactId', width: 120, dataIndex: 'contactid', sortable: true}, 
			{header: 'recipientsType', width: 120, dataIndex: 'recipientstype', sortable: true}, 
			{header: 'emailStatus', width: 120, dataIndex: 'emailstatus', sortable: true}, 
			{header: 'emailRecvId', width: 120, dataIndex: 'emailrecvid', sortable: true}, 
			{header: 'messageType', width: 120, dataIndex: 'messagetype', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadEmailrecipientsFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('companyEmailAddress').setValue(row.data.companyemailaddress);
	form.findField('messageId').setValue(row.data.messageid);
	form.findField('contactId').setValue(row.data.contactid);
	form.findField('recipientsType').setValue(row.data.recipientstype);
	form.findField('emailStatus').setValue(row.data.emailstatus);
	form.findField('emailRecvId').setValue(row.data.emailrecvid);
	form.findField('messageType').setValue(row.data.messagetype);
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
function getEmailrecipientsSearchField(store)
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
function getEmailrecipientsStore()
{
	var url = 'emailrecipientsAction.csmp?method=promptExtEmailrecipientsSearchSystemLevel';
	Ext.define('EmailrecipientsModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId'
,'companyemailaddress'
,'messageid'
,'contactid'
,'recipientstype'
,'emailstatus'
,'emailrecvid'
,'messagetype'
]
	});
	var emailrecipientsstore = Ext.create('Ext.data.Store', {
	    model: 'EmailrecipientsModel',
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
	emailrecipientsstore.load();
	return emailrecipientsstore;
}
