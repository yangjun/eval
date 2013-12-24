Ember.TEMPLATES["application"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', hashTypes, hashContexts, escapeExpression=this.escapeExpression;


  hashTypes = {};
  hashContexts = {};
  data.buffer.push(escapeExpression(helpers._triageMustache.call(depth0, "outlet", {hash:{},contexts:[depth0],types:["ID"],hashContexts:hashContexts,hashTypes:hashTypes,data:data})));
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
  


  data.buffer.push("<!-- Docs master nav -->\r\n<header class=\"navbar navbar-inverse navbar-fixed-top bs-docs-nav\" role=\"banner\">\r\n    <div class=\"container\">\r\n        <div class=\"navbar-header\">\r\n            <button class=\"navbar-toggle\" type=\"button\" data-toggle=\"collapse\" data-target=\".bs-navbar-collapse\">\r\n                <span class=\"sr-only\">Toggle navigation</span>\r\n                <span class=\"icon-bar\"></span>\r\n                <span class=\"icon-bar\"></span>\r\n                <span class=\"icon-bar\"></span>\r\n            </button>\r\n            <a href=\"./\" class=\"navbar-brand\">Bootstrap</a>\r\n        </div>\r\n        <nav class=\"collapse navbar-collapse bs-navbar-collapse\" role=\"navigation\">\r\n            <ul class=\"nav navbar-nav\">\r\n                <li>\r\n                    <a href=\"./getting-started\">Getting started</a>\r\n                </li>\r\n                <li>\r\n                    <a href=\"./css\">CSS</a>\r\n                </li>\r\n                <li>\r\n                    <a href=\"./components\">Components</a>\r\n                </li>\r\n                <li>\r\n                    <a href=\"./javascript\">JavaScript</a>\r\n                </li>\r\n                <li>\r\n                    <a href=\"./customize\">Customize</a>\r\n                </li>\r\n            </ul>\r\n            <ul class=\"nav navbar-nav navbar-right\">\r\n                <li>\r\n                    <a href=\"./about\">About</a>\r\n                </li>\r\n            </ul>\r\n        </nav>\r\n    </div>\r\n</header>");
  
});

Ember.TEMPLATES["index"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {
this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};
  var buffer = '', stack1, stack2, hashTypes, hashContexts, options, helperMissing=helpers.helperMissing, escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  
  data.buffer.push(" Go to the users page ");
  }

  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.partial || depth0.partial),stack1 ? stack1.call(depth0, "head", options) : helperMissing.call(depth0, "partial", "head", options))));
  data.buffer.push("\r\n<main class=\"bs-masthead\" id=\"content\" role=\"main\">\r\n    <div class=\"container\">\r\n      ");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},inverse:self.noop,fn:self.program(1, program1, data),contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  stack2 = ((stack1 = helpers['link-to'] || depth0['link-to']),stack1 ? stack1.call(depth0, "users", options) : helperMissing.call(depth0, "link-to", "users", options));
  if(stack2 || stack2 === 0) { data.buffer.push(stack2); }
  data.buffer.push("\r\n    </div>\r\n</main>\r\n");
  hashTypes = {};
  hashContexts = {};
  options = {hash:{},contexts:[depth0],types:["STRING"],hashContexts:hashContexts,hashTypes:hashTypes,data:data};
  data.buffer.push(escapeExpression(((stack1 = helpers.partial || depth0.partial),stack1 ? stack1.call(depth0, "footer", options) : helperMissing.call(depth0, "partial", "footer", options))));
  return buffer;
  
});