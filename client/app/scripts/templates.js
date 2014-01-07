Ember.TEMPLATES["404"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', hashTypes, hashContexts, escapeExpression=this.escapeExpression;


  data.buffer.push("<h2>404</h2>\r\n");
  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers._triageMustache.call(depth0, "App.Error.message", {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("\r\nSorry bra ,we couldn't find that.");
  return buffer;
  
});

Ember.TEMPLATES["application"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, hashTypes, hashContexts, options, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression;


  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.partial || (depth0 && depth0.partial)),stack1 ? stack1.call(depth0, "head", options) : helperMissing.call(depth0, "partial", "head", options))));
  data.buffer.push("\r\n<div class=\"container\">\r\n  ");
  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers._triageMustache.call(depth0, "outlet", {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("\r\n  ");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.outlet || (depth0 && depth0.outlet)),stack1 ? stack1.call(depth0, "loading", options) : helperMissing.call(depth0, "outlet", "loading", options))));
  data.buffer.push("\r\n</div>\r\n");
  return buffer;
  
});

Ember.TEMPLATES["chart"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["components/hbar-chart"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["components/line-chart"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["components/radar-chart"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["count"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, stack2, hashContexts, hashTypes, options, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = '', stack1, hashContexts, hashTypes, options;
  data.buffer.push("<a ");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "STRING"};
  options = {hash:{
    'href': ("view.href")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.bindAttr || (depth0 && depth0.bindAttr)),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "bindAttr", options))));
  data.buffer.push("\r\n                                      href=\"#\">总体质量</a>");
  return buffer;
  }

function program3(depth0,data) {
  
  var buffer = '', stack1, hashContexts, hashTypes, options;
  data.buffer.push("<a ");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "STRING"};
  options = {hash:{
    'href': ("view.href")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.bindAttr || (depth0 && depth0.bindAttr)),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "bindAttr", options))));
  data.buffer.push("\r\n                                      href=\"#\">维度资源评测</a>");
  return buffer;
  }

function program5(depth0,data) {
  
  var buffer = '', stack1, hashContexts, hashTypes, options;
  data.buffer.push("<a ");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "STRING"};
  options = {hash:{
    'href': ("view.href")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.bindAttr || (depth0 && depth0.bindAttr)),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "bindAttr", options))));
  data.buffer.push("\r\n                                      href=\"#\">单项资源总体质量</a>");
  return buffer;
  }

function program7(depth0,data) {
  
  var buffer = '', stack1, hashContexts, hashTypes, options;
  data.buffer.push("<a ");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "STRING"};
  options = {hash:{
    'href': ("view.href")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.bindAttr || (depth0 && depth0.bindAttr)),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "bindAttr", options))));
  data.buffer.push("\r\n                                    href=\"#\">总体质量趋势</a>");
  return buffer;
  }

function program9(depth0,data) {
  
  var buffer = '', stack1, hashContexts, hashTypes, options;
  data.buffer.push("<a ");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "STRING"};
  options = {hash:{
    'href': ("view.href")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.bindAttr || (depth0 && depth0.bindAttr)),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "bindAttr", options))));
  data.buffer.push("\r\n                                    href=\"#\">单项维度质量趋势</a>");
  return buffer;
  }

function program11(depth0,data) {
  
  var buffer = '', stack1, hashContexts, hashTypes, options;
  data.buffer.push("<a ");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "STRING"};
  options = {hash:{
    'href': ("view.href")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.bindAttr || (depth0 && depth0.bindAttr)),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "bindAttr", options))));
  data.buffer.push("\r\n                                    href=\"#\">单项资源总体质量趋势</a>");
  return buffer;
  }

