function getSamplecomStore()
{
	var url = 'samplecomAction.csmp?method=promptExtSamplecomSearchSystemLevel';
	Ext.define('SamplecomModel', {
	    extend: 'Ext.data.Model',
	    fields: ['componentId']
	});
	var samplecomstore = Ext.create('Ext.data.Store', {
	    model: 'SamplecomModel',
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
	samplecomstore.load();
	return samplecomstore;
}