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

    processSuccess: function(response) {
      this.scripts[response.argument.url] = true;
      if (window.execScript) {
        window.execScript(response.responseText);
      } else {
        window.eval(response.responseText);
      }
      //this.hideMask();
      if (typeof response.argument.callback == 'function') {
        response.argument.callback.call(response.argument.scope);
      }
    },

    processFailure: function(response) {
      Ext.MessageBox.show({title: 'Application Error', msg: 'Script library could not be loaded.', buttons: Ext.MessageBox.OK, icon: Ext.MessageBox.ERROR, minWidth: 200});
    },

    load: function(url, callback) {
    	//alert('in load');
      var cfg, callerScope;
      if (typeof url == 'object') { // must be config object
          cfg = url;
          url = cfg.url;
          callback = callback || cfg.callback;
          callerScope = cfg.scope;
          if (typeof cfg.timeout != 'undefined') {
            this.timeout = cfg.timeout;
          }
      }

      if (this.scripts[url]) {
        if (typeof callback == 'function') {
          callback.call(callerScope || window);
        }
        return null;
      }

      //this.showMask();

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