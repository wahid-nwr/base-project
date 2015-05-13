<!--
/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
-->
<html>
<head>
<title>Hello World Window</title>
<link rel="stylesheet" type="text/css" href="./Common/JavaScript/ext-4.1.0/resources/css/ext-all.css"/>
<script type="text/javascript" src="./Common/JavaScript/ext-4.1.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="./Common/JavaScript/ext-4.1.0/ext-all.js"></script>
<script type="text/javascript" src="./js/jquery.js"></script>
<style type="text/css">
	#holder{
	 height:200px;
	 width:480px;
	 background-color:#F5F5F5;
	 border:1px solid #A4A4A4;
	 margin-left:10px;
	}
	 #place {
	 position:relative;
	 margin:7px;
	 }
     #place a{
	 font-size:0.6em;
	 }
     #place li
     {
         list-style: none outside none;
         position: absolute;
     }
     #place li:hover
     {
        background-color:yellow;
     }
	 #place .seat{
	 background:url("./images/available_seat_img.gif") no-repeat scroll 0 0 transparent;
	 height:33px;
	 width:33px;
	 display:block;
	 }
      #place .selectedSeat
      {
		background-image:url("./images/booked_seat_img.gif");
      }
	   #place .selectingSeat
      {
		background-image:url("./images/selected_seat_img.gif");
      }
	  #place .row-2, #place .row-3{
		margin-top:10px;
	  }
	 #seatDescription{
	 padding:0px;
	 }
	  #seatDescription li{
	  verticle-align:middle;
	  list-style: none outside none;
	  padding-left:35px;
	  height:35px;
	  float:left;
	  }
    </style>

</head>

<!-- Revised from demo code in ext3.0.0 -->
<body>
<form id="form1" runat="server">
	   <h2 style="font-size:1.2em;"> Choose seats by clicking the corresponding seat in the layout below:</h2>
       
	 <div style="width:580px;text-align:left;margin:5px">
		


    </form>
    <script type="text/javascript">
        $(function () {
            var settings = {
                rows: 4,
                cols: 10,
                rowCssPrefix: 'row-',
                colCssPrefix: 'col-',
                seatWidth: 45,
                seatHeight: 45,
                seatCss: 'seat',
                selectedSeatCss: 'selectedSeat',
				selectingSeatCss: 'selectingSeat'
            };
            var rowLetter = ['A','B','C','D','E','F','G','H','I','J'];

            var init = function (reservedSeat) {
                var str = [], seatNo, className,seatNum;
                for (i = 0; i < settings.rows; i++) {
                    for (j = 0; j < settings.cols; j++) {
                        seatNo = (i + j * settings.rows + 1);
                        seatNum = rowLetter[j] + ' '+(i+1);
                        className = settings.seatCss + ' ' + settings.rowCssPrefix + i.toString() + ' ' + settings.colCssPrefix + j.toString();
                        if ($.isArray(reservedSeat) && $.inArray(seatNo, reservedSeat) != -1) {
                            className += ' ' + settings.selectedSeatCss;
                        }
                        str.push('<li class="' + className + '"' +
                                  'style="top:' + (i * settings.seatHeight).toString() + 'px;left:' + (j * settings.seatWidth).toString() + 'px">' +
                                  '<a title="' + seatNo + '">' + seatNum + '</a>' +
                                  '</li>');
                    }
                }
                $('#place').html(str.join(''));
            };

            //case I: Show from starting
            //init();

            //Case II: If already booked
            var bookedSeats = [5, 10, 25];
            init(bookedSeats);


            $('.' + settings.seatCss).click(function () {
			if ($(this).hasClass(settings.selectedSeatCss)){
				alert('This seat is already reserved');
			}
			else{
                $(this).toggleClass(settings.selectingSeatCss);
				}
            });

            $('#btnShow').click(function () {
                var str = [];
                $.each($('#place li.' + settings.selectedSeatCss + ' a, #place li.'+ settings.selectingSeatCss + ' a'), function (index, value) {
                    str.push($(this).attr('title'));
                });
                alert(str.join(','));
            })

            $('#btnShowNew').click(function () {
                var str = [], item;
                $.each($('#place li.' + settings.selectingSeatCss + ' a'), function (index, value) {
                    item = $(this).attr('title');
                    str.push(item);
                });
                alert(str.join(','));
            })
        });

    </script>
