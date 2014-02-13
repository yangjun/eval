/**
 * HbarChartComponent
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */

App.HbarChartComponent = Ember.Component.extend({
  didInsertElement:function () {
    this.draw();
  },

  draw:function () {
    // View not in DOM
    if (!this.$()) { return; }
    var elementId = this.get('elementId');
    var content = this.get("content");
    var height = this.get("height");
    var data = this.buildData();
    zingchart.render({
      id:elementId,
      height:height,
      data:data
    });
  },

  buildData:function () {
    var content = this.get("content");
    var data = {
      type:"hbar",
      "gradient-colors":"#0f2e52 #1c4577 #0f2e52",
      "gradient-stops":"0.1 0.5 0.9",
      "fill-angle":70,
      title:{text: content.title},
      source:{ text:"Source: Farnsworth Delivery Tracking Gizmo"},
      chart:{
        "background-color":"#FFFFFF"
      },
      plot:{
        "bar-width":"16px",
        valueBox:{
          type:"all",
          placement:"top-in",
          "font-color": "#FFFFFF"}
      },
      "scale-x":{
        "values":content.scale,
        "line-width":0,
        "tick":{
          "visible":false
        },
        "item":{
          "font-color":"#d5dce6"
        }
      },
      "scale-y":{
        "line-color":"#6F9CCA",
        "item":{
          "font-color":"#d5dce6"
        },
        "tick":{
          "line-color":"#6F9CCA"
        }
      },
      "tooltip":{
        "text":"%k<br>%t<br>%v"
      },
      series: content.series
    };
  },

  contentObserver:function () {
    var elementId = this.get('elementId');
    var data = this.buildData();
    zingchart.exec(
        elementId,
        'setdata', {
          data: data,
          update: true
        });
  }.observes("content")

})
