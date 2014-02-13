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
    var url = 'rest/evaluate/evaluateVersion';
    // promise
    return App.Ajax.get(url);
  },

  findFieldByResType:function (entityType) {
    //var url = "../data/rs/quota/integrity.json";
    var url = 'rest/dictinary/fieldDictionary/' + entityType;
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


App.CheckObject = Ember.Object.extend({
  ischeckObserver:function () {
    this.set('isLoading', true);
    console.log(this.id + " ischeck change = " + this.ischeck);
    // todo post
    this.set('isLoading', false);
  }.observes("ischeck")
});