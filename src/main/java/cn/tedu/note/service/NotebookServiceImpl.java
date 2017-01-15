package cn.tedu.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

@Service("notebookService")
public class NotebookServiceImpl
	implements NotebookService{
	
	@Resource
	private NotebookDao notebookDao;
	
	@Resource
	private UserDao userDao;
	
	@Transactional(readOnly=true,
			isolation=Isolation.DEFAULT
			,propagation=Propagation.REQUIRED)
	public List<Map<String, Object>> 
		listNotebooks(String userId) 
		throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNameException("id空");
		}
		User user = userDao.findUserById(userId);
		if(user==null){
			throw new UserNameException("查无此人");
		}
		return notebookDao
			.findNotebooksByUserId(userId);
	}
	
	@Resource
	private NoteService noteService;
	
	@Transactional(
		propagation=Propagation.REQUIRED)
	public void deleteNotebook(String id) {
		
		List<Map<String, Object>> list=
			noteService.listNotes(id);
		for (Map<String, Object> map : list) {
			String noteId=(String)map.get("id");
			noteService.deleteAll(noteId);
		}
		//String s=null;
		//s.length();
		notebookDao.deleteNotebook(id);
	}
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listNotebooks(
		String userId, int pageNum, int pageSize)
		throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("ID空");
		}
		User user = userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("木有人");
		}
		//计算出起始行号
		int start = pageNum*pageSize;
		Map<String, Object> params=
			new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("start", start);
		params.put("rows", pageSize);
		return notebookDao.findNotebooksByPage(params);
	}
}




