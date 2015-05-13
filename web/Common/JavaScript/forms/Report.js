/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
Ext.require('Ext.ux.IFrame');
function getReportPanel(){
	var store = getReportStore();
	//var reportGrid = getReportGrid(store);
	//var search = getReportStore(store);
	var reportFrame = Ext.create("Ext.ux.IFrame", {
		id:'reportIFrame',
		height:400,
        title: 'Page Under Test',
        hideMode: 'offsets'
    });
	/*var reportFrame = new Ext.Panel({
    	xtype:'panel',
        id:'report-panel',
        title: 'Demo Home',
        height:200,
        layout:'fit',
        autoLoad: {url: './report/CrystalReport1-viewer.jsp',scope: this},
        iconCls:'icon-docs',
        autoScroll: true
	});*/
	
	containerPanel = new Ext.Panel({
		id:'reportDetail',
		//frame: true,
		title: 'report List',
		
		autoHeight : true,
		layout: 'fit',
		items:[reportFrame],
		/*items: [
			reportGrid
		],
		tbar: [
	            'Search: ', ' ',
	            getSearchField(store)
	        ]
	    */
	});
	/*
	var tabpanel = Ext.get('docs-reportPanel');
	tabpanel.on('resize',function(){
		reportGrid.setSize(tabpanel.getSize());
		reportGrid.doLayout();
	});
    */    
	
	return containerPanel;
}
function getReportAddForm(tabpanel,store)
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
	
	var reportAddForm = Ext.create('Ext.FormPanel',{
        url:    'reportAction.csmp?method=addReport',
        id:     'reportAddForm',
        //layout: 'form',
        //renderTo:Ext.getBody(),
        frame: false,
        title: 'Add Report',
        closable: false,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Report Information',
                defaultType: 'textfield',
                items: getReportFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: reportAddForm,
            handler: function() {
            	reportAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getReportPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: reportAddForm,
            handler: function() {
                Ext.getCmp('reportAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: reportAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getReportPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(reportAddForm);
	extjsWindow.show();
	return reportAddForm;
}
function getReportFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'numberfield',name:'empId',fieldLabel: 'empId',emptyText:'0'}, 
			{ xtype: 'textfield',name:'examTitle', fieldLabel: 'examTitle' }, 
			{ xtype: 'textfield',name:'instituteName', fieldLabel: 'instituteName' }, 
			{ xtype: 'textfield',name:'result', fieldLabel: 'result' }, 
			{ xtype: 'numberfield',name:'passingYear',fieldLabel: 'passingYear',emptyText:'0'}, 
			{ xtype: 'textfield',name:'note', fieldLabel: 'note' }];
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
function getReportGrid(store)
{
	// create the grid
    var reportGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'reportGrid',
        store: store,
        columns: getReportColumns(),
        viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    reportGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data
    	rowData  = record;
		var tabpanel = Ext.get('docs-reportPanel');
		// Basic mask:
    	var myMask = new Ext.LoadMask(tabpanel, {msg:"Please wait! Loading window..."});
    	myMask.show();
		var reportAddForm = getReportAddForm(tabpanel,store);
		var form = Ext.getCmp('reportAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		//form.buttons[0].setText("Modify");
		
		form.url= 'reportAction.csmp?method=modifyReport';
		loadReportFormData(form,rowData);
		myMask.hide();
		//alert('i::'+index);
		//set_combobox_value_from_store(getRoleFunctionStore(),form.findField('role'), 'id', index);
	});
	
	reportGrid.on('dblClick',function() {
		var tabpanel = Ext.get('docs-reportPanel');
		var reportAddForm = getReportAddForm(tabpanel,store);
		var form = Ext.getCmp('reportAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'reportAction.csmp?method=modifyReport';
		loadReportFormData(form,rowData);
	});
    
    store.load();
    return reportGrid;
}
function getReportColumns()
{
	var columns = [{header: 'empId', width: 120, dataIndex: 'empid', sortable: true}, 
			{header: 'examTitle', width: 120, dataIndex: 'examtitle', sortable: true}, 
			{header: 'instituteName', width: 120, dataIndex: 'institutename', sortable: true}, 
			{header: 'result', width: 120, dataIndex: 'result', sortable: true}, 
			{header: 'passingYear', width: 120, dataIndex: 'passingyear', sortable: true}, 
			{header: 'note', width: 120, dataIndex: 'note', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadReportFormData(form,row)
{
form.findField('componentId').setValue(row.data.componentId);
	form.findField('empId').setValue(row.data.empid);
	form.findField('examTitle').setValue(row.data.examtitle);
	form.findField('instituteName').setValue(row.data.institutename);
	form.findField('result').setValue(row.data.result);
	form.findField('passingYear').setValue(row.data.passingyear);
	form.findField('note').setValue(row.data.note);
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
function getReportSearchField(store)
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
function getReportStore()
{
	var url = 'academicinfoAction.csmp?method=promptExtAcademicinfoSearchSystemLevel';
	Ext.define('ReportModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId'
,'empid'
,'examtitle'
,'institutename'
,'result'
,'passingyear'
,'note'
]
	});
	var reportstore = Ext.create('Ext.data.Store', {
	    model: 'ReportModel',
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
	reportstore.load();
	return reportstore;
}
