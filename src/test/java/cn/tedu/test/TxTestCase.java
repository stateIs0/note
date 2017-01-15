package cn.tedu.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookService;

public class TxTestCase {
	
	ApplicationContext ctx;
	
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext(
			"spring-mybatis.xml",
			"spring-aop.xml",
			"spring-service.xml",
			"spring-web.xml");
	}
	/*
 3febebb3-a1b7-45ac-83ba-50cdb41e5fc1
 9187ffd3-4c1e-4768-9f2f-c600e835b823
 ebd65da6-3f90-45f9-b045-782928a5e2c0
 fed920a0-573c-46c8-ae4e-368397846efd
	 */
	@Test
	public void testDeleteAll(){
		String id1="3febebb3-a1b7-45ac-83ba-50cdb41e5fc1";
		String id2="9187ffd3-4c1e-4768-9f2f-c600e835b823";
		String id3="ebd65da6-3f90-45f9-b045-782928a5e2c0";
		String id4="fed920a0-573c-46c8-ae4e-368397846efd";
		NoteService service = ctx.getBean(
			"noteService", NoteService.class);
		int n = service.deleteAll(
			id1, id2, id3, id4);
		System.out.println(n);
	}
	
	@Test
	public void testDeleteNotebook(){
		//测试事务的传播特性
		String id="d92e6b86-e48a-485d-8f11-04a93818bb42";
		NotebookService service=
			ctx.getBean("notebookService",
			NotebookService.class);
		service.deleteNotebook(id); 
	}
}





