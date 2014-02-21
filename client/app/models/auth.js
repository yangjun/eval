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
  },

  /**
   * 通过用户名和密码到服务端认证
   * @param data
   * @return {*} promise
   */
  auth: function(data) {
    
    	var url = "../data/rs/auth/auth.json";
    // promise
    return App.Ajax.get(url, data);
  },

  /**
   * 验证token是否有效
   * @param token
   * @return {*} promise
   */
  checkToken: function(token) {
    var url = 'rs/auth/'+ token;
    return App.Ajax.get(url);
  }
});