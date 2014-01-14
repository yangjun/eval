/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.QuotaIntegrityRoute = Ember.Route.extend({
  model: function(params) {
    var self = this;
    var controller = self.controllerFor('quotaIntegrity');
    var quotas = [];
	
    return App.Quota.quotaIntegrity().then(function (data) {
      var value = Em.Object.create(data);
     
      data.item.forEach(function(quota) {
				var model = App.Quota.create(quota);
				quotas.addObject(model);
			})
      return quotas;
    });
  },

 setupController: function(controller, model) {
      controller.set('model', model);
  }

//  redirect:function () {
//    console.log("transitionTo count.overview");
//    this.transitionTo('count.overview');
//  }
});