function program13(depth0,data) {
  
  var buffer = '', stack1, hashContexts, hashTypes, options;
  data.buffer.push("<a ");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "STRING"};
  options = {hash:{
    'href': ("view.href")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.bindAttr || (depth0 && depth0.bindAttr)),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "bindAttr", options))));
  data.buffer.push("\r\n                                    href=\"#\">单项资源单项维度质量趋势</a>");
  return buffer;
  }

  data.buffer.push("<div class=\"row\">\r\n    <div class=\"col-md-3\">\r\n\r\n        <!--折叠开始-->\r\n        <div class=\"accordion\" id=\"accordion2\">\r\n            <div class=\"accordion-group\">\r\n                <div class=\"accordion-heading\">\r\n                    <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-parent=\"#accordion2\"\r\n                            href=\"#collapseOne\">\r\n                        数据总体质量评价\r\n                    </button>\r\n                </div>\r\n                <div id=\"collapseOne\" class=\"accordion-body collapse\">\r\n                    <div class=\"accordion-inner\">\r\n                        <div class=\"btn-group-vertical\">\r\n                            <ul>\r\n                              ");
  hashContexts = {'tagName': depth0,'href': depth0};
  hashTypes = {'tagName': "STRING",'href': "BOOLEAN"};
  options = {hash:{
    'tagName': ("li"),
    'href': (false)
  },inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  stack2 = ((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "count.overview", options) : helperMissing.call(depth0, "link-to", "count.overview", options));
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("\r\n                              ");
  hashContexts = {'tagName': depth0,'href': depth0};
  hashTypes = {'tagName': "STRING",'href': "BOOLEAN"};
  options = {hash:{
    'tagName': ("li"),
    'href': (false)
  },inverse:self.noop,fn:self.program(3, program3, data),contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  stack2 = ((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "count.dimensions", options) : helperMissing.call(depth0, "link-to", "count.dimensions", options));
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("\r\n                              ");
  hashContexts = {'tagName': depth0,'href': depth0};
  hashTypes = {'tagName': "STRING",'href': "BOOLEAN"};
  options = {hash:{
    'tagName': ("li"),
    'href': (false)
  },inverse:self.noop,fn:self.program(5, program5, data),contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  stack2 = ((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "count", options) : helperMissing.call(depth0, "link-to", "count", options));
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("\r\n                            </ul>\r\n\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class=\"accordion-group\">\r\n                <div class=\"accordion-heading\">\r\n                    <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-parent=\"#accordion2\"\r\n                            href=\"#collapseTwo\">\r\n                        数据质量趋势\r\n                    </button>\r\n                </div>\r\n                <div id=\"collapseTwo\" class=\"accordion-body collapse\">\r\n                    <div class=\"accordion-inner\">\r\n                        <div class=\"btn-group-vertical\">\r\n                            <ul>");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "BOOLEAN"};
  options = {hash:{
    'href': (false)
  },inverse:self.noop,fn:self.program(7, program7, data),contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  stack2 = ((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "count.overtrend", options) : helperMissing.call(depth0, "link-to", "count.overtrend", options));
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("</ul>\r\n                            <ul>");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "BOOLEAN"};
  options = {hash:{
    'href': (false)
  },inverse:self.noop,fn:self.program(9, program9, data),contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  stack2 = ((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "count", options) : helperMissing.call(depth0, "link-to", "count", options));
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("</ul>\r\n                            <ul>");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "BOOLEAN"};
  options = {hash:{
    'href': (false)
  },inverse:self.noop,fn:self.program(11, program11, data),contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  stack2 = ((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "count", options) : helperMissing.call(depth0, "link-to", "count", options));
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("</ul>\r\n                            <ul>");
  hashContexts = {'href': depth0};
  hashTypes = {'href': "BOOLEAN"};
  options = {hash:{
    'href': (false)
  },inverse:self.noop,fn:self.program(13, program13, data),contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  stack2 = ((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "count", options) : helperMissing.call(depth0, "link-to", "count", options));
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("</ul>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <!--折叠结束-->\r\n    </div>\r\n    <div class=\"col-md-9\">\r\n      ");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.outlet || (depth0 && depth0.outlet)),stack1 ? stack1.call(depth0, "loading", options) : helperMissing.call(depth0, "outlet", "loading", options))));
  data.buffer.push("\r\n      ");
  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers._triageMustache.call(depth0, "outlet", {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("\r\n    </div>\r\n\r\n</div>\r\n\r\n");
  return buffer;
  
});

