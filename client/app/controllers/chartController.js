/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */

App.ChartController = Ember.ObjectController.extend({
  data:[
    {
      "key":"唯一性",
      "color":"#d62728",
      "values":[
        {
          "label":"贵州",
          "value":-1.8746444827653
        } ,
        {
          "label":"凯里",
          "value":-8.0961543492239
        } ,
        {
          "label":"Group C",
          "value":-0.57072943117674
        }
      ]
    },
    {
      "key":"逻辑性",
      "color":"#1f77b4",
      "values":[
        {
          "label":"贵州",
          "value":25.307646510375
        } ,
        {
          "label":"凯里",
          "value":16.756779544553
        } ,
        {
          "label":"Group C",
          "value":18.451534877007
        }
      ]
    }
  ]
})
