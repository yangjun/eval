App.ChartView = Ember.View.extend({
  templateName:"chart",
  classNames:["map"],
  didInsertElement:function () {
    var controller = this.get('controller');
    console.log("controller = " + controller);
    var data = controller.get('data');
    var elementId = this.get('elementId');

    var myChart = {
      type : "line",
      title : {text: "XX指标"},
      series : [
        {values:[5, 10, 15, 5, 10, 5]},
        {values:[2, 4, 6, 8, 10, 12]}
      ]
    };

    zingchart.render({
      id : elementId,
      height : 200,
      data : myChart
    });

//    nv.addGraph(function () {
//      var chart = nv.models.multiBarHorizontalChart()
//          .x(function (d) {
//            return d.label
//          })
//          .y(function (d) {
//            return d.value
//          })
//          .margin({top:30, right:20, bottom:50, left:175})
//          .showValues(true)
//          .tooltips(true)
//          .showControls(false);
//
//      chart.yAxis
//          .tickFormat(d3.format(',.2f'));
//
//
//      var svg = d3.select("#" + elementId + " svg");
//      svg.datum(data)
//          .transition().duration(500)
//          .call(chart);
//
//      nv.utils.windowResize(chart.update);
//
//      return chart;
//    });
 }
});