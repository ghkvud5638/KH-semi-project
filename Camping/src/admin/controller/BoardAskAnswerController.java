package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dto.TB_ASK_BOARD;
import admin.service.face.AdminAskService;
import admin.service.impl.AdminAskServiceImpl;
import common.Paging;

@WebServlet("/admin/ask")
public class BoardAskAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminAskService ask = new AdminAskServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  	HttpSession session = req.getSession();
	  	if(session.getAttribute("loginid") == null) {
 			resp.sendRedirect("/user/login");

	  	}else {
		String grade = (String)session.getAttribute("grade");
	  		System.out.println(grade);
  
 		if(grade.equals("admin")) {
 			Paging paging = ask.getPaging(req);
 			req.setAttribute("paging", paging);
 			
 			List<TB_ASK_BOARD> list = ask.askBoardList(paging);
 			
 			req.setAttribute("list", list);
 			
 			System.out.println(list);
 			req.getRequestDispatcher("/WEB-INF/views/admin/askboard/askboardlist.jsp").forward(req, resp);
 		} else {
	        
 			resp.sendRedirect("/myPage/main");

 		}
		
	  	}
	
	}
	
	
}
