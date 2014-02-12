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
		//
		// var url = 'rest/evaluate/evaluateVersion';
		var url = "../data/rs/count/overview.json";
    return App.Ajax.get(url);

	},

	/**
	 * 总体趋势
	 */
	overtrend : function() {
		//var url = 'rs/count/overtrend.json';
		var url = 'rest/evaluate/evaluateVersion';
		// promise
		return App.Ajax.get(url);
	},

	/**
	 * 单资源
	 */
	singleres : function() {
		//var url = 'rs/count/singleres.json';
		var url = 'rest/evaluate/evaluateVersion';
		// promise
		return App.Ajax.get(url);
	},

	findSingleresById : function(position, evaluateVersionID, instanceType) {
		var url = 'rest/result/unilateral/' + position + '/' + evaluateVersionID + '/' + instanceType;
		return App.Ajax.get(url);

	},

	procsingleres : function(value) {
		//todo
		console.log("value =" + value)
		var result = {};
		result.title = "单项资源总体质量";
		var scale = [];
		var series = [];
		var values = [];
		var items = [];
		
	//	value.forEach(function(entity) {
	//		var model = App.Count.create(entity);

	//		scale.addObject(entity.dimension);
	//		values.addObject(entity.score);
	//		var a = App.Count.create({});
			
	//		a.dimension = value[i].dimension
	//		a.score = value[i].score
	//		items.addObject(a);
	//	});
	
		for (var i = 0; i < value.length; i++) {
			scale.push(value[i].dimension);
      var score = value[i].score;
			values.push(score);
			var row = {};
			row.index = i;
			row.dimension = value[i].dimension;
			row.score = score;
			items.addObject(row);
		}
		
		result.scale = scale;
		var temp = {};
		temp.values = values;
		
		temp["background-color"] = "red";
		temp.alpha =  0.7;
		
		series.addObject(temp);
		
		result.series = series;
		result.items = items;

		console.log("result = " + JSON.stringify(result));

		return result;
		
	}, 
	/**
	 * 单维度
	 */
	dimensions : function() {
		//var url = 'rs/count/dimensions.json';
		var url = 'rest/evaluate/evaluateVersion';
		// promise
		return App.Ajax.get(url);
	},

	findEntityEnum : function() {
		var url = 'rest/basicData/entityEnum';
		// promise
		return App.Ajax.get(url);
	}
});
