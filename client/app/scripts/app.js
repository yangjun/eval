/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */


App = Ember.Application.create();

App.Router.map(function () {
  // put your routes here
});

App.IndexRoute = Ember.Route.extend({
  model:function () {
    return ['red', 'yellow', 'blue'];
  }

});

App.TypeAheadComponent = Ember.TextField.extend({
  didInsertElement:function () {
    this._super();
    var _this = this;

//    if (!this.get("data")) {
//      throw "No data property set";
//    }

//    if (jQuery.isFunction(this.get("data").then)) {
//      this.get("data").then(function (data) {
//        _this.initializeTypeahead(data);
//      });
//    } else {
//      this.initializeTypeahead(this.get("data"));
//    }

//    }

    this.initializeTypeahead(this.get("url"), this.get('template'));
  },

  initializeTypeahead:function (url, template) {
    var _this = this;
    this.typeahead = this.$().typeahead({
      name: 'twitter-oss',
      prefetch: url,
      limit: 3,
      template: [
        '<p class="repo-language">{{language}}</p>',
        '<p class="repo-name">{{name}}</p>',
        '<p class="repo-description">{{description}}</p>'
      ].join(''),
      header: '<h3 class="league-name">language</h3>',
      engine: Hogan
    });

    this.typeahead.on("typeahead:selected", function (event, item) {
      _this.set("selection", item);
    });

    this.typeahead.on("typeahead:autocompleted", function (event, item) {
      _this.set("selection", item);
    });

    if (this.get("selection")) {
      this.typeahead.val(this.get("selection.name"));
    }
  },

  selectionObserver:function () {
    return this.typeahead.val(this.get("selection.name"));
  }.observes("selection"),

  willInsertElement:function () {
    console.log("TypeAheadComponent willInsertElement");
  },

  willDestroyElement:function () {
    console.log("TypeAheadComponent willDestroyElement");
  }
});