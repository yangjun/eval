/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-25
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

App.QuotaIntegrityController = Ember.ObjectController.extend({

	selectedEntityEnum : null,

	data : {
		"title" : "5",

		"items" : [{
			"id" : "1",
			"name" : "区域名称",
			"type" : "区域",
			"ischeck" : true
		}, {
			"id" : "2",
			"name" : "区域简称",
			"type" : "区域",
			"ischeck" : false
		}, {
			"id" : "3",
			"name" : "区域编码",
			"type" : "区域",
			"ischeck" : true
		}, {
			"id" : "4",
			"name" : "区域类型",
			"type" : "区域",
			"ischeck" : false
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
		//return self.data;
		var entitys = [];
		App.Quota.findFieldByResType(entityID).then(function(data) {
			data.forEach(function(entity) {
				var model = App.Quota.create(entity);
				entitys.addObject(model);

			})
		});

		return entitys;

	}
});
