App.TypeAheadComponent = Ember.TextField.extend({
  didInsertElement:function () {
    this._super();
    var _this = this;
    this.initializeTypeahead(this.get("url"), this.get('template'));
  },

  initializeTypeahead:function (url, template) {
    var _this = this;
    this.typeahead = this.$().typeahead({
      name: _this.$().attr('id') || "typeahead",
      prefetch: url,
      remote: '../data/q?k=%QUERY',
      limit: 5,
      template: [
        '<p>{{name}}</p>'
      ].join(''),
      header: '<p>版本</p>',
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
      this.set('value', this.get("selection.name"));
    }
  },

  selectionObserver:function () {
    var value = this.get("selection.name");
    if (Em.isEmpty(value) === true) {
      return this.typeahead.val('');
    }
    return this.typeahead.val(value);
    this.set('value', value);
  }.observes("selection"),

  willInsertElement:function () {
    console.log("TypeAheadComponent willInsertElement");
  }

})