<%@ include file="/Common/Include/taglib.jsp"%>
<%@ taglib uri="/crystal-tags-reportviewer.tld" prefix="crviewer" %>
<%@page contentType="text/html" import="java.util.*"%>
<%@page contentType="text/html"%> 

<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%> 
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%> 
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%
RoleDTO role = null;
List<RoleDTO> roleList = null;
String uniqueCode = "";
String roleName = "";

uniqueCode = WebUtils.getUniqueCode(request);
role = WebUtils.getUserRole(request);
 
roleList = (List<RoleDTO>)request.getSession().getAttribute(SESSION_KEYS.ROLE_LIST); 
if(role!=null)
{
	roleName = role.getUniqueCode();
}
 //background:url("/app/assets/console/arrow-down.png") no-repeat scroll 95% 15px rgba(70, 70, 70, 0.25);
 
%>
<html>
	<head>     
	<title>Enterprise Resource Planning Platform</title>
	<style>
	
	.title{
		color: #000;
		background: #234666;
		font-size:22px;
		text-shadow: 0px 1px 1px #fff;
		/*
		background: #124666;
		color: #000;
		background: #000;
		text-shadow: 0 0 4px #ccc, 0 -5px 4px #ff3, 2px -10px 6px #fd3, -2px -15px 11px #f80, 2px -18px 18px #f20;
		*/
	}
	.logo{
	width:123px;
	height:54px;
	cursor:pointer;
	text-align:center;
	float:right;
	padding:10px 5px 0px 5px;
	margin:2px;
	font:bold 12px Tahoma, Geneva, sans-serif;	
	}
	.x-grid3-hd-inner{
		white-space:normal;
	}
	.x-grid3-cell-inner {
		white-space:normal;
	}
	.add24 {
    	background-image: url(./Common/JavaScript/ext-2.0/resources/images/default/dd/drop-add.gif) !important;
	}
	.upload-icon {
            background: url('extjs/icons/fam/image_add.png') no-repeat 0 0 !important;
        }
    .button-logout {
		background:url("./images/common/buttons.png") no-repeat scroll -6px -193px transparent;
		color:#EEEFFF;
		display:inline-block;
		padding:2px 10px 2px 20px;
		opacity: .75;
		border:2px solid WHITE;
	}
	.button-logout:hover{
	/*
	color:#FFF;	
	border:1px solid #8db2e3;
	background-color:#002866;
	*/
	
	opacity: 1;
 
	/*Reflection
	-webkit-box-reflect: below 0px -webkit-gradient(linear, left top, left bottom, from(transparent), color-stop(.7, transparent), to(rgba(0,0,0,0.4)));
	*/
	/*Glow*/
	border-color:white;
	-webkit-box-shadow: 0px 0px 20px rgba(0, 0, 255, 0.6);
	-moz-box-shadow: 0px 0px 20px rgba(0, 0, 255, 0.6);
	box-shadow: 0px 0px 20px rgba(0, 0, 255, 0.6);
	}
	</style>
	<link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/docs.css"></link>
	<link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/style.css"></link>
	<!-- <link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/common.css"></link>-->
	<link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/console.css"></link>
	</head>
	<body scroll="no" id="docs">
		<div id="loading-mask" style=""></div>
		<div id="loading">
		  	<div class="loading-indicator"><img src="./Common/JavaScript/resources/extanim32.gif" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>
		  	Loading...
		  	</div>
		</div>
<link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/mail.css"/>
<link rel="stylesheet" type="text/css" href="./Common/JavaScript/ext-4.1.0/resources/css/ext-all.css"/>


