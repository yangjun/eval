/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.CountIndexRoute = Ember.Route.extend({
//  model: function() {
//  },
//  setupController: function(controller, model) {
//  },
  redirect:function () {
    console.log("transitionTo count.overview");
    this.transitionTo('count.overview');
  }
});