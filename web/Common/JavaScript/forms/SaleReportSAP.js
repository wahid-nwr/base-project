Ext.require('Ext.ux.IFrame');
function getSAPSaleReportPanel(){
	//var store = getEmpReportStore();
	//var reportGrid = getReportGrid(store);
	//var search = getReportStore(store);
	var reportFrame = Ext.create("Ext.ux.IFrame", {
		id:'sapSaleReportIFrame',
		height:400,
        title: 'Page Under Test',
        hideMode: 'offsets'
    });
	
	containerPanel = new Ext.Panel({
		id:'sapSaleReportDetail',
		//frame: true,
		title: 'report List',
		
		autoHeight : true,
		layout: 'fit',
		items:[reportFrame]
	});
		
	return containerPanel;
}