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
        '<p class="repo-name">{{name}}</p>',
        '<p class="repo-description">{{description}}</p>'
      ].join(''),
      header: '<h3 class="league-name">name</h3>',
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
    return this.typeahead.val(this.get("selection.id"));
  }.observes("selection"),

  willInsertElement:function () {
    console.log("TypeAheadComponent willInsertElement");
  }

})