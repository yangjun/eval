/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */

// count

App.Count = Ember.Object.extend({
});

App.Count.reopenClass({
	/**
	 * 总体质量
	 */

	overview : function() {
		var url = "../data/rs/count/overview.json";
		return App.Ajax.get(url);

	},

	/**
	 * 总体趋势
	 */
	overtrend : function() {
		var url = "../data/rs/count/overtrend.json";
		return App.Ajax.get(url);
	},

	/**
	 * 单资源
	 */
	singleres : function() {

		var url = 'rest/evaluate/evaluateVersion';
		return App.Ajax.get(url);
	},

	findSingleresById : function(position, evaluateVersionID, instanceType) {
		var url = 'rest/result/unilateral/' + position + '/' + evaluateVersionID + '/' + instanceType;


		var result = App.Ajax.get(url);
	
		return result;

	},

	procsingleres : function(value) {

		var result = {};
		result.title = "单项资源总体质量";
		var scale = [];
		var series = [];
		var values = [];
		var items = [];

		for (var i = 0; i < value.length; i++) {
			scale.push(value[i].dimension);
			var score = value[i].score;
			values.push(score);
			var row = {};
			row.index = i + 1;
			row.dimension = value[i].dimension;
			row.score = score;
			items.addObject(row);
		}

		result.scale = scale;
		var temp = {};
		temp.values = values;

		temp["background-color"] = "red";
		temp.alpha = 0.7;

		series.addObject(temp);

		result.series = series;
		result.items = items;

		return result;

	},
	/**
	 * 单维度
	 */
	dimensions : function() {
		var url = "../data/rs/count/dimensions.json";
		return App.Ajax.get(url);
	},

	getLastVersion : function() {
		var url = 'rest/evaluate/evaluateVersion/TheLast';
		// promise
		return App.Ajax.get(url);
	},

	findEntityEnum : function() {
		var url = 'rest/basicData/entityEnum';
		// promise
		return App.Ajax.get(url);
	}
});
