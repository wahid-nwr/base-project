/*
 * Ext JS Library 2.2.1
 * Copyright(c) 2006-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
Ext.require([
    'Ext.form.*'
    ]);


function getPurchaseTransactionPanel(){
	voucherType = 'purchase';
	var store = getPurchaseTransactionStore();
	var purchaseTransactionGrid = getPurchaseTransactionGrid(store);

	var containerPanel = Ext.create('Ext.panel.Panel', {
		id:'purchaseTransactionDetail',
		//frame: true,
		title: 'purchaseTransaction List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			purchaseTransactionGrid
		],
		tbar: [
	            'Search: ', ' ',
	            getSearchField(store,'')
	        ],
        buttons:[{
			text:"ADD",
				handler:function() {
					var tabpanel = Ext.get('docs-purchaseTransactionPanel');
					var purchaseTransactionAddForm = getPurchaseTransactionAddForm(tabpanel,store);
					//tabpanel.remove(0);
					//tabpanel.add(purchaseTransactionAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				/*var tabpanel = Ext.getCmp('doc-body').findById('docs-purchaseTransactionPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();*/
			}
		}]
	});
	/*
	var tabpanel = Ext.getCmp('doc-body').findById('docs-purchaseTransactionPanel');
	tabpanel.on('resize',function(){
		purchaseTransactionGrid.setSize(tabpanel.getSize());
		purchaseTransactionGrid.doLayout();
	});
    */    
	return containerPanel;
}
function calculateTotalPayable(index)
{
	//var currency =document.getElementsByName("currency")[0].value;
    var qty_ = document.getElementsByName('quantity['+index+']')[0].value;
    //alert('qty_::'+qty_);
    var price_ = document.getElementsByName('unitPrice['+index+']')[0].value;
    var vat_ = document.getElementsByName('vat['+index+']')[0].value;
    var total= document.getElementsByName('totalPayable['+index+']')[0];
    //alert('total::'+total);
    
    var qty = qty_;
    var price=price_;
    var vat=vat_;

    if(isNaN(qty) || qty=='')
    {
        return;
    }
    if(isNaN(price) || price=='')
    {
        return;
    }
    if(isNaN(vat)||vat=='')
    {
        vat=0;
    }
    var x =qty*price;
    var vatAmount = (Number(qty_)*Number(price_)*Number(vat_))/100;
    if(vat>=0)
    {
        x = x*(1+(vat/100)) ;
    }
    //alert('kk::'+Number(x).toFixed(2))
    var form = Ext.getCmp('purchaseTransactionAddForm').getForm();
    form.findField('totalVat['+index+']').setValue(vatAmount);
    form.findField('totalPayable['+index+']').setValue(Number(x).toFixed(2));
    resetTotalAmount();
    //total.value =Number(x).toFixed(2);
}
function resetTotalAmount()
{
	var form = Ext.getCmp('purchaseTransactionAddForm').getForm();
	var rowIndices = form.findField('rowIndices').getValue();
	var indices = rowIndices.split(',');
	var totalAmount = 0.00;
	for(var i=0;i<indices.length;i++)
	{
		var total_= form.findField('totalPayable['+indices[i]+']').getValue();
        var a=Number(total_);
        totalAmount+=a;
	}
	form.findField('total').setValue(totalAmount);
	resetDue();
}
function resetDue()
{
	var form = Ext.getCmp('purchaseTransactionAddForm').getForm();
	var totalAmount = form.findField('total').getValue();
	var paid = form.findField('paid').getValue();
	var due = 0.00;
	if(isNaN(paid))
	{
		paid = 0.00;
		form.findField('paid').setValue(paid);
	}
	due = totalAmount - paid;
	due = (Number(due)).toFixed(2);
	form.findField('due').setValue(due);
}
function getPurchaseTransactionAddForm(tabpanel,store)
{
	//alert('in getPurchaseTransactionAddForm')
	/*var extDateField = new Ext.form.DateField({
		"id" : 'extDateFieldId',
		"width" : 200,
		"format" : 'd M Y'
	});*/
	var deleteRow = function(obj,e,index)
	{
		var form = Ext.getCmp('purchaseTransactionAddForm').getForm();
		var rowIndices = form.findField('rowIndices').getValue();
		var objId = obj.id;
		var index = objId.substr(objId.indexOf('[')+1,objId.indexOf(']'));
		index = parseInt(index);
		var indices = rowIndices.split(',');
		var indicesToSave = '';
		for(var i=0;i<indices.length;i++)
		{
			if(parseInt(indices[i])==index)
			{
				//alert('delete this index::'+index)
			}
			else
			{
				if(indicesToSave.length>0)
				{
					indicesToSave = indicesToSave+','+indices[i];
				}
				else
				{
					indicesToSave = ''+indices[i];
				}
			}
		}
		form.findField('rowIndices').setValue(indicesToSave);
		resetTotalAmount();
		obj.up('#itemList').remove(obj.up('fieldcontainer').id);
	}
	var inputChanged = function(element)
	{
		var name = (element.getTarget()).getAttribute('name');
		var index = name.substr(name.indexOf('[')+1,name.indexOf(']'));
		calculateTotalPayable(index);
	}
	
	var addRow = function (batch, item,qty,unitPrice,vat,totalVat,totalPayable,index) {
		var form = Ext.getCmp('purchaseTransactionAddForm');
		var rowIndices = form.getForm().findField('rowIndices');
		var value = rowIndices.getValue();
		if(value.length>0)
		{
			value = value+','+index;
		}
		else
		{
			value = index;
		}
		var ownerForm = Ext.getCmp('purchaseTransactionAddForm').getForm();
		rowIndices.setValue(value);
		form.down('#itemList').add([{
		//Ext.get('edu_items').add([{
		//this.getFormPanel().down('#edu_items').add([{
			xtype: 'fieldcontainer',
			height: 22,
			layout: 'hbox',
			defaultType: 'textfield',
			items: [{
				xtype:'pickerfield',
				name: 'batch['+index+']',
				editable:false,
				"onTriggerClick":function(e){
					//alert('name::'+e.getParent()+ ' ' +e.getTarget());					
					var fields = ['batchid','batchname','expiredate','manufacturedate','componentId'];
					getPickerPopupByType(e,ownerForm,'batch','batch['+index+']','batchAction.csmp?method=promptExtBatchSearchSystemLevel','BatchPopupModel',fields,true,voucherType);
				
				},
				value: batch,
				listeners:{
					blur: {
						fn: function (event, el, opts) {
							inputChanged(el);
						}
					}
				},
				flex: 1
			}, {
				xtype: 'splitter'
			}, {
				xtype:'pickerfield',
				name: 'item['+index+']',
				editable:false,
				"onTriggerClick":function(e){
					//alert('name::'+e.getParent()+ ' ' +e.getTarget());
					
					var fields = ['itemid','itemname','parent','componentId'];
					getPickerPopupByType(e,ownerForm,'item','item['+index+']','itemAction.csmp?method=promptExtItemSearchSystemLevel','ItemPopupModel',fields,true,voucherType);
				
				},
				value: item,
				listeners:{
					blur: {
						fn: function (event, el, opts) {
							inputChanged(el);
						}
					}
				},
				flex: 2
			}, {
				xtype: 'splitter'
			}, {
				name: 'quantity['+index+']',
				value: qty,
				listeners:{
					blur: {
						fn: function (event, el, opts) {
							inputChanged(el);
						}
					}
				},
				flex: 1
			}, {
				xtype: 'splitter'
			}, {
				name: 'unitPrice['+index+']',
				value: unitPrice,
				listeners:{
					blur: {
						fn: function (event, el, opts) {
							inputChanged(el);
						}
					}
				},
				flex: 1
			}, {
				xtype: 'splitter'
			}, {
				name: 'vat['+index+']',
				value: vat,
				style: 'text-align:right',
				listeners:{
					blur: {
						fn: function (event, el, opts) {
							inputChanged(el);
						}
					}
				},
				flex: 1
			}, {
				xtype: 'splitter'
			}, {
				name: 'totalVat['+index+']',
				readOnly:true,
				value: totalVat,
				flex: 1
			}, {
				xtype: 'splitter'
			}, {
				name: 'totalPayable['+index+']',
				readOnly:true,
				value: totalPayable,
				flex: 1
			}, {
				xtype: 'splitter'
			}, {
				xtype: 'button',
				id: 'delete['+index+']',
				sid:'delete',
				text: 'Delete',
				handler:function(e)
				{
					deleteRow(this,e);
				},
				flex: 1
			}]
		}]);
	}
	 	
	var MyField = Ext.create('Ext.form.field.Base',{
		name:'accountPicker',
	    flex: 1
	});
			
	var extjsWindow = Ext.create('Ext.window.Window', {
					id:'fs2col-win',
					width:700,
					minWidth:580,
					height:300,
					minHeight:140,
					layout:'fit',
					containerScroll:true,
					scroll:true,
					border:false,
					closable:true,
					maximizable:true,
					maximized:true,
					title:'Tow column window',
					items:[
					{
						// bounding form
				        id:'purchaseTransactionAddForm',
				        xtype:'form',
				        frame:true,
				        autoShow:true,
				        containerScroll:true,
						autoScroll:true,
				        labelWidth:50,	
				        bodyStyle:'padding:0 20px 0 0',
				    	defaultType: 'textfield',
				        // these are applied to columns
				        			
				        items: [{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' },
				                { xtype: 'hidden',name:'index', fieldLabel: 'index',value:0 },
				                { xtype: 'hidden',name:'rowIndices', fieldLabel: 'rowIndices' },
				                { xtype: 'hidden',name:'voucherType', fieldLabel: 'voucherType',value:'purchase' },
		                {
		                	xtype: 'fieldset',
							height:'auto',
							layout: 'hbox',
							
							items: [{								        	
									xtype: 'fieldset',
									height:'auto',
									layout: 'hbox',
									flex:1,
									items: [{
										xtype: 'fieldcontainer',
										layout: 'anchor',
										margin: '10',
										defaultType: 'textfield',
										flex:1,
										items: 
										[{
											xtype:'textfield',
											fieldLabel: 'Voucher No',
											emptyText: 'Enter Voucher No',
											name: 'voucherNo'
										},{
											xtype:'textfield',
											fieldLabel: 'Purchase From',
											name: 'purchaseFrom'
										},{
									   		fieldLabel: 'Due Approved By',
											emptyText: 'Choose due approved by',
											name: 'dueApprovedBy'
										}]
									},{
										xtype: 'fieldcontainer',
									   	layout: 'anchor',
									   	flex:1,
									   	defaultType: 'textfield',
									   	margin: '10',
									   	items: [{
									   		xtype:'datefield',
											fieldLabel: 'Purchase Date',
											emptyText: 'Enter Purchase Date',
											name: 'purchaseDate'
										},{
											xtype:'textfield',
											fieldLabel: 'Currency',
											name: 'currency'
										},{
											xtype:'textfield',
											fieldLabel: 'Remarks',
											name: 'remarks'
										}]
									}]
							},{
								xtype: 'fieldcontainer',
							   	layout: 'anchor',
							   	flex:1,
							   	defaultType: 'textfield',
							   	margin: '10',
							   	items: [{
							   		xtype:'checkbox',
									fieldLabel: 'Audited',									
									name: 'isAudited'
								},{
									xtype:'textarea',
									fieldLabel: 'Audit Comment',
									name: 'auditComment'
								}]
							}]
		                },
				        {
							xtype: 'fieldset',
							title: 'Item List:',
							layout: 'anchor',
							items: [{
								xtype: 'fieldcontainer',
								layout: 'anchor',
								margin: '0',
								items: [{
									xtype: 'fieldcontainer',
									height: 15,
									layout: 'hbox',
									items: [{
										xtype: 'displayfield',
										value: 'Batch:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Item:',
										flex: 2
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Qty:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Unit Price:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Vat%:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Total Vat:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Total Payable:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: '',
										flex: 1
									}]
								}]
							}, {
								xtype: 'fieldcontainer',
								layout: 'anchor',								
								id: 'itemList',
								margin: '0',
								items: []
							},{
								xtype: 'fieldcontainer',
								layout: 'hbox',
								height:15,
								items: [{
									xtype: 'displayfield',
									value: '',
									flex: 4
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'displayfield',
									value: 'Account:',
									flex: 1
								},{
									xtype: 'splitter'
								},{
									xtype: 'pickerfield',
									name: 'account',
									editable:false,
									"onTriggerClick":function(e){
										var ownerForm = Ext.getCmp('purchaseTransactionAddForm').getForm();
										var fields = ['time','scheduledate','isauto','componentId'];
										getPickerPopupByType(e,ownerForm,'account','account','accountAction.csmp?method=promptExtAccountTreeSearchSystemLevel','AccountPopupModel',fields,false,voucherType);
									
									},
									flex: 1
								},{
									xtype: 'splitter'
								},{
									xtype: 'displayfield',
									value: 'Total:',
									flex: 1
								}, {
									xtype: 'splitter'
								},{
									xtype: 'textfield',
									name: 'total',
									readOnly:true,
									allowBlank: false,
									flex: 1
								}, {
									xtype: 'splitter'
								}, {
									xtype: 'button',
									id: 'add',
									text: 'Add',
									handler: function() {
										var indexField = Ext.getCmp('purchaseTransactionAddForm').getForm().findField('index');										
										var index = indexField.getValue();
										index = parseInt(index);
										addRow('', '', '','','','','',index);
										(((Ext.getCmp('purchaseTransactionAddForm')).getForm()).findField('index')).setRawValue(index+1);										
									},
									flex: 1
								}]
							},{
								xtype: 'fieldcontainer',
								layout: 'hbox',
								items: [{
									xtype: 'displayfield',
									value: '',
									flex: 6
								}, {
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'displayfield',
									value: 'Paid:',
									flex: 1
								},{
									xtype: 'splitter'
								},{
									xtype: 'textfield',									
									name: 'paid',
									allowBlank: false,
									listeners: {
										blur: {
											fn: function (event, el, opts) {
												resetDue();
											}
										}
									},
									flex: 1
								}, {
									xtype: 'splitter'
								}, {
									xtype: 'displayfield',
									value: '',
									flex: 1
								}]
							},{
								xtype: 'fieldcontainer',
								layout: 'hbox',
								height:15,
								items: [{
									xtype: 'displayfield',
									value: '',
									flex: 6
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'splitter'
								},{
									xtype: 'displayfield',
									value: 'Dues:',
									flex: 1
								}, {
									xtype: 'splitter'
								},{
									xtype: 'textfield',
									name: 'due',
									readOnly:true,
									allowBlank: false,
									flex: 1
								}, {
									xtype: 'splitter'
								}, {
									xtype: 'displayfield',
									value: '',
									flex: 1
								}]
							}]
						}],

						buttons: [{
							text: 'Load',
							id: 'load'
						}, {
							text: 'Submit',
							id: 'submit',
							disabled: true,
							formBind: true
						}]			        	
					}]
				});
	addRow('', '', '','','','','',0);
	extjsWindow.show();
	(((Ext.getCmp('purchaseTransactionAddForm')).getForm()).findField('index')).setRawValue(1);
	
	extjsWindow.show();	
}
function getPurchaseTransactionFieldSet()
{
	var fieldSet = [
		{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
			{ xtype: 'textfield',name:'transactionNum', fieldLabel: 'transactionNum',width:'100%'}, 
			{ xtype: 'datefield',name:'transactionDate',fieldLabel: 'transactionDate',emptyText:'Enter Date',format:'Y-n-d H:i',readOnly:true,labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'textfield',name:'voucherType', fieldLabel: 'voucherType',labelStyle: 'width:200;' ,width:'100%'}, 
			{ xtype: 'textfield',name:'auditStatus', fieldLabel: 'auditStatus' ,labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'textfield',name:'auditComment', fieldLabel: 'auditComment',labelStyle: 'width:200;',width:'100%' }, 
			{ xtype: 'numberfield',name:'totalAmount',fieldLabel: 'totalAmount',emptyText:'0',labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'numberfield',name:'userId',fieldLabel: 'userId',emptyText:'0',labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'datefield',name:'actionDate',fieldLabel: 'actionDate',emptyText:'Enter Date',format:'Y-n-d H:i',readOnly:true,labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'numberfield',name:'amountTrans',fieldLabel: 'amountTrans',emptyText:'0',labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'numberfield',name:'approvedBy',fieldLabel: 'approvedBy',emptyText:'0',labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'textfield',name:'approveStatus', fieldLabel: 'approveStatus',labelStyle: 'width:200;',width:'100%' }, 
			{ xtype: 'numberfield',name:'dueApprovedBy',fieldLabel: 'dueApprovedBy',emptyText:'0',labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'textfield',name:'dueApproveStatus', fieldLabel: 'dueApproveStatus',labelStyle: 'width:200;' ,width:'100%'}, 
			{ xtype: 'numberfield',name:'commissionApprovedBy',fieldLabel: 'commissionApprovedBy',emptyText:'0',labelStyle: 'width:200;',width:'100%'}, 
			{ xtype: 'textfield',name:'commissionApproveStatus', fieldLabel: 'commissionApproveStatus',labelStyle: 'width:200;' ,width:'100%'},
			{xtype:'button',name:'add'}
			];
	
	return fieldSet;
}
function afterRender(parentGrid) {

}

function getPurchaseTransactionGrid(store)
{
    // create the grid
    var purchaseTransactionGrid = Ext.create('Ext.grid.Panel', {
    	id:'purchaseTransactionGrid',
        store: store,
        columns: getPurchaseTransactionColumns(),
        selType: 'rowmodel',
        dockedItems: [{
            xtype: 'pagingtoolbar',
            store: store,   // same store GridPanel is using
            dock: 'bottom',
            displayInfo: true
        }],
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    purchaseTransactionGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data    	
		rowData  = record;
		var tabpanel = Ext.get('docs-purchaseTransactionPanel');
		var purchaseTransactionAddForm = getPurchaseTransactionAddForm(tabpanel,store);
		var form = Ext.getCmp('purchaseTransactionAddForm').getForm();
		form.buttons[0].setText="Modify";
		form.url= 'transactionAction.csmp?method=modifyTransaction';
		loadPurchaseTransactionFormData(form,rowData);
		
	});
	
	purchaseTransactionGrid.on('click',function() {
		var tabpanel = Ext.getCmp('doc-body').findById('docs-purchaseTransactionPanel');
		var purchaseTransactionAddForm = getPurchaseTransactionAddForm(tabpanel,store);
		var form = Ext.getCmp('purchaseTransactionAddForm').getForm();
		form.buttons[0].setText="Modify";
		form.url= 'transactionAction.csmp?method=modifyTransaction';
		loadPurchaseTransactionFormData(form,rowData);
	});
    
    store.load();
    
    return purchaseTransactionGrid;
}
function getPurchaseTransactionHeaderColumns()
{
	var colModel = new Ext.grid.ColumnModel([
                   	{header: "Ticker", width: 60, sortable: true},
                	{header: "Company Name", width: 150, sortable: true},
                	{header: "Market Cap.", width: 100, sortable: true},
                	{header: "$ Sales", width: 100, sortable: true},
                	{header: "Employees", width: 100, sortable: true, resizable: false}
                 ]);
	var columns = [
			{
			    xtype: 'gridcolumn',
			    header: 'num',
			    sortable: false,
			    resizable: true,
			    menuDisabled:true,
			    width: '7%',
			    css:'background-color: #EEFFAA;border-style:solid;border-color:#0000ff;',
			    dataIndex: 'num'
			},
	        {header: 'transactionNum', anchor:'7%', dataIndex: 'transactionnum', sortable: true}, 
			{header: 'transactionDate', width: 120, dataIndex: 'transactiondate', sortable: true}, 
			{header: 'voucherType', anchor:'7%', dataIndex: 'vouchertype', sortable: true}, 
			{header: 'auditStatus', anchor:'7%', dataIndex: 'auditstatus', sortable: true}, 
			{header: 'auditComment', anchor:'7%', dataIndex: 'auditcomment', sortable: true}, 
			{header: 'totalAmount', anchor:'7%', dataIndex: 'totalamount', sortable: true}, 
			{header: 'userId', anchor:'7%', dataIndex: 'userid', sortable: true}, 
			{header: 'actionDate', anchor:'7%', dataIndex: 'actiondate', sortable: true}, 
			{header: 'amountTrans', anchor:'7%', dataIndex: 'amounttrans', sortable: true}, 
			{header: 'approvedBy', anchor:'7%', dataIndex: 'approvedby', sortable: true}, 
			{header: 'approveStatus', anchor:'7%', dataIndex: 'approvestatus', sortable: true}, 
			{header: 'dueApprovedBy', anchor:'7%', dataIndex: 'dueapprovedby', sortable: true}, 
			{header: 'dueApproveStatus', anchor:'7%', dataIndex: 'dueapprovestatus', sortable: true}, 
			{header: 'commissionApprovedBy', anchor:'7%', dataIndex: 'commissionapprovedby', sortable: true}, 
			{header: 'commissionApproveStatus', anchor:'7%', dataIndex: 'commissionapprovestatus', sortable: true}];
	
	return columns;
}
function getPurchaseTransactionColumns()
{
	var columns = [{header: 'transactionNum', width: 120, dataIndex: 'transactionnum', sortable: true}, 
			{header: 'transactionDate', width: 120, dataIndex: 'transactiondate', sortable: true}, 
			{header: 'voucherType', width: 120, dataIndex: 'vouchertype', sortable: true}, 
			{header: 'auditStatus', width: 120, dataIndex: 'auditstatus', sortable: true}, 
			{header: 'auditComment', width: 120, dataIndex: 'auditcomment', sortable: true}, 
			{header: 'totalAmount', width: 120, dataIndex: 'totalamount', sortable: true}, 
			{header: 'userId', width: 120, dataIndex: 'userid', sortable: true}, 
			{header: 'actionDate', width: 120, dataIndex: 'actiondate', sortable: true}, 
			{header: 'amountTrans', width: 120, dataIndex: 'amounttrans', sortable: true}, 
			{header: 'approvedBy', width: 120, dataIndex: 'approvedby', sortable: true}, 
			{header: 'approveStatus', width: 120, dataIndex: 'approvestatus', sortable: true}, 
			{header: 'dueApprovedBy', width: 120, dataIndex: 'dueapprovedby', sortable: true}, 
			{header: 'dueApproveStatus', width: 120, dataIndex: 'dueapprovestatus', sortable: true}, 
			{header: 'commissionApprovedBy', width: 120, dataIndex: 'commissionapprovedby', sortable: true}, 
			{header: 'commissionApproveStatus', width: 120, dataIndex: 'commissionapprovestatus', sortable: true}];
	return columns;
}
function loadPurchaseTransactionFormData(form,row)
{
	form.findField('componentId').setValue(row.data.componentId);
	form.findField('voucherNo').setValue(row.data.transactionnum);
	form.findField('purchaseDate').setValue(row.data.transactiondate);
	form.findField('voucherType').setValue(row.data.vouchertype);
	/*form.findField('auditStatus').setValue(row.data.auditstatus);
	form.findField('auditComment').setValue(row.data.auditcomment);
	form.findField('totalAmount').setValue(row.data.totalamount);
	form.findField('userId').setValue(row.data.userid);
	form.findField('actionDate').setValue(row.data.actiondate);
	form.findField('amountTrans').setValue(row.data.amounttrans);
	form.findField('approvedBy').setValue(row.data.approvedby);
	form.findField('approveStatus').setValue(row.data.approvestatus);
	form.findField('dueApprovedBy').setValue(row.data.dueapprovedby);
	form.findField('dueApproveStatus').setValue(row.data.dueapprovestatus);
	form.findField('commissionApprovedBy').setValue(row.data.commissionapprovedby);
	form.findField('commissionApproveStatus').setValue(row.data.commissionapprovestatus);*/
}
	
function getPurchaseTransactionStore()
{
	var url = 'transactionAction.csmp?method=promptExtTransactionSearchSystemLevel&vouchertype=purchase';
	Ext.define('PurchaseTransactionModel', {
	    extend: 'Ext.data.Model',
	    fields: ['transactionnum', 'description', 'updatedby']
	});
	var store = Ext.create('Ext.data.Store', {
	    model: 'PurchaseTransactionModel',
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
	
	return store;
}
