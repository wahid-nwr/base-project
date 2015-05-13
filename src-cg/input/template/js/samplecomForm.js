function getSamplecomAddForm(tabpanel,store)
{
	
	var extjsWindow = new Ext.Window({
		layout:'card',
		activeItem: 0,
		width:400,
		height:300,
		modal:true,
		minimizable:true,
		maximizable:true,
		autoScroll:true
	});
	
	var samplecomAddForm = Ext.create('Ext.FormPanel',{
        url:    'samplecomAction.csmp?method=addSamplecom',
        id:     'samplecomAddForm',
        //layout: 'form',
        //renderTo:Ext.getBody(),
        frame: false,
        title: 'Add Samplecom',
        closable: false,
        autoScroll:true,
        items: [
            Ext.create('Ext.form.FieldSet',{
                title: 'Samplecom Information',
                defaultType: 'textfield',
                items: getSamplecomFieldSet()
            })
        ],
		
        buttons: [{
            text: 'Save',
            scope: samplecomAddForm,
            handler: function() {
            	samplecomAddForm.getForm().submit({
                    success: function(f,a) {
                        Ext.Msg.alert('Success', 'Information saved');
                        //tabpanel.remove(0);
                        extjsWindow.close();
                        store.load();
                        //tabpanel.add(getSamplecomPanel());
                        //tabpanel.doLayout();                                       
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Error occured in previous action');
                    }
                });
            }
        },{
            text: 'Reset',
            scope: samplecomAddForm,
            handler: function() {
                Ext.getCmp('samplecomAddForm').getForm().reset();                                
            }
        },{
        	text: 'Back',
        	scope: samplecomAddForm,
        	handler: function(){
        		//tabpanel.remove(0);
        		extjsWindow.close();
        		store.load();                
                //tabpanel.add(getSamplecomPanel());
                //tabpanel.doLayout(); 
        	}
        }]
        
    });
	extjsWindow.add(samplecomAddForm);
	extjsWindow.show();
	return samplecomAddForm;
}