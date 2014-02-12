/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.CountSingleresController = Ember.ObjectController.extend({

	selectedEntityEnum : null,
	
	data : {
		"title" : "4",
		"scale" : ["完整性", "规范性", "唯一性", "逻辑性", "准确性", "管理类"],
		"series" : [{
			"values" : [8, 14, 53, 30, 69, 25],
			"background-color" : "red",
			"alpha" : 0.7
		}],

		"items" : [{
			"index" : "1",
			"dimension" : "完整性",
			"score" : "8"
		}, {
			"index" : "2",
			"dimension" : "规范性",
			"score" : "14"
		}, {
			"index" : "3",
			"dimension" : "唯一性",
			"score" : "53"
		}, {
			"index" : "4",
			"dimension" : "逻辑性",
			"score" : "30"
		}, {
			"index" : "5",
			"dimension" : "准确性",
			"score" : "69"
		}, {
			"index" : "6",
			"dimension" : "管理类",
			"score" : "25"
		}]
	},

	selectedEntity : function() {
		return this.refresh();
	}.property('selectedEntityEnum'),


	refresh : function() {
		console.log("refresh ...");
		var self = this;
		var entityID = self.get('selectedEntityEnum.key');
		console.log(entityID);
	
		var result = App.Count.findSingleresById('GZ', 'c83b23c1-42c4-4948-8478-b0c02223f92f', entityID);
		
		return result.then(function(data) {
			
			return App.Count.procsingleres(data);
		});

	}
});
