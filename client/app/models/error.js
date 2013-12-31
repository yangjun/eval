/**
 * Created with JetBrains WebStorm.
 * User: yangjungis@126.com
 * Date: 13-12-26
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */

App.Error = function(message, code) {
  this.name = 'Error';
  this.message = message;
  this.code = code;
  this.stack = (new Error()).stack;
}

App.Error.prototype = new Error;
