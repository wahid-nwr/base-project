PickerDemoWin = Ext.extend(Ext.Window, {

    constructor: function(config){
		
		if (!config) {
			config = {};
		}
    
        this.picker = new Pohon.ux.form.JPicker({
            fieldLabel: 'Pick a color',
			value: config.color,
            anchor: '100%'
        });
        
        config = Ext.apply({
            autoScroll: true,
            layout: 'form',
            title: 'Pick a color demo',
            modal: true,
            bodyStyle: {
                'padding': '20px'
            },
            width: 300,
            items: [this.picker],
            buttons: [this.selectButton = new Ext.Button({
                scope: this,
                text: 'Select color',
                handler: this.selectColor,
                scale: 'medium'
            }), {
                scope: this,
                text: 'Close',
                handler: this.cancel,
                scale: 'medium'
            }]
        }, config);
        
        PickerDemoWin.superclass.constructor.call(this, config);
        
    },
    
    selectColor: function(){
        var el = Ext.get(Ext.query('#pick_color_demo')[0]);
        el.applyStyles({
            'background-color': this.picker.getValue()
        });
		this.cancel();
        
    },
    
    cancel: function(){
        this.close();
    }
    
});


Ext.BLANK_IMAGE_URL = '../ext-3.4.0/resources/images/default/s.gif';
Ext.onReady(function(){

    var el = Ext.get(Ext.query('#pick_color_demo')[0]);
    el.on('click', function(e){
		var color = el.getStyle('background-color');
        var win = new PickerDemoWin({
			color: color
		});
        win.show();
    });
    
    
});
