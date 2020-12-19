package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import admin.service.face.UserManageService;
import admin.service.impl.UserManageServiceImpl;


@WebServlet("/admin/userremove")
public class UserRemoveManageContorller extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private UserManageService userService = new UserManageServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	  	if(session.getAttribute("loginid") == null) {//로그인 되어있는지 확인
 			resp.sendRedirect("/user/login");
	  		} else {
	  			String grade = (String)session.getAttribute("grade");//세션에서 등급 불러옴
	  			System.out.println(grade);
	  	  
	  		if(("admin").equals(grade)) {//등급이 관리자일 경우에만 관리자페이지 들어갈 수 있음
	  			System.out.println("작동");
	  			String user_id = req.getParameter("user_id");
	  			System.out.println(user_id);			
	  			boolean res = userService.removeUser(user_id);
	  			if (res) {
	  				resp.sendRedirect("/admin/userlist");
	  			}	
	  		
	  		} else {
	  			resp.sendRedirect("/myPage/main");
	  		}
	  	}	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("작동");
		String user_id = req.getParameter("user_id");
		System.out.println(user_id);			
		boolean res = userService.removeUser(user_id);
		
		Map<String, Object> map = new HashMap<>();
		Gson gson = new Gson();
		
		if (res) {
			try {
				//삭제 성공
				PrintWriter out = resp.getWriter();
				map.put("msg", "ok");
				map.put("redirectUrl", "/admin/userlist");
				resp.setContentType("text/html; charset=utf-8"); 
				String json = gson.toJson(map);
				out.print(json); // 변환한 데이터 넘기기
				out.flush();
				out.close();
				return;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			PrintWriter out = resp.getWriter();
			map.put("msg", "NotOk");
			resp.setContentType("text/html; charset=utf-8"); 
			String json = gson.toJson(map);
			out.print(json);
			out.flush();
			out.close();

		}
	}

	
}
