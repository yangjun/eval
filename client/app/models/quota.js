/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */

// Quota


	
App.Quota = Ember.Object.extend({
});

App.Quota.reopenClass({
	
  /**
   * 完整性评测指标管理
   */
  
   
   quotaIntegrity:function () {
    var url = 'rs/quota/integrity.json';
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