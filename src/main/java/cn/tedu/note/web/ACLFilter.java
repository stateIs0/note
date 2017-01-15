package cn.tedu.note.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.tedu.note.service.UserService;

public class ACLFilter implements Filter{
	
	private ServletContext sc;
	private ApplicationContext ctx;
	private UserService userService;
	
	/**
	 * 过滤器初始化代码
	 */
	public void init(FilterConfig cfg)
		throws ServletException {
 
		sc=	cfg.getServletContext();
		//获取Spring容器
		ctx=WebApplicationContextUtils
			.getWebApplicationContext(sc);
		//从容器中获取 UserService 对象
		userService=ctx.getBean(
				"userService",
				UserService.class);
		
	}
	public void doFilter(
			ServletRequest req, 
			ServletResponse res, 
			FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=
			(HttpServletRequest) req;
		HttpServletResponse response =
			(HttpServletResponse) res;

		String path=request.getRequestURI();
		//System.out.println(path);
		
		path = path.substring(
			path.indexOf('/', 1));
		
		//System.out.println("trim path:"+path);
		
		if(path.matches(".*/edit\\.html$")){
			checkLogin(request,response,chain);
			return;
		}
		
		if(path.matches(".(note).*\\.do$")){
			checkDotDo(request,response,chain);
			return;
		}
		
		chain.doFilter(request, response); 
	}
	private void checkDotDo(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//System.out.println("checkDotDo");
		String token = getCookie(request, "token");
		String userId= getCookie(request,"userId");
		//System.out.println("userId:"+userId); 
		
		if(userService.checkToken(userId, token)){
			chain.doFilter(request, response);
			return;
		}
		
		//没有登录时候，返回JSON错误消息
		String json="{\"state\":1,\"message\":\"必须登录！\"}";
		response.setCharacterEncoding("utf-8");
		response.setContentType(
			"application/json;charset=UTF-8");
		response.getWriter().println(json);
	}
	private String getCookie(HttpServletRequest request, 
			String cookieName) {
		Cookie[] cookies=request.getCookies();
		//如果客户端没有cookie，就会返回null
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookieName.equals(
						cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	private void checkLogin(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//System.out.println("checkLogin");
		//检查是否有 token cookie 
		//如果没有， 就重定向到log_in.html
		String token = getCookie(request, "token");
		String userId= getCookie(request,"userId");
		//System.out.println("userId:"+userId); 
		if(userService.checkToken(userId, token)){
			chain.doFilter(request, response);
			return;
		}
		//重定向到 log_in.html
		String path=request.getContextPath()+
			"/log_in.html";
		response.sendRedirect(path);
	}
	
	public void destroy() {
	}
}
