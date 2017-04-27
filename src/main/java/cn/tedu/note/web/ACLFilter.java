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
	 * 杩囨护鍣ㄥ垵濮嬪寲浠ｇ爜
	 */
	public void init(FilterConfig cfg)
		throws ServletException {
 
		sc=	cfg.getServletContext();
		//鑾峰彇Spring瀹瑰櫒
		ctx=WebApplicationContextUtils
			.getWebApplicationContext(sc);
		//浠庡鍣ㄤ腑鑾峰彇 UserService 瀵硅薄
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
		
		//娌℃湁鐧诲綍鏃跺�欙紝杩斿洖JSON閿欒娑堟伅
		String json="{\"state\":1,\"message\":\"蹇呴』鐧诲綍锛乗"}";
		response.setCharacterEncoding("utf-8");
		response.setContentType(
			"application/json;charset=UTF-8");
		response.getWriter().println(json);
	}
	private String getCookie(HttpServletRequest request, 
			String cookieName) {
		Cookie[] cookies=request.getCookies();
		//濡傛灉瀹㈡埛绔病鏈塩ookie锛屽氨浼氳繑鍥瀗ull
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
		//妫�鏌ユ槸鍚︽湁 token cookie 
		//濡傛灉娌℃湁锛� 灏遍噸瀹氬悜鍒發og_in.html
		String token = getCookie(request, "token");
		String userId= getCookie(request,"userId");
		//System.out.println("userId:"+userId); 
		if(userService.checkToken(userId, token)){
			chain.doFilter(request, response);
			return;
		}
		//閲嶅畾鍚戝埌 log_in.html
		String path=request.getContextPath()+
			"/log_in.html";
		response.sendRedirect(path);
	}
	
	public void destroy() {
	}
}
