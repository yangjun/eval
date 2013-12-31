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