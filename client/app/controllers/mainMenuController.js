/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 14-1-2
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */

App.MainMenuController = Ember.Controller.extend({
	// 配置一级菜单
	items : [{
		name : "count.index",
		icon : "fa fa-bar-chart-o",
		title : "评测统计"
		
	}, {
		name : "norm.index",
		icon : "fa fa-random",
		title : "标准数据管理"
	}, {
		name : "quota.index",
		icon : "fa fa-tachometer",
		title : "评测指标管理"
	}, {
		name : "system.index",
		icon : "fa fa-sitemap",
		title : "系统管理"
	}]
}); 