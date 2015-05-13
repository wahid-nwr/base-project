
tx.data.formStore = Ext.extend(Ext.data.GroupingStore, {
	constructor: function(){
		tx.data.formStore.superclass.constructor.call(this, {
	        sortInfo:{field: 'time', direction: "ASC"},
	        groupField:'time',
	        formFilter: 'all',
	        reader: new Ext.data.JsonReader({
	            id: 'id',
				fields: tx.data.formEntry
	        })
	    });
		this.conn = tx.data.conn;
	    this.proxy = new Ext.sql.Proxy(tx.data.conn, 'entryForm', 'id', this);
	},
	
	applyFilter : function(filter){
    	if(filter !== undefined){
    		this.formFilter = filter;
    	}
        var value = this.formFilter;
        if(value == 'all'){
            return this.clearFilter();
        }
        return this.filterBy(function(item){
            return item.data.time === value;
        });
    },

    addform : function(data){
        this.suspendEvents();
        this.clearFilter();
        this.resumeEvents();
        this.loadData([data], true);
        this.suspendEvents();
        this.applyFilter();
        this.applyGrouping(true);
        this.resumeEvents();
        this.fireEvent('datachanged', this);
    },

	loadList: function(listId){
		var multi = Ext.isArray(listId);
		this.activeList = multi ? listId[0] : listId;
		this.suspendEvents();
        if(multi){
			var ps = [];
			for(var i = 0, len = listId.length; i < len; i++){
				ps.push('?');
			}
			this.load({
				params: {
					where: 'where listId in (' + ps.join(',') + ')',
					args: listId
				}
			});
		}else{
			this.load({params: {
				where: 'where listId = ?',
				args: [listId]
			}});
		}		
        this.applyFilter();
        this.applyGrouping(true);
        this.resumeEvents();
        this.fireEvent('datachanged', this);
	},
	
	removeList: function(listId){
		this.conn.execBy('delete from entryForm where id = ?', [listId]);
		this.reload();
	},
	removeForm: function(){
		this.conn.exec('delete from entryForm');
		this.reload();
	},
	
    prepareTable : function(){
        try{
        this.createTable({
            name: 'entryForm',
            key: 'id',
            fields: tx.data.form.prototype.fields
        });
        }catch(e){console.log(e);}
    },
		
	createform : function(firstName, lastName, company, email, time){
		if(!Ext.isEmpty(firstName)){
			var listId = '';
			alert(firstName);
			/*
			if(!Ext.isEmpty(lastName)){
				listId = tx.data.lists.addList(Ext.util.Format.htmlEncode(listText)).id;
			}else{
				listId = tx.data.lists.newList(false).id;
			}
			*/
            this.addform({
                formId: Ext.uniqueId(),
                firstName: Ext.util.Format.htmlEncode(firstName),
                lastName: lastName||'',
                company: company||'',
                email: email,
                time: time ||'' 
            });
        }
	},
	
	afterEdit : function(r){
        if(r.isModified(this.getGroupState())){
			this.applyGrouping();
		}
		//workaround WebKit cross-frame date issue
		fixDateMember(r.data, 'completedDate');
		fixDateMember(r.data, 'reminder');
		fixDateMember(r.data, 'dueDate');
		if(r.isModified('completed')){
			r.editing = true;
			r.set('completedDate', r.data.completed ? new Date() : '');
			r.editing = false;
		}
		tx.data.formStore.superclass.afterEdit.apply(this, arguments);
    },
	
	init : function(){
		tx.data.lists.load();
		this.load({
			callback: function(){
				// first time?
				if(this.getCount() < 1){
					Ext.Msg.confirm('Create forms?', 'Your database is currently empty. Would you like to insert some demo data?', 
						function(btn){
							if(btn == 'yes'){
								tx.data.lists.loadDemoLists();
								this.loadDemoforms();	
							}
						}, this);
				}
			},
			scope: this
		});
	},
	
	lookup : function(id){
		var formEntry;
		if(formEntry = this.getById(id)){
			return formEntry;
		}
		var data = this.proxy.table.lookup(id);
		if (data) {
			var result = this.reader.readRecords([data]);
			return result.records[0];
		}
		return null; 
	},
	
	/* This is used to laod some demo forms if the form database is empty */
	loadDemoforms: function(){
		var s = new Date();
		// hardcoded demo forms
		this.addform({formId: Ext.uniqueId(), title:'Update Ext 2.0 documentation', listId:'ext2', description:'', dueDate: s.add('d', 21), completed: false, reminder: ''});
		this.addform({formId: Ext.uniqueId(), title:'Release Ext 2.l Beta 1', listId:'ext2', description:'', dueDate:s.add('d', 2), completed: false, reminder: s.add('d', 2).clearTime(true).add('h', 9)});
		this.addform({formId: Ext.uniqueId(), title:'Take wife to see movie', listId:'family', description:'', dueDate:s.add('d', 2), completed: false, reminder: ''});
		this.addform({formId: Ext.uniqueId(), title:'Finish Simple forms v2 sample app', listId:'ext2', description:'', dueDate:s.add('d', 2), completed: false, reminder: ''});
		this.addform({formId: Ext.uniqueId(), title:'Do something other than work', listId:'fun', description:'', dueDate:s.add('d', -1), completed: false, reminder: ''});
		this.addform({formId: Ext.uniqueId(), title:'Go to the grocery store', listId:'family', description:'', dueDate:s.add('d', -1), completed: true, reminder: '', completedDate: new Date()});
		this.addform({formId: Ext.uniqueId(), title:'Reboot my computer', listId:'personal-misc', description:'', dueDate:s, completed: false, reminder: ''});
		this.addform({formId: Ext.uniqueId(), title:'Respond to emails', listId:'work-misc', description:'', dueDate:s, completed: true, reminder: '', completedDate: new Date()});
	}
});