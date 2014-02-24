/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.CountAreaController = Ember.ObjectController.extend({
	//selectedEntityEnum : {},
	data : {},
	version : {},

	init : function() { isLoading:false
	},

	selectedEntity : function() {
		this.refresh1();
	}.property('version'),

	refresh1 : function() {

		this.set("isLoading", true);
		var self = this;
	//	var entityID = self.get('selectedEntityEnum.key');
	//	console.log("entityID = " + entityID );
		var promise = App.Count.findSingleresById('GZ', self.get("version.id"), 2);
		promise.then(function(data) {
			//var now = new Date();
			//console.log("开始 = " + now);
			var model = App.Count.procsingleres(data);
			//var now1 = new Date();
			//console.log("结束 = " + now1);
			self.set('data', model);
			self.set("isLoading", false);
		});
	}
});
