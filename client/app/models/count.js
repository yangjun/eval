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
    var url = 'rest/evaluate/evaluateVersion';
    // promise
    return App.Ajax.get(url);
  },
  
  /**
   * 总体趋势
   */
  overtrend:function () {
    //var url = 'rs/count/overtrend.json';
    var url = 'rest/evaluate/evaluateVersion';
    // promise
    return App.Ajax.get(url);
  },
  
  
   /**
   * 单资源
   */
  singleres:function () {
    //var url = 'rs/count/singleres.json';
    var url = 'rest/evaluate/evaluateVersion';
    // promise
    return App.Ajax.get(url);
  },
  
  findSingleresById : function(evaluateVersionID,position,instanceType){
  	
  	
  	
  	 var url = '/rest/result/unilateral/evaluateVersionID/position/instanceType';
    // promise
    return App.Ajax.get(url);
  },
  
  procsingleres: function(value) {
  	//todo
  	return null;
  },
  /**
   * 单维度
   */
  dimensions:function () {
    //var url = 'rs/count/dimensions.json';
    var url = 'rest/evaluate/evaluateVersion';
    // promise
    return App.Ajax.get(url);
  },
  findEntityEnum:function () {
  	var url = 'rest/basicData/entityEnum';
    // promise
    return App.Ajax.get(url);
  }
  
});