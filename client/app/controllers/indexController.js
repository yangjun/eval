/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.IndexController = Ember.ObjectController.extend({
  data: {
    title: "标题",
    scale: [
      "指标1","指标2","指标3","指标4","指标5","指标6"
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
    title: "唯一性指标地区排名",
    scale: [
      "地区3","地区5","地区1","地区6","地区5","地区4"
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
    title: "XX地区趋势",
    scale: [
      "2013-01","2013-02","2013-03","2013-04","2013-05","2013-06"
    ],
    series: [
      {
        "values":[8,14,30,68,75,98],
        "background-color":"green",
        "fill-angle":-90,
        "text":"唯一性指标"
      },
      {
        "values":[18,35,42,35,17,35],
        "background-color":"red",
        "fill-angle":-90,
        "text":"XX性指标"
      }
    ]
  },
  menu: {
    title: "XXX",
    id: "users"
  }
});