<%@ include file="/Common/Include/taglib.jsp"%>
<%@page contentType="text/html" import="java.util.*"%>
<%@page contentType="text/html"%> 

<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%> 
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%> 

<%

 String uniqueCode = WebUtils.getUniqueCode(request);
 RoleDTO role = WebUtils.getUserRole(request);
%>
<html>
	<head>     
	<link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/docs.css"></link>
	<link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/style.css"></link>
	<body scroll="no" id="docs">
		<div id="loading-mask" style=""></div>
		<div id="loading">
		  	<div class="loading-indicator"><img src="./Common/JavaScript/resources/extanim32.gif" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>
		  	Loading...
		  	</div>
		</div>
<link rel="stylesheet" type="text/css" href="./Common/JavaScript/ext-2.0/resources/css/ext-all.css"/>	
<script type="text/javascript" src="./Common/JavaScript/ext-2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="./Common/JavaScript/ext-2.0/ext-all.js"></script>	
<!-- <script type="text/javascript" src="ext-air/ext-air.js"></script>-->
<script type="text/javascript" src="./Common/JavaScript/config-js/ext-config.js"></script>	
<script type="text/javascript" src="./Common/JavaScript/config-js/TabCloseMenu.js"></script>  

<script type="text/javascript">
		//var nodes=call();
var success = {
  one: false,
  two: false
};

// Task
var task = new Ext.util.DelayedTask(function(){
	//console.log("enter delay");
   // Check for success
   if (success.one) {
	  // console.log("success true");
      // Callback
      //doCallback();
   } else {
	  // console.log("delaying");
      task.delay(50);
      
   }
});
task.delay(50);

