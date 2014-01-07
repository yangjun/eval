/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.CountSingleresRoute = Ember.Route.extend({
  model: function(params) {
    var self = this;
    var controller = self.controllerFor('countSingleres');
    return App.Count.singleres().then(function (data) {
      var value = Em.Object.create(data);
      return value;
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