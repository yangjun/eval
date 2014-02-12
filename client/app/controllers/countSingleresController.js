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
  "title": "4",
  "scale": [
    "完整性","规范性","唯一性","逻辑性","准确性","管理类"
  ],
  "series": [
    {
      "values":[8,14,53,30,69,25],
      "background-color":"red",
      "alpha":0.7
    }
  ],
  
  "items":[
  {"index":"1","name":"完整性","type":"8"},
  {"index":"2","name":"规范性","type":"14"},
  {"index":"3","name":"唯一性","type":"53"},
  {"index":"4","name":"逻辑性","type":"30"},
  {"index":"5","name":"准确性","type":"69"},
  {"index":"6","name":"管理类","type":"25"}
  ]
},
	selectedEntity : function() {
		
		return this.refresh();
	}.property('selectedEntityEnum'),
	
	refresh:function() {
		
    console.log("refresh ...");
    var self = this;
    var entityID = self.get('selectedEntityEnum.key');
   var ver = self.get("item") ;
   console.log(ver);
     return self.data;
     
     // return App.Count.findSingleresById('c83b23c1-42c4-4948-8478-b0c02223f92f','GZ',entityID).then(function (data) {
    
   }

}); 