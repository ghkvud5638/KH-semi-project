package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/admin/usermanager")
public class UserManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
	  	if(session.getAttribute("loginid") == null) {//로그인 되어있는지 확인
 			resp.sendRedirect("/user/login");
	  		} else {
	  			String grade = (String)session.getAttribute("grade");//세션에서 등급 불러옴
	  			System.out.println(grade);
	  	  
	  		if(("admin").equals(grade)) {//등급이 관리자일 경우에만 관리자페이지 들어갈 수 있음
	  			req.getRequestDispatcher("/WEB-INF/views/admin/user/userManager.jsp").forward(req, resp);
	  			} else {
	  			resp.sendRedirect("/myPage/main");
	  		}
	  	}
		
	}

	
}
