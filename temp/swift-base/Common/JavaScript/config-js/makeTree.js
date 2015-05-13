function makeTree(object){
	for(var i=0;i<object.length;i++){
		var one=object[i];
		var id=one.id;
		var text=one.text;
		//var iconCls=one.iconCls;
		var cls=one.cls;
		var leaf=one.leaf;
		//air.trace("id::"+id+" text::"+text+" cls::"+cls+" leaf::"+leaf);
	}

}
function dTree(object,rootId){
	var dTree=new Array();
	var total=0;
	//air.trace("this is the root id::"+rootId);
	for(var i=0;i<object.length;i++){
		var node=object[i];
		if(node.id==rootId){
			var addThisNode={"href":"zone.html","isClass":true,"iconCls":"icon-cls","text":node.text,"leaf":true,"cls":"cls","id":node.id};
			dTree[0]=addThisNode;
		}
	}
	
	var desc=getDescendants(rootId,object);
	//if(dTree!=null){
	var length=1;
	var parentId=rootId;
	for(var i=0;i<desc.length;i++){
		var node=desc[i];
		var count=0;
		//air.trace("in has parent:::"+node.id+","+node.text+","+node.leaf+","+node.cls);
		//air.trace(i);
		if(i!=2 && i!=4){
			//air.trace("in has parent:::"+node.id+","+node.text+","+node.leaf+","+node.cls+","+node.attributes);
		}
		if(node!=null){
			dTree[length]=desc[i];
			length++;
		}
	}
	parentId=rootId;
	var childs=new Array();
	var theTree=new Array();
	var compare=new Array();
	childs.push(dTree[0]);
	theTree.push(dTree[0]);
	theTree[0].leaf=false;
	for(var i=1;i<dTree.length;i++){
		var node=dTree[i];
		var isLeaf=hasChild(node.id,object);
		if(node.attributes==parentId){
			var length=childs.length;
			childs.unshift(node);
		}
		else{
			//alert(i);
			/*
			for(var j=0;j<theTree.length;j++){
				var ss=compare.pop();
				if(ss.id==parentId){
					index=j;
				}
			}*/
			theTree[theTree.length]=childs;
			compare.push(childs);
			childs=new Array();
			childs.unshift(node);
			parentId=node.attributes;

		}
		theTree.push(node);
	}
	//air.trace("this is tree made now:::"+dTree+" length::"+dTree.length);
	return theTree;
}
function hasChild(id,object){
	var x=getChildNodes(id,object);
	if(x!=null){
		return false;
	}
	else{
		return true;
	}
}

 function getDescendants(rootId,object)
    {
        var descendants = new Array();//arraylist
        var stack = new Array();//stack
        var childNodes = getChildNodes(rootId,object);//arraylist
        //var node=null;
        for (var i = 0; i < childNodes.length; i++)
        {
            var node =  childNodes[i];
			if(node!=null){
            stack.push(node);
			descendants[i]=node;
			}
			//air.trace("iiiiiiiiiiiiiiiiiiiiiii:::::"+i);
            
        }

        while (!stack.length==0)
        {
            var node = stack.pop();
            childNodes = getChildNodes(node.id,object);
            for (var i = 0; i < childNodes.length; i++)
            {
				if(childNodes!=null){
					var length=descendants.length+1;
					stack.push(childNodes[i]);
					//air.trace("size:::::::::::::::::"+length+" ff:"+childNodes.length);
					descendants[length]=childNodes[i];
				}
            }
        }
        return descendants;
    }