Ember.TEMPLATES["count/dimensions"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, hashTypes, hashContexts, options, escapeExpression=this.escapeExpression, helperMissing=helpers.helperMissing;


  data.buffer.push("<div class=\"row\">\r\n    <div class=\"col-md-10\">\r\n        <h1>");
  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers._triageMustache.call(depth0, "model.title", {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("</h1>\r\n      ");
  hashContexts = {'height': depth0,'content': depth0};
  hashTypes = {'height': "INTEGER",'content': "ID"};
  options = {hash:{
    'height': (500),
    'content': ("model")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['hbar-chart'] || (depth0 && depth0['hbar-chart'])),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "hbar-chart", options))));
  data.buffer.push("\r\n    </div>\r\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["count/index"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["count/overtrend"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, hashTypes, hashContexts, options, escapeExpression=this.escapeExpression, helperMissing=helpers.helperMissing;


  data.buffer.push("<div class=\"row\">\r\n    <div class=\"col-md-10\">\r\n        <h1>");
  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers._triageMustache.call(depth0, "model.title", {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("</h1>\r\n      ");
  hashContexts = {'height': depth0,'content': depth0};
  hashTypes = {'height': "INTEGER",'content': "ID"};
  options = {hash:{
    'height': (500),
    'content': ("model")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['line-chart'] || (depth0 && depth0['line-chart'])),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "line-chart", options))));
  data.buffer.push("\r\n    </div>\r\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["count/overview"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, hashTypes, hashContexts, options, escapeExpression=this.escapeExpression, helperMissing=helpers.helperMissing;


  data.buffer.push("<div class=\"row\">\r\n    <div class=\"col-md-10\">\r\n        <h1>");
  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers._triageMustache.call(depth0, "model.title", {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("</h1>\r\n      ");
  hashContexts = {'height': depth0,'content': depth0};
  hashTypes = {'height': "INTEGER",'content': "ID"};
  options = {hash:{
    'height': (500),
    'content': ("model")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['radar-chart'] || (depth0 && depth0['radar-chart'])),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "radar-chart", options))));
  data.buffer.push("\r\n    </div>\r\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["error"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, hashTypes, hashContexts, escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = '', hashTypes, hashContexts;
  data.buffer.push("\r\n  ");
  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers._triageMustache.call(depth0, "App.Error.message", {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("\r\n");
  return buffer;
  }

  hashTypes = {};
  hashContexts = {};
  stack1 = helpers['if'].call(depth0, "App.Error", {hash:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\r\n");
  return buffer;
  
});

Ember.TEMPLATES["footer"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  


  data.buffer.push("<!-- FOOTER -->\r\n<footer class=\"container\" role=\"contentinfo\">\r\n    <div id=\"carbonads-container\"><div class=\"carbonad\"><div id=\"azcarbon\"></div><script>var z = document.createElement(\"script\"); z.async = true; z.src = \"http://engine.carbonads.com/z/32341/azcarbon_2_1_0_HORIZ\"; var s = document.getElementsByTagName(\"script\")[0]; s.parentNode.insertBefore(z, s);</script></div></div>\r\n\r\n\r\n    <div class=\"bs-social\">\r\n        <ul class=\"bs-social-buttons\">\r\n            <li>\r\n                <iframe class=\"github-btn\" src=\"http://ghbtns.com/github-btn.html?user=twbs&amp;repo=bootstrap&amp;type=watch&amp;count=true\" width=\"100\" height=\"20\" title=\"Star on GitHub\"></iframe>\r\n            </li>\r\n            <li>\r\n                <iframe class=\"github-btn\" src=\"http://ghbtns.com/github-btn.html?user=twbs&amp;repo=bootstrap&amp;type=fork&amp;count=true\" width=\"102\" height=\"20\" title=\"Fork on GitHub\"></iframe>\r\n            </li>\r\n            <li class=\"follow-btn\">\r\n                <a href=\"https://twitter.com/twbootstrap\" class=\"twitter-follow-button\" data-link-color=\"#0069D6\" data-show-count=\"true\">Follow @twbootstrap</a>\r\n            </li>\r\n            <li class=\"tweet-btn\">\r\n                <a href=\"https://twitter.com/share\" class=\"twitter-share-button\" data-url=\"http://getbootstrap.com/\" data-count=\"horizontal\" data-via=\"twbootstrap\" data-related=\"mdo:Creator of Twitter Bootstrap\">Tweet</a>\r\n            </li>\r\n        </ul>\r\n    </div>\r\n\r\n\r\n    <ul class=\"bs-masthead-links\">\r\n        <li class=\"current-version\">\r\n            Currently v3.0.3\r\n        </li>\r\n        <li>\r\n            <a href=\"./2.3.2/\">Bootstrap 2.3.2 docs</a>\r\n        </li>\r\n        <li>\r\n            <a href=\"https://github.com/twbs/bootstrap\" onclick=\"_gaq.push(['_trackEvent', 'Jumbotron actions', 'Jumbotron links', 'GitHub project']);\">GitHub project</a>\r\n        </li>\r\n        <li>\r\n            <a href=\"./getting-started/#examples\" onclick=\"_gaq.push(['_trackEvent', 'Jumbotron actions', 'Jumbotron links', 'Examples']);\">Examples</a>\r\n        </li>\r\n        <li>\r\n            <a href=\"http://expo.getbootstrap.com\" onclick=\"_gaq.push(['_trackEvent', 'Jumbotron actions', 'Jumbotron links', 'Expo']);\">Expo</a>\r\n        </li>\r\n        <li>\r\n            <a href=\"http://blog.getbootstrap.com\" onclick=\"_gaq.push(['_trackEvent', 'Jumbotron actions', 'Jumbotron links', 'Blog']);\">Blog</a>\r\n        </li>\r\n    </ul>\r\n</footer>");
  
});

Ember.TEMPLATES["head"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, hashTypes, hashContexts, options, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression;


  data.buffer.push("<div class=\"navbar navbar-default navbar-fixed-top\">\r\n    <div class=\"container\">\r\n        <div class=\"navbar-header\">\r\n            <a href=\"#\" class=\"navbar-brand\">");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['t'] || (depth0 && depth0['t'])),stack1 ? stack1.call(depth0, "app.title", options) : helperMissing.call(depth0, "t", "app.title", options))));
  data.buffer.push("</a>\r\n            <button class=\"navbar-toggle\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar-main\">\r\n                <span class=\"icon-bar\"></span>\r\n                <span class=\"icon-bar\"></span>\r\n                <span class=\"icon-bar\"></span>\r\n            </button>\r\n        </div>\r\n        <div class=\"navbar-collapse collapse\" id=\"navbar-main\">\r\n          ");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["STRING","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.render || (depth0 && depth0.render)),stack1 ? stack1.call(depth0, "mainMenu", "mainMenu", options) : helperMissing.call(depth0, "render", "mainMenu", "mainMenu", options))));
  data.buffer.push("\r\n            <ul class=\"nav navbar-nav navbar-right\">\r\n                <li>");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["STRING","STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "XXX用户", "login", options) : helperMissing.call(depth0, "link-to", "XXX用户", "login", options))));
  data.buffer.push("</li>\r\n            </ul>\r\n        </div>\r\n    </div>\r\n</div>\r\n");
  return buffer;
  
});

