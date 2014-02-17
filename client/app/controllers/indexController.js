/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.IndexController = Ember.ObjectController.extend({
  data: {
    title: "总体质量",
    scale: [
    "完整性","规范性","唯一性","逻辑性","准确性","管理类"
  ],
    series: [
      {
        "values":[8,14,53,30,69,25],
        "background-color":"red",
        "alpha":0.7
      }
    ]
  },
  data1: {
    title: "单项资源总体质量",
    scale: [
       "完整性","规范性","唯一性","逻辑性","准确性","管理类"
    ],
    series: [
      {
        "values":[8,14,30,68,75,198],
        "background-color":"green",
        "fill-angle":-90,
        "text":"唯一性指标"
      }
    ]
  },
  data2: {
    title: "维度资源评测",
    scale: [
       "区域","站点","机房","机架","网元","光缆","传输段"
    ],
    series: [
      {
        "values":[99,62,53,30,69,79,89],
        "background-color":"red",
        "fill-angle":-90,
        "text":"唯一性指标"
      }
    ]
  },
  
   data3: {
    title: "总体质量趋势",
    scale: [
       "1月","3月","5月","5月","9月","11月"
    ],
    series: [
      {
        "values":[8,14,25,30,69,64],
        "background-color":"red",
        "fill-angle":-90,
        "text":"总体质量趋势"
      }
    ]
  },
  data4: {
    title: "单维度质量趋势",
    scale: [
       "1月","3月","5月","5月","9月","11月"
    ],
    series: [
      {
        "values":[15,14,25,39,69,64],
        "background-color":"red",
        "fill-angle":-90,
        "text":"单维度质量趋势"
      }
    ]
  },
  data5: {
    title: "单资源总体质量趋势",
    scale: [
       "1月","3月","5月","5月","9月","11月"
    ],
    series: [
      {
        "values":[1,14,25,30,14,64],
        "background-color":"red",
        "fill-angle":-90,
        "text":"单资源总体质量趋势"
      }
    ]
  },
  menu: {
    title: "XXX",
    id: "users"
  }
});