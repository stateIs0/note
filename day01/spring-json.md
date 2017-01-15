# 云笔记

## Spring-MVC JSON 

Spring MVC内嵌支持了JSON，只需要在控制器上使用@ResponseBody即可。在使用了这个注解以后，Spring会自动的将控制器返回值对象序列化为JSON反馈到客户端。

> 这个功能必须依赖 jackson 包。

如：

	@Controller
	@RequestMapping("/demo")
	class DemoConroller{
		
		@ReauestMapping("/hello")
		@ResponseBody
		public Object execute(String name){
			//业务处理
			return new User(...);
		}
	}

	返回： {"name":"Tom", "age":20}

案例步骤：

创建项目，导入相关包：

	<dependency>
	  <groupId>spring-webmvc</groupId>
	  <artifactId>spring-webmvc</artifactId>
	  <version>3.2.8.RELEASE</version>
	</dependency> 
	<dependency>
	  <groupId>org.springframework</groupId>
	  <artifactId>spring-web</artifactId>
	  <version>3.2.8.RELEASE</version>
	</dependency> 
	<dependency>
	  <groupId>org.springframework</groupId>
	  <artifactId>spring-context</artifactId>
	  <version>3.2.8.RELEASE</version>
	</dependency>
	<dependency>
	  <groupId>org.springframework</groupId>
	  <artifactId>spring-beans</artifactId>
	  <version>3.2.8.RELEASE</version>
	</dependency>
	<dependency>
	  <groupId>com.fasterxml.jackson.core</groupId>
	  <artifactId>jackson-databind</artifactId>
	  <version>2.3.4</version>
	</dependency>

配置主控制器:

	  <servlet>
	  	 <servlet-name>mvc</servlet-name>
	  	 <servlet-class>
	  	 	org.springframework.web.servlet.DispatcherServlet
	  	 </servlet-class>
	  	 <init-param>
	  	 	<param-name>contextConfigLocation</param-name>
	  	 	<param-value>classpath:spring-web.xml</param-value>
	  	 </init-param>
	  	 <load-on-startup>1</load-on-startup>
	  </servlet>
	  <servlet-mapping>
	  	 <servlet-name>mvc</servlet-name>
	  	 <url-pattern>/*</url-pattern>
	  </servlet-mapping>

添加Spring配置文件 spring-web.xml:

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans" 
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:context="http://www.springframework.org/schema/context" 
		xmlns:jdbc="http://www.springframework.org/schema/jdbc"  
		xmlns:jee="http://www.springframework.org/schema/jee" 
		xmlns:tx="http://www.springframework.org/schema/tx"
		xmlns:aop="http://www.springframework.org/schema/aop" 
		xmlns:mvc="http://www.springframework.org/schema/mvc"
		xmlns:util="http://www.springframework.org/schema/util"
		xmlns:jpa="http://www.springframework.org/schema/data/jpa"
		xsi:schemaLocation="
			http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
			http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.2.xsd
			http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-3.2.xsd
			http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd
			http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa-1.3.xsd
			http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
			http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd
			http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.2.xsd">
	
		<!-- 扫描到控制器组件 -->
		<context:component-scan 
			base-package="cn.tedu.web"/>
		<!-- 配置注解版本的 spring-mvc -->
		<mvc:annotation-driven/>
		
	</beans>	

添加控制器：

	@Controller
	@RequestMapping("/demo")
	public class DemoController {
		//@ResponseBody 自动处理 控制器方法的返回值
		// 如果 是对象，则将对象转换为JSON，返回
		// 到客户端，必须依赖于 jackson API
		@RequestMapping("/test")
		@ResponseBody
		public Object execute(String name){
			String[] ary = 
				{"Tom", "Jerry","Nemo", name};
			return ary;
		}
		
		@RequestMapping("/list")
		@ResponseBody
		public Object list(){
			List<String> list = 
				new ArrayList<String>();
			list.add("Tom");
			list.add("Jerry");
			list.add("Andy");
			list.add("Mac");
			return list;
		}
		
		@RequestMapping("/map")
		@ResponseBody
		public Object map(){
			Map<String, Object> map=
				new HashMap<String, Object>();
			map.put("id", 100);
			map.put("name", "Tom");
			map.put("friends",
				new String[]{"Jerry", "Mac"});
			return map;
		}
		
		@RequestMapping("/person")
		@ResponseBody
		public Object person(){
			Person p = new Person(5, "Jerry",
				new String[]{"Tom", "Andy"});
			return p;
		}
	}

部署测试...

## MySql 使用

1. 开启终端
2. 登录到 
	- mysql -uroot 
3. 显示数据库
	- show databases;
4. 使用数据库
	- use 数据库名 
	- use mysql
5. 显示全部的表
	- show tables;
6. 创建新数据库
	- create database 数据库名;
	- create database cloud_note;
7. 建表
	- create table 表名(col列表)
	- create table Person(pid int, pname varchar(100));
8. 删除表 drop table 表名;
9. 删除数据库 drop database 数据库名; 

10. 设置当前窗口的编码：
	- set names utf8;

11. 导入sql语句命令
	- source 文件的全路名
	- source d:\robin\cloud_note.sql;
	- source /home/soft01/note/cloud_note.sql;

Windows 安装MySQL 

1. Oracle 网站下载MySQL 
	- 建议下载 5.7，默认是UTF8编码（支持中文）
2. 如果安装 5.5 版本，要选择 utf-8 编码（支持中文）
3. 重要！！ 不要忘记安装时候设置的密码！！！
4. 使用密码登录


	


