/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.QuotaIntegrityRoute = Ember.Route.extend({

	model : function(params) {
		var self = this;
		var controller = self.controllerFor('quotaIntegrity');
		var entitys = [];

		App.Count.findEntityEnum().then(function(data) {
			data.forEach(function(entity) {
				var model = App.Count.create(entity);
				entitys.addObject(model);
			})
			
			if (entitys.length > 0) {
				console.log("entitys[0] : " + JSON.stringify(entitys[0]));
				controller.set('selectedEntityEnum', entitys[0]);
			}
		});
		return entitys;
	},

	setupController : function(controller, model) {
		controller.set('model', model);
	}

	

}); 