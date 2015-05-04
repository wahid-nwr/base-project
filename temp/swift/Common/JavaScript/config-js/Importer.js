tx.Importer = function(){
	function chooseFile(callback){
		var file = new air.File(air.File.documentsDirectory.nativePath);
		var filter = new air.FileFilter("Tasks XML File", "*.xml");
		
		file.addEventListener('select', function(e){
			doImport(e.target, callback);
		});
		
		file.browseForOpen('Open', [filter]);
	}
	
	
	/*
	 * This import function used to be clean and simple. The addition of asynchronous import and
	 * a progress bar changed that quickly. :) 
	 */
	function doImport(file, callback){
		
		Ext.Msg.progress('Import', 'Reading file...');
		
		var listTable = tx.data.conn.getTable('list', 'listId');
		var taskTable = tx.data.conn.getTable('task', 'taskId');
		var entryFormTable= tx.data.conn.getTable('entryForm', 'id');
		var taskCount = 0;
		var visibleIndex = 0;
		var doUpdate = true;
		var f = String.format;
		
		function getProgress(index){
			if(taskCount > 0){
				return (.8 * index) / taskCount;
			}else{
				return .8;
			}
		}
		
		function readFile(){
			var stream = new air.FileStream();
			stream.open(file, air.FileMode.READ);
			var xml = stream.readUTFBytes(stream.bytesAvailable);
			stream.close();
					
			Ext.Msg.updateProgress(.1, 'Parsing...');
			parse.defer(10, null, [xml]);
		}
		
		function parse(xml){
			try {
				var doc = new runtime.flash.xml.XMLDocument();
				doc.ignoreWhite = true;
				doc.parseXML(xml);				
				
				
				
				var entryFormList = doc.firstChild.childNodes;
				var entryCount = entryFormList.length;
				air.trace(entryCount)
				var appXml = new DOMParser();
				var xmlObject = appXml.parseFromString(xml, "text/xml");
				var root = xmlObject.getElementsByTagName('simple-tasks')[0];
				air.trace("root::"+root);
				var formData=root.getElementsByTagName("entryForm");
				var lists = root.getElementsByTagName("list");
				air.trace(lists.length);
				/*
				for(var k=0;k<lists.length;k++){
					var tasks = lists[k].getElementsByTagName("task");
					air.trace(tasks.length);
					taskCount += tasks.length;
					for (var l = 0; l < tasks.length; l++) {
						lists[k].appendChild(tasks[l]);
						air.trace(lists[k]);
					}
				}
				var count = lists.length;
				*/
				//entryFormTable.removeForm();
				//air.trace("count::"+count);
				Ext.Msg.updateProgress(.15, '', 'Loading Tasks...');
				tx.data.conn.exec('delete from entryForm');
				for(var i=0;i<formData.length;i++){
					var dd=formData[i];
					//entryFormTable.deleteAll();
					
					//entryFormTable.save(dd.attributes);
					air.trace("saved"+i);
					air.trace("dd::"+dd.attributes.length);
					var first=dd.attributes[0].value;
					air.trace("first::"+dd.attributes[0].value+" last::"+dd.attributes[1].value+" company::"+dd.attributes[2].value+" email::"+dd.attributes[3].value+" time::"+dd.attributes[4].value+" id::"+dd.attributes[5].value);
					
					//var entryFormRs =
					 tx.data.conn.exec('INSERT INTO `entryForm` (id,first_name,last_name,company,email,time)'+
						'VALUES (\'' + dd.attributes[5].value + '\',\'' + dd.attributes[0].value + '\',\'' + dd.attributes[1].value + '\',\''+ dd.attributes[2].value + '\',\'' + dd.attributes[3].value + '\',\' '+ dd.attributes[4].value + '\')');
					//for(var k = 0, klen = entryFormRs.length; k < klen; k++){
					//var task = entryFormRs[k];
					air.trace("first_name::"+first);
				}
				Ext.Msg.updateProgress(1, '', 'Completing import...');
				callback.defer(10);
				air.trace("formData::"+formData);
				air.trace("entryFormList::"+entryFormList+" entryCount::"+entryCount);
				//for (var i = 0; i < count; i++) {
					//taskCount += lists[i].childNodes.length;
				//}	
				air.trace("taskCount::"+taskCount);
				
				
				
				//loadLists(lists, 0);
			}catch(e){
				air.trace(e);
				alert('An error occured while trying to import the selected file.');
			}			
		}
		
		function loadLists(lists, index){
			if(index < lists.length){
				var list = lists[index];
				listTable.save(list.attributes);
				nextTask(list.childNodes, 0, lists, index);
			}
			else {
				Ext.Msg.updateProgress(1, '', 'Completing import...');
				callback.defer(10);
			}				
		}		
		
		function nextTask(tasks, index, lists, listIndex){
			if(index < tasks.length){
				Ext.Msg.updateProgress(
					getProgress(++visibleIndex),
					f('{0} of {1}', visibleIndex, taskCount)
				);
				loadTasks.defer(250, window, [tasks, index, lists, listIndex]);
			}
			else {
				loadLists(lists, ++listIndex);
			}
		}
		
		function loadTasks(tasks, index, lists, listIndex){
			taskTable.save(tasks[index].attributes);
			air.trace("tasks[index].attributes::"+tasks[index].attributes);
			nextTask(tasks, ++index, lists, listIndex);
		}
		
		readFile.defer(10);
	}
	
	this.doImport = function(callback){
		chooseFile(callback);
	}
};
