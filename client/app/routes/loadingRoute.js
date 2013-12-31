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