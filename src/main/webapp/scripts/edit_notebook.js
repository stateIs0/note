// edit_notebook.js  与笔记有关的代码

/*
 * 封装与笔记本有关的操作方法 
 */
//事件处理方法，用于显示笔记本列表
//在edit.html第一次显示时候，显示第0页的内容
//在点击more时候，从more中获取页号，并且显示
//这个页中的笔记本
function loadNotebooksAction(){
	
	//获取 page 数据 
	//$(this)是li.more, 显示li时候，已经在LI
	//上绑定了页号数据，这里是获取页号数据
	//如果不存在这个数居说明是第一次显示
	//则显示第0页的内容
	var page=$(this).data('page');
	if(! page){
		page = 0;
	}
	
	//console.log('loadNotebooksAction()');
	//var url='notebook/list.do';
	var url='notebook/notebooks.do';
	//var data={userId:getCookie('userId')};
	var data={userId:getCookie('userId'),
			page:page};
	//console.log(data);
	//console.log(url);
	$.getJSON(url, data, function(result){
		if(result.state==SUCCESS){
			var list=result.data;
			console.log(list); 
			model.updateNotebooks(list, page);
		}
	});
}

/*
 * <li class="online">
	<a  class="checked">
	<i class="fa fa-book" title="online" rel="tooltip-bottom">
	</i> 默认笔记本</a></li>
 */
model.updateNotebooks=function(list, page){
	var template='<li class="online notebook">'+
		'<a>'+
		'<i class="fa fa-book " title="online" rel="tooltip-bottom"></i>'+
		'[notebook.name]</a></li>';
	//检查如果是第一次获得笔记本列表，就直接
	//保存到 模型中，如果是追加显示就将两个
	//数组合并为一个笔记本列表
	if(! this.notebooks ){
		this.notebooks=list;
	}else{
		//concat 方法用于合并两个数组
		this.notebooks=
			this.notebooks.concat(list); 
	}
	//console.log(this);
	var ul=$('#notebooks').empty();
	for(var i=0;i<this.notebooks.length;i++){
		var notebook=this.notebooks[i];
		// id name
		var li=template.replace(
			'[notebook.name]', notebook.name);
		//在DOM对象上绑定数据 index
		li = $(li).data('index',i);
		ul.append(li);
	}
	var li = $('<li class="online more">'+
			'<a>More...</a></li>');
	//绑定下一页的页号
	li.data('page', page+1);
	ul.append(li);
};















