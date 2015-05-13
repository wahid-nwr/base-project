function getSearchField(store,fieldName)
{
	if(fieldName!=null && fieldName.length>0 && fieldName!='null')
	{}
	else
	{
		fieldName = 'searchInput';
	}
	//Ext.app.SearchField = Ext.extend(Ext.form.TwinTriggerField, {
	Ext.SearchField = Ext.define('Ext.ux.form.SearchField', {
	    extend: 'Ext.form.field.Trigger',
	    
	    alias: 'widget.searchfield',
	    
	    trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
	    
	    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
	    
	    hasSearch : false,
	    paramName : fieldName,
	    
	    initComponent: function(){
	        this.callParent(arguments);
	        this.on('specialkey', function(f, e){
	            if(e.getKey() == e.ENTER){
	                this.onTrigger2Click();
	            }
	        }, this);
	    },
	    
	    afterRender: function(){
	        this.callParent();
	        this.triggerEl.item(0).setDisplayed('none');  
	    },
	    
	    onTrigger1Click : function(){
	        var me = this,
	            store = me.store,
	            proxy = store.getProxy(),
	            val;
	            
	        if (me.hasSearch) {
	            me.setValue('');
	            proxy.extraParams[me.paramName] = '';
	            proxy.extraParams.start = 0;
	            store.load();
	            me.hasSearch = false;
	            me.triggerEl.item(0).setDisplayed('none');
	            me.doComponentLayout();
	        }
	    },


	    onTrigger2Click : function(){
	        var me = this,
	            store = me.store,
	            proxy = store.getProxy(),
	            value = me.getValue();
	            
	        if (value.length < 1) {
	            me.onTrigger1Click();
	            return;
	        }
	        proxy.extraParams[me.paramName] = value;
	        proxy.extraParams.start = 0;
	        store.load();
	        me.hasSearch = true;
	        me.triggerEl.item(0).setDisplayed('block');
	        me.doComponentLayout();
	    }
	});
	
	var SearchField = new Ext.SearchField({
        store: store,
        width:320
    });
	return SearchField;
}
function getTranTravelscheduleColumns()
{
	var columns = [{header: 'time', width: 120, dataIndex: 'time', sortable: true}, 
			{header: 'scheduleDate', width: 120, dataIndex: 'scheduledate', sortable: true}, 
			{header: 'isAuto', width: 120, dataIndex: 'isauto', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}

function getTranItemColumns()
{
	var columns = [{header: 'itemId', width: 120, dataIndex: 'itemid', sortable: true}, 
	   			{header: 'itemName', width: 120, dataIndex: 'itemname', sortable: true}, 
	   			{header: 'parent', width: 120, dataIndex: 'parent', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}

function getTranBatchColumns()
{
	var columns = [{header: 'batchId', width: 120, dataIndex: 'batchid', sortable: true}, 
		   			{header: 'batchName', width: 120, dataIndex: 'batchname', sortable: true}, 
		   			{header: 'expireDate', width: 120, dataIndex: 'expiredate', sortable: true}, 
		   			{header: 'manufactureDate', width: 120, dataIndex: 'manufacturedate', sortable: true}];
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function getPopupGridStore(e,ownerForm,winType,field,dataurl,model,fields)
{
	var allFields = [];
	for(var i=0;i<fields.length;i++ )
	{
		allFields.push(fields[i]);
	}
		
	Ext.define(model, {
	    extend: 'Ext.data.Model',
	    fields: allFields
	});
	var store = Ext.create('Ext.data.Store', {
	    model: model,
	    pageSize: 20,
	    proxy: {
	        type: 'ajax',
	        url: dataurl,
	        reader: {
	            type: 'xml',
	            record: 'Item',
	            root: 'Items',
	            totalProperty: 'TotalResults'
	        }
	    }
	});
	return store;
}
function getPopupWindowTree(dataurl,winType,field,voucherType)
{
	var store = getPopupWindowTreeStore(dataurl);	
	var tree = Ext.create('Ext.tree.Panel', {
		   store: store,
		   rootVisible: false,
		   useArrows: true,
		   //autoHeight:true,
		   //frame: true,
		   bodyStyle: 'padding:5px;',
		   layout: 'fit',
		   forceFit: true,
		   viewConfig: { forceFit: true },
		   //containerScroll:true,
		   //autoScroll:true,
		   //manageHeight:true,		   
		   title: 'Tree Items'
		});
	tree.on('itemexpand',function(){
		//alert('eee');
		var height = this.body.getHeight(true);
		//tree.setHeight(height);
		//tree.expand(true);
		tree.doLayout();
	});
	var selModel = tree.getSelectionModel();
	selModel.on('selectionChange',function(selModel,selected,opts){
		var selNode = selModel.getLastSelected();
		if(selNode!=null)
		{
			var form = Ext.getCmp(voucherType+'TransactionAddForm').getForm();
			var id = (selNode.id).substr((selNode.id).lastIndexOf('-')+1,(selNode.id).length);
			form.findField(field).setValue(id);		
			var win = Ext.getCmp(winType+'PopupWin');
			win.close();
		}
	});
	return tree;
}
function getPopupWindowTreeStore(dataurl)
{
	var store = Ext.create('Ext.data.TreeStore', {
		pageSize: 20,
	    proxy: {
            type: 'ajax',
            url: dataurl
        },
        root: {
            text: 'Ext JS',
            id: 'root',
            expanded: true
        }
		});
	return store;
}
function getPopupWindowGrid(e,ownerForm,winType,field,dataurl,model,fields,isGrid,voucherType)
{
	var store = getPopupGridStore(e,ownerForm,winType,field,dataurl,model,fields);	
	store.load();
	var columns = null;
	if(winType=='account')
	{
		columns = getTranTravelscheduleColumns();
	}
	else if(winType=='batch')
	{
		columns = getTranBatchColumns();
	}
	else if(winType=='item')
	{
		columns = getTranItemColumns();
	}
	var grid = Ext.create('Ext.grid.Panel', {
    	id:winType+'PopupGrid',
    	store: store,
        columns: columns,
        selType: 'rowmodel',
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    grid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data    	
		rowData  = record;
		var form = Ext.getCmp(voucherType+'TransactionAddForm').getForm();
    	form.findField(field).setValue(rowData.data.componentId);
    	var win = Ext.getCmp(winType+'PopupWin');
		win.close();
    });
    return grid;
}
function getWindowItems(e,ownerForm,winType,field,dataurl,model,fields,isGrid,voucherType)
{	
	var windowItem = null;
	if(isGrid)
	{
		windowItem = getPopupWindowGrid(e,ownerForm,winType,field,dataurl,model,fields,isGrid,voucherType);
	}
	else
	{
		windowItem = getPopupWindowTree(dataurl,winType,field,voucherType);
	}
	return windowItem;
}
function getPickerPopupByType(e,ownerForm,winType,field,dataurl,model,fields,isGrid,voucherType)
{
	//alert(fields.toString());
	
	
	//var itemDeleter = new Extensive.grid.ItemDeleter();
	
	var windowItem = null;
	
	windowItem = getWindowItems(e,ownerForm,winType,field,dataurl,model,fields,isGrid,voucherType);
		
	
    var win = Ext.create('Ext.window.Window',{
    	id:winType+'PopupWin',
		width:200,
		height:300,
		bodyStyle: 'padding:5px;',
		layout: 'fit',
		items:windowItem,
		modal:true,
		detachOnRemove:true,
		plain:true,
		animate:true
	});
    win.on('resize',function(){
    	windowItem.doLayout();
    });
    win.on('beforedestroy',function(){
    	//alert('destroyed');
    });
	win.showAt(e.getXY())
}