<script type="text/javascript">
/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';
	var html = '<div style="width:600px;text-align:center;overflow:auto">'+
				'<ul id="seatDescription">'+
				'<li style="background:url(\'images/available_seat_img.gif\') no-repeat scroll 0 0 transparent;">Available Seat</li>'+
				'<li style="background:url(\'images/booked_seat_img.gif\') no-repeat scroll 0 0 transparent;">Booked Seat</li>'+
				'<li style="background:url(\'images/selected_seat_img.gif\') no-repeat scroll 0 0 transparent;">Selected Seat</li>'+
				'</ul></div>'+
				'<input type="button" id="btnShowNew" value="Show Selected Seats" /><input type="button" id="btnShow" value="Show All" /></div>';
    var bd = Ext.getBody();
    var form = Ext.create('Ext.FormPanel',{
    url:    'reservationAction.csmp?method=addreservation',
    id:     'reservationAddForm',
    layout: 'form',
    //renderTo:Ext.getBody(),
    frame: true,
    title: 'Add reservation',
    closable: true,
    autoScroll:true,
    onSubmit:function()
    {
    	alert('Are u sure?');
    },
    items: [
              Ext.create('Ext.form.FieldSet',{
                  title: 'reservation Information',
                  defaultType: 'textfield',
                  items: getReservationFieldSet()
              })
          ],
  		
          buttons: [{
              text: 'Save',
              scope: form,
              handler: function() {
              	form.getForm().submit({
                      success: function(f,a) {
                          Ext.Msg.alert('Success', 'Information saved');
                          //tabpanel.remove(0);
                          extjsWindow.close();
                          store.load();
                          //tabpanel.add(getreservationPanel());
                          //tabpanel.doLayout();                                       
                      },
                      failure: function(f,a){
                          Ext.Msg.alert('Warnning', 'Error occured in previous action');
                      }
                  });
              }
          },{
              text: 'Reset',
              scope: form,
              handler: function() {
                  Ext.getCmp('reservationAddForm').getForm().reset();                                
              }
          },{
          	text: 'Back',
          	scope: form,
          	handler: function(){
          		//tabpanel.remove(0);
          		window.close();
          		//store.load();                
                  //tabpanel.add(getreservationPanel());
                  //tabpanel.doLayout(); 
          	}
          }]
          
      });
	var window = new Ext.Window({
		id:'seatplan',
		width:600,
		height:500,
		html:'<div id="holder"><ul  id="place"> </ul></div>'+html,
		items:form.show()
	});
	window.show();
	function getReservationFieldSet()
	{
		var fieldSet = [{ xtype: 'hidden',name:'componentId', fieldLabel: 'componentId' }, 
		        { xtype: 'textfield',name:'code', fieldLabel: 'code' }, 
				{ xtype: 'textfield',name:'name', fieldLabel: 'name' },
				{ xtype: 'textfield',name:'parentName', fieldLabel: 'parent' ,disabled:true},
				{ xtype: 'hidden',name:'parent', fieldLabel: 'parent' ,readOnly:true}
				];
		/* example set
		[{ xtype: 'datefield',name:'date',fieldLabel: 'Date',emptyText:'Enter Date',readOnly:true},
	     { xtype: 'textfield',name:'code', fieldLabel: 'Code' },
	     { xtype: 'textarea',name:'desc', fieldLabel: 'Description',width:100 },
	     { xtype: 'textfield',name:'session', fieldLabel: 'Session' },
	     { xtype: 'textfield',name:'location', fieldLabel: 'Location' },
	     { xtype: 'textfield',name:'doctor', fieldLabel: 'Doctor Name' }]
		*/
		return fieldSet;
	}
	
});</script> 
<div id="bd"></div>
</body>
</html>