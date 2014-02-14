/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.QuotaIntegrityController = Ember.ObjectController.extend({
	selectedEntityEnum : {},
	items : Em.A(),

	selectedEntity : function() {
		this.refresh();
	}.property('selectedEntityEnum'),

	refresh : function() {
		console.log("refresh ...");
		var self = this;
		var entityID = self.get('selectedEntityEnum.key');
		console.log(entityID);
		//todo clear items
		self.items.clear();


		App.Quota.findFieldByResType(entityID).then(function(data) {
			data.forEach(function(field) {
				var item = new App.CheckObject();

				item.set('evaluateItemID', field.evaluateItemID);
				item.set('propertyName', field.propertyName);
				item.set('propertyID', field.propertyID);
				item.set('typeName', field.typeName);
				item.set('typeID', field.typeID);
				if (field.evaluateItemID == null) {
					item.set('ischeck', false);
				} else {
					item.set('ischeck', true);
				}
				item.set('isLoading', false);
				self.items.pushObject(item);
			})
		});
	}
});
