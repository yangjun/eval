/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */

App.MainMenuController = Ember.Controller.extend({
  // 配置一级菜单
  items:[
    {name:"login",
      title:"登录"
    },
    {name:"user",
      title:"用户",
      param:"2"}
  ]
});