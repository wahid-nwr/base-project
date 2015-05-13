/*
 * http://extjs.com/forum/showthread.php?t=37897
 * 
 * Sample : 
 * 
ScriptMgr.load({
  scripts: ['/js/other-prerequisite.js', '/js/other.js'],
  callback: function() {
    var other = new OtherObject();
    alert(other); //just loaded
  }
}); 
 */

Ext.namespace('Ext.ux');

Ext.ux.ScriptLoader = function() {
	//alert('LOAF=DING');
    this.timeout = 30;
    this.scripts = [];
};

Ext.ux.ScriptLoader.prototype = {
    showMask: function() {
      Ext.MessageBox.wait('Loading...');
    },

    hideMask: function() {
      Ext.MessageBox.hide();
    },

    processSuccess: function(response,opts,callback) {
      //this.scripts[response.argument.url] = true;
    	//response;
    	//alert('response.url::'+opts.argument.url);
    	//alert('response.url::'+opts.argument.callback);
      this.scripts[opts.argument.url] = true;
      //alert('exec::'+(window.execScript));
      if (window.execScript) {
        window.execScript(response.responseText);
      } else {
        window.eval(response.responseText);
      }
      //this.hideMask();
      //if (typeof response.argument.callback == 'function') {
      //alert('typeof callback::'+(typeof opts.argument.callback));
      if (typeof opts.argument.callback == 'function') {
        //response.argument.callback.call(response.argument.scope);
    	  //alert('response.scope::'+opts.argument.scope);
    	  opts.argument.callback();
      }
    },

    processFailure: function(response) {
      Ext.MessageBox.show({title: 'Application Error', msg: 'Script library could not be loaded.', buttons: Ext.MessageBox.OK, icon: Ext.MessageBox.ERROR, minWidth: 200});
    },

    load: function(url, callback) {
    
    var cfg, callerScope;
    
      if (typeof url == 'object') { // must be config object
          cfg = url;
          url = cfg.url;
          //alert('url::'+url);
          callback = callback || cfg.callback;
          //alert('in load::::::'+callback);
          callerScope = cfg.scope;
          //alert('in callerScope::'+callerScope);
          if (typeof cfg.timeout != 'undefined') {
            this.timeout = cfg.timeout;
          }
      }

      if (this.scripts[url]) {
    	  //alert('is loaded');
        if (typeof callback == 'function') {
        	//alert('callback'+(typeof callback));
          callback.call(callerScope || window);
        }
        return null;
      }

      //this.showMask();
      //alert('in load'+callback);
      //alert('url::'+url);
      //alert('typeof url:::'+(typeof url));
      Ext.Ajax.request({
          url:url,
          success: this.processSuccess,
          failure: this.processFailure,
          scope: this,
          timeout: (this.timeout*1000),
          argument: {
            'url': url,
            'scope': callerScope || window,
            'callback': callback
          }
      });
    }
};

Ext.ux.ScriptLoaderMgr = function() {
    this.loader = new Ext.ux.ScriptLoader();

    this.load = function(o) {
    	//alert('script loadeer');
      if (!Ext.isArray(o.scripts)) {
        o.scripts = [o.scripts];
      }

      o.url = o.scripts.shift(o.scripts);

      if (o.scripts.length == 0) {
        this.loader.load(o);
      } else {
        o.scope = this;
        this.loader.load(o, function() {
          this.load(o);
        });
      }
    };
};

Ext.ux.ScriptMgr = new Ext.ux.ScriptLoaderMgr();