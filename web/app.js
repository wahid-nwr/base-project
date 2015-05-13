var ApiPanel;
var MainPaanel;
var DocPanel;
var isFirstLoad = true;
Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext', './Common/JavaScript/ext-4.1.0/src');
Ext.require(['Ext.data.proxy.Ajax','Ext.tree.Panel','Ext.XTemplate'],
function(){
// moved here for air
var resultTpl = new Ext.XTemplate(
	    '<tpl for=".">',
	    '<div class="search-item">',
	        
	        '<a class="member" ext:cls="{cls}" ext:member="{member}" href="output/{cls}.html">',
			'<img src="./Common/JavaScript/resources/s.gif" class="item-icon icon-{type}"/>{member}',
			'</a> ',
			'<a class="cls" ext:cls="{cls}" href="output/{cls}.html">{cls}</a>',
	        '<p>{doc}</p>',
	        
		    '<a class="member" ext:cls="{infoid}Panel" ext:member="{infoid}Panel" href="javascript:loadClassManually(\'{infoid}'+'Panel'+'\')">',
			'<img src="./Common/JavaScript/resources/s.gif" class="item-icon icon-{infoid}"/>{infoid}',
			'</a> ',
			'<a class="cls" ext:cls="{infoid}Panel" href="javascript:loadClassManually(\'{infoid}'+'Panel'+'\')">{infoid}</a>',
	    	'<p>{infodetail}</p>',
	    '</div></tpl>'
	); 
	var areaComboTpl= {}; new Ext.XTemplate(
			'<tpl for="."><div ext:qtip="{description}" class="x-combo-list-item" id="zone">{zone_name}</div></tpl>'
		);
		var branchComboTpl = new Ext.XTemplate(
			'<tpl for="."><div ext:qtip="{description}" class="x-combo-list-item" id="area">{area_name}</div></tpl>'
		);
		var centerComboTpl = new Ext.XTemplate(
			'<tpl for="."><div ext:qtip="{description}" class="x-combo-list-item" id="branch">{branch_name}</div></tpl>'
		);
		var loaneeComboTpl = new Ext.XTemplate(
			'<tpl for="."><div ext:qtip="{description}" class="x-combo-list-item" id="center">{center_name}</div></tpl>'
		); 
		/*
	var myMask = new Ext.MessageBox({
			   msg: 'Saving hot country data, please wait...',
			   progressText: 'Saving...',
			   width:300,
			   wait:true,
			   waitConfig: {interval:200},
			   //icon:'ext-mb-download', //custom class in msg-box.html
			   animEl: 'docs'
		   });
			setTimeout(function(){
				//This simulates a long-running operation like a database save or XHR call.
				//In real code, this would be in a callback function.
				Ext.MessageBox.hide();
				//Ext.example.msg('Done', 'Your fake data was saved!');
			}, 8000);
			apidocs
	*/
		/* 
	var ApiPanel = function() {
		var settingsTreeStore = Ext.create('Ext.data.TreeStore', {
		    root: {
		        expanded: true,
		        children: Docs.classData
		    }
		});
		ApiPanel.superclass.constructor.call(this, {
			id:'api-tree',
	        region:'west',
	        split:true,
	        width: 280,
	        minSize: 175,
	        maxSize: 500,
	        collapsible: true,
	        margins:'0 0 5 5',
	        cmargins:'0 0 0 0',
	        rootVisible:false,
	        lines:false,
	        autoScroll:true,
	        //animCollapse:false,
	        //animate: false,
	        //collapseMode:'mini',
		    //title: 'Admin Control Panel',
		    //renderTo: Ext.getBody(),
		    height: 300,
		    width: 300,
		    store: settingsTreeStore,
		    rootVisible: false
		});
		this.getSelectionModel().on('beforeselect', function(sm, node){
			//alert('node.isLeaf()::'+node.isLeaf());
	        return node.isLeaf();
	    });
	}; */
	//alert('b4 api panel');
	ApiPanel = function()
	{
		//alert('creating tree::'+Docs.classData);
		
		var settingsTreeStore = Ext.create('Ext.data.TreeStore', {
		    root: {
		        expanded: true,
		        children: Docs.classData
		    }
		});
		
		var treePanel = Ext.create('Ext.tree.Panel',{
			alias:'ApiPanel',
		constructor: function() {			
	    },
	    //extend: 'Ext.tree.Panel',	    
	    id:'api-tree',
        region:'west',
        split:true,
        width: 280,
        minSize: 175,
        maxSize: 500,
        collapsible: true,
        margins:'0 0 5 5',
        cmargins:'0 0 0 0',
        rootVisible:false,
        lines:false,
        autoScroll:true,        
        animCollapse:false,
        animate: false,
        collapseMode:'mini',
	    title: 'Admin Control Panel',
	    //renderTo: Ext.getBody(),
	    height: 300,
	    width: 300,
	    store: settingsTreeStore,
	    rootVisible: false,
	    /* store:Ext.create('Ext.data.TreeStore', {
		    root: {
		        expanded: true,
		        children: Docs.classData
		    }
	    }), */
	    selectClass : function(cls,tp,main){
	    	//alert('cls::'+cls+' tp::'+tp+' main::'+main);
	    	if(cls){
				var tab = main.getComponent('docs-'+cls);
				if(tab){
					if (cls.indexOf('Panel') > -1) {
					}
					var detail = cls.substr(cls.lastIndexOf('-')+1,cls.indexOf('Panel'));
					var ct = Ext.get(detail+'Detail');
					var form = Ext.get(detail+'AddForm');
					if(ct==null && form==null)
					{
						thePanel = loadClassManually(cls,tab,main);
					}
					var parts = cls.split('.');
					this.selectPath('/root/apidocs/'+parts.join('/'));
				}
		        else if (cls.indexOf('Panel') > -1) {
					thePanel = loadClassManually(cls,null,null);
					var oldId = thePanel.id;
					main.setActiveTab(thePanel);
				}
	        }
	    }	
	});
		
		return treePanel;
	}
	//alert('b 4 doc panel')
	DocPanel = function(config)
	{
		
		var docPanel = Ext.define('DocPanel',{
		requires:['ApiPanel'],
		closable: true,
	    autoScroll:true, 
		config:config,
		constructor:function(cfg) {
			this.initConfig(cfg);
			this.callParent(arguments);
		},
		   
	    initComponent : function(){
	    	var tclass = '';
	    	//alert('this.cclass::'+this.cclass);
	    	if(this.cclass!='unextended')
	    	{
	    		tclass = this.cclass;
	    	}
	    	if(tclass!=null && tclass!='' && tclass!='unextended' && tclass.indexOf('.'>-1))
	    	{
	    	//alert('tclass::'+tclass)
	        var ps = tclass.split('.');
	        //this.title = ps[ps.length-1];
	        this.setTitle(ps[ps.length-1]);
	    	}
			//air.trace("title::"+this.title);
	        this.superclass.initComponent.call(this);
	        
	    },

	    scrollToMember : function(member){
	        var el = Ext.fly(this.cclass + '-' + member);
			//air.trace("elllllll::"+" member::"+member);
	        if(el){
	            var top = (el.getOffsetsTo(this.body)[1]) + this.body.dom.scrollTop;
	            this.body.scrollTo('top', top-25, {duration:.75, callback: this.hlMember.createDelegate(this, [member])});
	        }
	    },

		scrollToSection : function(id){
			//air.trace("id::"+id);
			var el = Ext.getDom(id);
			if(el){
				var top = (Ext.fly(el).getOffsetsTo(this.body)[1]) + this.body.dom.scrollTop;
				this.body.scrollTo('top', top-25, {duration:.5, callback: function(){
	                Ext.fly(el).next('h2').pause(.2).highlight('#8DB2E3', {attr:'color'});
	            }});
	        }
		},

	    hlMember : function(member){	    	
	        var el = Ext.fly(this.cclass + '-' + member);
	        if(el){
	            el.up('tr').highlight('#cadaf9');
	        }
	    }
	});
	if(isFirstLoad)
	{
		isFirstLoad = false;
		docPanel = new DocPanel(config);
	}
	return docPanel;
}
	/* Ext.extend(ApiPanel, Ext.widget.treepanel, {
	    selectClass : function(cls,tp,main){
	    	if(cls){
				var tab = main.getComponent('docs-'+cls);
				if(tab){
					if (cls.indexOf('Panel') > -1) {
					}
					var detail = cls.substr(cls.lastIndexOf('-')+1,cls.indexOf('Panel'));
					var ct = Ext.get(detail+'Detail');
					var form = Ext.get(detail+'AddForm');
					if(ct==null && form==null)
					{
						thePanel = loadClassManually(cls,tab,main);
					}
					var parts = cls.split('.');
					this.selectPath('/root/apidocs/'+parts.join('/'));
				}
		        else if (cls.indexOf('Panel') > -1) {
					thePanel = loadClassManually(cls,null,null);
					var oldId = thePanel.id;
					main.setActiveTab(thePanel);
				}
	        }
	    }
	});
 */
		/* DocPanel = Ext.extend(Ext.widget.Panel, {
	    closable: true,
	    autoScroll:true,    
	    initComponent : function(){
	    	var tclass = '';
	    	if(this.cclass!='unextendd')
	    	{
	    		tclass = this.cclass;
	    	}
	    	if(tclass!=null && tclass!='' && tclass!='unextendd' && tclass.indexOf('.'>-1))
	    	{
	    	//alert('tclass::'+tclass)
	        var ps = tclass.split('.');
	        this.title = ps[ps.length-1];
	    	}
			//air.trace("title::"+this.title);
	        DocPanel.superclass.initComponent.call(this);
	        
	    },

	    scrollToMember : function(member){
	        var el = Ext.fly(this.cclass + '-' + member);
			//air.trace("elllllll::"+" member::"+member);
	        if(el){
	            var top = (el.getOffsetsTo(this.body)[1]) + this.body.dom.scrollTop;
	            this.body.scrollTo('top', top-25, {duration:.75, callback: this.hlMember.createDelegate(this, [member])});
	        }
	    },

		scrollToSection : function(id){
			//air.trace("id::"+id);
			var el = Ext.getDom(id);
			if(el){
				var top = (Ext.fly(el).getOffsetsTo(this.body)[1]) + this.body.dom.scrollTop;
				this.body.scrollTo('top', top-25, {duration:.5, callback: function(){
	                Ext.fly(el).next('h2').pause(.2).highlight('#8DB2E3', {attr:'color'});
	            }});
	        }
		},

	    hlMember : function(member){
	        var el = Ext.fly(this.cclass + '-' + member);
	        if(el){
	            el.up('tr').highlight('#cadaf9');
	        }
	    }
	}); */
	MainPanel = function()
	{
		return Ext.create('Ext.tab.Panel',{
			constructor:function(){},
			//extend: 'Ext.panel.Panel',  
			id:'doc-body',
	        region:'center',
	        margins:'0 5 5 0',
	        resizeTabs: true,
	        minTabWidth: 135,
	        tabWidth: 135,
	        //plugins: new Ext.ux.TabCloseMenu(),
	        enableTabScroll: true,
	        activeTab: 0,

	        items: {
	        	xtype:'panel',
	            id:'welcome-panel',
	            title: 'Demo Home',
	            layout:'fit',
	            autoLoad: {url: './Common/PageLayout/welcome.html', callback: this.initSearch, scope: this},
	            iconCls:'icon-docs',
	            autoScroll: true,
				tbar: [
					'Search: ', ' ' /*,
	                new Ext.ux.SelectBox({
	                    listClass:'x-combo-list-small',
	                    width:90,
	                    value:'Starts with',
	                    id:'search-type',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['text'],
	                        expandData: true,
	                        data : ['Starts with', 'Ends with', 'Any match']
	                    }),
	                    displayField: 'text'
	                }), ' ',
	                new Ext.SearchField({
		                width:240,
						store: this.searchStore,
						paramName: 'q'
		            }) */
	            ]
	        },
	        initEvents : function(){
		       /*  MainPanel.superclass.initEvents.call(this);
		        this.body.on('click', this.onClick, this); */
		    },

		    onClick: function(e, target){
				//air.trace("e::"+e+" target::"+target)
				 
			       
		    	//alert("e::"+e+" target::"+target);
		        if(target = e.getTarget('a:not(.exi)', 3)){
		            var cls = Ext.fly(target).getAttributeNS('ext', 'cls');
		            e.stopEvent();
		            if(cls){
		                var member = Ext.fly(target).getAttributeNS('ext', 'member');
		                this.loadClass(target.href, cls, member);
		            }else if(target.className == 'inner-link'){
		                this.getActiveTab().scrollToSection(target.href.split('#')[1]);
		            }else{
		                window.open(target.href);
		            }
		        }else if(target = e.getTarget('.micon', 2)){
		            e.stopEvent();
		            var tr = Ext.fly(target.parentNode);
		            if(tr.hasClass('expandable')){
		                tr.toggleClass('expanded');
		            }
		        }
		    },

		    loadClass : function(href, cls, member){
				
				//alert("href::"+href+" cls::"+cls+" member::"+member)
				var detail = cls.substr(cls.lastIndexOf('-')+1,cls.indexOf('Panel'));
		    	var id = 'docs-' + detail;
				//alert("id:::::::::::::::::::::::::::::::::::::::::::::"+id);
		        var tab = this.getComponent(id);
				//alert("component id::"+tab);
				var thePanel=null;
				
		        if(tab){
					if (id.indexOf('Panel') > -1) {
						//myMask.show();				
						Ext.Msg.progress("Wait","Loading form");				
						thePanel = Ext.getCmp(id);
						this.setActiveTab(thePanel);
						Ext.Msg.hide();				
					}
		            if(member){
						Ext.Msg.progress('Wait', 'Reading file...');
		                tab.scrollToMember(member);
		                Ext.Msg.hide();
		            }
		        }else{
					
		            //var autoLoad = {url: href};
		            if(member){
		               /* autoLoad.callback = function(){
		                    Ext.getCmp(id).scrollToMember(member);
		                }*/			
		            }
					//alert('id::'+id+' detail::'+detail+' Docs.icons[detail]::'+Docs.icons[detail]);
		            var p = this.add(new DocPanel({
		                id: id,
		                cclass : detail,
		                title:detail,
		                closable:true,
		                autoScroll:true,
		               // autoLoad: autoLoad,
		                iconCls: Docs.icons[detail]
		            }));
					//Ext.Msg.progress('Wait', 'Reading file...');
					//alert('if tab no');
					this.setActiveTab(p);		
		        }
		    },
			
			initSearch : function(){
				// Custom rendering Template for the View
			    
				
				var p = new Ext.DataView({
		            applyTo: 'search',
					tpl: resultTpl,
					loadingText:'Searching...',
		            store: this.searchStore,
		            itemSelector: 'div.search-item',
					emptyText: '<h3>Use the search field above to search the Ext API for classes, properties, config options, methods and events.</h3>'
		        });
			},
			
			doSearch : function(e){
				var k = e.getKey();
				if(!e.isSpecialKey()){
					var text = e.target.value;
					if(!text){
						this.searchStore.baseParams.q = '';
						this.searchStore.removeAll();
					}else{
						this.searchStore.baseParams.q = text;
						this.searchStore.reload();
					}
				}
			}
		});
		/* var main = Ext.create('Ext.panel.Panel',{
			extend:'MainPanel',
			alias:'main',
			constructor:function(){}
		    
		}); */
	}
		
	/* 
	MainPanel = function(){
		
		this.searchStore = new Ext.data.Store({
	        proxy: new Ext.data.HttpProxy({
	           // url: 'http://extjs.com/playpen/api.php'
	        	url: 'infoAction.csmp?method=promptExtInfoSearchSystemLevel'
	        }),
	        //reader: new Ext.data.JsonReader({root: 'data'},['cls', 'member', 'type', 'doc']),
	        reader: new Ext.data.XmlReader({
	            // records will have an "Item" tag
	            record: 'Item',
	            id: 'componentId',
	            totalRecords: 'TotalResults'
	        }, [
	            // set up the fields mapping into the xml doc
	            // The first needs mapping, the others are very basic
	            {name: 'infoid', mapping: 'ItemAttributes > infoid'},
	            'infodetail','componentId'
	        ]),
			baseParams: {},
	        listeners: {
	            'beforeload' : function(){
	                this.baseParams.qt = Ext.getCmp('search-type').getValue();
	            }
	        }
	    }); 
		
	    MainPanel.superclass.constructor.call(this, {
	        id:'doc-body',
	        region:'center',
	        margins:'0 5 5 0',
	        resizeTabs: true,
	        minTabWidth: 135,
	        tabWidth: 135,
	        //plugins: new Ext.ux.TabCloseMenu(),
	        enableTabScroll: true,
	        activeTab: 0,

	        items: {
	            id:'welcome-panel',
	            title: 'Demo Home',
	            autoLoad: {url: './Common/PageLayout/welcome.html', callback: this.initSearch, scope: this},
	            iconCls:'icon-docs',
	            autoScroll: true,
				tbar: [
					'Search: ', ' ',
	                new Ext.ux.SelectBox({
	                    listClass:'x-combo-list-small',
	                    width:90,
	                    value:'Starts with',
	                    id:'search-type',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['text'],
	                        expandData: true,
	                        data : ['Starts with', 'Ends with', 'Any match']
	                    }),
	                    displayField: 'text'
	                }), ' ',
	                new Ext.app.SearchField({
		                width:240,
						store: this.searchStore,
						paramName: 'q'
		            })
	            ]
	        }
	    });
	}; */
	//alert('b4 mainpanel');
	//var main=Ext.extend(MainPanel, Ext.widget.tabpanel, {
	

	
	/*
	Ext.SearchField =  Ext.define('Ext.ux.form.SearchField', {
	    extend: 'Ext.form.field.Trigger',
	    
	    alias: 'widget.searchfield',//Ext.extend(Ext.widget.triggerfield, {
	    initComponent : function(){
	        if(!this.store.baseParams){
				this.store.baseParams = {};
			}
			Ext.SearchField.superclass.initComponent.call(this);
			this.on('specialkey', function(f, e){
	            if(e.getKey() == e.ENTER){
	                this.onTrigger2Click();
	            }
	        }, this);
	    },

	    validationEvent:false,
	    validateOnBlur:false,
	    trigger1Class:'x-form-clear-trigger',
	    trigger2Class:'x-form-search-trigger',
	    hideTrigger1:true,
	    width:180,
	    hasSearch : false,
	    paramName : 'query',

	    onTrigger1Click : function(){
	        if(this.hasSearch){
	            this.store.baseParams[this.paramName] = '';
				this.store.removeAll();
				this.el.dom.value = '';
	            this.triggers[0].hide();
	            this.hasSearch = false;
				this.focus();
	        }
	    },

	    onTrigger2Click : function(){
	        var v = this.getRawValue();
	        if(v.length < 1){
	            this.onTrigger1Click();
	            return;
	        }
			if(v.length < 2){
				Ext.Msg.alert('Invalid Search', 'You must enter a minimum of 2 characters to search the API');
				return;
			}
			this.store.baseParams[this.paramName] = v;
	        var o = {start: 0};
	        this.store.reload({params:o});
	        this.hasSearch = true;
	        this.triggers[0].show();
			this.focus();
	    }
	});*/

	/**
	 * Makes a ComboBox more closely mimic an HTML SELECT.  Supports clicking and dragging
	 * through the list, with item selection occurring when the mouse button is released.
	 * When used will automatically set {@link #editable} to false and call {@link Ext.Element#unselectable}
	 * on inner elements.  Re-enabling editable after calling this will NOT work.
	 *
	 * @author Corey Gilmore
	 * http://extjs.com/forum/showthread.php?t=6392
	 *
	 * @history 2007-07-08 jvs
	 * Slight mods for Ext 2.0
	 */
	 
	/*Ext.ux.SelectBox  = function(config){
		this.searchResetDelay = 1000;
		config = config || {};
		config = Ext.apply(config || {}, {
			editable: false,
			forceSelection: true,
			rowHeight: false,
			lastSearchTerm: false,
	        triggerAction: 'all',
	        mode: 'local'
	    });

		//Ext.ux.SelectBox.superclass.constructor.apply(this, arguments);

		this.lastSelectedIndex = this.selectedIndex || 0;
	};
	Ext.define('Ext.form.ComboBox',{
	//Ext.extend(Ext.ux.SelectBox, Ext.form.ComboBox, {
		extend:'Ext.ux.SelectBox',
	    lazyInit: false,
		initEvents : function(){
			Ext.ux.SelectBox.superclass.initEvents.apply(this, arguments);
			// you need to use keypress to capture upper/lower case and shift+key, but it doesn't work in IE
			this.el.on('keydown', this.keySearch, this, true);
			this.cshTask = new Ext.util.DelayedTask(this.clearSearchHistory, this);
		},

		keySearch : function(e, target, options) {
			var raw = e.getKey();
			var key = String.fromCharCode(raw);
			var startIndex = 0;

			if( !this.store.getCount() ) {
				return;
			}

			switch(raw) {
				case Ext.EventObject.HOME:
					e.stopEvent();
					this.selectFirst();
					return;

				case Ext.EventObject.END:
					e.stopEvent();
					this.selectLast();
					return;

				case Ext.EventObject.PAGEDOWN:
					this.selectNextPage();
					e.stopEvent();
					return;

				case Ext.EventObject.PAGEUP:
					this.selectPrevPage();
					e.stopEvent();
					return;
			}

			// skip special keys other than the shift key
			if( (e.hasModifier() && !e.shiftKey) || e.isNavKeyPress() || e.isSpecialKey() ) {
				return;
			}
			if( this.lastSearchTerm == key ) {
				startIndex = this.lastSelectedIndex;
			}
			this.search(this.displayField, key, startIndex);
			this.cshTask.delay(this.searchResetDelay);
		},

		onRender : function(ct, position) {
			this.store.on('load', this.calcRowsPerPage, this);
			Ext.ux.SelectBox.superclass.onRender.apply(this, arguments);
			if( this.mode == 'local' ) {
				this.calcRowsPerPage();
			}
		},

		onSelect : function(record, index, skipCollapse){
			if(this.fireEvent('beforeselect', this, record, index) !== false){
				this.setValue(record.data[this.valueField || this.displayField]);
				if( !skipCollapse ) {
					this.collapse();
				}
				this.lastSelectedIndex = index + 1;
				this.fireEvent('select', this, record, index);
			}
		},

		render : function(ct) {
			Ext.ux.SelectBox.superclass.render.apply(this, arguments);
			if( Ext.isSafari ) {
				this.el.swallowEvent('mousedown', true);
			}
			this.el.unselectable();
			this.innerList.unselectable();
			this.trigger.unselectable();
			this.innerList.on('mouseup', function(e, target, options) {
				if( target.id && target.id == this.innerList.id ) {
					return;
				}
				this.onViewClick();
			}, this);

			this.innerList.on('mouseover', function(e, target, options) {
				if( target.id && target.id == this.innerList.id ) {
					return;
				}
				this.lastSelectedIndex = this.view.getSelectedIndexes()[0] + 1;
				this.cshTask.delay(this.searchResetDelay);
			}, this);

			this.trigger.un('click', this.onTriggerClick, this);
			this.trigger.on('mousedown', function(e, target, options) {
				e.preventDefault();
				this.onTriggerClick();
			}, this);

			this.on('collapse', function(e, target, options) {
				Ext.getDoc().un('mouseup', this.collapseIf, this);
			}, this, true);

			this.on('expand', function(e, target, options) {
				Ext.getDoc().on('mouseup', this.collapseIf, this);
			}, this, true);
		},

		clearSearchHistory : function() {
			this.lastSelectedIndex = 0;
			this.lastSearchTerm = false;
		},

		selectFirst : function() {
			this.focusAndSelect(this.store.data.first());
		},

		selectLast : function() {
			this.focusAndSelect(this.store.data.last());
		},

		selectPrevPage : function() {
			if( !this.rowHeight ) {
				return;
			}
			var index = Math.max(this.selectedIndex-this.rowsPerPage, 0);
			this.focusAndSelect(this.store.getAt(index));
		},

		selectNextPage : function() {
			if( !this.rowHeight ) {
				return;
			}
			var index = Math.min(this.selectedIndex+this.rowsPerPage, this.store.getCount() - 1);
			this.focusAndSelect(this.store.getAt(index));
		},

		search : function(field, value, startIndex) {
			field = field || this.displayField;
			this.lastSearchTerm = value;
			var index = this.store.find.apply(this.store, arguments);
			if( index !== -1 ) {
				this.focusAndSelect(index);
			}
		},

		focusAndSelect : function(record) {
			var index = typeof record === 'number' ? record : this.store.indexOf(record);
			this.select(index, this.isExpanded());
			this.onSelect(this.store.getAt(record), index, this.isExpanded());
		},

		calcRowsPerPage : function() {
			if( this.store.getCount() ) {
				//this.rowHeight = Ext.fly(this.view.getNode(0)).getHeight();
				this.rowsPerPage = this.maxHeight / this.rowHeight;
			} else {
				this.rowHeight = false;
			}
		}

	});  */

	Ext.Ajax.on('requestcomplete', function(ajax, xhr, o){
		//air.trace(ajax+xhr+o)
		//alert('ajax complete1'+o.url);
	    if(typeof urchinTracker == 'function' && o && o.url){
	    	//alert('ajax complete');
	        urchinTracker(o.url);
	    }
	});
});
function populateDocs()
{
	//alert('in populate');
Ext.onReady(function(){
	//alert('in populate ready');
    Ext.QuickTips.init();

    var api = new ApiPanel();
    //api.setTitle('General Menu');
    var mainPanel = new MainPanel();

    /*api.on('select', function(node, e){
    	alert('node::'+node)
    	
    	 * this.getSelectionModel().on('beforeselect', function(sm, node){
				//alert('node.isLeaf()::'+node.isLeaf());
		        return node.isLeaf();
		    });
    	 
    	alert('clicked1'+node.isLeaf());
         if(node.isLeaf()){
        	 alert('clicked');
            e.stopEvent();
            mainPanel.loadClass(node.attributes.href, node.id);
         }
    });*/
    
    api.on('itemClick', function(api,record,item,index,e,eOpts){
    	/*alert('record::'+record.id);
    	alert('item::'+item);
    	alert('index::'+index);
    	alert('e::'+e);
    	alert('eOpts::'+eOpts);*/
    	//this.getSelectionModel().on('beforeselect', function(sm, node){
				//alert('node.isLeaf()::'+node.isLeaf());
		//        return node.isLeaf();
		//    });
    	
    	//alert('clicked1'+record.isLeaf());
         if(record.isLeaf()){
        	// alert('clicked');
            e.stopEvent();
            mainPanel.loadClass('', record.id);
         }
    });

    mainPanel.on('tabchange', function(tp, tab){
		//air.trace("tab.cclass::"+tab.cclass+" this is tp:::::"+tp.id+" tab id::"+tab.id);
		//this.loadClass('area.html',tab.id);
        api.selectClass(tab.cclass,tp,this); 
    }); 
	//api.on('selectClass',)

    var hd = Ext.create('Ext.panel.Panel',{
    	id:'headtoolbar',
        border: false,
        layout:'anchor',
        region:'north',
        cls: 'docs-header',
        height:88,
        items: [{
            xtype:'box',
            el:'header',
            border:false,
            anchor: 'none -25'
        	},
        Ext.create('Ext.toolbar.Toolbar',{
            cls:'top-toolbar',
            items:[ ' ',
            Ext.create('Ext.form.TextField',{
				width: 200,
				emptyText:'Find a Form',
				listeners:{
					render: function(f){
						f.el.on('keydown', filterTree, f, {buffer: 350});
					}
				}
			}), ' ', ' ',
			{
                iconCls: 'icon-expand-all',
				tooltip: 'Expand All',
                handler: function(){ 
                	api.getRootNode().expand(true); 
                }
            }, '-', {
                iconCls: 'icon-collapse-all',
                tooltip: 'Collapse All',
                handler: function(){
                	api.getRootNode().collapse(true); 
                }
            }
            /*, '->', {
                tooltip:'Hide Inherited Members',
                iconCls: 'icon-hide-inherited',
                enableToggle: true,
                toggleHandler : function(b, pressed){
                     mainPanel[pressed ? 'addClass' : 'removeClass']('hide-inherited');
                }
            }, '-', {
                tooltip:'Expand All Members',
                iconCls: 'icon-expand-members',
                enableToggle: true,
                toggleHandler : function(b, pressed){
                    mainPanel[pressed ? 'addClass' : 'removeClass']('full-details');
                }
            }*/]
        })]
    })

    var mailMenu = Ext.create('Ext.Panel',{
    		title: 'Mail Menu',
    		autoScroll:true,
    		id:'mailMenu',
    		listeners: {
		        expand: function() {		        	
		        	var myMask = new Ext.LoadMask(Ext.getCmp('menuPanel'), {msg:"Please wait! Loading mail panel..."});
		        	myMask.show();
		        	Ext.ux.ScriptMgr.load({
		                scripts : ['./Common/JavaScript/config-js/mailbox.js'],
		                callback : function() {
		                	updateBoxes();
		                }
		        		});
		        	myMask.hide();
		        }
		    },
			items: [{					
					border: false,
					contentEl:'menu',
					iconCls: 'nav'
					}]
    	});
    var viewport = Ext.create('Ext.Viewport',{
        layout:'border',
        items:[ hd, 
                Ext.create('Ext.Panel',{
                	region: 'west',
        			id: 'menuPanel',        			
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
                	items:[api,mailMenu]      
                }),mainPanel]
    });

    //api.expandPath('/root/apidocs');
    api.getRootNode().on('expand',function(){
    	Ext.ux.ScriptMgr.load({
            scripts : ['./Common/JavaScript/config-js/ext-config.js'],
            callback : function() {
            	//updateBoxes();
            	console.log('done');
            }
    		});
    	Ext.ux.ScriptMgr.load({
            scripts : ['./Common/JavaScript/config-js/load.js'],
            callback : function() {
            	//updateBoxes();
            	console.log('done');
            }
    		});
    	Ext.ux.ScriptMgr.load({
            scripts : ['./Common/JavaScript/config-js/PickerWindow.js'],
            callback : function() {
            	//updateBoxes();
            	console.log('done');
            }
    		});
    });

    // allow for link in
    var page = window.location.href.split('?')[1];
    if(page){
        var ps = Ext.urlDecode(page);
        var cls = ps['class'];
		//air.trace("'output/' + cls + '.html'::"+'output/' + cls + '.html'+" ps.member::"+ps.member);
        
        //recently closed
        //mainPanel.loadClass('output/' + cls + '.html', cls, ps.member);
    }
    
    viewport.doLayout();
	
	setTimeout(function(){
        Ext.get('loading').remove();
        Ext.get('loading-mask').fadeOut({remove:true});
    }, 250);
	
	var hiddenPkgs = [];
	/*var filter = new Ext.tree.TreeFilter(api, {
		clearBlank: true,
		autoClear: true
	});*/
	
	function filterTree(e){
		var text = e.target.value;
		//air.trace("text::"+text);
		Ext.each(hiddenPkgs, function(n){
			n.ui.show();
		});
		if(!text){
			//filter.clear();
			return;
		}
		api.expandAll();
		
		var re = new RegExp('^' + Ext.escapeRe(text), 'i');
		/*filter.filterBy(function(n){
			return !n.attributes.isClass || re.test(n.text);
		});*/
		
		// hide empty packages that weren't filtered
		hiddenPkgs = [];
		//alert(api.getRootNode());
		//api.root.cascade(function(n){
		api.getRootNode().cascade(function(n){
			/*alert(n.toString());
			alert('n::'+n);
			alert(n.id);
			alert("isClass::"+n.isClass);
			alert(n.get('cls'));*/
			/*if(!n.attributes.isClass && n.ui.ctNode.offsetHeight < 3){
				n.ui.hide();
				hiddenPkgs.push(n);
			}*/
		});
	}
	if(window.nativeWindow!=null)
	{
		window.nativeWindow.visible = true;
	}
	
});

}
