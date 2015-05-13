/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
function getEmailrecvPanel(){
	var store = getEmailrecvStore();
	var emailrecvGrid = getEmailrecvGrid(store);
	var search = getEmailrecvStore(store);
	containerPanel = new Ext.Panel({
		id:'emailrecvDetail',
		//frame: true,
		title: 'emailrecv List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			emailrecvGrid
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
					var tabpanel = Ext.get('docs-emailrecvPanel');
					var emailrecvAddForm = getEmailrecvAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(emailrecvAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.get('docs-emailrecvPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
	
	var tabpanel = Ext.get('docs-emailrecvPanel');
	tabpanel.on('resize',function(){
		emailrecvGrid.setSize(tabpanel.getSize());
		emailrecvGrid.doLayout();
	});
        
	return containerPanel;
}
function getEmailrecvAddForm(tabpanel,store)
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
	
	var emailrecvAddForm = Ext.create('Ext.FormPanel',{
        url:    'emailrecvAction.csmp?method=addEmailrecv',
        id:     'emailrecvAddForm',
        layout: 'form',
        //renderTo:Ext.getBody(),
        frame: true,
        title: 'Add Emailrecv',
        closable: true,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Emailrecv Information',
                defaultType: 'textfield',
                items: getEmailrecvFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: emailrecvAddForm,
            handler: function() {
            	emailrecvAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getEmailrecvPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: emailrecvAddForm,
            handler: function() {
                Ext.getCmp('emailrecvAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: emailrecvAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getEmailrecvPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(emailrecvAddForm);
	extjsWindow.show();
	return emailrecvAddForm;
}
function getEmailrecvFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'textfield',name:'companyEmailAddress', fieldLabel: 'companyEmailAddress' }, 
			{ xtype: 'textfield',name:'popHost', fieldLabel: 'popHost' }, 
			{ xtype: 'textfield',name:'otherPartyEmailAddress', fieldLabel: 'otherPartyEmailAddress' }, 
			{ xtype: 'numberfield',name:'isSSL',fieldLabel: 'isSSL',emptyText:'0'}, 
			{ xtype: 'numberfield',name:'popPort',fieldLabel: 'popPort',emptyText:'0'}, 
			{ xtype: 'textfield',name:'password', fieldLabel: 'password' }, 
			{ xtype: 'textfield',name:'taggedUserId', fieldLabel: 'taggedUserId' }, 
			{ xtype: 'textfield',name:'smtpHost', fieldLabel: 'smtpHost' }, 
			{ xtype: 'numberfield',name:'smtpPort',fieldLabel: 'smtpPort',emptyText:'0'}, 
			{ xtype: 'textfield',name:'displayName', fieldLabel: 'displayName' }];
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
function getEmailrecvGrid(store)
{
	// create the grid
    var emailrecvGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'emailrecvGrid',
        store: store,
        columns: getEmailrecvColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    emailrecvGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-emailrecvPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var emailrecvAddForm = getEmailrecvAddForm(tabpanel,store);
		var form = Ext.getCmp('emailrecvAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'emailrecvAction.csmp?method=modifyEmailrecv';
		loadEmailrecvFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	emailrecvGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-emailrecvPanel');
		var emailrecvAddForm = getEmailrecvAddForm(tabpanel,store);
		var form = Ext.getCmp('emailrecvAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'emailrecvAction.csmp?method=modifyEmailrecv';
		loadEmailrecvFormData(form,rowData);
	});
    
    store.load();
    return emailrecvGrid;
}
function getEmailrecvColumns()
{
	var columns = [{header: 'companyEmailAddress', width: 120, dataIndex: 'companyemailaddress', sortable: true}, 
			{header: 'popHost', width: 120, dataIndex: 'pophost', sortable: true}, 
			{header: 'otherPartyEmailAddress', width: 120, dataIndex: 'otherpartyemailaddress', sortable: true}, 
			{header: 'isSSL', width: 120, dataIndex: 'isssl', sortable: true}, 
			{header: 'popPort', width: 120, dataIndex: 'popport', sortable: true}, 
			{header: 'password', width: 120, dataIndex: 'password', sortable: true}, 
			{header: 'taggedUserId', width: 120, dataIndex: 'taggeduserid', sortable: true}, 
			{header: 'smtpHost', width: 120, dataIndex: 'smtphost', sortable: true}, 
			{header: 'smtpPort', width: 120, dataIndex: 'smtpport', sortable: true}, 
			{header: 'displayName', width: 120, dataIndex: 'displayname', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadEmailrecvFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('companyEmailAddress').setValue(row.data.companyemailaddress);
	form.findField('popHost').setValue(row.data.pophost);
	form.findField('otherPartyEmailAddress').setValue(row.data.otherpartyemailaddress);
	form.findField('isSSL').setValue(row.data.isssl);
	form.findField('popPort').setValue(row.data.popport);
	form.findField('password').setValue(row.data.password);
	form.findField('taggedUserId').setValue(row.data.taggeduserid);
	form.findField('smtpHost').setValue(row.data.smtphost);
	form.findField('smtpPort').setValue(row.data.smtpport);
	form.findField('displayName').setValue(row.data.displayname);
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
function getEmailrecvSearchField(store)
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
function getEmailrecvStore()
{
	var url = 'emailrecvAction.csmp?method=promptExtEmailrecvSearchSystemLevel';
	Ext.define('EmailrecvModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId'
,'companyemailaddress'
,'pophost'
,'otherpartyemailaddress'
,'isssl'
,'popport'
,'password'
,'taggeduserid'
,'smtphost'
,'smtpport'
,'displayname'
]
	});
	var emailrecvstore = Ext.create('Ext.data.Store', {
	    model: 'EmailrecvModel',
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
	emailrecvstore.load();
	return emailrecvstore;
}
