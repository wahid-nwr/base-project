function getSamplecomStore()
{
	var store = new Ext.data.Store({
        // load using HTTP
        url: 'samplecomAction.csmp?method=promptExtSamplecomSearchSystemLevel',
        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'componentId',
               totalRecords: 'TotalResults'
           }, [
               // set up the fields mapping into the xml doc
               // The first needs mapping, the others are very basic
               {name: 'code', mapping: 'ItemAttributes > code'},
               'description',
			   'updatedby'
           ])
    });
	return store;
}