var nodes=null;
Ext.Ajax.request({
   url: 'loginAction.csmp?method=loginUserRoleFunctions',
   success: function(resp) {
       Ext.Msg.alert('Success', 'Role Functions Added');
       nodes = Ext.util.JSON.decode(resp.responseText);
       //console.log("docs is null::"+(Docs==null));
       while(Docs==null)
       {
			alert('a');  
       }
      // console.log("nodes:::"+nodes);
       Docs.classData = Ext.util.JSON.decode(resp.responseText);
       Docs.icons = {"Ext.Action":"icon-cls","Ext.Ajax":"icon-static","Ext.BoxComponent":"icon-cmp","Ext.Button":"icon-cmp","Ext.ColorPalette":"icon-cmp","Ext.Component":"icon-cls","Ext.ComponentMgr":"icon-static","Ext.CompositeElement":"icon-cls","Ext.CompositeElementLite":"icon-cls","Ext.Container":"icon-cmp","Ext.CycleButton":"icon-cmp","Ext.DataView":"icon-cmp","Ext.DatePicker":"icon-cmp","Ext.DomHelper":"icon-static","Ext.DomQuery":"icon-static","Ext.Editor":"icon-cmp","Ext.Element":"icon-cls","Ext.EventManager":"icon-static","Ext.EventObject":"icon-static","Ext.Fx":"icon-cls","Ext.History":"icon-static","Ext.KeyMap":"icon-cls","Ext.KeyNav":"icon-cls","Ext.Layer":"icon-cls","Ext.LoadMask":"icon-cls","Ext.MessageBox":"icon-static","Ext.PagingToolbar":"icon-cmp","Ext.Panel":"icon-cmp","Ext.ProgressBar":"icon-cmp","Ext.QuickTip":"icon-cmp","Ext.QuickTips":"icon-static","Ext.Resizable":"icon-cls","Ext.Shadow":"icon-cls","Ext.Slider":"icon-cmp","Ext.SplitBar":"icon-cls","Ext.SplitBar.AbsoluteLayoutAdapter":"icon-cls","Ext.SplitBar.BasicLayoutAdapter":"icon-cls","Ext.SplitButton":"icon-cmp","Ext.StatusBar":"icon-cmp","Ext.StoreMgr":"icon-static","Ext.TabPanel":"icon-cmp","Ext.TaskMgr":"icon-static","Ext.Template":"icon-cls","Ext.Tip":"icon-cmp","Ext.ToolTip":"icon-cmp","Ext.Toolbar":"icon-cmp","Ext.Toolbar.Button":"icon-cmp","Ext.Toolbar.Fill":"icon-cls","Ext.Toolbar.Item":"icon-cls","Ext.Toolbar.Separator":"icon-cls","Ext.Toolbar.Spacer":"icon-cls","Ext.Toolbar.SplitButton":"icon-cmp","Ext.Toolbar.TextItem":"icon-cls","Ext.Updater":"icon-cls","Ext.Updater.BasicRenderer":"icon-cls","Ext.Updater.defaults":"icon-cls","Ext.Viewport":"icon-cmp","Ext.Window":"icon-cmp","Ext.WindowGroup":"icon-cls","Ext.WindowMgr":"icon-static","Ext.XTemplate":"icon-cls","Ext.air.DragType":"icon-static","Ext.air.FileProvider":"icon-cls","Ext.air.NativeObservable":"icon-cls","Ext.air.NativeWindow":"icon-cls","Ext.air.NativeWindowGroup":"icon-cls","Ext.air.NativeWindowManager":"icon-static","Ext.air.Sound":"icon-static","Ext.air.SystemMenu":"icon-static","Ext.data.ArrayReader":"icon-cls","Ext.data.Connection":"icon-cls","Ext.data.DataProxy":"icon-cls","Ext.data.DataReader":"icon-cls","Ext.data.GroupingStore":"icon-cls","Ext.data.HttpProxy":"icon-cls","Ext.data.JsonReader":"icon-cls","Ext.data.JsonStore":"icon-cls","Ext.data.MemoryProxy":"icon-cls","Ext.data.Node":"icon-cls","Ext.data.Record":"icon-cls","Ext.data.ScriptTagProxy":"icon-cls","Ext.data.SimpleStore":"icon-cls","Ext.data.SortTypes":"icon-static","Ext.data.Store":"icon-cls","Ext.data.Tree":"icon-cls","Ext.data.XmlReader":"icon-cls","Ext.dd.DD":"icon-cls","Ext.dd.DDProxy":"icon-cls","Ext.dd.DDTarget":"icon-cls","Ext.dd.DragDrop":"icon-cls","Ext.dd.DragDropMgr":"icon-static","Ext.dd.DragSource":"icon-cls","Ext.dd.DragZone":"icon-cls","Ext.dd.DropTarget":"icon-cls","Ext.dd.DropZone":"icon-cls","Ext.dd.Registry":"icon-static","Ext.dd.ScrollManager":"icon-static","Ext.dd.StatusProxy":"icon-cls","Ext.form.Action":"icon-cls","Ext.form.Action.Load":"icon-cls","Ext.form.Action.Submit":"icon-cls","Ext.form.BasicForm":"icon-cls","Ext.form.Checkbox":"icon-cmp","Ext.form.CheckboxGroup":"icon-cmp","Ext.form.ComboBox":"icon-cmp","Ext.form.DateField":"icon-cmp","Ext.form.Field":"icon-cmp","Ext.form.FieldSet":"icon-cmp","Ext.form.FormPanel":"icon-cmp","Ext.form.Hidden":"icon-cmp","Ext.form.HtmlEditor":"icon-cmp","Ext.form.Label":"icon-cmp","Ext.form.NumberField":"icon-cmp","Ext.form.Radio":"icon-cmp","Ext.form.RadioGroup":"icon-cmp","Ext.form.TextArea":"icon-cmp","Ext.form.TextField":"icon-cmp","Ext.form.TimeField":"icon-cmp","Ext.form.TriggerField":"icon-cmp","Ext.form.VTypes":"icon-static","Ext.grid.AbstractSelectionModel":"icon-cls","Ext.grid.CellSelectionModel":"icon-cls","Ext.grid.CheckboxSelectionModel":"icon-cls","Ext.grid.ColumnModel":"icon-cls","Ext.grid.EditorGridPanel":"icon-cmp","Ext.grid.GridDragZone":"icon-cls","Ext.grid.GridPanel":"icon-cmp","Ext.grid.GridView":"icon-cls","Ext.grid.GroupingView":"icon-cls","Ext.grid.PropertyColumnModel":"icon-cls","Ext.grid.PropertyGrid":"icon-cmp","Ext.grid.PropertyRecord":"icon-cls","Ext.grid.PropertyStore":"icon-cls","Ext.grid.RowNumberer":"icon-cls","Ext.grid.RowSelectionModel":"icon-cls","Ext.layout.AbsoluteLayout":"icon-cls","Ext.layout.Accordion":"icon-cls","Ext.layout.AnchorLayout":"icon-cls","Ext.layout.BorderLayout":"icon-cls","Ext.layout.BorderLayout.Region":"icon-cls","Ext.layout.BorderLayout.SplitRegion":"icon-cls","Ext.layout.CardLayout":"icon-cls","Ext.layout.ColumnLayout":"icon-cls","Ext.layout.ContainerLayout":"icon-cls","Ext.layout.FitLayout":"icon-cls","Ext.layout.FormLayout":"icon-cls","Ext.layout.TableLayout":"icon-cls","Ext.menu.Adapter":"icon-cmp","Ext.menu.BaseItem":"icon-cmp","Ext.menu.CheckItem":"icon-cmp","Ext.menu.ColorItem":"icon-cmp","Ext.menu.ColorMenu":"icon-cls","Ext.menu.DateItem":"icon-cmp","Ext.menu.DateMenu":"icon-cls","Ext.menu.Item":"icon-cmp","Ext.menu.Menu":"icon-cls","Ext.menu.MenuMgr":"icon-static","Ext.menu.Separator":"icon-cmp","Ext.menu.TextItem":"icon-cmp","Ext.state.CookieProvider":"icon-cls","Ext.state.Manager":"icon-static","Ext.state.Provider":"icon-cls","Ext.tree.AsyncTreeNode":"icon-cls","Ext.tree.DefaultSelectionModel":"icon-cls","Ext.tree.MultiSelectionModel":"icon-cls","Ext.tree.RootTreeNodeUI":"icon-cls","Ext.tree.TreeDragZone":"icon-cls","Ext.tree.TreeDropZone":"icon-cls","Ext.tree.TreeEditor":"icon-cmp","Ext.tree.TreeFilter":"icon-cls","Ext.tree.TreeLoader":"icon-cls","Ext.tree.TreeNode":"icon-cls","Ext.tree.TreeNodeUI":"icon-cls","Ext.tree.TreePanel":"icon-cmp","Ext.tree.TreeSorter":"icon-cls","Ext.util.CSS":"icon-static","Ext.util.ClickRepeater":"icon-cls","Ext.util.DelayedTask":"icon-cls","Ext.util.Format":"icon-static","Ext.util.JSON":"icon-static","Ext.util.MixedCollection":"icon-cls","Ext.util.Observable":"icon-cls","Ext.util.TaskRunner":"icon-cls","Ext.util.TextMetrics":"icon-static","Array":"icon-cls","Date":"icon-cls","Ext":"icon-static","Function":"icon-cls","Number":"icon-cls","String":"icon-cls"};
       success.one=true;
   },
   failure: function(f,a){
       Ext.Msg.alert('Warnning', 'Error occured in previous action');
   },
   headers: {
       'my-header': 'foo'
   }
   /*,
   params: { foo: 'bar' }
   */
});

