/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */

Ember.Application.initializer({
  name: "store",
  initialize: function(container, application) {
    console.log("initialize...");
  }
});

App = Ember.Application.create({
  LOG_TRANSITIONS: true, // basic logging of successful transitions
  LOG_TRANSITIONS_INTERNAL: true // detailed logging of all routing steps
});
