package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dto.TB_USER;
import admin.service.face.UserManageService;
import admin.service.impl.UserManageServiceImpl;
import common.Paging;




@WebServlet("/admin/userlist")
public class UserListController extends HttpServlet {
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
	  			Paging paging = userService.getPaging(req);
	  			req.setAttribute("paging", paging);
	  			
	  			List<TB_USER> list = userService.userList(paging);
	  			
	  			
	  			req.setAttribute("list", list);
	  			System.out.println(list);
	  			
	  			req.getRequestDispatcher("/WEB-INF/views/admin/user/userList.jsp").forward(req, resp);
	  			} else {
	  			resp.sendRedirect("/myPage/main");
	  		}
	  	}

	  	  		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
