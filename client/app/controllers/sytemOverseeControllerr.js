/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.SystemOverseeController = Ember.ObjectController.extend({
  isImporting: false,	
  actions : {
		importData : function() {
			console.log("importData...");
			var self = this;
		
			//console.log("error:" + reason);
			self.set('isImporting',true);
			
			App.System.importData().then(function(data) {
				console.log("data:" + data);
			
			    self.start();
				
		}, function(reason) {
				console.log("reason:" + reason);
			   self.set('isImporting',false);
			   self.stop();
		});
				
		},
		
		evaluate : function() {
			var self = this;
			var controller = self.controllerFor('systemOversee');
			//console.log("error:" + reason);
			App.System.evaluate().then(function(data) {
			self.set('isImporting',true);
		});
			
			
		}
	},
	
	refresh: function() {
	    console.log("refresh...");
	    var _this = this;
		App.System.importDataStatus().then(function(data) {
				console.log("data:" + data);
				if(data.isImporting===false){
					//tishiwancheng
					
					//
					_this.stop();
				}
				_this.set('isImporting',data.isImporting);
		}, function(reason) {
				console.log("reason:" + reason);
			   _this.set('isImporting',false);
		})
	},
	
	start:function () {
    if (this.timer == undefined || this.timer === null) {
      this.timer = setInterval(this.onPull.bind(this), 1000);
    }
  },

  stop:function () {
    clearInterval(this.timer);
    this.timer = null;
  },

  onPull:function () {
    this.refresh();
  }
});