/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:15
 * To change this template use File | Settings | File Templates.
 */

App.SystemOverseeRoute = Ember.Route.extend({
  model:function (params) {
    var self = this;
    var controller = self.controllerFor('systemOversee');
    return Promise.all([App.System.importDataStatus(), App.System.evaludateStatus()]).then(function (values) {
      console.log("model...");
      var self = this;
      controller.set('isImporting', values[0].isImporting);
      controller.set('isEvaluating', values[1].isEvaluating);
      return values;
    });
  },

  setupController:function (controller, model) {
    controller.set('model', model);
  }

});


App.SystemOverseeRoute.reopen({
  activate:function () {
    console.log("activate...");
    var self = this;
    var controller = self.controllerFor('systemOversee');
  },

  deactivate:function () {
    console.log("deactivate...");
    var self = this;
    var controller = self.controllerFor('systemOversee');
    controller.stop();
    controller.stop2();
  }

})