<script type="text/javascript" src="./Common/JavaScript/ext-4.1.0/ext.js"></script>
<script type="text/javascript" src="./app-all.js"></script>
<!-- <script type="text/javascript" src="./Common/JavaScript/ext-2.0/DateTime.js"></script>-->
<!-- <script type="text/javascript" src="ext-air/ext-air.js"></script>-->
<!-- 
<script type="text/javascript" src="./Common/JavaScript/ux/FieldLabeler.js"></script>
    <script type="text/javascript" src="./Common/JavaScript/ux/FieldReplicator.js"></script>
	<script type="text/javascript" src="./Common/JavaScript/ux/fileuploadfield/FileUploadField.js"></script>
	<link rel="stylesheet" type="text/css" href="./Common/JavaScript/ux/fileuploadfield/css/fileuploadfield.css"/>
	<script type="text/javascript" src="./Common/JavaScript/ux//RowEditor.js"></script>
	<script type="text/javascript" src="./Common/JavaScriptstates.js"></script>
    <link rel="stylesheet" href="./Common/JavaScript/ux/css/RowEditor.css" />
-->
	
<!-- 
<script type="text/javascript" src="./Common/JavaScript/config-js/TabCloseMenu.js"></script>
 -->
<!-- 
<script type="text/css" src="./Common/JavaScript/resources/ext-add-button-formfield.css"></script> 
<script type="text/javascript" src="./Common/JavaScript/config-js/ext-add-button-formfield.js"></script>
substring
 -->
<link rel="stylesheet" href="./Common/JavaScript/resources/seatplan.css" type="text/css" media="screen" />
<script type="text/javascript" src="./Common/JavaScript/forms/ScriptLoader.js"></script>
<!-- 
<script type="text/javascript" src="./Common/JavaScript/config-js/docs.js"></script>
<script type="text/javascript" src="./Common/JavaScript/forms/mailbox.js"></script>	
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./Common/JavaScript/config-js/ext-config.js"></script>

<script type="text/javascript" src="./Common/JavaScript/config-js/load.js"></script>
<script type="text/javascript" src="./Common/JavaScript/config-js/Templates.js"></script>
<script type="text/javascript" src="./Common/JavaScript/config-js/makeTree.js"></script>
<script type="text/javascript" src="./Common/JavaScript/config-js/PickerWindow.js"></script>



-->
<!--
<script type="text/javascript" src="./Common/JavaScript/config-js/ItemDeleter.js"></script>
-->
 

<script type="text/javascript">

/*
Ext.application({
	name: 'T4E',
	appFolder: 'app',
    launch: function() {
		Ext.require(['T4E.panel.Custom','T4E.panel.Custom2','Ext.container.Viewport','Ext.layout.container.Anchor'],
				function() { // callback
					Ext.create('Ext.container.Viewport', {
						layout: { type: 'anchor' },
						items: [{
							xtype: 'button',
							text: 'Create by Ext.create()',
							handler: function(){
								Ext.ComponentQuery.query('viewport')[0].add(
									Ext.create('T4E.panel.Custom')
								);
							}
						},
						{
							xtype: 'button',
							text: 'Create by lazy xtype',
							handler: function(){
								Ext.ComponentQuery.query('viewport')[0].add(
									{ xtype: 't4e_custompanel2' }
								);
							}
						}] // eo items
					}); // eo Ext.create(viewport)
				}); // eo Ext.require() callback
    } // eo launch()
});
*/
		//var nodes=call();
