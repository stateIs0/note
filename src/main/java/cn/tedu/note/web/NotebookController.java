package cn.tedu.note.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.service.NotebookService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/notebook")
public class NotebookController
	extends AbstractController{
	
	@Resource
	private NotebookService notebookService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> 
		list(String userId){
		List<Map<String, Object>> list=
			notebookService.listNotebooks(userId);
		return new JsonResult<List<Map<String,Object>>>(list);
	}
	
	@RequestMapping("notebooks.do")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>>
		notebooks(String userId, int page){
		int size=6;
		List<Map<String, Object>> list=
			notebookService.listNotebooks(
				userId, page, size);
		return new JsonResult<List<Map<String,Object>>>(list);
	}
}




