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