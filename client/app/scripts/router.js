/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-24
 * Time: 下午8:12
 * To change this template use File | Settings | File Templates.
 */

App.Router.map(function() {
	this.route('login');
	this.resource('users', function() {
		this.resource('user', {
			path : '/:user_id'
		}, function() {
			this.route('edit');
		});
		this.route('create');
	})
	this.route('about');

	this.resource("count", function() {
		this.route("editpassword", {
			path : "/pw/:user_id"
		});
		this.route("find");
	});

}); 