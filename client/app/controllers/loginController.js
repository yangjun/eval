/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */

App.LoginController = Ember.Controller.extend({
  needs: "application",
  application: function() {
    var application = this.get('controllers.application'); // instance of App.ApplicationController
    return application;
  },
  actions:{
    login:function () {
      var data = this.getProperties('username', 'password');
      var self = this;
      App.Auth.auth(data).then(function (response) {
        //todo
        if (response.success) {
          App.token = response;
          var auth = App.Auth.create(response);
          var application = self.application();
          application.set('auth', auth);
          var attemptedTransition = self.get('attemptedTransition');
          if (attemptedTransition) {
            attemptedTransition.retry();
            self.set('attemptedTransition', null);
          } else {
            // Redirect to by default.
            self.transitionToRoute('index');
          }
        }
      }, function (error) {
        //todo
      });
    }
  }
});