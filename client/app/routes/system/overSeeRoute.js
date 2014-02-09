/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.SystemOverseeRoute = Ember.Route.extend({
  model: function(params) {
    var self = this;
    var controller = self.controllerFor('quotaIntegrity');
    var quotas = [];
	
    return App.Quota.quotaIntegrity().then(function (data) {
     var value = Em.Object.create(data);
    return value;
     // data.item.forEach(function(quota) {
	//			var model = App.Quota.create(quota);
	//			quotas.addObject(model);
	//		})
			
     // return quotas;
    });
  },

 setupController: function(controller, model) {
      controller.set('model', model);
  }

	actions : {
		error : function(reason) {
			//console.log("error:" + reason);
		},

		useradd : function() {
			var self = this;
			var controller = self.controllerFor('userAdd');
			var user = controller.get("user");
			//var data = controller.getProperties('name', 'password', 'nickname', 'email', 'mobilephone', 'telephone');
			controller.set('isLoad', true);
			user.add().then(function(data) {
				controller.transitionToRoute("user");
				controller.set('isLoad', false);
			}, function(reason) {
				controller.set('isError', reason);
				controller.set('isLoad', false);
			});

		}
	}
});