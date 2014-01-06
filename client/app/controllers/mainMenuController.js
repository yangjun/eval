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
    {name:"count.index",
      title:"评测统计"
    },
   /**	{name:"norm",
      title:"标准数据管理"
    },
    {name:"quota",
      title:"评测指标管理"
    },
    
    {name:"system",
      title:"系统管理"
    },**/
    
    {name:"user",
      title:"菜单2",
      param:"2"}
  ]
});