var nodes=[];
nodes.push('1');
/*
BCTreeRoot.eachChild(function(n){
	nodes.push(n); //create a snapshot of request chain.
});
*/
if(!!nodes.length)
{
	var totalNodes = nodes.length;
	var progPerc;
	var textData = [];
	var upBar = new Ext.ProgressBar({text:'updating'});
	upBar.show();
	(function(){
		var node, self = arguments.callee ;
		if(node = nodes.shift()){
			progPerc = (totalNodes-nodes.length+1 / totalNodes);
			upBar.updateProgress(progPerc, 'Processing');
			Ext.Ajax.request({
				method: 'GET',
				timeout: 100000,
				url: 'blade-info.pl',
				success: function(response){
					textData.push( response.responseText); //array buffering faster!
					upText.setValue(textData.join('\n'));

					setTimeout(self,10); // <--- Adjust this to smooth out UI response time during loop
				}//,
				//{params:{ data: 'up', bc: node.text }}
			});
		} else {
			upBar.updateProgress(100, 'Done!');
			upCloseButton.enable();
		}
	})();
}