/* Ext.application({
	name: 'T4E',
	appFolder: 'app',
    launch: function() {
		Ext.require([ 'Common.JavaScript.config-js.TabCloseMenu'],
				function() { // callback
					alert('loaded');
				}); // eo Ext.require() callback
    } // eo launch()
});
 */
 Docs = {};
 var voucherType = '';	

Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext', './Common/JavaScript/ext-4.1.0/src');
Ext.require(['Ext.data.proxy.Ajax',
             'Ext.form.field.Trigger','Ext.tree.Panel'/*,'Ext.XTemplate','Ext.tree.Panel',
             'Ext.panel.Panel','Ext.tab.Panel','Ext.form.field.ComboBox','Ext.data.reader.Xml' */],
function(){
	
 	//p=getDcrinfoPanel();
 	Ext.Ajax.request({
		   url: 'loginAction.csmp?method=loginUserRoleFunctions',
		   success: function(resp) {
		       //Ext.Msg.alert('Success', 'Role Functions Added');
		       //nodes = Ext.util.JSON.decode(resp.responseText);
		       nodes = Ext.JSON.decode(resp.responseText);
		       
		       //console.log("docs is null::"+(Docs==null));
		      Ext.BLANK_IMAGE_URL = './Common/JavaScript/resources/s.gif';

		     
		      // console.log("nodes:::"+nodes);
		      //var Docs;
		      if(Docs!='undefined' && Docs!=null)
			  {		    	  
		    	  Docs.classData = Ext.JSON.decode(resp.responseText);
		          Docs.icons = {"Ext.Action":"icon-cls","Ext.Ajax":"icon-static","Ext.BoxComponent":"icon-cmp","Ext.Button":"icon-cmp","Ext.ColorPalette":"icon-cmp","Ext.Component":"icon-cls","Ext.ComponentMgr":"icon-static","Ext.CompositeElement":"icon-cls","Ext.CompositeElementLite":"icon-cls","Ext.Container":"icon-cmp","Ext.CycleButton":"icon-cmp","Ext.DataView":"icon-cmp","Ext.DatePicker":"icon-cmp","Ext.DomHelper":"icon-static","Ext.DomQuery":"icon-static","Ext.Editor":"icon-cmp","Ext.Element":"icon-cls","Ext.EventManager":"icon-static","Ext.EventObject":"icon-static","Ext.Fx":"icon-cls","Ext.History":"icon-static","Ext.KeyMap":"icon-cls","Ext.KeyNav":"icon-cls","Ext.Layer":"icon-cls","Ext.LoadMask":"icon-cls","Ext.MessageBox":"icon-static","Ext.PagingToolbar":"icon-cmp","Ext.Panel":"icon-cmp","Ext.ProgressBar":"icon-cmp","Ext.QuickTip":"icon-cmp","Ext.QuickTips":"icon-static","Ext.Resizable":"icon-cls","Ext.Shadow":"icon-cls","Ext.Slider":"icon-cmp","Ext.SplitBar":"icon-cls","Ext.SplitBar.AbsoluteLayoutAdapter":"icon-cls","Ext.SplitBar.BasicLayoutAdapter":"icon-cls","Ext.SplitButton":"icon-cmp","Ext.StatusBar":"icon-cmp","Ext.StoreMgr":"icon-static","Ext.TabPanel":"icon-cmp","Ext.TaskMgr":"icon-static","Ext.Template":"icon-cls","Ext.Tip":"icon-cmp","Ext.ToolTip":"icon-cmp","Ext.Toolbar":"icon-cmp","Ext.Toolbar.Button":"icon-cmp","Ext.Toolbar.Fill":"icon-cls","Ext.Toolbar.Item":"icon-cls","Ext.Toolbar.Separator":"icon-cls","Ext.Toolbar.Spacer":"icon-cls","Ext.Toolbar.SplitButton":"icon-cmp","Ext.Toolbar.TextItem":"icon-cls","Ext.Updater":"icon-cls","Ext.Updater.BasicRenderer":"icon-cls","Ext.Updater.defaults":"icon-cls","Ext.Viewport":"icon-cmp","Ext.Window":"icon-cmp","Ext.WindowGroup":"icon-cls","Ext.WindowMgr":"icon-static","Ext.XTemplate":"icon-cls","Ext.air.DragType":"icon-static","Ext.air.FileProvider":"icon-cls","Ext.air.NativeObservable":"icon-cls","Ext.air.NativeWindow":"icon-cls","Ext.air.NativeWindowGroup":"icon-cls","Ext.air.NativeWindowManager":"icon-static","Ext.air.Sound":"icon-static","Ext.air.SystemMenu":"icon-static","Ext.data.ArrayReader":"icon-cls","Ext.data.Connection":"icon-cls","Ext.data.DataProxy":"icon-cls","Ext.data.DataReader":"icon-cls","Ext.data.GroupingStore":"icon-cls","Ext.data.HttpProxy":"icon-cls","Ext.data.JsonReader":"icon-cls","Ext.data.JsonStore":"icon-cls","Ext.data.MemoryProxy":"icon-cls","Ext.data.Node":"icon-cls","Ext.data.Record":"icon-cls","Ext.data.ScriptTagProxy":"icon-cls","Ext.data.SimpleStore":"icon-cls","Ext.data.SortTypes":"icon-static","Ext.data.Store":"icon-cls","Ext.data.Tree":"icon-cls","Ext.data.XmlReader":"icon-cls","Ext.dd.DD":"icon-cls","Ext.dd.DDProxy":"icon-cls","Ext.dd.DDTarget":"icon-cls","Ext.dd.DragDrop":"icon-cls","Ext.dd.DragDropMgr":"icon-static","Ext.dd.DragSource":"icon-cls","Ext.dd.DragZone":"icon-cls","Ext.dd.DropTarget":"icon-cls","Ext.dd.DropZone":"icon-cls","Ext.dd.Registry":"icon-static","Ext.dd.ScrollManager":"icon-static","Ext.dd.StatusProxy":"icon-cls","Ext.form.Action":"icon-cls","Ext.form.Action.Load":"icon-cls","Ext.form.Action.Submit":"icon-cls","Ext.form.BasicForm":"icon-cls","Ext.form.Checkbox":"icon-cmp","Ext.form.CheckboxGroup":"icon-cmp","Ext.form.ComboBox":"icon-cmp","Ext.form.DateField":"icon-cmp","Ext.form.Field":"icon-cmp","Ext.form.FieldSet":"icon-cmp","Ext.form.FormPanel":"icon-cmp","Ext.form.Hidden":"icon-cmp","Ext.form.HtmlEditor":"icon-cmp","Ext.form.Label":"icon-cmp","Ext.form.NumberField":"icon-cmp","Ext.form.Radio":"icon-cmp","Ext.form.RadioGroup":"icon-cmp","Ext.form.TextArea":"icon-cmp","Ext.form.TextField":"icon-cmp","Ext.form.TimeField":"icon-cmp","Ext.form.TriggerField":"icon-cmp","Ext.form.VTypes":"icon-static","Ext.grid.AbstractSelectionModel":"icon-cls","Ext.grid.CellSelectionModel":"icon-cls","Ext.grid.CheckboxSelectionModel":"icon-cls","Ext.grid.ColumnModel":"icon-cls","Ext.grid.EditorGridPanel":"icon-cmp","Ext.grid.GridDragZone":"icon-cls","Ext.grid.GridPanel":"icon-cmp","Ext.grid.GridView":"icon-cls","Ext.grid.GroupingView":"icon-cls","Ext.grid.PropertyColumnModel":"icon-cls","Ext.grid.PropertyGrid":"icon-cmp","Ext.grid.PropertyRecord":"icon-cls","Ext.grid.PropertyStore":"icon-cls","Ext.grid.RowNumberer":"icon-cls","Ext.grid.RowSelectionModel":"icon-cls","Ext.layout.AbsoluteLayout":"icon-cls","Ext.layout.Accordion":"icon-cls","Ext.layout.AnchorLayout":"icon-cls","Ext.layout.BorderLayout":"icon-cls","Ext.layout.BorderLayout.Region":"icon-cls","Ext.layout.BorderLayout.SplitRegion":"icon-cls","Ext.layout.CardLayout":"icon-cls","Ext.layout.ColumnLayout":"icon-cls","Ext.layout.ContainerLayout":"icon-cls","Ext.layout.FitLayout":"icon-cls","Ext.layout.FormLayout":"icon-cls","Ext.layout.TableLayout":"icon-cls","Ext.menu.Adapter":"icon-cmp","Ext.menu.BaseItem":"icon-cmp","Ext.menu.CheckItem":"icon-cmp","Ext.menu.ColorItem":"icon-cmp","Ext.menu.ColorMenu":"icon-cls","Ext.menu.DateItem":"icon-cmp","Ext.menu.DateMenu":"icon-cls","Ext.menu.Item":"icon-cmp","Ext.menu.Menu":"icon-cls","Ext.menu.MenuMgr":"icon-static","Ext.menu.Separator":"icon-cmp","Ext.menu.TextItem":"icon-cmp","Ext.state.CookieProvider":"icon-cls","Ext.state.Manager":"icon-static","Ext.state.Provider":"icon-cls","Ext.tree.AsyncTreeNode":"icon-cls","Ext.tree.DefaultSelectionModel":"icon-cls","Ext.tree.MultiSelectionModel":"icon-cls","Ext.tree.RootTreeNodeUI":"icon-cls","Ext.tree.TreeDragZone":"icon-cls","Ext.tree.TreeDropZone":"icon-cls","Ext.tree.TreeEditor":"icon-cmp","Ext.tree.TreeFilter":"icon-cls","Ext.tree.TreeLoader":"icon-cls","Ext.tree.TreeNode":"icon-cls","Ext.tree.TreeNodeUI":"icon-cls","Ext.tree.TreePanel":"icon-cmp","Ext.tree.TreeSorter":"icon-cls","Ext.util.CSS":"icon-static","Ext.util.ClickRepeater":"icon-cls","Ext.util.DelayedTask":"icon-cls","Ext.util.Format":"icon-static","Ext.util.JSON":"icon-static","Ext.util.MixedCollection":"icon-cls","Ext.util.Observable":"icon-cls","Ext.util.TaskRunner":"icon-cls","Ext.util.TextMetrics":"icon-static","Array":"icon-cls","Date":"icon-cls","Ext":"icon-static","Function":"icon-cls","Number":"icon-cls","String":"icon-cls"};
		      }
		              success.one=true;
		       Ext.namespace('Ext.staticdata');
		       
		       populateDocs();
		       /*
		       updateBoxes(); */
		        
		       
				
		       Ext.staticdata.role = [
				<%
				if(roleList!=null)
				{
				for(RoleDTO roledto:roleList)
				{
				%>
				alert('<%=roledto.getUniqueCode()%>');
				<%
				}
				}
				%>
		                               
		       ];
		   },
		   failure: function(f,a){
		       Ext.Msg.alert('Warnning', 'Error occured in previous action');
		   },
		   headers: {
		       'my-header': 'foo'
		   }
		});	   
	}
);
/*  Ext.ux.ScriptMgr.load({
     scripts : ['./Common/JavaScript/ext-4.1.0/src/data/proxy/Ajax.js'],
     callback : function() {
     }
	}); */
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
      //alert('b4::'+Docs.classData);
	  Docs.classData = nodes;
	  //alert('after::'+Docs.classData);
   } else {
	  // console.log("delaying");
      task.delay(50);
      
   }
});
task.delay(50);

