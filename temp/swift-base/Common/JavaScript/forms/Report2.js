Ext.require('Ext.ux.IFrame');
function getEmpReportPanel(){
	//var store = getEmpReportStore();
	//var reportGrid = getReportGrid(store);
	//var search = getReportStore(store);
	var reportFrame = Ext.create("Ext.ux.IFrame", {
		id:'empReportIFrame',
		height:400,
        title: 'Page Under Test',
        hideMode: 'offsets'
    });
	
	containerPanel = new Ext.Panel({
		id:'empReportDetail',
		//frame: true,
		title: 'report List',
		
		autoHeight : true,
		layout: 'fit',
		items:[reportFrame]
	});
		
	return containerPanel;
}