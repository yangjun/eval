/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.CountSingleresRoute = Ember.Route.extend({
	model : function(params) {
		var self = this;
		var controller = self.controllerFor('countSingleres');
		var entitys = [];
		//version : {},
		//entityID : {},
		App.Count.findEntityEnum().then(function(data) {
			data.forEach(function(entity) {
				var model = App.Count.create(entity);
				entitys.addObject(model);
			})
		});
		return entitys;
		
	},

	setupController : function(controller, model) {
		controller.set('model', model);
	}
	//  redirect:function () {
	//    console.log("transitionTo count.overview");
	//    this.transitionTo('count.overview');
	//  }
}); 