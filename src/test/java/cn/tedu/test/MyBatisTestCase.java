package cn.tedu.test;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

public class MyBatisTestCase {
	
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext(
			"spring-web.xml",
			"spring-service.xml",
			"spring-mybatis.xml");
	}
	@Test
	public void testDataSource(){
		DataSource ds = ctx.getBean(
			"dataSource", DataSource.class);
		System.out.println(ds); 
	}
	@Test
	public void testSqlSessionFactory(){
		SqlSessionFactory factory=
			ctx.getBean("sqlSessionFactory",
			SqlSessionFactory.class);
		System.out.println(factory);
	}
	
	@Test
	public void testMapperScanner(){
		MapperScannerConfigurer scanner=
			ctx.getBean("mapperScanner",
			MapperScannerConfigurer.class);
		System.out.println(scanner); 
	}
	
	@Test
	public void testSaveUser(){
		UserDao dao = ctx.getBean(
			"userDao", UserDao.class);
		User user=new User("123", "Tom",
			"123", "", "Cat");
		dao.saveUser(user);
		//select * from cn_user  where cn_user_id='123'
	}
	@Test
	public void testFindUserById(){
		UserDao dao = ctx.getBean(
			"userDao", UserDao.class);
		String id="123";
		User user=dao.findUserById(id);
		System.out.println(user); 
	}
	@Test
	public void testFindUserByName(){
		UserDao dao = ctx.getBean(
			"userDao", UserDao.class);
		String name="Tom";
		User user=dao.findUserByName(name);
		System.out.println(user); 
	}
	
	
}