Ember.TEMPLATES["index"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, hashTypes, hashContexts, options, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression;


  data.buffer.push("\r\n<div class=\"row\">\r\n    <div class=\"col-md-2\">\r\n        <div class=\"bs-sidebar\" role=\"complementary\">\r\n            <ul class=\"nav bs-sidenav\">\r\n                <li class=\"active\">xxxxxxx\r\n                    <ul class=\"nav\">\r\n                        <li class=\" active\">");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["STRING","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "login", "login", options) : helperMissing.call(depth0, "link-to", "login", "login", options))));
  data.buffer.push("</li>\r\n                        <li>");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["ID","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "menu.title", "menu.id", options) : helperMissing.call(depth0, "link-to", "menu.title", "menu.id", options))));
  data.buffer.push("</li>\r\n                        <li>");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["ID","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "menu.title", "menu.id", options) : helperMissing.call(depth0, "link-to", "menu.title", "menu.id", options))));
  data.buffer.push("</li>\r\n                        <li class=\" active\">");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["STRING","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "login", "login", options) : helperMissing.call(depth0, "link-to", "login", "login", options))));
  data.buffer.push("</li>\r\n                        <li class=\" active\">");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["STRING","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "login", "login", options) : helperMissing.call(depth0, "link-to", "login", "login", options))));
  data.buffer.push("</li>\r\n                    </ul>\r\n                </li>\r\n                <li>");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["STRING","STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "关于", "about", options) : helperMissing.call(depth0, "link-to", "关于", "about", options))));
  data.buffer.push("</li>\r\n                <li><a href=\"about\">menu.title</a></li>\r\n            </ul>\r\n        </div>\r\n    </div>\r\n    <div class=\"col-md-10\" role=\"main\">\r\n        <div class=\"row\">\r\n          ");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["STRING","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.render || (depth0 && depth0.render)),stack1 ? stack1.call(depth0, "map", "map", options) : helperMissing.call(depth0, "render", "map", "map", options))));
  data.buffer.push("\r\n        </div>\r\n        <div class=\"row\">\r\n            <div class=\"col-md-4\">\r\n              ");
  hashContexts = {'height': depth0,'content': depth0};
  hashTypes = {'height': "INTEGER",'content': "ID"};
  options = {hash:{
    'height': (300),
    'content': ("data")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['radar-chart'] || (depth0 && depth0['radar-chart'])),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "radar-chart", options))));
  data.buffer.push("\r\n            </div>\r\n            <div class=\"col-md-4\">\r\n              ");
  hashContexts = {'height': depth0,'content': depth0};
  hashTypes = {'height': "INTEGER",'content': "ID"};
  options = {hash:{
    'height': (300),
    'content': ("data1")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['hbar-chart'] || (depth0 && depth0['hbar-chart'])),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "hbar-chart", options))));
  data.buffer.push("\r\n            </div>\r\n            <div class=\"col-md-4\">\r\n              ");
  hashContexts = {'height': depth0,'content': depth0};
  hashTypes = {'height': "INTEGER",'content': "ID"};
  options = {hash:{
    'height': (300),
    'content': ("data2")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['line-chart'] || (depth0 && depth0['line-chart'])),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "line-chart", options))));
  data.buffer.push("\r\n            </div>\r\n        </div>\r\n    </div>\r\n\r\n</div>");
  return buffer;
  
});

