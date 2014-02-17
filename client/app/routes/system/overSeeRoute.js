/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.SystemOverseeRoute = Ember.Route.extend({
	model : function(params) {
		var self = this;
		var controller = self.controllerFor('systemOversee');
		self.set('isImporting',false);
	
	},

	setupController : function(controller, model) {
		controller.set('model', model);
	},

	actions : {
		error : function(reason) {
			//console.log("error:" + reason);
		},

		importData : function() {
			var self = this;
			var controller = self.controllerFor('systemOversee');
			console.log("error:" + reason);
			App.System.importData().then(function(data) {
			self.set('isImporting',true);
		});
			
			

		}
	}
}); 