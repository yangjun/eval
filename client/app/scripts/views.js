/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
App.ApplicationView = Ember.View.extend({
  templateName:"application"
});
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
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 下午9:42
 * To change this template use File | Settings | File Templates.
 */
App.ErrorView = Em.View.extend({
  templateName: 'error'
});
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:51
 * To change this template use File | Settings | File Templates.
 */


App.FooterView = Em.View.extend({
  templateName: 'footer'
});
App.HeadView = Em.View.extend({
  templateName: 'head'
});
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
App.LoignView = Ember.View.extend({
  templateName:"login"
});
/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-3
 * Time: 下午2:14
 * To change this template use File | Settings | File Templates.
 */

App.MainMenuView = Ember.View.extend({
  templateName: 'mainMenu',
  tagName: "ul",
  classNames:"nav navbar-nav"
});

App.MapView = Ember.View.extend({
  templateName:"map",
  classNames: ["map"],
  didInsertElement:function () {
    var elementId = this.get('elementId');
    var center = [27.323265355533533, 107.19582928247779];
    var controller = this.get('controller');
    var zoomLevel = controller.get('zoomLevel') || 15;
    var map = L.map(elementId).setView(center, zoomLevel);

    var style = function style(feature) {
      return {
        fillColor: getColor(feature.properties.density),
        weight: 2,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 0.7
      };
    };

    var onEachFeature = function(feature, layer) {
      layer.on({
        mouseover: highlightFeature,
        mouseout: resetHighlight,
        click: onClickFeature
      });
    };

    var onClickFeature = function (e) {
      var layer = e.target;
    };

    var highlightFeature = function(e) {
      var layer = e.target;
      layer.setStyle({
        weight: 8
      });

      if (!L.Browser.ie && !L.Browser.opera) {
        layer.bringToFront();
      }

      controller.set('feature', layer.feature);
    };

    var resetHighlight = function resetHighlight(e) {
      var feature = e.target;
      feature.setStyle({
        weight: 2
      });
      controller.set('feature',null);
    };

    var url = controller.get('tileUrl');
    L.tileLayer(url).addTo(map);

    var features = controller.get("features");

    L.geoJson(features, {
      style: function(feature) {
//        console.log("feature = " +feature.id );
        switch (feature.id) {
          case 103: return {fillColor: "#ff0000", weight: 2, opacity: 1, fillOpacity: 0.7};
          case 104: return {fillColor: "#0000ff", weight: 2, opacity: 1, fillOpacity: 0.7};
        }
      }, onEachFeature: onEachFeature
    }).addTo(map);

    map.on('zoomend', function (e) {
      var zoom = e.target.getZoom()
      //console.log('zoomend', 'Setting zoomLevel ' + zoom);
      controller.set('zoomLevel', zoom);
    });

    this.controller.set('map', map);
  }
})