var nodes=null;



//Ext.onReady(function(){
	
//});

//console.log("delay") // -> 5 records


</script>
<div id="header">



	<div class="logo blank">
	<a href="http://www.swift-corp.com" style="float:right;margin-right:10px;" target="_blank"><img src="./images/logo/Logo_swift.gif" 
	style="width:83px;height:34px;margin-bottom:0px;bodyStyle:'padding:2px'"/></a>
	</div>

	<div class="api-title"><span class="title">Enterprise Resource Planning Platform</span>
	
	
		        <div width="330" style="float:right;margin-right:20px;">					    
						<div><span>|</span>
						<span class="logout">Welcome</span> 
						<span class="logout">[<%=roleName%>]</span>
						<span>|</span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span>
							<ul id="utility-nav" class="navbar-text" style="right:130px;top:1px;">
								<li id="toggle" class="dropdown username hidden-phone">
								<a onclick="return toggleMenu();" class="dropdown-toggle" href="#"><%=uniqueCode%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
								<ul class="dropdown-menu hidden-phone">
								<li><a href="#">My Account</a></li>
								<li><a class="sign_out" href="loginAction.csmp?method=logout">Sign Out</a></li>
								</ul>
								</li>
							</ul>
						</span>					
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
<script type="text/javascript">
var htmlInput='<div id="welcome-mail" class="x-hide-display">Main Open Tab.</div>'+
'<div id="menu" class="x-hidden">'+
'	<div id="main_Menu">'+
'		 <table width="100%" border="0" cellspacing="1" cellpadding="0">'+
'		 <tr><td width="11%" id="nav_menu" class="th3"><%//=user_name%></td></tr>'+
'			<tr><td>'+
'				<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
'				  <tr>'+
'					<td>'+
'					<input type="text" class="search_in" id="search_mail" value="Search mail ..." onclick="make_blank(\'search_mail\',\'Search mail ...\');" />'+
'					</td>'+
'					<td><input name="" value="Go" type="button" class="search_btn" onClick="searchMail();" /></td>'+
'				  </tr>'+
'				</table>'+
'				<br>'+
'			</td></tr>'+		
'			<tr><td width="11%" id="nav_menu" class="items-icon" onClick="" style="border-bottom:1px solid #E6F2F2;">'+
'				<a href="javascript:stop()" onClick="check_inbox();return anchorPreventDefault(event);" >'+
				'<img class="items-icon check" src="./images/mail/images/refresh-icon.png"/>'+
