/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

function getDcrReportAddForm(tabpanel,store)
{
	var dcrReportAddForm = Ext.create('Ext.FormPanel',{
        url:    'dcrReportAction.csmp?method=addDcrReport',
        id:     'dcrReportAddForm',
        //layout: 'form',
        frame: false,
        title: 'Add DCR Info',
        closable: false,

        items: [
            { xtype: 'textfield',name:'code', fieldLabel: 'Code' },
            { xtype: 'textarea',name:'desc', fieldLabel: 'Description',width:100 },
            { xtype: 'datefield',name:'date', fieldLabel: 'Date',allowDomMove:true,readOnly:true},
            { xtype: 'textfield',name:'session', fieldLabel: 'Session' },
            { xtype: 'textfield',name:'location', fieldLabel: 'Location' },
            { xtype: 'textfield',name:'doctor', fieldLabel: 'Doctor Name' }                            
        ],
		
        buttons: [{
            text: 'Save',
            scope: dcrReportAddForm,
            handler: function() {
                dcrReportAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        tabpanel.remove(0);
                        store.load();
                        tabpanel.add(getDcrReportPanel());
                        tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: dcrReportAddForm,
            handler: function() {
                Ext.getCmp('dcrReportAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: dcrReportAddForm,
        	handler: function(){
        		tabpanel.remove(0);
                store.load();
                tabpanel.add(getDcrReportPanel());
                tabpanel.doLayout(); 
        	}
        }
        ]
        
    });
	return dcrReportAddForm;
}
function getDcrReportPanel(){
	alert("dddd");
	var store = new Ext.data.Store({
        // load using HTTP
        url: 'dcrReportAction.csmp?method=promptExtDcrReportSearchSystemLevel',
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

	var itemDeleter = new Extensive.grid.ItemDeleter();
    // create the grid
    var dcrReportGrid = Ext.create('Ext.grid.GridPanel',{
    	id:'dcrReportGrid',
        store: store,
        columns: [
            {header: "code", width: 120, dataIndex: 'code', sortable: true},
            {header: "description", width: 180, dataIndex: 'description', sortable: true},
            {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
            //use this if item delete function is needed
            itemDeleter			
        ],
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
	
    // Search Field for DCR Info
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
	
	
	dcrReportGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r) {
		var tabpanel = Ext.getCmp('doc-body').findById('docs-dcrReportPanel');
		var dcrReportAddForm = getDcrReportAddForm(tabpanel,store);
		tabpanel.remove(0);
		tabpanel.add(dcrReportAddForm);
		tabpanel.doLayout();
		var form = Ext.getCmp('dcrReportAddForm').getForm();
		//url:    'dcrReportAction.csmp?method=adddcrReport',
		form.buttons[0].setText="Modify";
		form.url= 'dcrReportAction.csmp?method=modifyDcrReport';
		form.findField('code').setValue(r.data.code);
		form.findField('desc').setValue(r.data.description);
		form.findField('date').setValue('aa');
		form.findField('session').setValue('aa');
		form.findField('location').setValue('aa');
		form.findField('doctor').setValue('aa');
	});
    store.load();
	containerPanel = new Ext.Panel({
		id:'dcrReportDetail',
		//frame: true,
		title: 'DCR Info List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			dcrReportGrid
		],
		tbar: [
	            'Search: ', ' ',
	            new Ext.app.SearchField({
	                store: store,
	                width:320
	            })
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
					var tabpanel = Ext.getCmp('doc-body').findById('docs-dcrReportPanel');
					var dcrReportAddForm = getDcrReportAddForm(tabpanel,store);
					tabpanel.remove(0);
					tabpanel.add(dcrReportAddForm);
					tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				var tabpanel = Ext.getCmp('doc-body').findById('docs-dcrReportPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();
			}
		}]
	});
        
	return containerPanel;
}