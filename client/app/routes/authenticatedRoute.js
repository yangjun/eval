/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */

// AuthenticatedRoute
App.AuthenticatedRoute = Ember.Route.extend({

  beforeModel:function (transition) {
    if (!this.controllerFor('login').get('token')) {
      this.redirectToLogin(transition);
    }
  },

  redirectToLogin:function (transition) {
    var loginController = this.controllerFor('login');
    loginController.set('attemptedTransition', transition);
    this.transitionTo('login');
  },

  getJSONWithToken:function (url) {
    var token = this.controllerFor('login').get('token');
    return $.getJSON(url, {
      token:token
    });
  },

  events:{
    error:function (reason, transition) {
      if (reason.status === 401) {
        this.redirectToLogin(transition);
      } else {
        alert('Something went wrong');
      }
    }
  },

  actions:{
    willTransition:function (transition) {
      console.log('transition.targetName = ' + transition.targetName);
    }
  }
});