'				 Check Mail</a></td></tr>'+
'			<tr><td id="nav_menu" class="item-icon" style="border-bottom:1px solid #E6F2F2;">'+
'				<a href="javascript:stop()" onClick="show(\'munu_1\');return anchorPreventDefault(event);">'+
					'<img class="items-icon" src="./images/mail/images/mailbox-icon_b.png"/>'+
					'Inbox</a></td></tr>'+
'			<tr><td><div id="munu_1" class="hidden"></div></td></tr>'+		
'			<tr><td id="nav_menu" class="item-icon" style="border-bottom:1px solid #E6F2F2;">'+
'				<a href="javascript:stop()" onClick="show_inbox(\'\',\'Refered\',\'\',\'\',\'\',\'share\');return anchorPreventDefault(event);">'+
				'<img class="items-icon" src="./images/mail/images/send-icon_b.png"/>'+
					' Share Message</a></td></tr>'+						
'			<tr><td id="nav_menu" class="item-icon" style="border-bottom:1px solid #E6F2F2;">'+
'				<a href="javascript:stop()" onClick="compose();return anchorPreventDefault(event);" >'+
					'<img class="items-icon" src="./images/mail/images/Compose-icon_b.png"/>'+
				' Compose New </a></td></tr>'+
'			<tr><td id="nav_menu" class="item-icon" onClick="return false;" style="border-bottom:1px solid #E6F2F2;">'+
'				<a href="javascript:stop()" onClick="show_inbox(\'\',\'Sent\',\'\',\'\',\'\',\'sent\');return anchorPreventDefault(event);">'+
					'<img class="items-icon" src="./images/mail/images/SentMail_b.png"/>'+
				' Sent </a></td></tr>'+
