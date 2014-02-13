/**
 * LineChartComponent
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */

App.LineChartComponent = Ember.Component.extend({
  didInsertElement:function () {
    this.draw();
  },

  draw:function () {
    // View not in DOM
    if (!this.$()) {
      return;
    }
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
      type:"line",
      "gradient-colors":"#0f2e52 #1c4577 #0f2e52",
      "gradient-stops":"0.1 0.5 0.9",
      "fill-angle":70,
      title:{text:content.title},
      legend:{},
      plot:{ valueBox:{ type:"all", placement:"top" }},
      tooltip:{ text:"%t<br> %v<br>%k" },
      crosshairX:{},
      crosshairY:{},
      source:{ text:"Source: Farnsworth Delivery Tracking Gizmo"},
      scaleX:{ values:content.scale},
      scaleY:{},
      chart:{
        "background-color":"#FFFFFF"
      },
      series:content.series
    };
    return data;
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
