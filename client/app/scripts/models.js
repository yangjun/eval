// Ajax
App.Ajax = Ember.Object.extend({
});

App.Ajax.reopenClass({
  genUrl:function (url) {
    var value = App.BASE_URL + url;
    var isToken = App.Auth.isAuth();
    if (isToken) {
      if (value.indexOf("?") > 0) {
        value = value + "&token=" + App.token;
      } else {
        value = value + "?token=" + App.token;
      }
    }
    return value;
  },

  put:function (url, data) {
    var self = this;
    var ajax = Ember.$.ajax({
      type:"PUT",
      url:self.genUrl(url),
      data:JSON.stringify(data),
      contentType:"application/json"
    });

    return ajax;
  },

  get:function (url) {
    var self = this;
    var ajax = Ember.$.getJSON(self.genUrl(url));
    return ajax;
  },

  post:function (url, data) {
    var self = this;
    var ajax = Ember.$.ajax({
      type:"POST",
      url:self.genUrl(url),
      data:JSON.stringify(data),
      contentType:"application/json"
    });
    return ajax;
  },

  delete:function (url) {
    var self = this;
    var ajax = Ember.$.ajax({
      type:"DELETE",
      url:self.genUrl(url),
      contentType:"application/json"
    });
    return ajax;
  }

});

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
    var url = 'rs/auth/auth.json';
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
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */

// count

App.Count = Ember.Object.extend({
});

App.Count.reopenClass({
  /**
   * 总体质量
   */
  overview:function () {
    var url = 'rs/count/overview.json';
    // promise
    return App.Ajax.get(url);
  },
  
  /**
   * 总体趋势
   */
  overtrend:function () {
    var url = 'rs/count/overtrend.json';
    // promise
    return App.Ajax.get(url);
  },
  
  /**
   * 单维度
   */
  dimensions:function () {
    var url = 'rs/count/dimensions.json';
    // promise
    return App.Ajax.get(url);
  }
});
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */

App.Error = function(message, code) {
  this.name = 'Error';
  this.message = message;
  this.code = code;
  this.stack = (new Error()).stack;
}

App.Error.prototype = new Error;
