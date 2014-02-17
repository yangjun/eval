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
		
		return App.System.importDataStatus().then(function(data) {
			controller.set('isImporting', data.isImporting);
			
		});
		
		
	},

	setupController : function(controller, model) {
		controller.set('model', model);
		App.System.evaludateStatus().then(function(data) {
			controller.set('isEvaluating', data.isEvaluating);
		});
	}
	
}); 


App.SystemOverseeRoute.reopen({
  activate:function () {
    console.log("activate...");
    var self = this;
    var controller = self.controllerFor('systemOversee');
    console.log("controller = " + controller);
    //
  },

  deactivate:function () {
    console.log("deactivate...");
    var self = this;
    var controller = self.controllerFor('systemOversee');
    controller.stop();
  }

})