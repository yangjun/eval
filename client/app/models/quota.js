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

    var url = 'rest/dictinary/fieldDictionary/NOT_NULL/' + entityType;
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
    var self = this;
    self.set('isRunning', true);
    if (self.ischeck) {
      //增加
      var url = 'rest/evaluateItem/evaluateItem?evaluateType=NOT_NULL&objectDictionaryID='
          + self.typeID
          + '&fieldDictionaryID='
          + self.propertyID;

      var promise = App.Ajax.post(url, {});
      promise.then(function (data) {
        self.set("evaluateItemID", data.id);
        self.set('isRunning', false);
      });

    } else {
      //删除
      var url = 'rest/evaluateItem/evaluateItem/NOT_NULL/' + self.evaluateItemID;
      var promise = App.Ajax.delete(url);
      promise.then(function (data) {
        self.set('isRunning', false);
      });
    }
  }.observes("ischeck")
});