Ext.require('Ext.ux.IFrame');
function getEmpReportPanel(){
	//var store = getEmpReportStore();
	//var reportGrid = getReportGrid(store);
	//var search = getReportStore(store);
	var reportFrame = Ext.create("Ext.ux.IFrame", {
		id:'generalSetupReportIFrame',
		height:400,
        title: 'General Setup Report',
        hideMode: 'offsets'
    });
	
	containerPanel = new Ext.Panel({
		id:'generalSetupReportDetail',
		//frame: true,
		title: 'General Setup Report',
		
		autoHeight : true,
		layout: 'fit',
		items:[reportFrame]
	});
		
	return containerPanel;
}