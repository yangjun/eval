/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.CountStationController = Ember.ObjectController.extend({
	selectedEntityEnum : {},
	data : {},
	version : {},

	init : function() { isLoading:false
	},

	selectedEntity : function() {
		this.refresh1();
	}.property('selectedEntityEnum', 'version'),

	refresh1 : function() {

		this.set("isLoading", true);
		var self = this;
		var entityID = self.get('selectedEntityEnum.key');
		console.log("entityID = " + entityID);
		var promise = App.Count.findSingleresById('GZ', self.get("version.id"), entityID);
		promise.then(function(data) {
			var model = App.Count.procsingleres(data);
			self.set('data', model);
			self.set("isLoading", false);
		});
	}
});
