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