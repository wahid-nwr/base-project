Ext.BLANK_IMAGE_URL = './Common/JavaScript/resources/s.gif';

// Initialize the state provider
Ext.state.Manager.setProvider(new Ext.air.FileProvider({
	file: 'docs.state',
	// if first time running
	defaultState : {
		mainWindow : {
			width:950,
			height:700,
			x:10,
			y:10
		}
	}
}));