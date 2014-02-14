/**
 * RadarChartComponent
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */

App.RadarChartComponent = Ember.Component.extend({
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
      type:"radar",
      title:{text: content.title},
      "chart":{
        "background-color":"#FFFFFF"
      },
      "plot":{
        "aspect":"rose",
        "border-color":"green",
        "border-width":3
      },
      "scale-k":{
        "values":content.scale
      },
      series: content.series
    };
    return data;
  },

  updateData: function() {
    var elementId = this.get('elementId');
    var data = this.buildData();
    zingchart.exec(
        elementId,
        'setdata', {
          data: data,
          update: true
        });
  },

  contentObserver:function () {
    Ember.run.once(this, 'updateData');
  }.observes("content")

})