//console.log("delay") // -> 5 records


</script>
<script type="text/javascript" src="./Common/JavaScript/config-js/docs.js"></script>
<script type="text/javascript" src="./Common/JavaScript/config-js/load.js"></script>
<script type="text/javascript" src="./Common/JavaScript/config-js/Templates.js"></script>
<script type="text/javascript" src="./Common/JavaScript/config-js/makeTree.js"></script>

<script type="text/javascript" src="./Common/JavaScript/config-js/ItemDeleter.js"></script>
<script type="text/javascript" src="./Common/JavaScript/forms/ScriptLoader.js"></script>
<div id="header">
	<a href="http://extjs.com" style="float:right;margin-right:10px;"><img src="./Images/logo/logo.png" style="width:83px;height:24px;margin-top:1px;"/></a>

	<div class="api-title">Demo Platform
	
	
		        <div width="330" style="float:right;margin-right:10px;">					    
						<span>|</span>
						<span class="logout">Welcome</span> 
						<span class="logout"><%=uniqueCode%></span>
						<span class="logout">[<%=role.getUniqueCode()%>]</span>
						<span>|</span>
						<span class="logout"><b><a href="loginAction.csmp?method=logout"><font color="white">Logout</font></b></a></span>					
				</div>
	</div>
  </div>

<div id="classes"></div>

<div id="main"></div>
<div id="dcrinfosearch"></div>
<select id="search-options" style="display:none">
<option>Starts with</option>
<option>Ends with</option>
<option>Any Match</option>
</select>
</html>

