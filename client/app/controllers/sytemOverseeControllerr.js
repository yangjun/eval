/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.SystemOverseeController = Ember.Controller.extend({
	isImporting : false,
	ImportOK : false,
	isEvaluating : false,
	EvalOK : false,
	actions : {
		importData : function() {
			console.log("importData...");
			var self = this;

			//console.log("error:" + reason);
			self.set('isImporting', true);
			self.set('ImportOK', false);
			self.set('EvalOK', false);
			App.System.importData().then(function(data) {
				console.log("data:" + data);
				self.start();
			}, function(reason) {
				console.log("reason:" + JSON.stringify(reason));
				self.set('isImporting', true);
				self.start();
				//self.stop();
			});

		},

		evaluate : function() {
			console.log("evaluate...");
			var self = this;
			self.set('isEvaluating', true);

			self.set('ImportOK', false);
			self.set('EvalOK', false);
			
			App.System.evaluate().then(function(data) {
				console.log("data:" + data);
				self.start2();
			}, function(reason) {
				console.log("reason:" + JSON.stringify(reason));
				self.set('isEvaluating', true);
				self.start2();
				//self.stop();
			});
		}
	},

	refresh : function() {
		//console.log("refresh...");
		var _this = this;
		App.System.importDataStatus().then(function(data) {
			//console.log("data:" + JSON.stringify(data));
			if (data.isImporting === false) {
				//tishiwancheng
				_this.set('ImportOK', true);
				//
				_this.stop();
			}
			_this.set('isImporting', data.isImporting);
		}, function(reason) {
			console.log("reason:" + reason);
			_this.set('isImporting', false);
		})
	},

	start : function() {
		if (this.timer == undefined || this.timer === null) {
			this.timer = setInterval(this.onPull.bind(this), 1000);
		}
	},

	stop : function() {
		clearInterval(this.timer);
		this.timer = null;
	},

	onPull : function() {
		this.refresh();
	},
	//
	//
	//下面为评测所用
	refresh2 : function() {
		//console.log("refresh...");
		var _this = this;
		App.System.evaludateStatus().then(function(data) {
			//console.log("data:" + JSON.stringify(data));
			if (data.isEvaluating === false) {
				//tishiwancheng
_this.set('EvalOK', true);
				//
				_this.stop2();
			}
			_this.set('isEvaluating', data.isEvaluating);
		}, function(reason) {
			console.log("reason:" + reason);
			_this.set('isEvaluating', false);
		})
	},

	start2 : function() {
		if (this.timer == undefined || this.timer === null) {
			this.timer = setInterval(this.onPull2.bind(this), 1000);
		}
	},

	stop2 : function() {
		clearInterval(this.timer);
		this.timer = null;
	},

	onPull2 : function() {
		this.refresh2();
	}
});