function getChildNodes(parentId,object){
	var total=0;
	var dTree=new Array();
	for(var j=0;j<object.length;j++){
		var chNode=object[j];
		if(chNode.parent_id==parentId){
			//air.trace("hhhhhhhhhhhhhh::"+total+" chNode.text::"+chNode.text+" id::"+chNode.id);
			dTree[total]={"href":"zone.html","isClass":true,"iconCls":"icon-cls","text":chNode.text,"leaf":true,"cls":"cls","id":chNode.id};
			total=total+1;
			//air.trace("in has parent:::"+dTree[total].id+","+dTree[total].text+","+dTree[total].leaf+","+dTree[total].cls);
		}
	}
	return dTree;
}
var object2=[
	{"text":"testtest","isLeaf":true,"parent_id":"320","cls":"folder nonSelectable","id":"120"},
	{"text":"Other","isLeaf":false,"parent_id":"40","cls":"folder nonSelectable","id":"320"},
	{"text":"DDCSP Service","isLeaf":false,"parent_id":"22","cls":"folder nonSelectable","id":"40"},
	{"text":"Smile Service","isLeaf":false,"parent_id":"100","cls":"folder nonSelectable","id":"22"},
	{"text":"Internet Service","isLeaf":true,"parent_id":"122","cls":"folder nonSelectable","id":"25"},
	{"text":"Voice Traffic","isLeaf":true,"parent_id":"22","cls":"file","id":"50"}
];

function call(){
return object2;//dTree(object2,22);
}
var object=[
	{"text":"Other","leaf":false,"cls":"folder nonSelectable","id":"320"},
	{"text":"DDCSP Service","leaf":false,"cls":"folder nonSelectable","id":"40"},
	{"text":"Smile Service","leaf":false,"cls":"folder nonSelectable","id":"22"},
	{"text":"Internet Service","leaf":false,"cls":"folder nonSelectable","id":"25"},
	{"text":"Voice Traffic","leaf":true,"cls":"file","id":"50"}
];
//makeTree(object);
/*
Docs.classData =
{
	"id":"apidocs",
	"iconCls":"icon-docs",
	"text":"Demo Application Root",
	"singleClickExpand":true,
	"children":[
	{
		"id":"pkg-Ext",
		"text":"Port Folio",
		"iconCls":"icon-pkg",
		"cls":"package",
		"singleClickExpand":true,
		"children":[
		{
			"id":"pkg-air",
			"text":"Entry Forms",
			"iconCls":"icon-pkg",
			"cls":"package",
			"singleClickExpand":true,
			"children":[
			{
				"href":"zone.html",
				"text":"Zone",
				"id":"zonePanel",
				"isClass":true,
				"iconCls":"icon-static",
				"cls":"cls",
				"leaf":true
			},{
				"href":"area.html",
				"text":"Area",
				"id":"areaPanel",
				"isClass":true,
				"iconCls":"icon-cls",
				"cls":"cls",
				"leaf":true
			},{
				"href":"branch.html",
				"text":"Branch",
				"id":"branchPanel",
				"isClass":true,
				"iconCls":"icon-cls",
				"cls":"cls",
				"leaf":true
			},{
				"href":"center.html",
				"text":"Center",
				"id":"centerPanel",
				"isClass":true,
				"iconCls":"icon-cls",
				"cls":"cls",
				"leaf":true
			},{
				"href":"loanee.html",
				"text":"Loanee",
				"id":"loaneePanel",
				"isClass":true,
				"iconCls":"icon-cls",
				"cls":"cls",
				"leaf":true
			}]
		}]
	}]
};
*/
				/*
				function getAllDescendent(rootId,object,dTree){
	var total=0;
	var length=dTree.length;
	var stack=new Array();
	var children=getChildNodes(rootId,object);
	var descendents=new Array();
		for (int i = 0; i < children.length; i++)
        {
			//length=length+1;
            node =  children[i];
            descendents[i]=node;
            //dTree[length]=node;
        }
		for (int j;j<children.length;j++)
        {
            node = stack.pop();
            childNodes = getChildNodes(node.getId(),object);
            for (int i = 0; i < childNodes.length; i++)
            {
                stack.push(childNodes[i]);
				length=length+1;
                descendants[]=(childNodes[i]);
				dTree[length]=node;
            }
        }
}
*/