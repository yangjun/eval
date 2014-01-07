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


/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.CountDimensionsRoute = Ember.Route.extend({
  model: function(params) {
    var self = this;
    var controller = self.controllerFor('countDimensions');
    return App.Count.dimensions().then(function (data) {
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
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.CountOvertrendRoute = Ember.Route.extend({
  model: function(params) {
    var self = this;
    var controller = self.controllerFor('countOvertrend');
    return App.Count.overtrend().then(function (data) {
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
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.CountOverviewRoute = Ember.Route.extend({
  model: function(params) {
    var self = this;
    var controller = self.controllerFor('countOverview');
    return App.Count.overview().then(function (data) {
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