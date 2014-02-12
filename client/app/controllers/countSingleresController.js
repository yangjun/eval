/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.CountSingleresController = Ember.ObjectController.extend({
	selectedEntityEnum : {},
	data : {},
	version : {},
	entityID : {},
	
	selectedEntity : function() {
		this.refresh1();
		return this.get("data");
	}.property('selectedEntityEnum', 'version'),

	refresh1 : function() {
		console.log("refresh ...");
		var self = this;
		//var entityID = self.get('selectedEntityEnum.key');
		entityID = self.get('selectedEntityEnum.key');
		console.log("entityID = " + entityID);
		var result = App.Count.findSingleresById('GZ', self.get("version.id"), entityID);
		result.then(function(data) {
			var model = App.Count.procsingleres(data);
			self.set('data', model);
		});
	}
});
