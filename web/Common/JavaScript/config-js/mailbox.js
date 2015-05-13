    //Ext.onReady(function(){

Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext', './Common/JavaScript/ext-4.1.0/src');
Ext.require('Ext.data.proxy.Ajax');
/*Ext.Loader.setPath('Ext.jquery', './js');

Ext.require([
    'Ext.jquery.jquery-132-min'
]);
*/
Ext.Loader.setPath('Ext.ux', './Common/JavaScript/ux');

Ext.require([
    'Ext.tab.*',
    'Ext.ux.TabCloseMenu'
]);



function getType(obj){
    if (obj === undefined) { return 'undefined'; }
    if (obj === null) { return 'null'; }
    return Object.prototype.toString.call(obj).split(' ').pop().split(']').shift().toLowerCase();
}
	var id = '';
	var user = '';
	 /*
	 $('a').bind('click',function(e){
     var $me = $(this);
      e.preventDefault();
      e.stopPropagation();
      $('<a />')
        .attr('href', $me.data('href'))
        .attr('target', '_blank')
        .css({display:'none'})
        .appendTo($('body'))
        .get(0).click();
  });*/
	
	function anchorPreventDefault(event)
	{
		event.preventDefault();
		event.stopPropagation();
		return false;
	}
	function replaceAll(txt, replace, with_this) {
		return String(txt).replace(new RegExp(replace, 'g'),with_this);
	}
	String.format = function() 
	{
		var s = arguments[0];
		var argument = '';
		for (var i = 0; i < arguments.length - 1; i++) {       
		    var reg = new RegExp("\\{" + i + "\\}", "gm");
		    argument = arguments[i + 1];
		    if(argument!=null)
		    {
		    	//argument = replaceAll(argument,"\"","\\\'");
		    	argument = replaceAll(argument,"\"","");
		    	argument = replaceAll(argument,"\n","");
		    	argument = replaceAll(argument,"\t","");
		    	argument = replaceAll(argument,"\b","");
		    	argument = replaceAll(argument,"'","\\\'");
		    			    	
		    }
		    s = s.replace(reg, argument);		    
		}
		//alert('s::'+s);
		return s;
	}
	function getMailBoxPanel(){		
		
		//alert('in mail box');
		var htmlInput='<div id="welcome" class="x-hide-display">Main Open Tab.</div>'+
			'<div id="menu" class="x-hidden">'+
	'	<div id="main_Menu">'+
	'		 <table width="100%" border="0" cellspacing="1" cellpadding="0">'+
	'		 <tr><td width="11%" id="nav_menu" class="th3">'+user+'</td></tr>'+
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
	'			<tr><td width="11%" id="nav_menu" class="check" onClick="" style="border-bottom:1px solid #E6F2F2;">'+
	'				<a href="javascript:stop()" onClick="check_inbox();return anchorPreventDefault(event);" > Check Mail</a></td></tr>'+
	'			<tr><td id="nav_menu" class="inbox" style="border-bottom:1px solid #E6F2F2;">'+
	'				<a href="javascript:stop()" onClick="show(\'munu_1\');return anchorPreventDefault(event);"> Inbox</a></td></tr>'+
	'			<tr><td><div id="munu_1" class="hidden"></div></td></tr>'+		
	'			<tr><td id="nav_menu" class="share" style="border-bottom:1px solid #E6F2F2;">'+
	'				<a href="javascript:stop()" onClick="show_inbox(\'\',\'Refered\',\'\',\'\',\'\',\'share\');return anchorPreventDefault(event);"> Share Message</a></td></tr>'+						
	'			<tr><td id="nav_menu" class="compose" style="border-bottom:1px solid #E6F2F2;">'+
	'				<a href="javascript:stop()" onClick="compose();return anchorPreventDefault(event);" > Compose New </a></td></tr>'+
	'			<tr><td id="nav_menu" class="sent" onClick="return false;" style="border-bottom:1px solid #E6F2F2;">'+
	'				<a href="javascript:stop()" onClick="show_inbox(\'\',\'Sent\',\'\',\'\',\'\',\'sent\');return anchorPreventDefault(event);"> Sent </a></td></tr>'+
	'			<tr><td id="nav_menu" class="draft" onClick="return false;" style="border-bottom:1px solid #E6F2F2;">'+
	'				<a href="javascript:stop()" onClick="show_inbox(\'\',\'Draft\',\'\',\'\',\'\',\'draft\');return anchorPreventDefault(event);"> Draft </a></td></tr>'+
    '            <tr><td id="nav_menu" class="acc_settings" style="border-bottom:1px solid #E6F2F2;">'+
	'				<a href="javascript:stop()" onClick="mail_settings();return anchorPreventDefault(event);"> Settings</a></td></tr>'+
	'			 <tr><td id="nav_menu" class="acc_settings" style="border-bottom:1px solid #E6F2F2;">'+
	'				<a href="Promotion.jsp?id='+id+'">Promotion</a></td></tr>'+		    
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
    '            <li><A href="report.jsp?id='+id+'" target="_new">Reports</A></li>'+
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
    '<div id="settingsDiv"></div>';
		
		var tempText = '<span class="t1"><img src="images/mail/images/loading-balls.gif" ></span>';
    	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    	var containerPanel = Ext.create('Ext.panel.Panel', {
            id:'mailboxDetail',
    		//frame: true,
    		title: 'Mail Box',
    		height:500,
    		autoHeight : true,
    		layout: 'fit',
    		html:htmlInput
	     });
 	       var viewport = Ext.create('Ext.panel.Panel', {
            layout: 'border',
            autoHeight:true,
            items: [
            		/*Ext.create('Ext.panel.Panel', {
                		region: 'north',
                		height: 50,
                		contentEl:'top_bar'
            		}),*/ {
					region: 'east',
					title: '',
					id:'comPanel',
					collapsible: true,
					split: true,
					width: 250,
					minSize: 175,
					maxSize: 400,
					margins: '0 5 5 0',
					layout: 'fit',
					items:Ext.create('Ext.tab.Panel', {
								border: false,
								activeTab: 0, 
								tabPosition: 'bottom',
								items: [{
										html: '<div id="company_inbox"></div><br><div id="company_all"></div>',
										title: 'Folders',
										frame: true,
										autoScroll: true
										},{
										title: 'Contacts',
										frame: true,
										border:false,
										autoScroll: true,
										items: new Ext.tree.TreePanel({
													useArrows:true,
													autoScroll:true,
													animate:true,
													enableDD:true,
													containerScroll: true,
													rootVisible: true,
													frame: true,
													triggerAction: 'all'
													/*,
													root: new Ext.tree.AsyncTreeNode({
																	 expanded:false
																	,leaf:false
																	,text:'All Contacts'
																	,id:'first'
																}),
													loader: new Ext.tree.TreeLoader({ dataUrl: 'contacts_company_json.jsp?id=<%=id%>'})
													*/
												})
										},{
										html: '<p>Demo Text</p>',
										title: 'Settings',
										frame: true,
										autoScroll: true
										}]
								})
					},{
					region: 'west',
					id: 'menuPanel',
					title: '',
					split: true,
					width: 250,
					minSize: 175,
					maxSize: 400,
					collapsible: true,
					margins: '0 0 5 5',
					layout: {
							type: 'accordion',
							animate: true
							},
					items: [{
							title: 'Mail',
							border: false,
							contentEl:'menu',
							iconCls: 'nav'
							}/*,{
							title: 'Admin',
							border: false,
							contentEl:'menu2',
							iconCls: 'nav'
							},{
							title: 'settings',
							border: false,
							contentEl:'settingsTab',
							iconCls: 'nav'
							},{
							title: 'Reports',
							border: false,
							//contentEl:'report_menu',
							iconCls: 'nav',
							layout: 'accordion',
							items: [{
									title: 'Flexi Load ',
									border: false,
									collapseFirst:true,
									contentEl:'flexi_sub_menu',
									},{
									title: '1PINLESS',
									border: false,
									collapseFirst:false,
									contentEl:'1pinless_sub_menu',
									}]	
							}*/]
            		},
            		Ext.create('Ext.tab.Panel', {
						region: 'center',
						id: 'tabs', 
						height:400,
						enableTabScroll : true,
						deferredRender: false,
						border: false,
						margins: '0 0 5 0',
						activeTab: 0,
						//plugins: Ext.create('Ext.ux.TabCloseMenu'),
						//bodyStyle:'padding:5px',
						autoScroll: true,						
						items: [{
							title: 'Welcome ',
							border: false,
							layout:'fit',
							items:[{
									layout:'column',
									items:[{
											columnWidth:.49,
											baseCls:'x-plain',
											bodyStyle:'padding:5px 0 2px 5px',
											items:[{
												frame: true,
												border:true,
												closeAction: 'hide',
												bodyStyle:'padding:5px',
												html:'<div id="welcome_message">'+tempText+'</div>'
												},{
												border:false,
												bodyStyle:'padding:2px',
												},{
												frame: true,
												border:true,
												closeAction: 'hide',
												bodyStyle:'padding:5px',
												html:'<div id="task_board">'+tempText+'</div>'
												}]
										},{
											columnWidth:.49, 
											baseCls:'x-plain',
											bodyStyle:'padding:5px 0 2px 5px',
											items:[{
												//frame: true,
												border:true,
												closeAction: 'hide',
												bodyStyle:'padding:5px',
												autoScroll:true,
												html:'<div id="message_board">'+tempText+'</div>'
												}//,{
												//border:false,
												//bodyStyle:'padding:2px',
												//},{
												//frame: true,
												//border:true,
												//closeAction: 'hide',
												//bodyStyle:'padding:5px',
												//html:'<div>'+tempText+'</div>'
												//}
											]
										}]

									}]							
							}]
            		})
					]
        	});// END OF "Viewport"
		   
 	      viewport.doLayout();
	//====================
	//	Load Inbox
	//====================
		

 	    containerPanel.add(viewport);
		return containerPanel;
    }
	function msg(notificationType,notificationDtl)
	{
		Ext.Msg.alert(notificationType,notificationDtl);
	}
	function updateBoxes()
	{		
		var tempText = '<span class="t1"><img src="images/mail/images/loading_00.gif" > Loading .... </span>';
		Ext.fly('munu_1').update(tempText);		
		Ext.Ajax.request({
			//url: 'initial_load.jsp?id=<%=id%>',
			url:'mailinfoAction.csmp?method=sessionCheck',
			success: function(r) {
				if(r.responseText =='timeOut'){
					msg('Sorry!', 'Session Time out.');
					window.location = 'index.jsp';
					}
				else	
				{
					//alert('r.responseText:::'+r.responseText);
					var res = Ext.JSON.decode(r.responseText);//JSON.parse(r.responseText);
					var response = Ext.JSON.decode(r.responseText);
					//alert('userid::'+r.responseText.userInfo.userId);
					var json_data = eval(res);
					//alert('res::'+res);
					//alert('json_data::'+json_data.userInfo);
					var company_all= json_data.company_all;
					var userInfo = json_data.userInfo;
					id = userInfo.userId;
					user = userInfo.userName;
					
					//alert('id::'+id+' user::'+user);
				}
			},
			failure: function(){ 
					msg('Sorry!', 'Request Could not be performed.');
					Ext.getBody().unmask();
					Ext.fly('munu_1').doLayout();
					Ext.fly('message_board').doLayout();
					Ext.fly('welcome_message').doLayout();
					}	
			});
		Ext.Ajax.request({
				//url: 'initial_load.jsp?id=<%=id%>',
				url:'mailinfoAction.csmp?method=initialLoad',
				success: function(r) {
					if(r.responseText =='timeOut'){
						msg('Sorry!', 'Session Time out.');
						window.location = 'index.jsp';
						}
					else
					{
						var res = Ext.JSON.decode(r.responseText);//JSON.parse(r.responseText);
						var json_data = eval(res);
						var company_all= json_data.company_all;
						var company_unread= json_data.company_unread;
						var personal_mail= json_data.personal_mail;	
						var share_mail= json_data.share_mail;
						var messages= json_data.messages;
						//alert('personal_mail::'+personal_mail.length);
						//INBOX(GENERAL)
						var tbl = '<table width="100%">';
						var all_td='';
						for(var i=0; i<personal_mail.length; i++){
							var p_sub_id = personal_mail[i].sub_id;
							var p_display_name = personal_mail[i].display_name;
							var p_email = personal_mail[i].email;
							var p_email = personal_mail[i].email;
							var p_total = personal_mail[i].total;
							
							tbl += '<tr><td id="nav_menu2" class="" ';
							tbl += ' onClick="" >';
							tbl += '<a href="javascript:stop()" onClick=" show_inbox(\''+p_sub_id+'\',\''+p_display_name+'\',\'\',\'\',\''+p_email+'\',\'mailInbox\'); return anchorPreventDefault(event);" title="email">'+
							'<img class="items-icon" src="./images/mail/images/plusbottom.gif"/>'+p_display_name+'('+p_total+')</a>';
							tbl += '</td></tr>';
							
							var td ='';
							td +='</td></tr><tr><td class="t1_1" align="right">'+p_display_name+'&nbsp;: </td><td class="t1_1">';
							td +='<a href="javascript:stop()" onClick="show_inbox(\''+p_sub_id+'\',\''+p_display_name+'\',\'\',\'\',\''+p_email+'\',\'mailInbox\');';
							td += 'return anchorPreventDefault(event);">'+p_total+'</a></td></tr>';
							all_td = all_td + td;
							}
						tbl += '</table>';
						//alert('tbl::'+tbl);
						Ext.fly('munu_1').update(tbl);	
						
						// SHARED MAILS
						tbl ='';
						tbl +='<table width="100%" border="0" cellspacing="3" cellpadding="3">';
						tbl +='<tr><td align="left" colspan="2" class="t1_1">';
						tbl +='<b>Hello, '+user+',You Have New Messages</b>';
						tbl +='</td></tr><tr><td class="t1_1" align="right" width="40%" >Refered By Othes &nbsp;: </td><td  class="t1_1">';
						tbl +='<a href="javascript:stop()" onClick="show_inbox(\'\',\'Refered\',\'\',\'\',\'\',\'share\');return anchorPreventDefault(event);">'+share_mail.total+'</a>';
						tbl +=all_td;
						tbl +='</table>';
						
						//Ext.fly('welcome_message').update(tbl);
						
						//MESSAGES
						var msg_all = '';
						for(var j=0; j<messages.length; j++){
							var msg_tbl = '';
							var mess_id = messages[j].mess_id;
							var mess_comment = messages[j].mess_comment;
							var mess_msgId = messages[j].mess_msgId;
							var mess_ownerId = messages[j].mess_ownerId;
							var mess_creat_time = messages[j].mess_creat_time;
							var mess_refId = messages[j].mess_refId;
							var mess_type = messages[j].mess_type;
							var mess_name = messages[j].mess_name;
							var children = messages[j].children;
							msg_tbl = '<span class="mainConName">'+mess_name+':</span>';
							msg_tbl += '<span class="mainCommentBox">'+mess_comment+'</span>';
							msg_tbl += '<span class="mainConTime">'+mess_creat_time+'</span>';
							for(var k=0; k<children.length; k++){
								var mess_id = children[k].mess_id;
								var ch_mess_comment = children[k].mess_comment;
								var ch_mess_msgId = children[k].mess_msgId;
								var ch_mess_ownerId = children[k].mess_ownerId;
								var ch_mess_creat_time = children[k].mess_creat_time;
								var ch_mess_refId = children[k].mess_refId;
								var ch_ch_mess_type = children[k].mess_type;
								var ch_mess_name = children[k].mess_name;
								msg_tbl += '<div class="childCommentBox">'+ch_mess_name+':'+ch_mess_comment+'</div>';								
							}
						
						//onblur="hideComment(\''+mess_id+'\'	
						var comment_box = '<div class="childCommentBox"><table width="100%" border="0" cellpadding="3" cellspacing="2"><tr><td>';
						comment_box += '<input class="comm_in" onclick="activeComment(\''+mess_id+'\');" onblur="hideComment(\''+mess_id+'\');" ';
						comment_box += ' id="comm_'+mess_id+'" value="Write a comment ..." type="text" /></td></tr><tr><td align="right">';
						comment_box += '<div class="hidden" id="td_'+mess_id+'">';
						comment_box += '<input class="comm_sub" name="" value="Comment" onclick="insertComment(\''+mess_id+'\');" type="button" /></div>';
						comment_box += '</td></tr></table></div>';
				
						msg_all = msg_all+msg_tbl+comment_box+'<div class="comm_hr"></div><br>';	
						//mess_id,mess_comment,mess_msgId,mess_ownerId,mess_creat_time,mess_refId,mess_type,mess_name,children								
						}
						/*Ext.fly('message_board').update(msg_all);
						var unreadTreeStore = Ext.create('Ext.data.TreeStore', {
							root: {
					            text: 'All Mails',
					            id: '-1',
					            expanded: true
					        },
						    proxy:{
						    	type:'ajax',
						    	url:'accountAction.csmp?method=promptExtAccountTreeSearchSystemLevel'
						    }
						});							
						//COMPANY NEW MAILS
						var unread_tree = new Ext.tree.TreePanel({
							useArrows:true,
							autoScroll:true,
							animate:true,
							containerScroll: true,
							rootVisible: true,
							//loader: new Ext.tree.TreeLoader({ dataUrl: 'com_gr_mail_json.jsp?id=<%=id%>'}),
							root:new Ext.tree.AsyncTreeNode({
											 expanded:true
											,leaf:false
											,text:'All Mails'
											,comId:'comId'
											,comName:'comName'
											,group_name:'group_name'
											,group_id:'group_id'
											,type:'type'
											,children:company_unread
										}),
							listeners: {
								click: function(node,event) {
											var comName = node.attributes.comName;
											var comId = node.attributes.comId;
											var group_name = node.attributes.group_name;
											var group_id = node.attributes.group_id;
											var type = node.attributes.type;
											var text = node.attributes.text;
											if(type == 'company')
												show_inbox('',comName,comId,'','',type);
											if(type == 'group')	
												show_inbox('',comName+'('+group_name+')',comId,group_id,'',type);
											if(type == 'company_person')
												show_inbox('',text,comId,'',text,type);
											}
								}			
							});	
						var new_mail =	new Ext.Panel({
								renderTo: 'panel-basic',
								tag:'div',
								cls:'ref',
								renderTo:'company_inbox',
								items:[{
									html:'<div class="th1">Unread Mails</div>'
									},unread_tree]
							});
							
						// ALL MAILS
						var accountTreeStore = Ext.create('Ext.data.TreeStore', {
							root: {
					            text: 'All Mails',
					            id: '-1',
					            expanded: true
					        },
						    proxy:{
						    	type:'ajax',
						    	url:'accountAction.csmp?method=promptExtAccountTreeSearchSystemLevel'
						    }
						});
						var all_mail_tree = new Ext.tree.TreePanel({
							useArrows:true,
							autoScroll:true,
							animate:true,
							id:'all_mail_tree',
							containerScroll: true,
							rootVisible: true,
							loader: new Ext.tree.TreeLoader({ dataUrl: 'com_gr_mail_json.jsp?id=<%=id%>'}),
							root:new Ext.tree.AsyncTreeNode({
											 expanded:true
											,leaf:false
											,text:'All Mails'
											,comId:'comId'
											,comName:'comName'
											,group_name:'group_name'
											,group_id:'group_id'
											,type:'type'
											,children:company_all
										}),
							listeners: {
								click: function(node,event) {
											var comName = node.attributes.comName;
											var comId = node.attributes.comId;
											var group_name = node.attributes.group_name;
											var group_id = node.attributes.group_id;
											var type = node.attributes.type;
											var text = node.attributes.text;
											if(type == 'company')
												show_inbox('',comName,comId,'','',type);
											if(type == 'group')	
												show_inbox('',comName+'('+group_name+')',comId,group_id,'',type)
											if(type == 'company_person');
												show_inbox('',text,comId,'',text,type)		
											}	
								}
							});	
						var all_mail =	new Ext.Panel({
								title: 'All Mails',
    							collapsible:true,
								frame: true,
								renderTo:'company_all',
								items:all_mail_tree
							});*/
					}
				},
				failure: function(){ 
						msg('Sorry!', 'Request Could not be performed.');
						Ext.getBody().unmask();
						Ext.fly('munu_1').doLayout();
						Ext.fly('message_board').doLayout();
						Ext.fly('welcome_message').doLayout();
						}	
				});
		//Ext.getCmp('docs-mailboxPanel').doLayout();
	}
    //}); // END OF "onReady"
		
	
	//============
	//COMPOSE MAIL
	//============
	
	function compose(){
		//alert('compose');
		// COMPOSE MAIL FORM
		var myMask = new Ext.LoadMask(Ext.getCmp('doc-body'), {msg:"Please wait! Loading compose panel..."});
    	myMask.show();
		var attachment = ''; var flag = 0; var pre_val=''; var pre_val_cc = ''; var is_first = 0; var is_first_cc = 0;
		var url = 'userAction.csmp?method=promptExtMailUserSearchSystemLevel';
		Ext.define('ContactModel', {
		    extend: 'Ext.data.Model',
		    fields: ['componentId', 'username', 'email']
		});
		var str_contacts = Ext.create('Ext.data.Store', {
		    model: 'ContactModel',
		    pageSize: 20,
		    proxy: {
		        type: 'ajax',
		        url: url,
		        reader: {
		            type: 'xml',
		            record: 'Item',
		            root: 'Items'
		        }
		    }
		});
		/*accountstore.load();
		var str_contacts = new Ext.data.Store({
			autoDestroy: true,
			url: 'contacts.xml',
			reader: new Ext.data.XmlReader({
				record: 'contact',
				fields: [
					{name: 'name', type: 'string'},
					{name: 'email', type: 'email'}
				]
			}),
			sortInfo: {field:'contact', direction:'ASC'}
		});*/
	
		var form = Ext.create('Ext.form.Panel',{
					labelWidth: 80,
					frame:true,
					autoScroll:true,
					bodyStyle:'padding:5px 5px 0;',
					url:'mailinfoAction.csmp?method=sendMail&id='+id,
					items: [{
							fieldLabel: 'From',
							name: 'from',
							id:'from',
							xtype: 'combo',
							anchor:'100%',
							autoSelect: true, 
							editable : false,
							triggerAction: 'all',
							forceSelection : true,
							emptyText:'From',
							hideTrigger : false,
							submitValue : true,
							loadingText :'Loading...',
							lazyRender:true,
							mode: 'local',
							store: new Ext.data.ArrayStore({
										id: 0,
										fields: [
											'myId',
											'displayText'
										],
										//data: ['<%=display_from_contacts%>']
										data: [['wahid@swift-corp.com', 'wahid@swift-corp.com'], [2, 'item2']]
										}),
							valueField: 'myId',
							displayField: 'displayText',
							listeners:{
								'select': function(){	
									var sel =  Ext.getCmp('from').getRawValue();
									sel =  sel.replace(/&lt;/gi, '<'); 
									sel =  sel.replace(/&gt;/gi, '>'); 									
									Ext.getCmp('from').setRawValue(sel);
									}
								}
							},{
							fieldLabel: 'To',
							name: 'to',
							id:'to',
							anchor:'100%',
							xtype: 'combo',
							store: str_contacts,
						    mode: 'local',
						    displayField: 'email',
							valueField:'name',
							editable: true,
							typeAhead: true,
							autoSelect: true,
							hideTrigger : true,
							loadingText :'Loading...',
							triggerAction: 'all',
							emptyText:'To',
							selectOnFocus:true,
							triggerAction: 'all',
							listeners:{
								'select': function(){	
									//alert("Hello......."+pre_val+"<<<");
									/*if(is_first == 0){
										pre_val = pre_val + Ext.getCmp('to').getRawValue()+',';
										is_first += 1;
										}
									else if(is_first == 1)
									{
										pre_val = pre_val +','+ Ext.getCmp('to').getRawValue();
										is_first += 1;
									}	
									else
									{
										pre_val = pre_val +','+ Ext.getCmp('to').getRawValue();
									}*/
									pre_val =  Ext.getCmp('to').getRawValue()+',';
									
									pre_val =  pre_val.replace(/&lt;/gi, '<'); 
									pre_val =  pre_val.replace(/&gt;/gi, '>'); 									
									//alert("look@me:"+pre_val);
									//Ext.getCmp('to').value = '';  	setRawValue
									//Ext.getCmp('to').value = pre_val;
									Ext.getCmp('to').setRawValue(pre_val);
									//alert('to::'+Ext.getCmp('to').getRawValue());
									}
								}
							},{
							fieldLabel: 'cc',
							name: 'cc',
							id:'cc',
							anchor:'100%',
							xtype: 'combo',
							store: str_contacts,
						    mode: 'local',
						    displayField: 'email',
							valueField:'name',
							editable: true,
							typeAhead: true,
							autoSelect: true,
							hideTrigger : true,
							loadingText :'Loading...',
							triggerAction: 'all',
							emptyText:'CC',
							selectOnFocus:true,
							triggerAction: 'all',
							listeners:{
								'select': function(){	
									//alert("Hello......."+pre_val_cc+"<<<");
									if(is_first_cc == 0){
										pre_val_cc = pre_val_cc + Ext.getCmp('cc').getRawValue()+',';
										is_first_cc += 1;
										}
									else if(is_first_cc == 1)
									{
										pre_val_cc = pre_val_cc + Ext.getCmp('cc').getRawValue()+',';
										is_first_cc += 1;
									}	
									else
									{
										pre_val_cc = pre_val_cc + Ext.getCmp('cc').getRawValue()+',';
										is_first_cc += 1;
									}
										
									pre_val_cc =  pre_val_cc.replace(/&lt;/gi, '<'); 
									pre_val_cc =  pre_val_cc.replace(/&gt;/gi, '>'); 									
									//alert("look@me:"+pre_val_cc);
									//Ext.getCmp('to').value = '';  	setRawValue
									//Ext.getCmp('to').value = pre_val;
									var btn = '<div class="dialog">'+pre_val_cc+' <a href="#" class="close-thik"></a></div>';
									Ext.getCmp('cc').setRawValue(pre_val_cc);
									//alert('to::'+Ext.getCmp('cc').getRawValue());
									}
								}							
							},{
							fieldLabel: 'Subject',
							name: 'subject',
							xtype: 'textfield',
							anchor: '100%'
							},{
								height:75,
								containerScroll:true,								
								autoScroll: true,
								forceFit : true,
								tag: 'div',
								id:'newmail-msgDiv',	
								bodyStyle:'display:none', 
								html: ''
							},{
							xtype: 'htmleditor',
							hideLabel: true,
							height:350,
							name: 'body',
							anchor: '100%'
							},{ 
							xtype: 'hidden', 
							id: 'newmail-attachments',
							name:'attachments',
							value: attachment,
							inputType: 'hidden'						
							}]
					});
		
				// FOR ATTACHMENT				
				var fp = Ext.create('Ext.form.Panel', {
						fileUpload: true,
						border:false,
						labelWidth: 1,
						layout:'fit',
						bodyStyle:'background-color:#cddcee',
						margins: '0 0 0 0',
						width: 310,
						height:60,
						items: [{
							xtype: 'filefield',			
							id: 'newMail-form-file',
							name: 'newMail-form-file',
							margins: '0 0 0 0',
							//anchor: '100%',
							bodyStyle:'background-color:#cddcee',
							buttonText: '',
					        buttonConfig: {
					            iconCls: 'upload-icon'
					        }
						}],
            			buttons: [{
            				text: 'Attach File',
            				handler: function(){
            					//alert('in handler');
									if(fp.getForm().isValid()){
											fp.getForm().submit({
												url: 'FileUploadServlet.csmp',
												waitMsg: 'Uploading ',
												success: function(fp, o){
													var filename = o.result.file;
													var fileId = o.result.fileSize;

													var att_div = '<div id="'+fileId+'">'+																
																	'<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>'+
																		'<td id="" width="25"><img src="images/mail/images/icon-complete.gif"/></td>'+
																		'<td id="" class="t1">'+filename+' &nbsp;'+
																		' &nbsp;Cancel</td>'+
																	'</tr></table>'+
																	'</div>';
													
													var el = Ext.fly('newmail-msgDiv');
													var att_text = document.getElementById('newmail-msgDiv').innerHTML;
													var newAttText = att_text+att_div;
													el.update(newAttText);
													
													if(flag == 1){
														var pevVal = document.getElementById('newmail-attachments').value;
														attachment = pevVal+','+filename;
														document.getElementById('newmail-attachments').value = attachment;
													}else {	
														 attachment = filename;
														 document.getElementById('newmail-attachments').value = attachment;
														 flag = 1;
														 }

													if(!el.isVisible()){
														el.slideIn('t', {
															duration: .2,
															easing: 'easeIn',
															callback: function(){
																el.highlight();
																}
															});
														}else{
															el.highlight();
															}
													},
												failure: function(fp, o){
													var filename = o.result.file;
													msg('Sorry!', 'Filed to upload.');
													}
											});
										}
								}
							//}
						}]
					});
		
			//Ext.getCmp('newmail').add(fp);		

		
		//COMPOSE MAIN COMPONENT
		var x = Ext.getCmp('doc-body');
		//Ext.getCmp('doc-body')
		var newMailTab = new Ext.Panel({
			title: 'New Mail',
			id:'newmail',
            closable: true,
            autoScroll: true,
            containerScroll:true,
			//border: true,
			layout:'fit',
			forceFit:true,
			items:form,
			plain:true,
       	 	bodyStyle:'background-color:#cddcee',
        	buttonAlign:'center',
			tbar:[{
				text:'Send',
				tooltip:'Send',
				iconCls:'Send',
				handler: function(){
					if(form.getForm().isValid({})){
						form.getForm().submit({
						url: 'mailinfoAction.csmp?method=sendMail&id='+id,
						waitMsg: 'Please,wait.. mail Sending',
						success: function(form, o){ 
									var q = Ext.getCmp('doc-body');
									Ext.getCmp('doc-body').remove('newmail');
									q.doLayout();
									Ext.getCmp('doc-body').add({
											title: 'Mail Sent',					
											closable: true,
											autoScroll: true,
											autoHeight: true,
											border: false,
											html:'Mail sent '
											}).show();
								},
						failure: function(){ 
							msg('Sorry!', 'Request Could not be performed.Please check the mail Settings');
							}		
							});
						}
					}
				},'-',{
				text:'Save as Draft',
				tooltip:'Save As Draft',
				handler: function(){
					if(form.getForm().isValid({})){
						form.getForm().submit({
						url: 'mailinfoAction.csmp?method=saveAsDraft&id='+id,
						waitMsg: 'Please,wait.. mail Saving',
						success: function(form, o){ 
									var q = Ext.getCmp('doc-body');
									Ext.getCmp('doc-body').remove('newmail');
									q.doLayout();
									Ext.getCmp('doc-body').add({
											title: 'Mail Saved',					
											closable: true,
											autoScroll: true,
											autoHeight: true,
											border: false,
											html:'Mail Saved '
											}).show();
								},
						failure: function(){ 
							msg('Sorry!', 'Request Could not be performed.Please check the mail Settings');
							}		
							});
						}
					}

				},'-',fp
				],
				listeners: {
				    close: function (c) {          
				        c.getEl().on(
				            'click',
				            function() {
				                this.el.child('a', true).href = 'www.google.com/#q=' + some_dynamic_value;
				            },
				            c,
				            { stopEvent: false }
				        );
				    }
				}
        });
		var tab = x.add(newMailTab).show();
		tab.doLayout();
		str_contacts.load();
		myMask.hide();
		//Ext.get('to').on('select', function(){alert("Hello...");});
		
	} // MAIL COMPOSE END
		
	//==========
	//SHOW INBOX
	//==========			
	function show_inbox(recv_id,tab_title,com_id,email_group_id,recv_email_address,type){

		var total_id = [];	
		var	total_sm = [];
		var	total_rowindex = [];
						
		if(tab_title==null)
			tab_title = 'Inbox';
		if(com_id==null)
			com_id = '';
		if(email_group_id==null)
			email_group_id = '';
		if(recv_email_address==null)
			recv_email_address = '';	
			
			
	//alert('in inbox');
	var gridPrev = Ext.get('inbox_g'+type+recv_email_address);
	function render_date(val) {
	    val = Ext.util.Format.date(val, 'Y-m-d h:i:s');
	    //alert('date::'+val);
	    return val;
	}
	//alert('ddd:::'+'inbox_g'+type+recv_email_address);
	//alert('gridPrev::'+gridPrev);
	if(gridPrev!=null)
	{
		//Ext.get('panel_inbox_g'+type+recv_email_address).show();
		return false;
	}
	else
	{		
		Ext.define(type+recv_email_address+'MailModel', {
		    extend: 'Ext.data.Model',
		    fields: [{name: 'email_id', type: 'string'},
						{name: 'senderName', type: 'string'},
						{name: 'senderEmail', type: 'string'},
						{name: 'recipients', type: 'string'},
						{name: 'cc', type: 'string'},
						{name: 'subject', type: 'string'},
		           		{name: 'send_date',dataIndex:'send_date', type: 'date'/*, renderer: render_date*/},//dateFormat: 'Y-m-d hh:mi:ss'},
						{name: 'status', type: 'string'}
		        	]
		});
	
		var store = Ext.create('Ext.data.Store', {
		    model: type+recv_email_address+'MailModel',

		    pageSize: 20,
		    proxy: {
		        type: 'ajax',
		        //url: 'get-images.php',
		        url: 'mailinfoAction.csmp?method=inbox&id='+id+'&recv_id='+recv_id+'&com_id='+com_id+'&email_group_id='+email_group_id+'&email_address='+recv_email_address+'&email_group='+email_group_id+'&type='+type,
		        reader: {
		            type: 'json',
		            root: 'topics',
		            idProperty: 'email_id',
		            totalProperty: 'totalCount'
		        }
		    }/*,

		    //alternatively, a Ext.data.Model name can be given (see Ext.data.Store for an example)
		    fields: [
		           		{name: 'email_id', type: 'string'},
						{name: 'senderName', type: 'string'},
						{name: 'senderEmail', type: 'string'},
						{name: 'recipients', type: 'string'},
						{name: 'cc', type: 'string'},
						{name: 'subject', type: 'string'},
		           		{name: 'send_date', type: 'date', dateFormat: 'm/d/Y'},
						{name: 'status', type: 'string'}
		        	]*/
		});
		store.load();
		
		/*
			new Ext.data.JsonStore({
			root: 'topics',
			totalProperty: 'totalCount',
			remoteSort: true,
			fields: [
           		{name: 'email_id', type: 'string'},
				{name: 'senderName', type: 'string'},
				{name: 'senderEmail', type: 'string'},
				{name: 'recipients', type: 'string'},
				{name: 'cc', type: 'string'},
				{name: 'subject', type: 'string'},
           		{name: 'send_date', type: 'date', dateFormat: 'm/d/Y'},
				{name: 'status', type: 'string'}
        	],			
			proxy: new Ext.data.HttpProxy({
            url: 'inbox.jsp?id=<%=id%>&recv_id='+recv_id+'&com_id='+com_id+'&email_group_id='+email_group_id+'&email_address='+recv_email_address+'&email_group='+email_group_id+'&type='+type
        			})
			});
			*/
			//store.setDefaultSort('send_date', 'desc');
		
		var xg = Ext.grid;
		//var sm2 = new xg.CheckboxSelectionModel();
		//var sm2 = Ext.create('Ext.selection.CheckboxModel');
		var sm = Ext.create('Ext.selection.CheckboxModel',{
						singleSelect: false,
						id:'sm'+type+recv_email_address,
						sortable: false,
						checkOnly: true,
						listeners: {
							deselect: function(sm, r, rowIndex) {
					            id = r.data.email_id;
					            var selectedRow = new Array();
					            
					            for(var i=0;i<total_rowindex.length;i++)
					            {
					            	if(rowIndex==total_rowindex[i])
					            	{
					            		
					            	}
					            	else
					            	{
					            		selectedRow.push(total_rowindex[i]);
					            	}
					            }
					            total_rowindex = selectedRow;
					            //alert(id);
					        },
					        select: function(sm, r, rowIndex) {
					            id = r.data.email_id;
					            total_id.push(r.data.email_id);
								total_sm.push(sm);
								total_rowindex.push(rowIndex);
					            //alert(id);
					        }/*,
							rowselect : function( sm, rowIndex, r ){
								alert('selecting');
									total_id.push(r.data.email_id);
									total_sm.push(sm);
									total_rowindex.push(rowIndex);
								}*/
						}
					});
		function renderTopic(value,p,record,index){			
			if(record.data.status == 'U')
				return String.format('<div class="t1"><a href="#" onClick="showmail(\''+recv_email_address+'\',\''+index+'\',\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\',\'{7}\'); return anchorPreventDefault(event);"><b>{0}</b></a></div>',value,record.data.email_id,record.data.senderName,record.data.senderEmail,record.data.recipients,record.data.cc,record.data.subject,record.data.send_date);
			else
				return String.format('<div class="t1"><a href="#" onClick="showmail(\''+recv_email_address+'\',\''+index+'\',\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\',\'{7}\'); return anchorPreventDefault(event);">{0}</a></div>',value,record.data.email_id,record.data.senderName,record.data.senderEmail,record.data.recipients,record.data.cc,record.data.subject,record.data.send_date);			
			}
		//showmail(email_id,senderName,senderEmail,recipient,subject,send_date)	
		//alert('before grid 1');
		/*var bbar = new Ext.PagingToolbar({
		xtype: 'pagingtoolbar',
			pageSize: 23,
			id:'paging'+type+recv_email_address,
			store: store,
			displayInfo: true,
			displayMsg: 'Displaying topics {0} - {1} of {2}',
			emptyMsg: "No Mail"
		});*/
		//alert('before grid 2');
		var mailGridcolumns = [];
		//alert('mailGridcolumns::'+mailGridcolumns)
		mailGridcolumns = [
						//sm,
						{
						//id:type+recv_email_address+'senderName',
						header: 'Sender', 
						width: 250, 
						sortable: true, 
						dataIndex: 'senderName'
						},{
						//id:type+recv_email_address+'subject',
						header: 'Subject', 
						width: 400, 
						sortable: true, 
						dataIndex: 'subject',
						renderer: renderTopic
						},{
						//id:type+recv_email_address+'send_date',
						header: 'Date', 
						width: 200, 
						//renderer: Ext.util.Format.dateRenderer('D m/d/Y'),
						renderer: Ext.util.Format.dateRenderer('D Y-m-d h:i:s'),
						sortable: true, 
						dataIndex: 'send_date'
						}];
		//alert('before grid 3');
		var tbar = [{
			text:'Delete',
			tooltip:'Delete',
			defaultType: 'button',
			iconCls:'option',
			handler: function(){
				msg('Delete', 'Mail deleted.');
				}
			}, '-', {
			text:'Reply',
			tooltip:'Reply',
			defaultType: 'button',
			iconCls:'option',
			handler: function(){
				msg('Reply', 'Mail Reply.');
				}
			},'-',{
			text:'Forward',
			tooltip:'Forward',
			iconCls:'remove',
			handler: function(){
				msg('Forward', 'Mail Forward.');
				}
			},'-',{
				text:'&nbsp; &nbsp;Action&nbsp;&nbsp;',
				xtype: 'splitbutton',
				menu: [{
						text: 'Delete',
						handler: function(){
							//alert("TOTAL:"+total_id);
							}
						},'-',{
						text: 'Mark as Read',
						handler: function(){
									var grid = Ext.getCmp('inbox_g'+type+recv_email_address);
									var i;
									for(i=0; i < total_rowindex.length; i++ ) {
										//alert(total_rowindex[i]);
										grid.view.addReadRowClass(total_rowindex[i], 'readMail');
										 var record = store.getAt(total_rowindex[i]);
									        record.set("status", "I");
										//Ext.fly(grid.getView().getRow(total_rowindex[i])).addClass('readMail');												
										}
									var selectedRows = grid.view.selModel.selected;
									for(var j=0;j<selectedRows.items.length;j++)
									{
										//selectedRows.items[j].addClass('readMail');
										//grid.view.addRowClass(0, 'readMail');
									}
									Ext.Ajax.request({
										url: 'mailinfoAction.csmp?method=markAsRead&id='+id+'&total_id='+total_id,
										success: function(r) {
												if(r.responseText=='timeOut')
													window.location = 'logout.jsp';
											},
										failure: function(){ 
													msg('Sorry!', 'Mails could not be marked as read.');
													}						
										});
									grid.getSelectionModel().clearSelections();	
									total_id = [];	
									total_sm = [];
									total_rowindex = [];	
									
								}
						},'-',{
						text: 'Mark as Unread',
						handler: function(){
									var grid = Ext.getCmp('inbox_g'+type+recv_email_address);
									var i;
									for(i=0; i < total_rowindex.length; i++ ) {
										//alert(total_rowindex[i]);
										grid.view.addUnReadRowClass(total_rowindex[i], 'unReadMail');
										var record = store.getAt(total_rowindex[i]);
									    record.set("status", "U");
										//Ext.fly(grid.getView().getRow(total_rowindex[i])).addClass('readMail');												
									}
									/*var i;
									for(i=0; i < total_rowindex.length; i++ ) {
										//Ext.fly(grid.getView().getRow(total_rowindex[i])).removeClass('readMail');
										//Ext.fly(grid.getView().getRow(total_rowindex[i])).addClass('unReadMail');												
										}*/
									Ext.Ajax.request({
										url: 'mailinfoAction.csmp?method=markAsUnRead&id='+id+'&total_id='+total_id,
										success: function(r) {
												if(r.responseText=='timeOut')
													window.location = 'logout.jsp';
											},
										failure: function(){ 
													msg('Sorry!', 'Mails could not be marked as read.');
													}						
										});
									grid.getSelectionModel().clearSelections();	
									total_id = [];	
									total_sm = [];
									total_rowindex = [];
									
									}
						},'-',{
						text: 'Span',
						handler: function(){msg('Sorry!','This Button is Inactive, We are working on it.');}
						}]
				},'-',{
					text:'Close',
					tooltip:'close',
					defaultType: 'button',
					iconCls:'option',
					handler: function(){
						//alert('ddd::'+'panel_inbox_g'+type+recv_email_address);
						var replace = new Array();
						var ch = Ext.getCmp('doc-body').items.items;
						for(var i=0;i<ch.length;i++)
						{							
							
							if(ch[i].id == 'panel_inbox_g'+type+recv_email_address)
							{
								//alert('removing::'+ch[i].hide());
								Ext.getCmp('doc-body').remove('panel_inbox_g'+type+recv_email_address);
								break;
							}
						}
						//alert('ch::'+ch.length);
						//Ext.getCmp('panel_inbox_g'+type+recv_email_address).destroy();
						Ext.getCmp('doc-body').doLayout();
						//Ext.get('panel_inbox_g'+type+recv_email_address).destroy();
						}
					}];
		
		//alert('before grid 4');
		var grid = Ext.create('Ext.grid.Panel',{
			store: store,
			layout: 'fit',
			id:'inbox_g'+type+recv_email_address,
			//loadMask: true,
			//trackMouseOver:true,
			//forceLayout : true,
			frame : true,
			//stateful: true,
        	//disableSelection:false,
			//autoExpandColumn: 'subject',
			//checkOnly : true,
			//unstyled:true,
			selModel: sm,
        	columnLines: true,
			//cm: new Ext.grid.ColumnModel({
			columns: mailGridcolumns,
					//}),
			 viewConfig: {
						forceFit: true,
						addUnReadRowClass : function(rowId, cls) {							
							var grid = Ext.getCmp('inbox_g'+type+recv_email_address);
							var tr = grid.getView().getNode(rowId);
							//alert(tr.getAttribute('class'));
							var trclass = tr.getAttribute('class')
							trclass = trclass.replace(' unReadMail','');
							trclass = trclass + ' '+'readMail';
							//alert('tr::'+Ext.get(tr));
							Ext.get(tr).removeCls('readMail');
							Ext.get(tr).addCls('unReadMail');
							//alert('tr new class::'+tr.getAttribute('class')+' --- '+trclass);
							grid.doLayout();
							//tr.removeClass('unReadMail');
							//tr.addClass('readMail');
							grid.view.refresh();
							//store.load();
							/*el = Ext.get(tr).select('td');
							el.removeCls('red-back yellow-back');
							if(rec.data.data == 'data2')
							{
							el.addCls('red-back');
							}*/


							//Read more: http://jaspreetchahal.org/extjs-4-changing-grid-row-background-color/#ixzz2KPa7Hzjl
								
						    /*var row = Ext.getCmp('inbox_g'+type+recv_email_address).view.getRow(rowId);
						    if (row) {
						        this.fly(row).addClass(cls);
						    }*/
						},
						addReadRowClass : function(rowId, cls) {							
							var grid = Ext.getCmp('inbox_g'+type+recv_email_address);
							var tr = grid.getView().getNode(rowId);
							//alert(tr.getAttribute('class'));
							var trclass = tr.getAttribute('class')
							trclass = trclass.replace(' unReadMail','');
							trclass = trclass + ' '+'readMail';
							//alert('tr::'+Ext.get(tr));
							Ext.get(tr).removeCls('unReadMail');
							Ext.get(tr).addCls('readMail');
							//alert('tr new class::'+tr.getAttribute('class')+' --- '+trclass);
							grid.doLayout();
							//tr.removeClass('unReadMail');
							//tr.addClass('readMail');
							grid.view.refresh();
							//store.load();
							/*el = Ext.get(tr).select('td');
							el.removeCls('red-back yellow-back');
							if(rec.data.data == 'data2')
							{
							el.addCls('red-back');
							}*/


							//Read more: http://jaspreetchahal.org/extjs-4-changing-grid-row-background-color/#ixzz2KPa7Hzjl
								
						    /*var row = Ext.getCmp('inbox_g'+type+recv_email_address).view.getRow(rowId);
						    if (row) {
						        this.fly(row).addClass(cls);
						    }*/
						},
						getRowClass: function(record, index) {
						var c = record.get('status');						
								if (c == 'I') {
									return 'readMail';   		//return 'x-grid3-row x-grid3-row-alt';  
								} else if (c == 'U') {
									return 'unReadMail';       // return 'x-grid3-row-table';  
								}
							}
							
						}, //x-grid3-row-table  ||  x-grid3-row x-grid3-row-alt
			tbar:tbar,
			dockedItems: [{
				xtype: 'pagingtoolbar',
				pageSize: 23,
				id:'paging'+type+recv_email_address,
				store: store,
				dock: 'bottom',
				displayInfo: true,
				displayMsg: 'Displaying topics {0} - {1} of {2}',
				emptyMsg: "No Mail" 
			}],
			});
			



		//alert('grid::'+grid);
		// ADD INBOX TAB
		var docbody = Ext.getCmp('doc-body');
		var tab = docbody.add({
			title: tab_title,					
            closable: true,
			id:'panel_inbox_g'+type+recv_email_address,
            autoScroll: true,
			layout: 'fit',
			items:grid,
			plain:true
        	});
		docbody.setActiveTab(tab);

		
//		var main_tab = Ext.getCmp('mainTab');
//		if(!main_tab){
//			//alert("Add");
//			Ext.getCmp('doc-body').add({
//				title: tab_title,					
//				closable: true,
//				id:'mainTab',
//				autoScroll: true,
//				layout: 'fit',
//				items:grid,
//				plain:true
//				}).show();
//			}
//		else
//			{
//				//Ext.getCmp('mainTab').show();
//				//alert("Update");
//				Ext.getCmp('mainTab').update('');
//				Ext.getCmp('mainTab').update({
//					title: tab_title,					
//					closable: true,
//					id:'mainTab',
//					autoScroll: true,
//					layout: 'fit',
//					items:grid,
//					plain:true
//					}).show();
//			}
		return false;
	}
		
		//store.load({params:{start:0, limit:23}});	
		}//END OF SHOW INBOX
		

	//=================
	//SHOW FAILED MAILS
	//==================
	function show_failed(){
		var id = id;
		var uId = '<%=session.getId()%>'; 	
		if(id != uId){
			msg('Sorry!', 'Session Time Out ... Please login again.');
			parent.location.replace('index.jsp');
			}
		
		var s_id = document.getElementById('s_id');
		
		//STORE FAILED MAILS
		var store2 = new Ext.data.JsonStore({
				root: 'topics',
				totalProperty: 'totalCount',
				remoteSort: true,
				loadMask: true,
				fields: [
					{name: 'object_id', type: 'string'},
					{name: 'senderName', type: 'string'},
					{name: 'senderEmail', type: 'string'}, 
					{name: 'recipient', type: 'string'},
					{name: 'schedule_time', type: 'date', dateFormat: 'm/d/Y'},
					{name: 'subject', type: 'string'},
					{name: 'send_status', type: 'string'}
				],
				proxy: new Ext.data.HttpProxy({
							url: 'pendingMails.jsp?id='+id
						})
				});
			store2.setDefaultSort('send_date', 'desc');
			//email_id,senderName,senderEmail,subject,send_date
			function renderFailed(value,p,record){
				return String.format('<a href="" onClick="faildmail(\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\'); return anchorPreventDefault(event);">{0}</a>',
				value,record.data.object_id,record.data.senderName,record.data.senderEmail,record.data.recipient,record.data.subject,record.data.subject,record.data.subject,record.data.schedule_time,record.data.send_status);
				}	
			var xg = Ext.grid;
			//var sm2 = new xg.CheckboxSelectionModel();
			var sm2 = Ext.create('Ext.selection.CheckboxModel');
			var grid2 = new xg.GridPanel({
				store: store2,
				layout: 'fit',
				selModel: sm2,
				//cm: new Ext.grid.ColumnModel({
					columns: [
						//sm2,
						{
							id:'senderName',
							header: 'Sender', 
							width: 250, 
							sortable: true, 
							dataIndex: 'senderName'
							},
						{
							id:'subject',
							header: 'Subject', 
							width: 400, 
							sortable: true, 
							dataIndex: 'subject',
							renderer: renderFailed,
							},
						{
							id:'send_date',
							header: 'Date', 
							width: 100, 
							renderer: Ext.util.Format.dateRenderer('D m/d/Y'), 
							sortable: true, 
							dataIndex: 'schedule_time'
						}
					],					
				//sm: sm2,
				columnLines: true,
				tbar:[{
					text:'Delete',
					tooltip:'Delete',
					defaultType: 'button',
					iconCls:'option',
					handler: function(){
						msg('Delete', 'Mail deleted.');
						}
					}, '-', {
					text:'Reply',
					tooltip:'Reply',
					defaultType: 'button',
					iconCls:'option',
					handler: function(){
						msg('Reply', 'Mail Reply.');
						}
					},'-',{
					text:'Forward',
					tooltip:'Forward',
					iconCls:'remove',
					handler: function(){
						msg('Forward', 'Mail Forward.');
						}
					},'-',{
					text:'Move',
					tooltip:'Move',
					iconCls:'remove',
					handler: function(){
						msg('Move', 'Mail Moved.');
						}
					},'-',{
					text:'Action',
					tooltip:'Action',
					iconCls:'remove',
					handler: function(){
						msg('Action', 'Action ');
						}
					}],
				 dockedItems: [{
					xtype: 'pagingtoolbar',
					dock: 'bottom',
					store: store,
					pageSize: 25,
					store: store2,
					displayInfo: true,
					displayMsg: 'Displaying topics {0} - {1} of {2}',
					emptyMsg: "No topics to display",
				}]
			});
			store2.load({params:{start:0, limit:25}});
			
			// ADD FAILED TAB
			Ext.getCmp('doc-body').add({
				title: 'Failed Mails',					
				closable: true,
				autoScroll: true,
				border: true,
				layout: 'fit',
				items:grid2,
				plain:true,
				}).show();
			return false;
		}// END OF FAILED MAILS

	//==========================
	//SHOW DETAILS OF INBOX MAIL
	//==========================
	//(index,email_id,senderName,senderEmail,recipient,subject,send_date)
	function showmail(recv_email_address,index,email_id,senderName,senderEmail,recipient,cc,subject,send_date){
		//alert('recv_email_address::'+recv_email_address+' index::'+index+' email_id::'+email_id+' senderName::'+senderName+' senderEmail::'+senderEmail);
		//alert('recipient::'+recipient+' cc::'+cc+' subject::'+subject+' send_date::'+send_date);
		//alert('show');
			var counter=1;
			var bodytext ; var reply_body_text; var reply_subj ; var recipients_reply; var cc_reply; 		
			//Ext.fly(Ext.getCmp('inbox_g').getView().getRow(index)).addClass('readMail'); // MARK THE MAIL AS READ
			//var grid = Ext.getCmp('inbox_g').getView();
			var grid = Ext.get('inbox_gmailInbox'+recv_email_address);
			//alert('subject::'+subject);
			/*var seletionModel = grid.getSelectionModel();
			var selectedRecords = selectionModel.getSelection();
			alert(selectedRecords);
			//alert('sender::'+selectedRecords[0].get('sender'));
			selectedRecords[0].addClass('readMail');*/
			var sub_len = subject.length;
			if(sub_len >30){
				var sub_show = subject.substring(0,30);
				sub_show = sub_show + '....';
				}
			else
				var sub_show = subject;	

			//alert('sub_show::'+sub_show);
			var x = Ext.get('doc-body');
			var tab = Ext.getCmp('doc-body').add({
            		title: sub_show,
					id:'mail_'+email_id,
            		autoScroll: true,
					bodyStyle:'padding:10px',
					items:[{
						   	height: 15,
							margins: '0 0 0 0',
							border: false,
						   	html: '<div class="t1" align="right">'+send_date+'</div>'
						   },{
							height: 30,
							margins: '0 5 0 0',
							border: false,
						   	html: '<div class="th1">'+subject+'</div>'   
						   },{
							height: 20,   
							margins: '0 5 0 0',
							border: false,   
						   	html: '<div class="t1" >From: '+senderEmail+'</div>'
						   },{
							margins: '0 5 0 0',
							border: false,   
						   	html: '<div class="t1" id="tbl_recipients'+email_id+'"></div>' 
						   },{
							margins: '0 5 0 0',
							border: false,   
						   	html: '<div class="t1" id="tbl_cc'+email_id+'"></div>'
						   },{
							margins: '5 5 5 5',
							border: false,   
						   	html: '<div class="t1" id="mailAttachment_'+email_id+'"></div>',
							autoScroll: true
						   },{
							margins: '0 5 0 0',
							border: false,   
						   	html: '<div class="ref" id="ref_'+email_id+'" align="right"></div>'
						   },{
							margins: '5 5 5 5',
							border: false,   
						   	html: '<div class="t1" id="mailbody_'+email_id+'"></div>',
							autoScroll: true
						   }],
					closable:true,
					tbar:[{
						text:'Delete',
						tooltip:'Delete',
						defaultType: 'button',
						iconCls:'option',
						handler: function(){
							msg('Delete', 'Mail deleted.');
							}
						}, '-', {
						text:'Reply',
						xtype: 'splitbutton',
						menu: [{
								text: 'Reply',
								handler: function(){
									var bodytext = document.getElementById('mail_body_text_'+email_id).innerHTML;
									var maes_ref_id = document.getElementById('maes_ref_'+email_id).innerHTML;
									var to = document.getElementById('recipients'+email_id).innerHTML;
									var cc = document.getElementById('cc'+email_id).innerHTML;
									bodytext = bodytext + "<br>" + maes_ref_id;
									var p = Ext.getCmp('doc-body');
									Ext.getCmp('doc-body').remove('mail_'+email_id);
									p.doLayout();
									mailReply(recv_email_address,email_id,senderName,senderEmail,reply_subj,reply_body_text,senderEmail);
									}
								},'-',{
								text: 'Reply to all',
								handler: function(){
									var bodytext = document.getElementById('mail_body_text_'+email_id).innerHTML;
									var maes_ref_id = document.getElementById('maes_ref_'+email_id).innerHTML;
									var to = document.getElementById('recipients'+email_id).innerHTML;
									var cc = document.getElementById('cc'+email_id).innerHTML;
									//alert("TO:"+to+" ----  CC:"+cc);
									bodytext = bodytext + "<br>" + maes_ref_id;
									var p = Ext.getCmp('doc-body');
									Ext.getCmp('doc-body').remove('mail_'+email_id);
									p.doLayout();
									mailReply(recv_email_address,email_id,senderName,senderEmail,reply_subj,reply_body_text,recipients_reply,cc_reply);
									}
								}],
						handler: function(){
							var bodytext = document.getElementById('mail_body_text_'+email_id).innerHTML;
							var maes_ref_id = document.getElementById('maes_ref_'+email_id).innerHTML;
							bodytext = bodytext + "<br>" + maes_ref_id;
							var p = Ext.getCmp('doc-body');
							Ext.getCmp('doc-body').remove('mail_'+email_id);
							p.doLayout();
							mailReply(recv_email_address,email_id,senderName,senderEmail,reply_subj,reply_body_text,senderEmail);
							}
						},'-',{
						text:'Forward',
						tooltip:'Forward',
						iconCls:'remove',
						handler: function(){
							msg('Forward', 'Mail Forward.');
							}
						},'-',{
						text:'Move',
						tooltip:'Move',
						iconCls:'remove',
						handler: function(){
							msg('Move', 'Mail Moved.');
			 				}
						},'-',{
						text:'Action',
						tooltip:'Action',
						iconCls:'remove',
						handler: function(){
							msg('Action', 'Action ');
							}
						}]
        			}).show();
			
			
			//var url = 'mailDetails.jsp?id=<%=id%>&mail_id='+email_id;
			var url = 'mailinfoAction.csmp?method=mailDtl&id='+id+'&mail_id='+email_id;
			// Showing Details mail
			var tempText = '<span class="t1"><img src="images/mail/images/loading_00.gif" > Loading .... </span>';
			Ext.fly('mailbody_'+email_id).update(tempText);			
			Ext.Ajax.request({
				url: url,
                 success: function (r){
					if(r.responseText=='timeOut'){
						window.location = 'index.jsp';
						}else {
                    	var str = r.responseText;	
						//var i = Ext.decode(r.responseText).progress;
						//alert("LOOK@ME:"+i);
						var referenceText = str.substring(1,str.indexOf("<@@@###%%%BODY>"));
						var bodyText = str.substring(str.indexOf("<@@@###%%%BODY>")+15,str.indexOf("<@@@###%%%ATTACHMENT>"));
						var attachment = str.substring(str.indexOf("<@@@###%%%ATTACHMENT>")+21,str.indexOf("<@@@###%%%RECIPIENT>"));
						var recipients = str.substring(str.indexOf("<@@@###%%%RECIPIENT>")+20,str.indexOf("<@@@###%%%CC>"));
						var cc = str.substring(str.indexOf("<@@@###%%%CC>")+13,str.indexOf("<@@@###%%%FINISH>"));
						
						var to_tbl = '<table width="100%" border="0" cellpadding="5" cellspacing="5">'+
									'<tr>'+
    								'<td width="5%" class="t1">To: </td>'+
    								'<td><div id="recipients'+email_id+'" class="t1">'+recipients+'</div></td>'+
  									'</tr>'+
									'</table>';
						var cc_tbl = '<table width="100%" border="0" cellpadding="5" cellspacing="5">'+
									'<tr>'+
    								'<td width="5%">cc: </td>'+
    								'<td><div id="cc'+email_id+'" class="t1">'+cc+'</div></td>'+
  									'</tr>'+
									'</table>';
						
						recipients_reply =  recipients.replace(/&lt;/gi, '<'); 
						recipients_reply =  recipients_reply.replace(/&gt;/gi, '>'); 

						cc_reply = cc.replace(/&lt;/gi, '<');
						cc_reply = cc_reply.replace(/&gt;/gi, '>');

						var reply_top_text = '<hr size="1"><span class="t1"><b>From: </b>'+senderEmail+'<br>'+
											 '<b>To: </b>'+recipients_reply+'<br>'+ 
											 '<b>CC: </b>'+cc_reply+'<br>'+   
											 '<b>Sent: </b>'+send_date+'<br>'+
											 '<b>Subject: </b>'+subject+'<br>'+
											 '</span>';
						reply_body_text = reply_top_text + bodyText;
						var only_re_sub = subject.replace(/Re:/gi, '');	
						reply_subj = 'Re:'+only_re_sub;	
																		
						Ext.fly('ref_'+email_id).update(referenceText);
						Ext.fly('mailbody_'+email_id).update(bodyText);
						Ext.fly('mailAttachment_'+email_id).update(attachment);
						Ext.fly('tbl_recipients'+email_id).update(to_tbl);
						//Ext.getCmp('ref_'+email_id).doLayout();
						if(cc.length>1)
							Ext.fly('tbl_cc'+email_id).update(cc_tbl);
						else
							Ext.fly('tbl_cc'+email_id).update('<div id="cc'+email_id+'" class="t1"></div>');
					 }	
					tab.doLayout();
					},
				failure: function(){ 
						msg('Sorry!', 'Filed to load Details mail.');
					}						
				});
			// Showing Ref
//			
//			var ref_url = 'mailRef.jsp?mail_id='+email_id;
//			Ext.Ajax.request({
//				url: ref_url,
//				success: function(r) {	
//					Ext.fly('ref_'+email_id).update(r.responseText);
//					}					
//				});	
//			
			//alert('tab::'+tab);
			
			//Ext.getCmp('mailbody_'+email_id).doLayout();
			//Ext.get('doc-body').doLayout();
			updateBoxes();			
			//grid.getStore().refresh();
			//grid.store.load();
			return false;
		}//END OF INBOX DETAILS
	
	//======================= 
	//DETAILS OF FAILED MAILS 	
	//=======================
	function faildmail(a,b,c,d,e,f){
			var counter=1;
			//alert("Hello Procees Failed Mails");
			var email_id = a;
			var senderName = b;
			var senderEmail = c;
			var recipient = d;
			var subject = e;
			var send_date = f;
			var bodytext ;
			//var url = 'mailDetails.jsp?id=<%=id%>&mail_id='+email_id;
			var url = 'mailinfoAction.csmp?method=mailDtl&id='+id+'&mail_id='+email_id;
			var htmltext = '<div id="mailbox_'+email_id+'"><table width="100%" border="1" cellspacing="5" cellpadding="5">'+
						   '<tr><td colspan=\"2\"><b>'+subject+'</b></td></tr>'+
						   '<tr><td>To</td><td>'+recipient+'</td></tr>'+
						   '<tr><td>From</td><td>'+senderName+'('+senderEmail+')</td></tr>'+
						   '<tr><td colspan="2"><div id="mailbody_'+email_id+'">'+ email_id +
						   '</div></td></tr>'+
						   '</table></div>';
			var x = Ext.get('doc-body');
			Ext.getCmp('doc-body').add({
            		title: subject,
					id:'mail_'+email_id,
					items:[{
						html: htmltext
						}],
					closable:true,
					tbar:[{
						text:'Delete',
						tooltip:'Delete',
						defaultType: 'button',
						iconCls:'option',
						handler: function(){
							msg('Delete', 'Mail deleted.');
							}
						}, '-', {
						text:'Send Mail',
						tooltip:'Send',
						defaultType: 'button',
						iconCls:'option',
						handler: function(){
							mailfailedmails(email_id,senderName,senderEmail,recipient,subject,bodytext);
							}
						}]
        			}).show();
			
			Ext.Ajax.request({
				url: url,
				success: function(r) {
					if(r.responseText=='timeOut'){
						window.location = 'index.jsp';
						}	
					else{
						Ext.fly('mailbody_'+email_id).update(r.responseText);
						bodytext = r.responseText;
						}
					},
				failure: function(){ 
							msg('Sorry!', 'Request Could not be performed.');
							}						
				});
			}// END OF DETAILS DETAILS OF FAILED

	//===================
	//REPLY - INBOX MAILS
	//===================
	var mailReply = function(recv_email_address,email_id,senderName,senderEmail,subject,bodytext,to,cc){
		var attachment = ''; var flag = 0; var pre_val=''; var pre_val_cc = ''; var is_first = 0; var is_first_cc = 0;
		var url = 'userAction.csmp?method=promptExtMailUserSearchSystemLevel';
		Ext.define('ContactModel', {
		    extend: 'Ext.data.Model',
		    fields: ['componentId', 'username', 'email']
		});
		var str_contacts = Ext.create('Ext.data.Store', {
		    model: 'ContactModel',
		    pageSize: 20,
		    proxy: {
		        type: 'ajax',
		        url: url,
		        reader: {
		            type: 'xml',
		            record: 'Item',
		            root: 'Items'
		        }
		    }
		});
					//alert("email_id"+email_id);
					
		// FOR ATTACHMENT				
		var fp = Ext.create('Ext.form.Panel', {
				fileUpload: true,
				border:false,
				labelWidth: 1,
				layout:'fit',
				bodyStyle:'background-color:#cddcee',
				margins: '0 0 0 0',
				width: 310,
				height:60,
				items: [{
					xtype: 'filefield',			
					id: 'reply-form-file',
					name: 'reply-form-file',
					margins: '0 0 0 0',
					//anchor: '100%',
					bodyStyle:'background-color:#cddcee',
					buttonText: '',
			        buttonConfig: {
			            iconCls: 'upload-icon'
			        }
				}],
    			buttons: [{
    				text: 'Attach File',
    				handler: function(){
    					//alert('in handler');
							if(fp.getForm().isValid()){
									fp.getForm().submit({
										url: 'FileUploadServlet.csmp',
										waitMsg: 'Uploading ',
										success: function(fp, o){
											var filename = o.result.file;
											var fileId = o.result.fileSize;

											var att_div = '<div id="'+fileId+'">'+																
															'<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>'+
																'<td id="" width="25"><img src="images/mail/images/icon-complete.gif"/></td>'+
																'<td id="" class="t1">'+filename+' &nbsp;'+
																' &nbsp;Cancel</td>'+
															'</tr></table>'+
															'</div>';
											
											var el = Ext.fly('reply-'+email_id+'-msgDiv');
											var att_text = document.getElementById('reply-'+email_id+'-msgDiv').innerHTML;
											var newAttText = att_text+att_div;
											el.update(newAttText);
											
											if(flag == 1){
												var pevVal = document.getElementById('reply-'+email_id+'-attachments').value;
												attachment = pevVal+','+filename;
												document.getElementById('reply-'+email_id+'-attachments').value = attachment;
											}else {	
												 attachment = filename;
												 document.getElementById('reply-'+email_id+'-attachments').value = attachment;
												 flag = 1;
												 }

											if(!el.isVisible()){
												el.slideIn('t', {
													duration: .2,
													easing: 'easeIn',
													callback: function(){
														el.highlight();
														}
													});
												}else{
													el.highlight();
													}
											},
										failure: function(fp, o){
											var filename = o.result.file;
											msg('Sorry!', 'Filed to upload.');
											}
									});
								}
						}
					//}
				}]
			});
			
					var replyForm = new Ext.form.FormPanel({
					labelWidth: 80,
					id:'reply-'+email_id+'Mail',
					autoScroll:true,
					forceFit:true,
					xtype: 'form',
					layout:'form',
					frame:true,
					bodyStyle:'padding:5px 5px 0;',
					url:'mailinfoAction.csmp?method=sendMail',
					items: [{
							fieldLabel: 'From',
							name: 'from',
							xtype: 'combo',
							anchor:'95%',
							typeAhead: true,
							editable: true,
							triggerAction: 'all',
							autoSelect: true,
							hideTrigger : true,
							loadingText :'Loading...',
							lazyRender:true,
							mode: 'local',
							store: new Ext.data.ArrayStore({
								id: 0,
								fields: [
									'myId',
									'displayText'
								],
								data: ['<%=display_from_contacts%>']
								//data: [[1, 'item1'], [2, 'item2']]
							}),
							valueField: 'myId',
							displayField: 'displayText'
							},{
								fieldLabel: 'To',
								name: 'to',
								id:'reply-'+email_id+'-to',
								anchor:'100%',
								xtype: 'combo',
								store: str_contacts,
							    mode: 'local',
							    displayField: 'email',
								valueField:'name',
								typeAhead: true,
								autoSelect: true,
								hideTrigger : true,
								loadingText :'Loading...',
								triggerAction: 'all',
								emptyText:'To',
								selectOnFocus:true,
								triggerAction: 'all',
								listeners:{
									'select': function(){	
										//alert("Hello......."+pre_val+"<<<");
										/*if(is_first == 0){
											pre_val = pre_val + Ext.getCmp('to').getRawValue()+',';
											is_first += 1;
											}
										else if(is_first == 1)
										{
											pre_val = pre_val +','+ Ext.getCmp('to').getRawValue();
											is_first += 1;
										}	
										else
										{
											pre_val = pre_val +','+ Ext.getCmp('to').getRawValue();
										}*/
										pre_val =  Ext.getCmp('reply-'+email_id+'-to').getRawValue()+',';
										
										pre_val =  pre_val.replace(/&lt;/gi, '<'); 
										pre_val =  pre_val.replace(/&gt;/gi, '>'); 									
										//alert("look@me:"+pre_val);
										//Ext.getCmp('to').value = '';  	setRawValue
										//Ext.getCmp('to').value = pre_val;
										Ext.getCmp('reply-'+email_id+'-to').setRawValue(pre_val);
										//alert('to::'+Ext.getCmp('reply-'+email_id+'-to').getRawValue());
										}
									}
								},{
								fieldLabel: 'cc',
								name: 'cc',
								id:'reply-'+email_id+'-cc',
								anchor:'100%',
								xtype: 'combo',
								store: str_contacts,
							    mode: 'local',
							    displayField: 'email',
								valueField:'name',
								typeAhead: true,
								autoSelect: true,
								hideTrigger : true,
								loadingText :'Loading...',
								triggerAction: 'all',
								emptyText:'CC',
								selectOnFocus:true,
								triggerAction: 'all',
								listeners:{
									'select': function(){	
										//alert("Hello......."+pre_val_cc+"<<<");
										if(is_first_cc == 0){
											pre_val_cc = pre_val_cc + Ext.getCmp('reply-'+email_id+'-cc').getRawValue()+',';
											is_first_cc += 1;
											}
										else if(is_first_cc == 1)
										{
											pre_val_cc = pre_val_cc + Ext.getCmp('reply-'+email_id+'-cc').getRawValue()+',';
											is_first_cc += 1;
										}	
										else
										{
											pre_val_cc = pre_val_cc + Ext.getCmp('reply-'+email_id+'-cc').getRawValue()+',';
											is_first_cc += 1;
										}
											
										pre_val_cc =  pre_val_cc.replace(/&lt;/gi, '<'); 
										pre_val_cc =  pre_val_cc.replace(/&gt;/gi, '>'); 									
										//alert("look@me:"+pre_val_cc);
										//Ext.getCmp('to').value = '';  	setRawValue
										//Ext.getCmp('to').value = pre_val;
										Ext.getCmp('reply-'+email_id+'-cc').setRawValue(pre_val_cc);
										//alert('to::'+Ext.getCmp('reply-'+email_id+'-cc').getRawValue());
										}
									}							
								},{
							fieldLabel: 'Subject',
							name: 'subject',
							xtype: 'textfield',
							value:subject,
							anchor: '95%'
							},{
								height:75,
								autoScroll: true,
								tag: 'div',
								id:'reply-'+email_id+'-msgDiv',	
								bodyStyle:'display:none', 
								html: ''
							},{
							xtype: 'htmleditor',
							hideLabel: true,
							name: 'body',
							height:350,
							value:bodytext,
							anchor: '100%'
							},{ 
							xtype: 'hidden', 
							id: 'reply-'+email_id+'-attachments',
							name:'attachments',
							value: attachment,
							inputType: 'hidden'						
							},{ 
							xtype: 'hidden', 
							id: 'reply-'+email_id+'-from',
							name:'from',
							value: attachment,
							inputType: 'hidden'						
							}]
					});

					var x = Ext.get('doc-body');
					var tab = Ext.getCmp('doc-body').add({
							xtype:'panel',
							title: subject,					
							closable: true,
							autoScroll: true,
							containerScroll:true,
							layout:'fit',
							forceFit:true,
							bodyStyle:'background-color:#cddcee',
							autoHeight: true,
							border: false,
							id:'reply_tab_'+email_id,
							items:replyForm,					
							plain:true,
							buttonAlign:'center',
							tbar:[{
								text:'Send',
								id:'reply-'+email_id+'_tab_b1',
								tooltip:'Send',
								iconCls:'add',
								handler: function(){
										Ext.getCmp('reply-'+email_id+'Mail').getForm().submit({					  
											url: 'mailinfoAction.csmp?method=sendMail&id='+id+'&email_id='+email_id,
											waitMsg: 'Please,wait.. mail Sending',
											success: function(form, o){ 												
												var q = Ext.getCmp('doc-body');
												Ext.getCmp('doc-body').remove('reply_tab_'+email_id);
												q.doLayout();
												Ext.getCmp('doc-body').add({
													title: 'Mail Sent',					
													closable: true,
													autoScroll: true,
													autoHeight: true,
													border: false,
													html:'Mail sent to '+senderEmail
													}).show();
												},
											failure: function(){ 
												msg('Sorry!', 'Request Could not be performed.');
												}	
										});
									}
								}, '-', {
								text:'Attachment',
								id:'reply-'+email_id+'_tab_b2',
								tooltip:'Attachment',
								defaultType: 'button',
								iconCls:'option',
								handler: function(){
									msg('Success', 'Mail Sent.');
									}
								},'-',{
								text:'Save as Draft',
								id:'reply-'+email_id+'_tab_b3',
								tooltip:'Options',
								iconCls:'remove',
								},fp]
							}).show();	
						tab.doLayout();
					} // END OF REPLY INBOX MAIL
					

	//====================
	//PROCESS FAILED MAILS
	//====================
	var mailfailedmails = function(email_id,senderName,senderEmail,recipient,subject,bodytext){
							//alert("PROCEED FAILED MAILS ------ ");
							var x = Ext.get('doc-body');
							Ext.getCmp('doc-body').add({
									title: subject,
									layout:'form',
									xtype: 'form',
									closable: true,
									autoScroll: true,
									border: true,
									bodyStyle:'padding:5px',
									labelWidth: 80,
									id:'frm2',
									url:'mailinfoAction.csmp?method=sendMail',
									items: [{
											fieldLabel: 'To',
											name: 'to',
											xtype: 'textfield',
											value: recipient,
											anchor:'100%'
											},{
											fieldLabel: 'cc',
											name: 'cc',
											xtype: 'textfield',
											//value: senderEmail,
											anchor:'100%'
											},{
											fieldLabel: 'Subject',
											name: 'subject',
											xtype: 'textfield',
											value: subject,
											anchor: '100%'
											},{
											xtype: 'htmleditor',
											hideLabel: true,
											name: 'body',
											value:bodytext,
											anchor: '100%'
											}],
									tbar:[{
										text:'Send',
										tooltip:'Send',
										iconCls:'add',						
										handler: function(){
													Ext.getCmp('frm2').getForm().submit({					  
														url: 'mailinfoAction.csmp?method=sendMail&id='+id,
														waitMsg: 'Please,wait.. mail Sending',
														success: function(form, o){ msg('Success', 'Mail Sent.'); },
														failure: function(){msg('Sorry!', 'Request Could not be performed.');}
														});
												}
										}]
									}).show();
						} // END OF PROCESS FAILED MAILS
			
	
	//=====================
	//EXT - MESSAGE WINDOW
	//=====================
	var msg = function(title, msg){
					/*Ext.Msg.show({
						title: title,
						msg: msg,
						minWidth: 200,
						modal: true,
						icon: Ext.Msg.INFO,
						buttons: Ext.Msg.OK
						});	*/
				}//END OF EXT MESSAGE
	
	//=======================
	//SHOW COMPANY CONTACTS
	//======================
	function show_company_contacts(){
			var tempText = '<span class="t1"><img src="images/mail/images/loading_00.gif" > Loading .... </span>';
			Ext.fly('company_contacts').update(tempText);
			Ext.Ajax.request({
				url: 'contacts_company.jsp?id='+id,
				success: function(r) {
						if(r.responseText=='timeOut')
							window.location = 'index.jsp';
						else	
							Ext.fly('company_contacts').update(r.responseText);
					},
				failure: function(){ 
							msg('Sorry!', 'Request Could not be performed.');
							}						
				});
		}
	
	
	//=====================
	//SHOW OFFICE CONTACTS
	//====================
	function show_office_contacts(){
			var tempText = '<span class="t1"><img src="images/mail/images/loading_00.gif" > Loading .... </span>';
			//alert("Hello ...");
			Ext.fly('office_contacts').update(tempText);
			Ext.Ajax.request({
				url: 'office_contacts.jsp?id='+id,
				success: function(r) {
					if(r.responseText=='timeOut')
						window.location = 'index.jsp';
					else	
						Ext.fly('office_contacts').update(r.responseText);
					},
				failure: function(){ 
							msg('Sorry!', 'Request Could not be performed.');
							}						
				});
		}
	
	//====================
	//SHOW OFFICE CONTACTS
	//====================
	function show_tagged_mail(){
			var tempText = '<span class="t1"><img src="images/mail/images/loading_00.gif" > Loading .... </span>';
			//alert("Hello ...");
			Ext.fly('tagged_email').update(tempText);
			Ext.Ajax.request({
				url: 'taggedEmail.jsp?id='+id,
				success: function(r) {
					if(r.responseText=='timeOut')
						window.location = 'index.jsp';
					else
						Ext.fly('tagged_email').update(r.responseText);
					},
				failure: function(){ 
							msg('Sorry!', 'Request Could not be performed.');
							}						
				});
		}
		
	//=====================
	//CHECK MAIL
	//====================
	function check_inbox(){
			var w = Ext.getCmp('doc-body');
            //w.getEl().mask('Checking new mails...');
			var showMask = document.getElementById('checkMailDiv');
			var classname = showMask.className;
			classname = classname.replace('hidden','show');
			document.getElementById('checkMailDiv').className = classname;
			Ext.Ajax.request({
				url:'mailinfoAction.csmp?method=checkMail',
				success: function(r) {
					if(r.responseText=='timeOut')
							window.location = 'index.jsp';
					else{
						w.getEl().unmask();
						//====================
						// Load Inbox (Count)
						//====================
						var tempText = '<span class="t1"><img src="images/mail/images/loading_00.gif" > Loading .... </span>';
						Ext.fly('munu_1').update(tempText);
						Ext.Ajax.request({
							//url: 'count_inbox.jsp?id=<%=id%>',
							url:'mailinfoAction.csmp?method=countInbox&id='+id,
							success: function(r) {
								if(r.responseText=='timeOut')
									window.location = 'index.jsp';
								else
									Ext.fly('munu_1').update(r.responseText);
								},
							failure: function(){ 
								msg('Sorry!', 'Request Could not be performed.');
								}	
							});
						//=====================
						//Load Inbox (Company )
						//=====================
						/*var v = Ext.getCmp('comPanel');
						v.getEl().mask('Loading...');*/
						Ext.Ajax.request({
							//url: 'company_inbox.jsp?id=<%=id%>',
							url:'mailinfoAction.csmp?method=companyInbox&id='+id,
							success: function(r) {
								if(r.responseText=='timeOut')
									window.location = 'index.jsp';
								else{
									Ext.fly('company_inbox').update(r.responseText);
									//v.getEl().unmask();	
									}
								},
							failure: function(){ 
								msg('Sorry!', 'Request Could not be performed.');
								}	
							});	
						}
					}
				});
		}
		
	//==========================
	//ADD TAGGED  MAIL
	//==========================	
	var add_win;
	var usedId = id;	
	function add_tagged_mail(){
		   var addEmailForm;
		   // Add tagged email Address form
		   addEmailForm = { 
				xtype: 'form',
				id: 'add-form',
				bodyStyle: 'padding:15px;background:transparent',
				border: false,
				//url:'login_check.jsp',
				items: [{ 
						xtype: 'textfield', 
						id: 'displayName',
						name:'displayName',
						fieldLabel: 'Display Name',
						allowBlank: false, 
						minLength: 3, 
						maxLength: 32,
						msgTarget:'side'
						},{ 
						xtype: 'textfield', 
						id: 'email',
						name:'email',
						fieldLabel: 'Email id',
						inputType: 'email',
						allowBlank: false, 
						minLength: 3, 
						maxLength: 32,
						msgTarget:'side'
						},{ 
						xtype: 'textfield', 
						id: 'host',
						name:'host',
						fieldLabel: 'POP Host', 
						allowBlank: false, 
						minLength: 2, 
						maxLength: 32,
						msgTarget:'side' 
						},{ 
						xtype: 'textfield', 
						id: 'port',
						name:'port',
						fieldLabel: 'POP Port', 
						allowBlank: false, 
						minLength: 2, 
						maxLength: 32,
						msgTarget:'side' 
						},{ 
						xtype: 'textfield', 
						id: 'smtp_host',
						name:'smtp_host',
						fieldLabel: 'SMTP Host', 
						allowBlank: false, 
						minLength: 2, 
						maxLength: 32,
						msgTarget:'side' 
						},{ 
						xtype: 'textfield', 
						id: 'smtp_port',
						name:'smtp_port',
						fieldLabel: 'SMTP Port', 
						allowBlank: false, 
						minLength: 2, 
						maxLength: 32,
						msgTarget:'side' 
						},{ 
						xtype: 'textfield', 
						id: 'password',
						name:'password',
						inputType: 'password',
						fieldLabel: 'Password', 
						allowBlank: false, 
						minLength: 3, 
						maxLength: 32,
						msgTarget:'side' 
						},{ 
						xtype: 'hidden', 
						id: 'user_id',
						name:'user_id',
						value: usedId,
						inputType: 'hidden'						
						},{ 
						xtype: 'radiogroup', 
						id: 'ssl',
						name:'ssl',
						fieldLabel: 'SSL', 
						items: [
							{boxLabel: 'NO', inputValue: 'N' , name: 'ssl', checked: true},
							{boxLabel: 'YES', inputValue: 'Y', name: 'ssl'}
							]
						}
					],
				buttons: [{
					text: 'ADD',
					handler: function() {
						Ext.getCmp('add-form').getForm().submit({
							url: 'saveNew.jsp?id='+id,
							waitMsg: 'Please wait...',
							success: function(fp, o){ 
								add_win.hide();
								msg('Added!', 'New E-mail Address Addeed ');								
								},
							failure: function(){ 
								msg('Sorry!', 'Pleae check your settings.');
								Ext.getCmp('add-form').getForm().reset();
								}
							});
						}
					},{
					text: 'Reset',
					handler: function(){
						Ext.getCmp('add-form').getForm().reset();
						}
					},{
					text: 'Close',
					handler: function(){
						add_win.hide();
						}
					}]
			}// End of form -- tagged email Address form
			
			// Create window of tagged email Address form			
			if(!add_win){
				//alert("ADDING WINDOW"+add_win);
				add_win = new Ext.Window({
						layout: 'form',
						title:'Add E-mail Address',
						closable:true,
						width: 500,
						height:350,
						autoHeight: true,
						closeAction: 'hide',
						items: [addEmailForm]
						});
				}
		   add_win.show();
		}	
	//===============
	//SHOW - HIDDEN
	//===============
		
	function show(x){
		var y = document.getElementById(x).className;
		//alert('y::'+y);
		if(y == 'hidden')
		{
			document.getElementById(x).className = 'show';
		}
		else
		{
			document.getElementById(x).className = 'hidden';
		}
		Ext.getCmp('mailMenu').doLayout();
	}
	
	//========================
	//SEARCH FIELD MAKE BLANK
	//========================
		
	function make_blank(x,y){
		var z = document.getElementById(x).value;
		if(z == y)
			document.getElementById(x).value = '';
		}
	//=====================
	//MANAGE REF CHECK BOX
	//=====================		
	function checked_item(x){
			document.getElementById('opt_1_'+x).disabled = false;
			document.getElementById('opt_2_'+x).disabled = false;
			document.getElementById('opt_3_'+x).disabled = false;
			document.getElementById('opt_4_'+x).disabled = true;
			document.getElementById('opt_5_'+x).disabled = true;
			document.getElementById('opt_6_'+x).disabled = true;
		}
	function checked_item2(x){
			document.getElementById('opt_1_'+x).disabled = true;
			document.getElementById('opt_2_'+x).disabled = true;
			document.getElementById('opt_3_'+x).disabled = true;
			document.getElementById('opt_4_'+x).disabled = false;
			document.getElementById('opt_5_'+x).disabled = false;
			document.getElementById('opt_6_'+x).disabled = false;
		}
	//========
	//SET REF
	//========
	function set_ref(x){
		var a = document.getElementById('opt_1_'+x).disabled;
		var b = document.getElementById('opt_2_'+x).disabled;		
		var c = document.getElementById('opt_3_'+x).disabled;
		var d = document.getElementById('opt_4_'+x).disabled;
		var e = document.getElementById('opt_5_'+x).disabled;
		var f = document.getElementById('opt_6_'+x).disabled;

		if(a==false && b==false && c==false){
			var action = 'update';
			var com_id = document.getElementById('opt_1_'+x).value;
			var group_id = document.getElementById('opt_2_'+x).value;
			var ref_id = document.getElementById('opt_3_'+x).value;
			}
		if(d==false && e==false && f==false){
			var action = 'insert';
			var com_id = document.getElementById('opt_4_'+x).value;
			var group_id = document.getElementById('opt_5_'+x).value;
			var ref = document.getElementById('opt_6_'+x).value;
			}
			
		var url = 'mailRef_edit.jsp?id='+id+'&ref_id='+ref_id+'&group_id='+group_id+'&ref='+ref+'&action='+action+'&email_id='+x+'&com_id='+com_id;
		Ext.Ajax.request({
			url: url,
			success: function(r) {
					if(r.responseText=='timeOut')
							window.location = 'index.jsp';
					else{
						document.getElementById('ref_edit_'+x).className = 'hidden';
						document.getElementById('ref_mess_'+x).className = 'show';
						Ext.fly('ref_mess_'+x).update('Successfully Updated');
						}
					},
			failure: function(){ 
						msg('Sorry!', 'Request Could not be performed.');
						}		
			});
		
		}
		
	//=============
	//SEARCH -MAIL
	//=============
	function searchMail(){
		var x = document.getElementById('search_mail').value;
		var id = id;
		var uId = '<%=session.getId()%>'; 	
		if(id != uId){
			//msg('Sorry!', 'Session Time Out ... Please login again.');
			//parent.location.replace('index.jsp');
			}
//		
//		Ext.getCmp('doc-body').add({
//				title: 'Search Mail',					
//				closable: true,
//				autoScroll: true,
//				layout: 'fit',
//				html:'Hello ',
//				plain:true,
//				 dockedItems: [{
//			xtype: 'pagingtoolbar',
//			dock: 'bottom',
//			store: store,
//						pageSize: 23,
//						displayInfo: true,
//						displayMsg: 'Displaying topics {0} - {1} of {2}',
//						emptyMsg: "No Mail",
//						})
//				}).show();		
//		
		
		var store_search = Ext.create('Ext.data.JsonStore',{
			root: 'topics',
			totalProperty: 'totalCount',
			remoteSort: true,
			fields: [
           		{name: 'email_id', type: 'string'},
				{name: 'senderName', type: 'string'},
				{name: 'senderEmail', type: 'string'},
				{name: 'subject', type: 'string'},
           		{name: 'send_date', type: 'date', dateFormat: 'm/d/Y'},
				{name: 'body_text',type: 'steing'},
				{name: 'status', type: 'string'}
        	],
			
			proxy: Ext.create('Ext.data.HttpProxy',{
            			url: 'mailinfoAction.csmp?method=mailSearch&id='+id+'&topic='+x
        			})
			});
		//store_search.setDefaultSort('send_date', 'desc');
		var xg = Ext.grid;
		function renderSubject_serch(value,p,record){			
			return String.format('<div class="t1"><a href="" onClick="showmail(\'{1}\',\'{2}\',\'{3}\',\'\',\'{4}\',\'{5}\');return anchorPreventDefault(event);">{0}</a>',
			value,record.data.email_id,record.data.senderName,record.data.senderEmail,record.data.subject,record.data.send_date,record.data.body_text);
			}
		function renderBody_serch(value,p,record){			
			return String.format('<div class="t1">{0}</div>',value,record.data.body_text);
			}	
		var grid_search = Ext.create('Ext.grid.GridPanel',{
			store: store_search,
			layout: 'fit',
			loadMask: true,
			trackMouseOver:true,
        	disableSelection:false,
			columnLines: true,
			columns: [{
						id:'senderName',
						header: 'Sender', 
						width: 150, 
						sortable: true, 
						dataIndex: 'senderName'
						},{
						id:'subject',
						header: 'Subject', 
						width: 500, 
						sortable: true, 
						dataIndex: 'subject',
						renderer: renderSubject_serch,
						},{
						id:'send_date',
						header: 'Date', 
						width: 100, 
						//renderer: Ext.util.Format.dateRenderer('D m/d/Y'), 
						renderer: Ext.util.Format.dateRenderer('D Y-m-d h:i:s'),
						sortable: true, 
						dataIndex: 'send_date'
						}],
			 viewConfig: {		
						forceFit: true,	
						//getRowClass: function(record, index) {
						//var c = record.get('status');
						//		if (c == 'I') {
						//			return 'x-grid3-row x-grid3-row-alt';
						//		} else if (c == 'U') {
						//			return 'x-grid3-row-table';
						//		}		
						//}
						enableRowBody:true,
						showPreview:true//,
						/*getAdditionalData : function(record, rowIndex, p, store){
							if(this.showPreview){
						        	p.body = '<p>'+record.data.body_text+'</p>';
						                    return 'x-grid3-row-expanded';
						                }
						                return 'x-grid3-row-collapsed';
						            }*/

						},
			tbar:[{
						text:'Delete',
						tooltip:'Delete',
						defaultType: 'button',
						iconCls:'option',
						handler: function(){
							msg('Delete', 'Mail deleted.');
							}
						}, '-', {
						text:'Reply',
						tooltip:'Reply',
						defaultType: 'button',
						iconCls:'option',
						handler: function(){
							msg('Reply', 'Mail Reply.');
							}
						},'-',{
						text:'Forward',
						tooltip:'Forward',
						iconCls:'remove',
						handler: function(){
							msg('Forward', 'Mail Forward.');
							}
						},'-',{
						text:'Move',
						tooltip:'Move',
						iconCls:'remove',
						handler: function(){
							msg('Move', 'Mail Moved.');
							}
						},'-',{
						text:'Action',
						tooltip:'Action',
						iconCls:'remove',
						handler: function(){
							msg('Action', 'Action ');
							}
						}],
						 dockedItems: [{
						xtype: 'pagingtoolbar',
						dock: 'bottom',						
						pageSize: 23,
						store: store_search,
						displayInfo: true,
						displayMsg: 'Displaying topics {0} - {1} of {2}',
						emptyMsg: "No Mail"
						 }],
						items:[
							'-', {
						        pressed: true,
						        enableToggle:true,
						        text: 'Show Preview',
						        cls: 'x-btn-text-icon details',
						        toggleHandler: function(btn, pressed){
						        	var view = grid_search.getView();
						        	view.showPreview = pressed;
						        	view.refresh();
						       		 }
						       }]

						//})
			});
			// ADD SEARCH MAIL TAB
			Ext.getCmp('doc-body').add({
				title: 'Search Mail',					
				closable: true,
				autoScroll: true,
				layout: 'fit',
				items:grid_search,
				plain:true
				}).show();		
			
			//store_search.load({params:{start:0, limit:23}});	
		}
		
	// =====================
	//	COMPANY OTHER EMAIL
	//==================== 
		
		function check_com_other_mail(x){			
			var tempText = '<span class="t1"><img src="images/mail/images/loading_00.gif" > Loading .... </span>';
			Ext.fly('com_'+x).update(tempText);
			Ext.Ajax.request({
				url: 'contacts_company.jsp?id='+id+'&com_id='+x,
				success: function(r) {
					if(r.responseText=='timeOut')
						window.location = 'index.jsp';
					else	
						Ext.fly('com_'+x).update(r.responseText);
					},
				failure: function(){ 
					msg('Sorry!', 'Request Could not be performed.');
					}	
				});
			
	
		}
		
	// =====================
	//	COMPANY OTHER EMAIL
	//   ===================== 
	function show_gr_dtl(x,y){
			document.getElementById(x+'_'+y).className = 'show';
			var tempText = '<span class="t1"><img src="images/mail/images/loading_00.gif" > Loading .... </span>';
			Ext.fly(x+'_'+y).update(tempText)
			Ext.Ajax.request({
				url: 'show_gr_dtl.jsp?com_id='+x+'&gr_id='+y+'&id='+id,
				success: function(r) {
					if(r.responseText=='timeOut')
						window.location = 'index.jsp';
					else	
						Ext.fly(x+'_'+y).update(r.responseText);
					},
				failure: function(){ 
					msg('Sorry!', 'Request Could not be performed.');
					}	
				});
			}	
	//======================
	//	SHARE A  MAIL
	//=======================
	  function share_mail(x,y){
		 document.getElementById('ref_mess_'+x).className = 'show';
		 document.getElementById('share_mail_'+x).className = 'hidden';
		 //
		  var z = document.getElementById('sel_share_'+x).value;
		  alert('look@ SHARE OMS ID:'+z);
		  Ext.Ajax.request({
				url: 'mailinfoAction.csmp?method=shareMail&oms_id='+z+'&email_id='+y+'&id='+id,
				success: function(r) {
					if(r.responseText=='timeOut')
						window.location = 'index.jsp';
					else{
						document.getElementById('share_'+x).className = 'hidden';
						document.getElementById('ref_mess_'+x).className = 'show';
						Ext.fly('share_'+x).update('Successfully Updated');
						}
					},
				failure: function(){ 
					msg('Sorry!', 'Request Could not be performed.');
					}		
				});
		  }
		
	// ==================================
	//	  ADD/EDIT/DELETE - SETTINGS
	// ================================== 
	  var settings_win;
	  function mail_settings(){
		  
		  var url = 'mailinfoAction.csmp?method=emailSettings&s_id='+id;
			Ext.define('EMailinfoModel', {
			    extend: 'Ext.data.Model',
			    fields: ['row_id','email','pop_host','pop_port','smtp_host','smtp_port','uPass','ssl','displayName']
			/*{name: 'row_id'},
							{name: 'email', allowBlank: false},
							{name: 'pop_host', allowBlank: false},
							{name: 'pop_port', allowBlank: false},
							{name: 'smtp_host', allowBlank: false},
							{name: 'smtp_port', allowBlank: false},
							{name: 'uPass', allowBlank: false,type: 'password'},
							{name: 'ssl', allowBlank: false},
							{name: 'displayName', allowBlank: false}*/
			});
			var settings_store = Ext.create('Ext.data.Store', {
			    model: 'EMailinfoModel',
			    pageSize: 20,
			    proxy: {
			        type: 'ajax',
			        url: url,
			        autoSave: true,
		            autoSync:true,

			        reader: {
			        	//totalProperty: 'total',
						successProperty: 'success',
						idProperty: 'row_id',
						root: 'data',
						messageProperty: 'message'
			        }/*,
			        writer: new Ext.data.writer.Json({
						encode: true,
						writeAllFields : true,
						listful : true
					})*/
			    }
			});
			settings_store.load();
			
			
		  	/*var settings_store = new Ext.data.Store({
				id: 'user',
				restful: true,
				paramsAsHash: true, 
				proxy: new Ext.data.HttpProxy({
								url: 'emailSettings.jsp?s_id=<%=id%>'
							}),
				reader:  new Ext.data.JsonReader({
								totalProperty: 'total',
								successProperty: 'success',
								idProperty: 'row_id',
								root: 'data',
								messageProperty: 'message'
							}, [{name: 'row_id'},
								{name: 'email', allowBlank: false},
								{name: 'pop_host', allowBlank: false},
								{name: 'pop_port', allowBlank: false},
								{name: 'smtp_host', allowBlank: false},
								{name: 'smtp_port', allowBlank: false},
								{name: 'uPass', allowBlank: false,type: 'password'},
								{name: 'ssl', allowBlank: false},
								{name: 'displayName', allowBlank: false}
							]),
				writer: new Ext.data.JsonWriter({
							encode: true,
							writeAllFields : true,
							listful : true
						})	
			});*/
			var settings_columns =  [
				{header: "ID", width: 20, sortable: true, dataIndex: 'row_id'},
				{header: "Email", width: 100, sortable: true, dataIndex: 'email', editor: new Ext.form.TextField({})},
				{header: "POP Host", width: 100, sortable: true, dataIndex: 'pop_host', editor: new Ext.form.TextField({})},
				{header: "POP Port", width: 50, sortable: true, dataIndex: 'pop_port', editor: new Ext.form.TextField({})},
				{header: "SMTP Host", width: 100, sortable: true, dataIndex: 'smtp_host', editor: new Ext.form.TextField({})},
				{header: "SMTP Port", width: 50, sortable: true, dataIndex: 'smtp_port', editor: new Ext.form.TextField({})},
				{header: "Password", width: 50, sortable: true, dataIndex: 'uPass', editor: new Ext.form.TextField({inputType: 'password'})},
				{header: "SSL", width: 50, sortable: true, dataIndex: 'ssl', 
							editor: Ext.create('Ext.form.ComboBox',{
												anchor:'100%',
												autoSelect: true, 
												editable : false,
												forceSelection : true,
												emptyText:'Y',
												hideTrigger : false,
												submitValue : true,
												mode: 'local',
												store: new Ext.data.ArrayStore({id: 0,fields: ['myId','displayText'],data: [['Y', 'Y'],['N', 'N']]}),
												valueField: 'myId',
												displayField: 'displayText'
												})
								},
				{header: "Display", width: 100, sortable: true, dataIndex: 'displayName', editor: new Ext.form.TextField({})}
			];
		
			var editor = '';
			/*
			var editor = new Ext.ux.grid.RowEditor({
				saveText: 'Update'
			});
			*/
			var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
		        clicksToMoveEditor: 1,
		        autoCancel: false
		    });
			var settings_grid = Ext.create('Ext.grid.GridPanel',{
				//renderTo: 'settingsDiv',
				iconCls: 'icon-grid',
				loadMask: true,
				trackMouseOver:true,
				forceLayout : true,
				//frame: true,
				autoScroll: true,
				border:false,
				height: 300,
				store: settings_store,
				//plugins: [editor],
		        plugins: [rowEditing],
				columns : settings_columns,
				tbar: [{
					text: 'Add',
					iconCls: 'silk-add',
					handler: onAdd
				}, '-', {
					text: 'Delete',
					iconCls: 'silk-delete',
					handler: onDelete
				}, '-']/*,
				viewConfig: {
					forceFit: true
				}*/
			});
			settings_grid.on('edit', function(editor, e) 
		    {
				//alert(settings_grid.store)
		        settings_grid.store.sync();
				settings_grid.store.load();
		    });
			/*
			settings_grid.on('edit', function(e){
				alert('You are Editing'+e);
	            alert('You are Editing'+e.record);
	            e.record.commit();	            
	        });
			*/
			/*settings_grid.getSelectionModel().on("rowdeselect", function(selModel, rowIndex, record) {
			    if (record.dirty) {
			        // record has been modified
			    	alert('You are Editing'+record);
			    	record.commit();
			    }
			});*/
			function onAdd(btn, ev) {
				/*var u = new settings_grid.store.recordType({
					first : '',
					last: '',
					email : ''
				});
				editor.stopEditing();
				settings_grid.store.insert(0, u);
				editor.startEditing(0);*/
                rowEditing.cancelEdit();

                // Create a model instance
                var r = Ext.create('EMailinfoModel', {
                	email: '',
                	pop_host: 'default',
                	pop_port: 110,
                    smtp_host: 'default',
                    smtp_port:587,
                    uPass: '*****',
                    ssl:'N'
                });

                settings_grid.store.insert(0, r);
                rowEditing.startEdit(0, 0);                
			}
			function onDelete() {
				var sm = settings_grid.getSelectionModel();
                rowEditing.cancelEdit();
                settings_grid.store.remove(sm.getSelection());
                if (settings_grid.store.getCount() > 0) {
                    sm.select(0);
                }
				/*var rec = settings_grid.getRowSelectionModel().getSelected();
				if (!rec) {
					return false;
				}
				settings_grid.store.remove(rec);*/
			}
			
			if(!settings_win){
				settings_win = new Ext.Window({
						layout: 'form',
						title:'Add/ Edit/ Delete E-mail Address',
						closable:true,
						width: 800,
						height:350,
						modal:true,
						autoHeight: true,
						closeAction: 'hide',
						items: [settings_grid]
						});
				}
		   settings_win.show();
		   settings_store.load();	
		  }
	
	//=========================
	//	Active Comments
	//===========================	  
  	function activeComment(x){
		//document.getElementById('comm_'+x).value = '';
		//document.getElementById('td_'+x).className = 'show';
		var b = document.getElementById('comm_'+x).value;
		if(b=='Write a comment ...'){
			document.getElementById('comm_'+x).value = '';
			document.getElementById('td_'+x).className = 'show';
			}else{
				return false;	
				}
		
		}
	function hideComment(x){
		var a = document.getElementById('comm_'+x).value;
		if(a==''){
			document.getElementById('comm_'+x).value = 'Write a comment ...';
			document.getElementById('td_'+x).className = 'hidden';
			}else{
				return false;	
				}
		}
	function insertComment(x){
		var comment = document.getElementById('comm_'+x).value;
		Ext.Ajax.request({
			url: 'insertComment.jsp?id='+id+'&cid='+x+'&comment='+comment,
			success: function(r) {
				if(r.responseText =='timeOut'){
					msg('Sorry!', 'Session Time out.');
					window.location = 'index.jsp';
				}else{
					var res ="";	
					//var res = JSON.parse(r.responseText);
				}
			}
		});
	 }
	
	
	// ==========================
	//		FLEXI-DELAY REPORT
	//==========================	
	var flexi_total_chart_win;
	function flexi_delay(){   		   
			var store_flexi_delay = new Ext.data.JsonStore({
					root: 'req',
					totalProperty: 'totalCount',
					remoteSort: true,
					fields: [
						{name: 'req_id', type: 'string'},
						{name: 'dst_no', type: 'string'},
						{name: 'amount', type: 'string'},
						{name: 'process', type: 'string'},
						{name: 'process_time',  type: 'date', dateFormat: 'm/d/Y'},
						{name: 'req_time', type: 'date', dateFormat: 'm/d/Y'},
						{name: 'delay_time', type: 'string'}
					],			
					proxy: new Ext.data.HttpProxy({ url: 'flexiDelay.jsp?id='+id})
					});
		   store_flexi_delay.setDefaultSort('req_time', 'desc');
		  
		   var pannel =  new Ext.Panel({
				frame:true,
				items: {
					xtype: 'columnchart',
					store: store_flexi_delay,
					yField: 'delay_time',
					url: 'resources/charts.swf',
					xField: 'dst_no',
					xAxis: new Ext.chart.CategoryAxis({
						title: 'MOBILE NO'
					}),
					yAxis: new Ext.chart.NumericAxis({
						title: 'DELAY TIME'
					}),
					extraStyle: {
						   xAxis: {showLabels:false}
						}
					}
				});
				    
			var flexi_total_grid = Ext.create('Ext.grid.GridPanel',{
								store: store_flexi_delay,
								loadMask: true,
								stripeRows: true,
								autoExpandColumn: 'req_id',
								layout: 'fit',
								stateful: true,
								stateId: 'grid',        
								columns: [
									{id:'req_id',header: 'ID', width: 100, sortable: true, dataIndex: 'req_id'},
									{header: 'DST No.', width: 150, sortable: true, dataIndex: 'dst_no'},
									{header: 'Amount', width: 150, sortable: true, dataIndex: 'amount'},
									{header: 'Process Time', width: 150, sortable: true, dataIndex: 'process_time', 
												renderer: Ext.util.Format.dateRenderer('D m/d/Y'),},
									{header: 'Req. Time', width: 150, sortable: true, dataIndex: 'req_time', renderer: Ext.util.Format.dateRenderer('D m/d/Y'),},
									{header: 'Delay Time', width: 150, sortable: true, dataIndex: 'delay_time'},
								]
							});
							
			if(!flexi_total_chart_win){
				flexi_total_chart_win = new Ext.Window({
						layout: 'form',
						title:'Flexi Delay Chart [Last 7 days]',
						closable:true,
						width: 650,
						height:350,
						//autoHeight: true,
						closeAction: 'hide',
						items: [pannel]
						});
				}
			
			Ext.getCmp('doc-body').add({
				title: 'Flexi Delay Report',
				id:'delay_report',
				closable: true,
				//frame:true,
				autoScroll: true,
				layout: 'fit',
				items:flexi_total_grid,
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'Send',
					handler: function(){
								flexi_total_chart_win.show();
								}
					}]
					}).show();
			store_flexi_delay.load();		
			}	

		// ==========================
		// 	FLEXI-TOTAL USE REPORT
	   	//==========================	
		var flexi_total_use_chart_win;
		function total_flexi_chart(){
			
			var store_flexi_total= new Ext.data.JsonStore({
						root: 'req',
						totalProperty: 'totalCount',
						remoteSort: true,
						fields: [
							{name: 'ori', type: 'string'},
							{name: 'total', type: 'string'},
						],			
						proxy: new Ext.data.HttpProxy({ url: 'flexi_total.jsp?id='+id})
						});
		   	store_flexi_total.setDefaultSort('total', 'desc');
			
			var flexi_total_grid = Ext.create('Ext.grid.GridPanel',{
					store: store_flexi_total,
					loadMask: true,
					stripeRows: true,
					autoExpandColumn: 'company',
					layout: 'fit',
					stateful: true,
					stateId: 'grid',        
					columns: [
						{id:'company',header: 'Company', width: 200, sortable: true, dataIndex: 'ori'},
						{header: 'Total', width: 200, sortable: true, dataIndex: 'total'}
						]
					});
					
		   	var flexi_total_chart = new Ext.Panel({
			   				frame:true,
							items: {
								store: store_flexi_total,
								xtype: 'piechart',
								dataField: 'total',
								categoryField: 'ori',
								extraStyle:
								{
									legend:
									{
										display: 'bottom',
										padding: 5,
										font:
										{
											family: 'Tahoma',
											size: 13
										}
									}
								}
							}
						});
			
			if(!flexi_total_use_chart_win){
				flexi_total_use_chart_win = new Ext.Window({
						layout: 'form',
						title:'Flexi Use In Last 30 Days in BDT',
						closable:true,
						width: 500,
						height:350,
						//autoHeight: true,
						closeAction: 'hide',
						items: [flexi_total_chart]
						});
				}
 
			Ext.getCmp('doc-body').add({
				title: 'Flexi Total Use in last 30days',
				id:'flexi_all_report',
				closable: true,
				//frame:true,
				autoScroll: true,
				layout: 'fit',
				items:flexi_total_grid,
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'Send',
					handler: function(){
								flexi_total_use_chart_win.show();
								}
					}]
					}).show();
			store_flexi_total.load();	
			}	
		
		
		// ==========================
		// 	1PINLESS ALL USERS
	   	//==========================
			
		var user_chart_win;
		function pinless_users_states(){
			
			var store_pinless_all_users= new Ext.data.JsonStore({
						root: 'req',
						totalProperty: 'totalCount',
						remoteSort: true,
						fields: [
							{name: 'states', type: 'string'},
							{name: 'state_name', type: 'string'},
							{name: 'total', type: 'string'}
						],			
						proxy: new Ext.data.HttpProxy({ url: '1pinless_all_users_state.jsp?id='+id})
						});
		   	store_pinless_all_users.setDefaultSort('total', 'desc');
			
			var pinless_all_users_grid = Ext.create('Ext.grid.GridPanel',{
					store: store_pinless_all_users,
					loadMask: true,
					stripeRows: true,
					autoExpandColumn: 'states',
					layout: 'fit',
					stateful: true,
					stateId: 'grid',        
					columns: [
						{id:'states',header: 'Sate Code', width: 200, sortable: true, dataIndex: 'states'},
						{header: 'State Name', width: 200, sortable: true, dataIndex: 'state_name'},
						{header: 'Total', width: 200, sortable: true, dataIndex: 'total'}
						]
					});
		   
		   var pinless_all_users_chart = new Ext.Panel({
			   				frame:true,
							items: {
								store: store_pinless_all_users,
								xtype: 'piechart',
								dataField: 'total',
								categoryField: 'state_name',
								extraStyle:
								{
									legend:
									{
										display: 'bottom',
										padding: 5,
										font:
										{
											family: 'Tahoma',
											size: 13
										}
									}
								}
							}
						});
			
			if(!user_chart_win){
				user_chart_win = new Ext.Window({
						layout: 'form',
						title:'1PINLESS ALL USERS LIST',
						closable:true,
						width: 700,
						height:550,
						//autoHeight: true,
						closeAction: 'hide',
						items: [pinless_all_users_chart]
						});
				}
 			
			Ext.getCmp('doc-body').add({
				title: '1PINLESS All Users Sate Wise',
				id:'pinless_user_list',
				closable: true,
				autoScroll: true,
				layout: 'fit',
				items:pinless_all_users_grid,
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'Send',
					handler: function(){
								user_chart_win.show();
								}
					}]
					}).show();
			store_pinless_all_users.load();	
			}	
	
		// ==========================
		// 	1PINLESS CURRENT USERS
	   	//==========================
			
		var cur_user_chart_win;
		function pinless_cur_users_states(){
			
			var store_pinless_cur_users= new Ext.data.JsonStore({
						root: 'req',
						totalProperty: 'totalCount',
						remoteSort: true,
						fields: [
							{name: 'states', type: 'string'},
							{name: 'state_name', type: 'string'},
							{name: 'total', type: 'string'}
						],			
						proxy: new Ext.data.HttpProxy({ url: '1pinless_current_users_state.jsp?id='+id})
						});
		   	store_pinless_cur_users.setDefaultSort('total', 'desc');
			
			var pinless_cur_users_grid = Ext.create('Ext.grid.GridPanel',{
					store: store_pinless_cur_users,
					loadMask: true,
					stripeRows: true,
					autoExpandColumn: 'states',
					layout: 'fit',
					stateful: true,
					stateId: 'grid',        
					columns: [
						{id:'states',header: 'Sate Code', width: 200, sortable: true, dataIndex: 'states'},
						{header: 'State Name', width: 200, sortable: true, dataIndex: 'state_name'},
						{header: 'Total', width: 200, sortable: true, dataIndex: 'total'}
						]
					});
		   
		   var pinless_cur_users_chart = new Ext.Panel({
			   				frame:true,
							items: {
								store: store_pinless_cur_users,
								xtype: 'piechart',
								dataField: 'total',
								categoryField: 'state_name',
								extraStyle:
								{
									legend:
									{
										display: 'bottom',
										padding: 5,
										font:
										{
											family: 'Tahoma',
											size: 13
										}
									}
								}
							}
						});
			
			if(!cur_user_chart_win){
				cur_user_chart_win = new Ext.Window({
						layout: 'form',
						title:'1PINLESS CURRENT USERS LIST',
						closable:true,
						width: 700,
						height:550,
						//autoHeight: true,
						closeAction: 'hide',
						items: [pinless_cur_users_chart]
						});
				}
 			
			Ext.getCmp('doc-body').add({
				title: '1PINLESS Current Users Sate Wise',
				id:'pinless_cur_user_list',
				closable: true,
				autoScroll: true,
				layout: 'fit',
				items:pinless_cur_users_grid,
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'Send',
					handler: function(){
								cur_user_chart_win.show();
								}
					}]
					}).show();
			store_pinless_cur_users.load();	
			}	
 		
		
		// =============================
		// 	1PINLESS SIGNUP PER MONTH
	   	//=================================
			
		var user_signup_chart_win;
		function user_signup_month(){
			var store_signup_month= new Ext.data.JsonStore({
						root: 'req',
						totalProperty: 'totalCount',
						remoteSort: true,
						fields: [
							{name: 'activate_on', type: 'string'},
							{name: 'total', type: 'string'}
						],	
						proxy: new Ext.data.HttpProxy({ url: '1pinless_signup_month.jsp?id='+id})
						});
		   	store_signup_month.setDefaultSort('activate_on', 'desc');
			
			var signup_month_grid = Ext.create('Ext.grid.GridPanel',{
					store: store_signup_month,
					loadMask: true,
					stripeRows: true,
					autoExpandColumn: 'total',
					layout: 'fit',
					stateful: true,
					stateId: 'grid',        
					columns: [
						{id:'activate_on',header: 'Activate On', width: 200, sortable: true, dataIndex: 'activate_on'},
						{id:'total',header: 'Total', width: 200, sortable: true, dataIndex: 'total'},
						]
					});
		   
		   var users_signup_chart = new Ext.Panel({
			   				frame:true,
							items: {
								xtype: 'columnchart',
								store: store_signup_month,
								yField: 'total',
								url: 'resources/charts.swf',
								xField: 'activate_on',
								xAxis: new Ext.chart.CategoryAxis({
									title: 'Month'
								}),
								yAxis: new Ext.chart.NumericAxis({
									title: 'Signup'
								}),
								extraStyle: {
									   xAxis: {
										   showLabels:true,
										   labelRotation: -90
										   }
									}
								}

						});
			
			if(!user_signup_chart_win){
				user_signup_chart_win = new Ext.Window({
						layout: 'form',
						title:'1PINLESS CURRENT USERS LIST',
						closable:true,
						width: 800,
						height:550,
						closeAction: 'hide',
						items: [users_signup_chart]
						});
				}
 			
			Ext.getCmp('doc-body').add({
				title: '1PINLESS SIGN-UP',
				id:'pinless_sign_up',
				closable: true,
				autoScroll: true,
				layout: 'fit',
				items:signup_month_grid,
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'Send',
					handler: function(){
								user_signup_chart_win.show();
								}
					}]
					}).show();
			store_signup_month.load();	
			}	
		
		
		// =============================
		// 	FLEXI SIGNUP PER MONTH
	   	//=================================
			
		var flexi_user_signup_chart_win;
		function flexi_user_signup_month(){
			var store_flexi_signup_month= new Ext.data.JsonStore({
						root: 'req',
						totalProperty: 'totalCount',
						remoteSort: true,
						fields: [
							{name: 'activate_on', type: 'string'},
							{name: 'total', type: 'string'}
						],	
						proxy: new Ext.data.HttpProxy({ url: '1pinless_flexi_signup_month.jsp?id='+id})
						});
		   	store_flexi_signup_month.setDefaultSort('activate_on', 'desc');
			
			var flexi_signup_month_grid = Ext.create('Ext.grid.GridPanel',{
					store: store_flexi_signup_month,
					loadMask: true,
					stripeRows: true,
					autoExpandColumn: 'total',
					layout: 'fit',
					stateful: true,
					stateId: 'grid',        
					columns: [
						{id:'activate_on',header: 'Activate On', width: 200, sortable: true, dataIndex: 'activate_on'},
						{id:'total',header: 'Total', width: 200, sortable: true, dataIndex: 'total'},
						]
					});
		   
		   var flexi_users_signup_chart = new Ext.Panel({
			   				frame:true,
							items: {
								xtype: 'columnchart',
								store: store_flexi_signup_month,
								yField: 'total',
								url: 'resources/charts.swf',
								xField: 'activate_on',
								xAxis: new Ext.chart.CategoryAxis({
									title: 'Month'
								}),
								yAxis: new Ext.chart.NumericAxis({
									title: 'Signup'
								}),
								extraStyle: {
									   xAxis: {
										   showLabels:true,
										   labelRotation: -90
										   }
									}
								}

						});
			
			if(!flexi_user_signup_chart_win){
				flexi_user_signup_chart_win = new Ext.Window({
						layout: 'form',
						title:'1PINLESS FLEXI USERS ',
						closable:true,
						width: 800,
						height:550,
						closeAction: 'hide',
						items: [flexi_users_signup_chart]
						});
				}
 			
			Ext.getCmp('doc-body').add({
				title: '1PINLESS-FLEXI USER SIGN-UP',
				id:'pinless_flexi_sign_up',
				closable: true,
				autoScroll: true,
				layout: 'fit',
				items:flexi_signup_month_grid,
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'Send',
					handler: function(){
								flexi_user_signup_chart_win.show();
								}
					}]
					}).show();
			store_flexi_signup_month.load();	
			}	

		// =============================
		// 	WEB SIGNUP LAST 30 DAYS
	   	//=================================
			
		var signup_day_chart_win;
		function user_signup_day(){
			
			var store_signup_day= new Ext.data.JsonStore({
						root: 'req',
						totalProperty: 'totalCount',
						remoteSort: true,
						fields: [
							{name: 'signup_date', type: 'string'},
							{name: 'total', type: 'string'}
						],	
						proxy: new Ext.data.HttpProxy({ url: '1pinless_signup_day.jsp?id='+id})
						});
		   	store_signup_day.setDefaultSort('signup_date', 'desc');
			
			var signup_day_grid = Ext.create('Ext.grid.GridPanel',{
					store: store_signup_day,
					loadMask: true,
					stripeRows: true,
					autoExpandColumn: 'total',
					layout: 'fit',
					stateful: true,
					stateId: 'grid',        
					columns: [
						{id:'activate_on',header: 'Signup On', width: 200, sortable: true, dataIndex: 'signup_date'},
						{id:'total',header: 'Total', width: 200, sortable: true, dataIndex: 'total'},
						]
					});
					
		   var users_signup_day_chart = new Ext.Panel({
			   				frame:true,
							items: {
								xtype: 'columnchart',
								store: store_signup_day,
								yField: 'total',
								url: 'resources/charts.swf',
								xField: 'signup_date',
								xAxis: new Ext.chart.CategoryAxis({
									title: 'Day'
								}),
								yAxis: new Ext.chart.NumericAxis({
									title: 'Signup'
								}),
								extraStyle: {
									   xAxis: {
										   showLabels:true,
										   labelRotation: -90
										   }
									}
								}

						});
			
			if(!signup_day_chart_win){
				signup_day_chart_win = new Ext.Window({
						layout: 'form',
						title:'1PINLESS USERS SIGNUO[LAST 30DAYS]',
						closable:true,
						width: 800,
						height:550,
						closeAction: 'hide',
						items: [users_signup_day_chart]
						});
				}

			Ext.getCmp('doc-body').add({
				title: '1PINLESS USER SIGN-UP [LAST 30DAYS]',
				id:'pinless_sign_up_day',
				closable: true,
				autoScroll: true,
				layout: 'fit',
				items:signup_day_grid,
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'Send',
					handler: function(){
								signup_day_chart_win.show();
								}
					}]
					}).show();
			store_signup_day.load();	
			}	
		
		//CONCURRENT CALLS
		var conn_calls_win;
		function conncurrent_calls(){
			
			var store_conn_call= new Ext.data.JsonStore({
						root: 'req',
						totalProperty: 'totalCount',
						remoteSort: true,
						fields: [
							{name: 'conn_time', type: 'string'},
							{name: 'total', type: 'string'}
						],	
						proxy: new Ext.data.HttpProxy({ url: 'concurrent_calls.jsp?id='+id})
						});
		   	store_conn_call.setDefaultSort('conn_time', 'desc');
						
			var conn_call_grid = Ext.create('Ext.grid.GridPanel',{
					store: store_conn_call,
					loadMask: true,
					stripeRows: true,
					autoExpandColumn: 'total',
					layout: 'fit',
					stateful: true,
					stateId: 'grid',        
					columns: [
						{id:'activate_on',header: 'Time', width: 200, sortable: true, dataIndex: 'conn_time'},
						{id:'total',header: 'Total', width: 200, sortable: true, dataIndex: 'total'},
						]
					});
					
		   var conn_calls_chart = new Ext.Panel({
			   				frame:true,
							items: {
								xtype: 'columnchart',
								store: store_conn_call,
								yField: 'total',
								url: 'resources/charts.swf',
								xField: 'conn_time',
								xAxis: new Ext.chart.CategoryAxis({
									title: 'Time'
								}),
								yAxis: new Ext.chart.NumericAxis({
									title: 'Calls'
								}),
								extraStyle: {
									   xAxis: {
										   showLabels:true,
										   labelRotation: -90
										   }
									}
								}

						});
			
			if(!conn_calls_win){
				conn_calls_win = new Ext.Window({
						layout: 'form',
						title:'1PINLESS USERS SIGNUO[LAST 30DAYS]',
						closable:true,
						width: 800,
						height:550,
						closeAction: 'hide',
						items: [conn_calls_chart]
						});
				}

			Ext.getCmp('doc-body').add({
				title: 'CONCURRENT CALLS(BREAK DOWN 30min)',
				id:'conncurrent_calls',
				closable: true,
				autoScroll: true,
				layout: 'fit',
				items:conn_call_grid,
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'CHART',
					handler: function(){
								conn_calls_win.show();
								}
					}]
					}).show();
			
			store_conn_call.load();							
		}
		
		// CONCURRENT CALLS ONLY CANADA
		
		
		//CONCURRENT CALLS WITH STATES
		
		var conn_states_win;
		var tempText = '<span class="t1"><img src="images/mail/images/loading-balls.gif" ></span>';
		function conncurrent_calls_state(){
			
			var req_data ="";
			var states_list = "";
			var states_obj = "";
			var resp;
			var json_resp;
			var conn_state_chart="";
			
			Ext.getCmp('doc-body').add({
				title: 'CONCURRENT CALLS(BREAK DOWN 30min)',
				//id:'conncurrent_calls_state',
				closable: true,
				autoScroll: true,
				layout: 'fit',
				html:'<div id="state_call_grid">'+tempText+'</div>',
				plain:true,
				buttonAlign:'center',
				tbar:[{
					text:'Show in Chart',
					tooltip:'Click here to view the data in Chart',
					iconCls:'CHART',
					handler: function(){
								conn_states_win.show();
								}
					}]
					}).show();
			
			Ext.Ajax.request({
					url: 'concurrent_calls_state.jsp?id='+id,
					success: function(r) {
							resp = Ext.JSON.decode(r.responseText);//JSON.parse(r.responseText);
							json_resp = eval(resp);
							req_data = json_resp.req;
							states_list = json_resp.states_list;
							states_obj = json_resp.states_obj;
							//alert("req_data:"+req_data);
							//alert("states_list:"+states_list);
							//alert("states_obj:"+states_obj);
							Ext.fly('state_call_grid').update('');
							var store_conn_state = new Ext.data.JsonStore({
								fields: ['total','conn_time','CA','AL','GA','MI','IL','KS','CT','TX','WA','FL','NC','NA'],
								data: req_data	
							});
							
							conn_state_chart = new Ext.Panel({
								frame:true,
								renderTo: 'state_call_grid',
								items: {
										xtype: 'stackedbarchart',
										store: store_conn_state,
										url: 'resources/charts.swf',
										yField: 'conn_time',
										series: states_obj
									}
							});
							
							if(!conn_states_win){
							conn_states_win = new Ext.Window({
									layout: 'form',
									title:'1PINLESS USERS SIGNUO[LAST 30DAYS]',
									closable:true,
									width: 800,
									height:550,
									closeAction: 'hide',
									items:[conn_state_chart]
									});
							}								
					}							
				});
			}

			/*
			var store_conn_state_grid= new Ext.data.JsonStore({
					root: 'req',
					totalProperty: 'totalCount',
					remoteSort: true,
					fields: [
							{name: 'conn_time', type: 'string'},
							{name: 'total', type: 'string'}
						],	
					proxy: new Ext.data.HttpProxy({ url: 'concurrent_calls.jsp?id=<%=id%>'})
					});
		   	store_conn_state_grid.setDefaultSort('conn_time', 'desc');	
			
			var conn_call_state_grid = Ext.create('Ext.grid.GridPanel',{
					store: store_conn_state_grid,
					loadMask: true,
					stripeRows: true,
					autoExpandColumn: 'total',
					layout: 'fit',
					stateful: true,
					stateId: 'grid',        
					columns: [
						{id:'conn_time',header: 'Time', width: 200, sortable: true, dataIndex: 'conn_time'},
						{id:'total',header: 'Total', width: 200, sortable: true, dataIndex: 'total'},
						]
					});
	
			
			store_conn_state_grid.load(req_data); 		
						
		}
		*/
	