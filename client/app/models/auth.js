/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */

// auth

App.Auth = Ember.Object.extend({
});

App.Auth.reopenClass({
  /**
   * 是否认证
   */
  isAuth:function () {
    return !(App.token == undefined || App.token === '')
  }

});