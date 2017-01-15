package cn.tedu.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.PostDao;
import cn.tedu.note.entity.Post;

public class PostTestCase {
	
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
	public void testFindPostById(){
		PostDao dao = ctx.getBean(
			"postDao", PostDao.class);
		Post post=dao.findPostById(1);
		System.out.println(post); 
	}
}





