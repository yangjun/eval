/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 下午9:00
 * To change this template use File | Settings | File Templates.
 */

App.ErrorRoute = Ember.Route.extend({
  renderTemplate: function () {
    switch (this.get('controller.content.code')) {
      case 404 :
        return this.render('404');
      default :
        return this.render();
    }
  }
})
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.IndexRoute = Ember.Route.extend({
  model: function() {
//   throw new App.Error("Unable to find translation", 404);
  },
  setupController: function(controller, model) {
  }
//  redirect:function () {
//    this.transitionTo('users');
//  }
});
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 下午8:31
 * To change this template use File | Settings | File Templates.
 */

App.LoadingRoute = Ember.Route.extend({
  renderTemplate: function() {
    this.render('loading', {
      outlet: 'loading',
      into: 'application'
    });
  }
});