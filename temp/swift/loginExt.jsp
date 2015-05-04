
<%@page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page contentType="text/html" import="java.util.*"%>
<html>
  <head>
  <title>Demo Platform</title>
  <link rel="stylesheet" type="text/css" href="./Common/JavaScript/ext-2.0/resources/css/ext-all.css"/>
	
	<script type="text/javascript" src="./Common/JavaScript/ext-2.0/adapter/ext/ext-base.js"></script>
  <script type="text/javascript" src="./Common/JavaScript/ext-2.0/ext-all.js"></script>
  <link rel="shortcut icon" href="../Images/hsbc-icon.jpg" type="image/jpg" />
  
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
 
  <link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/docs.css"></link>
	<link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/style.css"></link>  
<script type="text/javascript">
Ext.onReady(function(){
	var samplecomAddForm = new Ext.FormPanel
	({
        url:    'loginAction.csmp?method=login',
        id:     'samplecomAddForm',
        layout: 'form',
        alignTo:'align',
        anchorTo:'align',
        anchors:{x: 0.5, y : 0.5},
        width:210,
        height:390,
        floating:true,
        
        renderTo:Ext.getBody(),
        frame: true,
        title: 'Add Samplecom',
        closable: true,
        autoScroll:true,
        bodyStyle:'background:transparent;padding:10px;',
		border:false,
		defaultType: 'textfield',
		labelAlign:'left',
        items: [{ 
        			xtype: 'textfield',
        			name:'userId',
        			fieldLabel: 'User Nane',
        			allowBlank:false,
        			anchor:'90%',
        			value:'admin'
        		},
                {
					inputType:'password',
					fieldLabel: 'Password',
					id:'password',
					name: 'password',
					allowBlank:false,
					anchor:'90%',
					value:'admin',
					msgTarget : "side" 
				}
    	],
		
        buttons: [{
            text: 'Log In',
            scope: samplecomAddForm,
            handler: function() {
            	samplecomAddForm.getForm().submit({
            		waitMsg:'Please Wait',
                    success: function(f,a) {
                    	Ext.Msg.alert('ALert', 'Login Successful');
                    	var contextPath = '<%=request.getContextPath()%>';
                    	//alert('contextPath::'+contextPath);
                        window.location = contextPath+'/loginAction.csmp?method=loginSuccess';                    	
                    },
                    failure: function(f,a){
                        Ext.Msg.alert('Warnning', 'Exception occured in Login Action');
                    }
                });
            }
        }]        
    });
	
  	
  	
  	
  	
  	//var x = Ext.getViewSize();
  	//alert('x::'+x);
  	//loginPanel.add(samplecomAddForm);
  	//loginPanel.doLayout();
  	samplecomAddForm.setPosition(1000,100);
  	//samplecomAddForm.setAnchor('50% 40%');
  	samplecomAddForm.show();
  	//loginPanel.show();
  	
  	/*
  	
  	new Ext.Viewport({
  	    layout: 'border',
  	    defaults: {
  	        activeItem: 0
  	    },
  	    items: [{
  	        region: 'north',
  	        html: '<h1 class="x-panel-header">Page Title</h1>',
  	        autoHeight: true,
  	        border: false,
  	        margins: '0 0 5 0'
  	    }, {
  	        region: 'west',
  	        collapsible: true,
  	        title: 'Navigation',
  	        xtype: 'treepanel',
  	        width: 200,
  	        autoScroll: true,
  	        split: true,
  	        loader: new Ext.tree.TreeLoader(),
  	        root: new Ext.tree.AsyncTreeNode({
  	            expanded: true,
  	            children: [{
  	                text: 'Menu Option 1',
  	                leaf: true
  	            }, {
  	                text: 'Menu Option 2',
  	                leaf: true
  	            }, {
  	                text: 'Menu Option 3',
  	                leaf: true
  	            }]
  	        }),
  	        rootVisible: false,
  	        listeners: {
  	            click: function(n) {
  	                Ext.Msg.alert('Navigation Tree Click', 'You clicked: "' + n.attributes.text + '"');
  	            }
  	        }
  	    }, {
  	        region: 'center',
  	        xtype: 'tabpanel',
  	        items: {
  	            title: 'Default Tab',
  	            html: 'The first tab\'s content. Others may be added dynamically'
  	        }
  	    }, {
  	        region: 'south',
  	        title: 'Information',
  	        collapsible: true,
  	        html: 'Information goes here',
  	        split: true,
  	        height: 100,
  	        minHeight: 100
  	    }]
  	});*/

});
  	
</script>
  
</head>



<body>
<div id="welcome">
    <div class="col">
        <div id="search">     	
        </div>
    </div>
    <div class="col-last">
        <div class="res-block">
            <div class="res-block-inner" id="align">
            </div>
        </div>
    </div>
</div>
</body>

</html>