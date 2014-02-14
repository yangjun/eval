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
    var _url = self.genUrl(url);
    //console.log("_url = " + _url);
    var ajax = Ember.$.ajax({
      type:"POST",
      dataType: 'json',
      url: _url,
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
