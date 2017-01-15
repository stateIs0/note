// edit_init.js 初始化

var SUCCESS=0;

var model = {};//当前页面中的数据模型

$(function(){
	//console.log('init()');
	//页面加载以后，立即加载笔记本列表
	loadNotebooksAction();//edit_notebook.js
	//绑定点击笔记本列表的事件
	//showNotesAction函数定义在edit_note.js中
	$('#notebooks').on(
			'click','li.notebook', showNotesAction);
	$('#notebooks').on(
			'click','li.more', loadNotebooksAction);
	//绑定笔记列表点击事件
	//loadNoteAction 方法在 edit_note.js中定义
	$('#notes').on('click','li',loadNoteAction);

	$('#save_note').click(saveNoteAction);
	
	//edit_init.js
	//绑定添加笔记按钮事件
	$('#add_note').click(showAddNoteDialog);
	
	$('#can').on('click','.close,.cancle',
			closeDialog);
	//$('#can').on('click','.sure',function(){});
	
	//edit_init.js
	//绑定 笔记子菜单
	$('#notes').on('click',
		'.btn_slide_down',showNoteMenu);
	$('body').click(closeNoteMenu);
	
});

function closeNoteMenu(){
	$('#notes .note_menu').hide();
}
function showNoteMenu(){
	console.log('showNoteMenu');
	//button->a->
	var menus = $('#notes .note_menu');
	var menu =$(this).parent().next(); 
	menus.each(function(index, e){
		if(e!=menu[0]){
			$(e).hide();
		}
	});
	menu.toggle();
	return false;
}


function closeDialog(){
	$('#can').empty();
	$('.opacity_bg').hide();
}













