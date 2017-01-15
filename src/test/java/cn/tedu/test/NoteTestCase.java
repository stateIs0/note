package cn.tedu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteService;

public class NoteTestCase {
	
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext(
			"spring-web.xml",
			"spring-mybatis.xml",
			"spring-service.xml",
			"spring-aop.xml");
	}
	
	@Test
	public void testFindNotesByNotebookId(){
		// select cn_notebook_id from cn_note;
		String id="d0e7ce0d-4893-4705-a51a-9a73d259bc70";
		NoteDao dao = ctx.getBean(
			"noteDao", NoteDao.class);
		List<Map<String, Object>> list=
			dao.findNotesByNotebookId(id);
		for(Map<String, Object> n:list){
			System.out.println(n); 
		}
	}
	
	@Test
	public void testListNotes(){
		String id="d0e7ce0d-4893-4705-a51a-9a73d259bc70";
		NoteService service = ctx.getBean(
			"noteService", NoteService.class);
		List<Map<String, Object>> list=
			service.listNotes(id);
		for (Map<String, Object> n : list) {
			System.out.println(n);
		}
	}
	
	@Test
	public void testFindNoteById(){
		String id = "051538a6-0f8e-472c-8765-251a795bc88f";
		NoteDao dao = ctx.getBean(
			"noteDao", NoteDao.class);
		Note note = dao.findNoteById(id);
		System.out.println(note); 
	}
	
	@Test
	public void testLoadNote(){
		String id="051538a6-0f8e-472c-8765-251a795bc88f";
		NoteService noteService = 
			ctx.getBean("noteService",
			NoteService.class);
		Note note = noteService.loadNote(id);
		System.out.println(note); 
	}
	
	@Test
	public void testUpdateNote(){
		NoteDao dao=ctx.getBean(
			"noteDao", NoteDao.class);
		String id="051538a6-0f8e-472c-8765-251a795bc88f";
		Map<String, Object> note=
			new HashMap<String, Object>();
		//加入必选参数:
		note.put("id", id);
		note.put("lastModifyTime", 
			System.currentTimeMillis());
		//加入可选参数：
		//note.put("title", "问候");
		note.put("body", "Hello World!");
		dao.updateNote(note); 
		//select * from cn_note 
		// where cn_note_id='051538a6-0f8e-472c-8765-251a795bc88f'
		
	}
	
	@Test
	public void testCountNotesById(){
		String id="051538a6-0f8e-472c-8765-251a795bc88f";
		NoteDao dao = ctx.getBean(
			"noteDao", NoteDao.class);
		int n = dao.countNotesById(id);
		System.out.println(n); 
	}
	
	@Test
	public void testSaveNote(){
		String id="051538a6-0f8e-472c-8765-251a795bc88f";
		NoteService service = ctx.getBean(
			"noteService", 
			NoteService.class);
		boolean b = service.saveNote(
			id, "Java","Java Hello World!");
		System.out.println(b); 
		Note n = service.loadNote(id);
		System.out.println(n);
	}
	
 
	@Test
	public void testAddNote(){
		NoteDao dao = ctx.getBean(
			"noteDao",NoteDao.class);
		String id="12121";
		String notebookId="20b4cbec-bd55-4c21-9c41-3a11ada2b803 ";
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String statusId="0";
		String typeId="0";
		String title="Java";
		String body = "Hello";
		long now=System.currentTimeMillis();
		Note note = new Note(id,notebookId, 
			userId, statusId, typeId, 
			title,body,now, now);
		int n = dao.addNote(note);
		System.out.println(n);
	}
	@Test
	public void testServiceAddNote(){
		NoteService service = ctx.getBean(
			"noteService",NoteService.class);
		String notebookId="20b4cbec-bd55-4c21-9c41-3a11ada2b803 ";
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";

		Note note=service.addNote(
			userId, notebookId, "汽车");
		
		System.out.println(note);
	}
	
	@Test
	public void testFindNoteByParams(){
		Map<String, Object> params=
			new HashMap<String, Object>();
		//params.put("userId", "");
		//params.put("notebookId", "");
		params.put("key", "%1%");
		params.put("start", 0);
		params.put("rows", 10);
		
		NoteDao dao = ctx.getBean(
			"noteDao", NoteDao.class);
		List<Map<String, Object>> list =
			dao.findNoteByParams(params);
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void testDeleteNotes(){
		String userId="524f7440-7283-4b2d-8af5-4a67570e892e";
		List<String> idList=
			new ArrayList<String>();
		idList.add("22222222222222222222222222222222222");
		idList.add("41712949-4a1a-4e24-944a-9b8ed1001238");
		idList.add("60480071-f989-4945-9b1c-0d2aba07ae96");
		idList.add("60480071-f989-4945-9b1c-0d2aba07ae96");
		Map<String, Object> params = 
			new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("idList", idList);
		NoteDao dao = ctx.getBean(
			"noteDao", NoteDao.class);
		dao.deleteNotes(params); 
	}
}









