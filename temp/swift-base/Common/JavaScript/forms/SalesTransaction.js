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

var addRow = null;
var deleteRow = null;
function getSalesTransactionPanel(){
	voucherType = 'sales';
	var store = getSalesTransactionStore();
	var salesTransactionGrid = getSalesTransactionGrid(store);
	//var search = getSalesTransactionStore(store);
	//alert('in getting panel');
	
	var containerPanel = Ext.create('Ext.panel.Panel', {
		id:'salesTransactionDetail',
		//frame: true,
		title: 'salesTransaction List',
		
		autoHeight : true,
		layout: 'fit',
		items: [
			salesTransactionGrid
		],
		tbar: [
	            'Search: ', ' ',
	            getSearchField(store,'')
	        ],/*
		 dockedItems: [{
			xtype: 'pagingtoolbar',
			dock: 'bottom',
			store: store,
           displayInfo: true
        }],*/
        buttons:[{
			text:"ADD",
				handler:function() {
					var tabpanel = Ext.get('docs-salesTransactionPanel');
					var transactionAddForm = getSalesTransactionAddForm(tabpanel,store,false);
					//tabpanel.remove(0);
					//tabpanel.add(transactionAddForm);
					//tabpanel.doLayout();                    
				}
	  	},{
			text:"Cancel",
			handler:function(){
				/*var tabpanel = Ext.getCmp('doc-body').findById('docs-transactionPanel');				
				tabpanel.ownerCt.remove(tabpanel);
				tabpanel.destroy();*/
			}
		}]
	});
	/*
	var tabpanel = Ext.getCmp('doc-body').findById('docs-transactionPanel');
	tabpanel.on('resize',function(){
		transactionGrid.setSize(tabpanel.getSize());
		transactionGrid.doLayout();
	});
    */    
	return containerPanel;
}
function calculateTotalReceivable(index)
{
	//var currency =document.getElementsByName("currency")[0].value;
    var qty_ = document.getElementsByName('quantity['+index+']')[0].value;
    //alert('qty_::'+qty_);
    var price_ = document.getElementsByName('unitPrice['+index+']')[0].value;
    var vat_ = document.getElementsByName('vat['+index+']')[0].value;
    var total= document.getElementsByName('totalReceivable['+index+']')[0];
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
    var form = Ext.getCmp('salesTransactionAddForm').getForm();
    form.findField('totalVat['+index+']').setValue(vatAmount);
    form.findField('totalReceivable['+index+']').setValue(Number(x).toFixed(2));
    resetTotalAmount();
    //total.value =Number(x).toFixed(2);
}
function resetTotalAmount()
{
	var form = Ext.getCmp('salesTransactionAddForm').getForm();
	var rowIndices = form.findField('rowIndices').getValue();
	var indices = rowIndices.split(',');
	var totalAmount = 0.00;
	for(var i=0;i<indices.length;i++)
	{
		var total_= form.findField('totalReceivable['+indices[i]+']').getValue();
        var a=Number(total_);
        totalAmount+=a;
	}
	form.findField('total').setValue(totalAmount);
	resetDue();
}
function resetDue()
{
	var form = Ext.getCmp('salesTransactionAddForm').getForm();
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
function getSalesTransactionAddForm(tabpanel,store,isEdit)
{
	//alert('in getSalesTransactionAddForm')
	/*var extDateField = new Ext.form.DateField({
		"id" : 'extDateFieldId',
		"width" : 200,
		"format" : 'd M Y'
	});*/
	deleteRow = function(obj,e,index)
	{
		var form = Ext.getCmp('salesTransactionAddForm').getForm();
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
		calculateTotalReceivable(index);
	}
	
	addRow = function (batch, item,qty,unitPrice,vat,totalVat,totalReceivable,index) {
		var form = Ext.getCmp('salesTransactionAddForm');
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
		var ownerForm = Ext.getCmp('salesTransactionAddForm').getForm();
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
				name: 'available['+index+']',
				readOnly:true,
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
				name: 'comm['+index+']',
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
			},{
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
				name: 'totalComm['+index+']',
				readOnly:true,
				value: totalVat,
				flex: 1
			},{
				xtype: 'splitter'
			}, {
				name: 'totalVat['+index+']',
				readOnly:true,
				value: totalVat,
				flex: 1
			}, {
				xtype: 'splitter'
			}, {
				name: 'totalReceivable['+index+']',
				readOnly:true,
				value: totalReceivable,
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
	var extjsWindow = null;
	/*
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
	};*/
	/*
	extjsWindow = new Ext.Window({
		layout:'card',
		activeItem: 0,
		minWidth:750,
		width:750,
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
	*/
	var MyField = Ext.create('Ext.form.field.Base',{
		name:'accountPicker',
	    flex: 1
	});
	//MyField.createPicker();
/*
	var newField = new Ext.form.CompositeField({
	    fieldLabel: 'My field',
	    items: [
	        MyField,
	        {
	            xtype: 'button',
	            text: 'Choose item'
	            handler: function() {
	                // Show a menu or selection dialog, then set the user's
	                // selected value with:
	                MyField.setValue(value);
	            }
	        }
	    ]
	});
*/
		
	extjsWindow = Ext.create('Ext.window.Window', {
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
					bodyStyle: 'padding:5px;',
					autoScroll:true,
					title:'Sales Entry window',
					items:[
					{
						// bounding form
				        id:'salesTransactionAddForm',
				        xtype:'form',
				        frame:true,
				        url:'transactionAction.csmp?method=addTransaction',
				        //layout:'fit',
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
				                { xtype: 'hidden',name:'voucherType', fieldLabel: 'voucherType',value:'sale' },
		                {
		                	xtype: 'fieldset',
							layout: 'hbox',
							
							items: [{								        	
									xtype: 'fieldset',
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
											fieldLabel: 'Customer',
											name: 'customer'
										},{
											xtype:'textfield',
											fieldLabel: 'Sold By',
											name: 'soldBy'
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
											fieldLabel: 'Sale Date',
											emptyText: 'Enter Sale Date',
											name: 'saleDate'
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
							bodyStyle: 'padding:5px;',
							autoScroll:true,
							layout: 'fit',							
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
										value: 'Avialable:',
										flex: 1
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
										value: 'Comm%:',
										flex: 1
									},{
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Vat%:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Total Comm:',
										flex: 1
									},{
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Total Vat:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: 'Total Recieveable:',
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'displayfield',
										value: '',
										flex: 1
									}]
								}/*,{
									xtype: 'fieldcontainer',
									height: 22,
									layout: 'hbox',
									defaultType: 'textfield',
									items: [{
										name: 'year[]',
										//value: year,
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										name: 'college[]',
										//value: college,
										flex: 2
									}, {
										xtype: 'splitter'
									}, {
										name: 'grade[]',
										//value: grade,
										flex: 1
									}, {
										xtype: 'splitter'
									}, {
										xtype: 'button',
										sid: 'delete',
										text: 'Delete',
										flex: 1
									}]
								}*/]
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
									flex: 7
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
									value: 'Account:',
									flex: 1
								},{
									xtype: 'splitter'
								},{
									xtype: 'pickerfield',
									name: 'account',
									editable:false,
									"onTriggerClick":function(e){
										//alert('name::'+e.getParent()+ ' ' +e.getTarget());
										var ownerForm = Ext.getCmp('salesTransactionAddForm').getForm();
										var fields = ['time','scheduledate','isauto','componentId'];
										getPickerPopupByType(e,ownerForm,'account','account','accountAction.csmp?method=promptExtAccountTreeSearchSystemLevel','AccountPopupModel',fields,false,voucherType);
									
									},
									flex: 1
								}/*MyField*/,{
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
										var indexField = Ext.getCmp('salesTransactionAddForm').getForm().findField('index');
										
										var index = indexField.getValue();
										index = parseInt(index)
										//alert('index::'+index);
										addRow('', '', '','','','','',index);
										(((Ext.getCmp('salesTransactionAddForm')).getForm()).findField('index')).setRawValue(index+1);										
									},
									/*onclick:function({
										addRow('', '', '');
									}),*/
									flex: 1
								}]
							},{
								xtype: 'fieldcontainer',
								layout: 'hbox',
								items: [{
									xtype: 'displayfield',
									value: '',
									flex: 9
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
									value: 'Received:',
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
									flex: 9
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
							formBind: true,
							handler: function() {
								Ext.getCmp('salesTransactionAddForm').getForm().submit({
					                   method    : 'POST',
					                   params    : {crud: "U"},
					                   waitTitle : 'Processing',
					                   waitMsg   : 'Please Wait',
					                   success   : function () {
					                	   //storeMasterGridRows.reload();
					                	   Ext.Msg.alert('Information Saved!');
					                   },
					                   failure   : function (form, action) {
					                       if (action.failureType === 'server') {
					                           obj = Ext.util.JSON.decode(action.response.responseText);
					                           Ext.Msg.alert('Process Failed!', obj.reason);
					                       } else {
					                           obj = Ext.util.JSON.decode(action.response.responseText);
					                           Ext.Msg.alert('Warning!', obj.reason);
					                       }
					                   }
					               });
							}
						}]			        	
					}]
				});
	if(!isEdit)
	{
		addRow('', '', '','','','','',0);
	}	
	extjsWindow.show();
	(((Ext.getCmp('salesTransactionAddForm')).getForm()).findField('index')).setRawValue(1);
	//alert('after window');
	/*
	var salesTransactionAddForm = Ext.create('Ext.form.Panel',{
        url:    'salesTransactionAction.csmp?method=addsalesTransaction',
        id:     'salesTransactionAddForm',
        layout: 'form',
        //renderTo:Ext.getBody(),
        frame: true,
        title: 'Add salesTransaction',
        closable: true,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Purchase Voucher Information',
                width:'60%',
                height:'30%',
                style:'float:left;',
                defaultType: 'textfield',
                items: getsalesTransactionFieldSet()
            }),
            Ext.create('Ext.form.FieldSet',{
                title: 'Audit Information',
                width:'38%',
                height:'30%',
                style:'float:left;',
                defaultType: 'textfield',
                items: getsalesTransactionFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: salesTransactionAddForm,
            handler: function() {
            	salesTransactionAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getsalesTransactionPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: salesTransactionAddForm,
            handler: function() {
                Ext.getCmp('salesTransactionAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: salesTransactionAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getsalesTransactionPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(salesTransactionAddForm);
	*/
	extjsWindow.show();
	//return salesTransactionAddForm;
}
function getSalesTransactionFieldSet()
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
function afterRender(parentGrid) {

}
function getSalesTransactionHeaderGrid(store)
{
	//var itemDeleter = new Extensive.grid.ItemDeleter();
    // create the grid
    var salesTransactionGrid = Ext.create('Ext.grid.Panel', {
    	id:'salesTransactionHeaderGrid',
    	//anchor:'100%',
        store: store,
        columns: getSalesTransactionHeaderColumns(),
       
        //selModel: itemDeleter,
        //sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
        /*
		viewConfig: {
			forceFit: true,
			enableRowBody: true
		},
		
		stripeRows : true,*/
		autoHeight : true,
		split: true,
		region: 'north'
    });
    //transactionGrid.on('viewready', afterRender, this);
    salesTransactionGrid.on('columnresize', function(columnIndex,newSize){
    	salesTransactionGrid.syncSize();
    });
       
    return salesTransactionGrid;
}
function getSalesTransactionGrid(store)
{
	//var itemDeleter = new Extensive.grid.ItemDeleter();
    // create the grid
    var salesTransactionGrid = Ext.create('Ext.grid.Panel', {
    	id:'salesTransactionGrid',
        store: store,
        columns: getSalesTransactionColumns(),
        selType: 'rowmodel',
        dockedItems: [{
            xtype: 'pagingtoolbar',
            store: store,   // same store GridPanel is using
            dock: 'bottom',
            displayInfo: true
        }],
        //selModel: itemDeleter,
        //sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
        /*
		viewConfig: {
			forceFit: true
		},
		
		stripeRows : true,*/
		autoHeight : true,
		split: true,
		region: 'north'
    });
    
    var rowData = null;
    salesTransactionGrid.getSelectionModel().on('select', function(rowModel, record, rowIdx, objs) {
		//get row data    	
		rowData  = record;
		var tabpanel = Ext.get('docs-salesTransactionPanel');
		var salesTransactionAddForm = getSalesTransactionAddForm(tabpanel,store,true);
		var form = Ext.getCmp('salesTransactionAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'transactionAction.csmp?method=modifyTransaction';
		loadSalesTransactionFormData(form,rowData);
		
	});
	
	salesTransactionGrid.on('click',function() {
		//alert('clicked double');
		var tabpanel = Ext.getCmp('doc-body').findById('docs-salesTransactionPanel');
		var salesTransactionAddForm = getSalesTransactionAddForm(tabpanel,store,true);
		var form = Ext.getCmp('salesTransactionAddForm').getForm();
		//url:    'dcrinfoAction.csmp?method=addDcrinfo',
		form.buttons[0].setText="Modify";
		form.url= 'transactionAction.csmp?method=modifyTransaction';
		loadSalesTransactionFormData(form,rowData);
	});
    
    store.load();
    //alert('in get store');
    return salesTransactionGrid;
}
function getSalesTransactionHeaderColumns()
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
			   // fixed: false, //Will not be resized
			    width: '7%',
			    css:'background-color: #EEFFAA;border-style:solid;border-color:#0000ff;',
			    //tdCls: 'x-change-cell',
			    //anchor:'5%',
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
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function getSalesTransactionColumns()
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
	/* sample columns
	[{header: "code", width: 120, dataIndex: 'code', sortable: true},
     {header: "description", width: 180, dataIndex: 'description', sortable: true},
     {header: "updatedby", width: 115, dataIndex: 'updatedby', sortable: true},
     //use this if item delete function is needed
     itemDeleter]
     */
	return columns;
}
function loadSalesTransactionFormData(form,row)
{
	form.findField('componentId').setValue(row.data.componentId);
	form.findField('voucherNo').setValue(row.data.transactionnum);
	form.findField('saleDate').setRawValue(row.data.transactiondate);
	form.findField('voucherType').setValue(row.data.vouchertype);
	var salesTransactionDetailStore = getSalesTransactionDetailStore(row.data.componentId);
	salesTransactionDetailStore.load();
	/*
	store.on('load', function(store, records, options)
	{
		var name = '';
		store.each(function(record)  
		{  
			name = record.get('name');			
		}, this);
		var form = Ext.getCmp('seatBookingForm').getForm();
		var nameField = form.findField('buyerName');
		nameField.setValue(name);
	});
	*/
	salesTransactionDetailStore.on('load', function(store, records, options)
	{
		var fieldValue;
		salesTransactionDetailStore.each(function(record)  
		{ 
			//alert('adding row');
			var indexField = Ext.getCmp('salesTransactionAddForm').getForm().findField('index');
			//'amount','quantity','areaid','accountid','transactiontype','itemid','userid',
            //'actorid','comments','unitprice','transactiondetailorder'
			var batch = record.get('seatno');
			var item = record.get('itemid');
			var qty = record.get('quantity');
			var unitPrice = record.get('unitprice');
			var vat = record.get('vat');
			var totalVat = record.get('totalVat');
			var totalReceivable = qty*unitPrice;
			
			var index = indexField.getValue();
			index = parseInt(index)
			//batch, item,qty,unitPrice,vat,totalVat,totalReceivable
			//alert('index::'+index);
			addRow(1, item, qty,unitPrice,0,0,totalReceivable,index);
			(((Ext.getCmp('salesTransactionAddForm')).getForm()).findField('index')).setRawValue(index+1);
		},this);
	}); 
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
	/* sample field load
	form.findField('code').setValue(row.data.code);
	form.findField('desc').setValue(row.data.description);
	form.findField('date').setValue('aa');
	form.findField('session').setValue('aa');
	form.findField('location').setValue('aa');
	form.findField('doctor').setValue('aa');
	*/
}
function getSalesTransactionStore()
{
	var url = 'transactionAction.csmp?method=promptExtTransactionSearchSystemLevel&vouchertype=sale';
	Ext.define('SalesTransactionModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId','transactionnum', 'transactiondate','vouchertype','auditstatus','auditcomment',
	             'totalamount','userid','actiondate','amounttrans','approvedby','approvestatus',
	             'dueapprovedby','dueapprovestatus','commissionapprovedby','commissionapprovestatus','description', 'updatedby']
	});
	var store = Ext.create('Ext.data.Store', {
	    model: 'SalesTransactionModel',
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
	/*
	var store = new Ext.data.Store({
        // load using HTTP
        url: 'transactionAction.csmp?method=promptExtTransactionSearchSystemLevel',
        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'componentId',
               totalRecords: 'TotalResults'
           }, [
               // set up the fields mapping into the xml doc
               // The first needs mapping, the others are very basic
               {name: 'transactionnum', mapping: 'ItemAttributes > transactionnum'},
               'description',
			   'updatedby'
           ])
    });*/
	return store;
}

function getSalesTransactionDetailStore(transactionId)
{
	var url = 'transactionAction.csmp?method=promptExtTransactionDetailSearchSystemLevel&vouchertype=sale&transactionId='+transactionId;
	Ext.define('SalesTransactionDetailModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId','transactionid','amount','quantity','areaid','accountid','transactiontype','itemid','userid',
	             'actorid','comments','unitprice','transactiondetailorder']
	});
	var store = Ext.create('Ext.data.Store', {
	    model: 'SalesTransactionDetailModel',
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
	/*
	var store = new Ext.data.Store({
        // load using HTTP
        url: 'transactionAction.csmp?method=promptExtTransactionSearchSystemLevel',
        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'componentId',
               totalRecords: 'TotalResults'
           }, [
               // set up the fields mapping into the xml doc
               // The first needs mapping, the others are very basic
               {name: 'transactionnum', mapping: 'ItemAttributes > transactionnum'},
               'description',
			   'updatedby'
           ])
    });*/
	return store;
}
