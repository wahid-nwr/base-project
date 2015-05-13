/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getEmailPanel(){
	var store = getEmailStore();
	var emailGrid = getEmailGrid(store);
	var search = getEmailStore(store);
	containerPanel = new Ext.Panel({
		id:'emailDetail',
		//frame: true,
		title: 'email List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			emailGrid
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
					var tabpanel = Ext.get('docs-emailPanel');
					var emailAddForm = getEmailAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(emailAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-emailPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-emailPanel');
	tabpanel.on('resize',function(){
		emailGrid.setSize(tabpanel.getSize());
		emailGrid.doLayout();
	});
        
	return containerPanel;
}
function getEmailAddForm(tabpanel,store)
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
	
	var emailAddForm = Ext.create('Ext.FormPanel',{
        url:    'emailAction.csmp?method=addEmail',
        id:     'emailAddForm',
        l//ayout: 'form',
        //renderTo:Ext.getBody(),
        frame: false,
        title: 'Add Email',
        closable: false,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Email Information',
                defaultType: 'textfield',
                items: getEmailFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: emailAddForm,
            handler: function() {
            	emailAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getEmailPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: emailAddForm,
            handler: function() {
                Ext.getCmp('emailAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: emailAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getEmailPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(emailAddForm);
	extjsWindow.show();
	return emailAddForm;
}
function getEmailFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'textfield',name:'sender', fieldLabel: 'sender' }, 
			{ xtype: 'datefield',name:'sendDate',fieldLabel: 'sendDate',emptyText:'Enter Date',format:'Y-n-d H:i',readOnly:true}, 
			{ xtype: 'textfield',name:'subject', fieldLabel: 'subject' }, 
			{ xtype: 'textfield',name:'type', fieldLabel: 'type' }, 
			{ xtype: 'textfield',name:'eChecked', fieldLabel: 'eChecked' }, 
			{ xtype: 'textfield',name:'senderType', fieldLabel: 'senderType' }, 
			{ xtype: 'textfield',name:'others', fieldLabel: 'others' }, 
			{ xtype: 'textfield',name:'ossBy', fieldLabel: 'ossBy' }, 
			{ xtype: 'numberfield',name:'refId',fieldLabel: 'refId',emptyText:'0'}, 
			{ xtype: 'numberfield',name:'allRecipients',fieldLabel: 'allRecipients',emptyText:'0'}, 
			{ xtype: 'numberfield',name:'cc',fieldLabel: 'cc',emptyText:'0'}, 
			{ xtype: 'textfield',name:'recepient', fieldLabel: 'recepient' }, 
			{ xtype: 'numberfield',name:'internalUserId',fieldLabel: 'internalUserId',emptyText:'0'}, 
			{ xtype: 'textfield',name:'ossStatus', fieldLabel: 'ossStatus' }, 
			{ xtype: 'numberfield',name:'priority',fieldLabel: 'priority',emptyText:'0'}, 
			{ xtype: 'numberfield',name:'emailGroupId',fieldLabel: 'emailGroupId',emptyText:'0'}, 
			{ xtype: 'numberfield',name:'comId',fieldLabel: 'comId',emptyText:'0'}];
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
function getEmailGrid(store)
{
	// create the grid
    var emailGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'emailGrid',
        store: store,
        columns: getEmailColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    emailGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-emailPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var emailAddForm = getEmailAddForm(tabpanel,store);
		var form = Ext.getCmp('emailAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'emailAction.csmp?method=modifyEmail';
		loadEmailFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	emailGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-emailPanel');
		var emailAddForm = getEmailAddForm(tabpanel,store);
		var form = Ext.getCmp('emailAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'emailAction.csmp?method=modifyEmail';
		loadEmailFormData(form,rowData);
	});
    
    store.load();
    return emailGrid;
}
function getEmailColumns()
{
	var columns = [{header: 'sender', width: 120, dataIndex: 'sender', sortable: true}, 
			{header: 'sendDate', width: 120, dataIndex: 'senddate', sortable: true}, 
			{header: 'subject', width: 120, dataIndex: 'subject', sortable: true}, 
			{header: 'type', width: 120, dataIndex: 'type', sortable: true}, 
			{header: 'eChecked', width: 120, dataIndex: 'echecked', sortable: true}, 
			{header: 'senderType', width: 120, dataIndex: 'sendertype', sortable: true}, 
			{header: 'others', width: 120, dataIndex: 'others', sortable: true}, 
			{header: 'ossBy', width: 120, dataIndex: 'ossby', sortable: true}, 
			{header: 'refId', width: 120, dataIndex: 'refid', sortable: true}, 
			{header: 'allRecipients', width: 120, dataIndex: 'allrecipients', sortable: true}, 
			{header: 'cc', width: 120, dataIndex: 'cc', sortable: true}, 
			{header: 'recepient', width: 120, dataIndex: 'recepient', sortable: true}, 
			{header: 'internalUserId', width: 120, dataIndex: 'internaluserid', sortable: true}, 
			{header: 'ossStatus', width: 120, dataIndex: 'ossstatus', sortable: true}, 
			{header: 'priority', width: 120, dataIndex: 'priority', sortable: true}, 
			{header: 'emailGroupId', width: 120, dataIndex: 'emailgroupid', sortable: true}, 
			{header: 'comId', width: 120, dataIndex: 'comid', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadEmailFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('sender').setValue(row.data.sender);
	form.findField('sendDate').setValue(row.data.senddate);
	form.findField('subject').setValue(row.data.subject);
	form.findField('type').setValue(row.data.type);
	form.findField('eChecked').setValue(row.data.echecked);
	form.findField('senderType').setValue(row.data.sendertype);
	form.findField('others').setValue(row.data.others);
	form.findField('ossBy').setValue(row.data.ossby);
	form.findField('refId').setValue(row.data.refid);
	form.findField('allRecipients').setValue(row.data.allrecipients);
	form.findField('cc').setValue(row.data.cc);
	form.findField('recepient').setValue(row.data.recepient);
	form.findField('internalUserId').setValue(row.data.internaluserid);
	form.findField('ossStatus').setValue(row.data.ossstatus);
	form.findField('priority').setValue(row.data.priority);
	form.findField('emailGroupId').setValue(row.data.emailgroupid);
	form.findField('comId').setValue(row.data.comid);
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
function getEmailSearchField(store)
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
function getEmailStore()
{
	var url = 'emailAction.csmp?method=promptExtEmailSearchSystemLevel';
	Ext.define('EmailModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId'
,'sender'
,'senddate'
,'subject'
,'type'
,'echecked'
,'sendertype'
,'others'
,'ossby'
,'refid'
,'allrecipients'
,'cc'
,'recepient'
,'internaluserid'
,'ossstatus'
,'priority'
,'emailgroupid'
,'comid'
]
	});
	var emailstore = Ext.create('Ext.data.Store', {
	    model: 'EmailModel',
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
	emailstore.load();
	return emailstore;
}