'			<tr><td id="nav_menu" class="item-icon" onClick="return false;" style="border-bottom:1px solid #E6F2F2;">'+
'				<a href="javascript:stop()" onClick="show_inbox(\'\',\'Draft\',\'\',\'\',\'\',\'draft\');return anchorPreventDefault(event);">'+
				'<img class="items-icon" src="./images/mail/images/Contacts-icon_b.png"/>'+
				'Draft </a></td></tr>'+
'            <tr><td id="nav_menu" class="item-icon" style="border-bottom:1px solid #E6F2F2;">'+
'				<a href="javascript:stop()" onClick="mail_settings();return anchorPreventDefault(event);">'+
				'<img class="items-icon" src="./images/mail/images/E-mail-icon.png"/>'+
				' Settings</a></td></tr>'+
'			 <tr><td id="nav_menu" class="item-icon" style="border-bottom:1px solid #E6F2F2;">'+
'				<a href="Promotion.jsp?id=<%//=id%>">'+
				'<img class="items-icon" src="./images/mail/images/E-mail-icon.png"/>'+
				'Promotion</a></td></tr>'+		    
'		</table>'+		
'	</div>'+
'</div>'+
'<div id="menu2" class="x-hidden">'+
'	<div id="failed_Menu">'+
'	<UL>'+
'		<LI><A href="#" onClick="show_failed();return false;" >Failed List</A>'+
'	</UL>'+
'	</div>'+
'</div>'+
'<div id="contacts" class="x-hidden">'+
'	<table width="100%" border="0" cellspacing="5" cellpadding="5">'+
'	  <tr>'+
'		<td class="m_t1">'+
'			<A href="#" onClick="show_company_contacts();return false;" > Company Contacts</A>'+
'			<div id="company_contacts"></div>'+
'		</td>'+
'	  </tr>'+
'	  <tr>'+
'		<td class="m_t1">'+
'			<A href="#" onClick="show_office_contacts(); return false;"> Office Contacts</A>'+
'			<div id="office_contacts"></div>'+
'		</td>'+
'	  </tr>'+
'	</table>'+
'</div>'+
'<div id="settingsTab" class="x-hidden">'+
'	<table width="100%" border="0" cellspacing="5" cellpadding="5">'+
'	  <tr>'+
'		<td class="m_t1">'+
'			<A href="#" onClick="show_tagged_mail();return false;" >Tagged Email</A>'+
'			<div id="tagged_email"></div>'+
'		</td>'+
'	  </tr>'+
'	  <tr>'+
'		<td class="m_t1">'+
'			<A href="#" onClick="add_tagged_mail(); return false;"> Add New Mail</A>'+
'		</td>'+
'	  </tr>'+
'     <tr>'+
'		<td class="m_t1">'+
'			<A href="#" onClick="mail_settings(); return false;"> Mail Settings</A>'+
'		</td>'+
'	  </tr>'+
'	</table>'+
'</div>'+

