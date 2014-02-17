/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */

// count

App.System = Ember.Object.extend({
});

App.System.reopenClass({
	/**
	 * 数据导入
	 */

	importData : function() {
		var url = "rest/evaluate/importData";
		return App.Ajax.post(url, {});

	},
	
	/**
	 * 数据导入状态
	 */

	importDataStatus : function() {
		var url = "rest/evaluate/importDataStatus";
		return App.Ajax.get(url);

	},
	
	/**
	 * 数据评测
	 */

	evaluate : function() {
		var url = "rest/evaluate/evaluate";
		return App.Ajax.post(url, {});
	},
	

    /**
	 * 数据评测状态
	 */

	evaludateStatus : function() {
		var url = "rest/evaluate/evaludateStatus";
		return App.Ajax.get(url);

	},
	findEntityEnum : function() {
		var url = 'rest/basicData/entityEnum';
		// promise
		return App.Ajax.get(url);
	}
});