Ember.TEMPLATES["loading"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  


  data.buffer.push("<h1>Loading</h1>");
  
});

Ember.TEMPLATES["login"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, stack2, hashTypes, hashContexts, options, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = '', stack1, hashTypes, hashContexts, options;
  data.buffer.push("\r\n              <i class=\"fa icon-spinner icon-spin fa-1x\"></i>");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['t'] || (depth0 && depth0['t'])),stack1 ? stack1.call(depth0, "app.loading.title", options) : helperMissing.call(depth0, "t", "app.loading.title", options))));
  data.buffer.push("\r\n          ");
  return buffer;
  }

  data.buffer.push("<h1>Login</h1>\r\n<form class=\"form-horizontal\">\r\n    <div class=\"control-group\">\r\n        <legend>\r\n          ");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['t'] || (depth0 && depth0['t'])),stack1 ? stack1.call(depth0, "login.title", options) : helperMissing.call(depth0, "t", "login.title", options))));
  data.buffer.push("\r\n        </legend>\r\n    </div>\r\n\r\n    <div class=\"control-group\">\r\n        <label class=\"control-label\" for=\"username\">");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['t'] || (depth0 && depth0['t'])),stack1 ? stack1.call(depth0, "login.username.title", options) : helperMissing.call(depth0, "t", "login.username.title", options))));
  data.buffer.push("</label>\r\n        <div class=\"controls\">\r\n          ");
  hashContexts = {'id': depth0,'value': depth0,'type': depth0,'placeholderTranslation': depth0};
  hashTypes = {'id': "STRING",'value': "ID",'type': "STRING",'placeholderTranslation': "STRING"};
  data.buffer.push(escapeExpression(helpers.view.call(depth0, "Ember.TextField", {hash:{
    'id': ("username"),
    'value': ("username"),
    'type': ("text"),
    'placeholderTranslation': ("login.username.placeholder")
  },contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("\r\n        </div>\r\n    </div>\r\n\r\n    <div class=\"control-group\">\r\n        <label class=\"control-label\" for=\"password\">");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['t'] || (depth0 && depth0['t'])),stack1 ? stack1.call(depth0, "login.password.title", options) : helperMissing.call(depth0, "t", "login.password.title", options))));
  data.buffer.push("</label>\r\n        <div class=\"controls\">\r\n          ");
  hashContexts = {'id': depth0,'value': depth0,'type': depth0,'placeholderTranslation': depth0};
  hashTypes = {'id': "STRING",'value': "ID",'type': "STRING",'placeholderTranslation': "STRING"};
  data.buffer.push(escapeExpression(helpers.view.call(depth0, "Ember.TextField", {hash:{
    'id': ("password"),
    'value': ("password"),
    'type': ("password"),
    'placeholderTranslation': ("login.password.placeholder")
  },contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push("\r\n        </div>\r\n    </div>\r\n\r\n    <div class=\"control-group\">\r\n        <div class=\"controls\">\r\n            <button class=\"btn btn-link\" ");
  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers.action.call(depth0, "login", {hash:{},contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
  data.buffer.push(" ");
  hashContexts = {'title': depth0};
  hashTypes = {'title': "STRING"};
  options = {hash:{
    'title': ("login.button.title")
  },contexts:[],types:[],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.translateAttr || (depth0 && depth0.translateAttr)),stack1 ? stack1.call(depth0, options) : helperMissing.call(depth0, "translateAttr", options))));
  data.buffer.push(">\r\n                <i class=\"fa fa-sign-in fa-2x\"></i>\r\n            </button>\r\n          ");
  hashTypes = {};
  hashContexts = {};
  stack2 = helpers['if'].call(depth0, "isLoad", {hash:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data});
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("\r\n        </div>\r\n    </div>\r\n</form>");
  return buffer;
  
});

Ember.TEMPLATES["mainMenu"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var stack1, hashTypes, hashContexts, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = '', stack1, hashTypes, hashContexts;
  data.buffer.push("\r\n    <li>\r\n      ");
  hashTypes = {};
  hashContexts = {};
  stack1 = helpers['if'].call(depth0, "param", {hash:{},inverse:self.program(4, program4, data),fn:self.program(2, program2, data),contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  data.buffer.push("\r\n    </li>\r\n");
  return buffer;
  }
function program2(depth0,data) {
  
  var buffer = '', stack1, hashTypes, hashContexts, options;
  data.buffer.push("\r\n        ");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0,depth0],types:["ID","ID","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "title", "name", "param", options) : helperMissing.call(depth0, "link-to", "title", "name", "param", options))));
  data.buffer.push("\r\n      ");
  return buffer;
  }

function program4(depth0,data) {
  
  var buffer = '', stack1, hashTypes, hashContexts, options;
  data.buffer.push("\r\n        ");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["ID","ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "title", "name", options) : helperMissing.call(depth0, "link-to", "title", "name", options))));
  data.buffer.push("\r\n      ");
  return buffer;
  }

  hashTypes = {};
  hashContexts = {};
  stack1 = helpers.each.call(depth0, "items", {hash:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data});
  if(stack1 || stack1 === 0) { data.buffer.push(stack1); }
  else { data.buffer.push(''); }
  
});

Ember.TEMPLATES["map"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '';


  return buffer;
  
});

Ember.TEMPLATES["users"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, hashTypes, hashContexts, options, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression;


  data.buffer.push("users\r\n");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0,depth0],types:["STRING","STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers['link-to'] || (depth0 && depth0['link-to'])),stack1 ? stack1.call(depth0, "首页", "index", options) : helperMissing.call(depth0, "link-to", "首页", "index", options))));
  return buffer;
  
});