'<div id="flexi_sub_menu" class="x-hidden">'+
'	<div  id="sub_menu">'+
'        <ul>'+
'			<li><A href="#" onClick="flexi_delay(); return false;" >Flexi Delay</A></li>'+
'            <li><A href="report.jsp?id=<%//=id%>" target="_new">Reports</A></li>'+
'            <li><A href="#" onClick="total_flexi_chart(); return false;" >Usage</A></li>'+
'        </ul>'+
'    </div>'+
'</div>'+

'<div id="1pinless_sub_menu" class="x-hidden">'+
'	<div  id="sub_menu">'+
'        <ul>'+
'			<li><A href="#" onClick="pinless_users_states(); return false;" >All Users [State wise]</A></li>'+
'            <li><A href="#" onClick="pinless_cur_users_states(); return false;" >Active Users [State wise]</A></li>'+
'            <li><A href="#" onClick="user_signup_month(); return false;" >Users Sign up [Month wise]</A></li>'+
'            <li><A href="#" onClick="user_signup_day(); return false;" >Users Sign up [Last 30Days]</A></li>'+
'            <li><A href="#" onClick="flexi_user_signup_month(); return false;" >Flexi Signup [Month wise]</A></li>'+
'            <li><A href="#" onClick="flexi_user_signup_days(); return false;" >Flexi Signup [Last 30Days]</A></li>'+
'            <li><A href="#" onClick="conncurrent_calls(); return false;" >CONCURRENT CALLS</A></li>'+
'            <li><A href="#" onClick="conncurrent_calls_state(); return false;" >CONCURRENT CALLS [STATES ]</A></li>'+                 
'        </ul>'+
'    </div>'+
'</div>'+



'<div id="company_inbox" class="x-hidden"></div>'+
'<div id="top_bar" class="x-hidden">'+
'	<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
'	  <tr>'+
'		<td>&nbsp;</td>'+
'		<td align="right"  class="t1"><a href="logout.jsp">Logout</a>&nbsp;&nbsp;</td>'+
'	  </tr>'+
'	</table>'+
'</div>'+
'<div id="settingsDiv"></div>'+
'<div id="checkMailDiv" class="checkMailDiv hidden">Loading Mails</div>';
document.write(htmlInput);
function toggleMenu()
{
	var toggle = document.getElementById("toggle");
	var className = toggle.getAttribute("class");
	if(className.indexOf("open")>-1)
	{
		className = className.replace(" open","");
	}
	else
	{
		className = className+" open";
	}
	toggle.setAttribute("class",className);
	return false;
}
//updateBoxes();
</script>
</html>

