package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Paging;
import mypage.dto.Board;
import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/myBoard")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService myPageService = new myPageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("loginid")==null) {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter(); 
			writer.println("<script>alert('로그인 해주세요.'); location.href='/user/login';</script>"); 
			writer.close();
			resp.sendRedirect("/user/login");
			return;
		}else {
			String user_id = session.getAttribute("loginid").toString();
			Paging paging = myPageService.getPaging(req);
			req.setAttribute("paging", paging);
			System.out.println(user_id);		
			List<Board> list = myPageService.getList(paging, user_id);
			System.out.println(list);
			TB_USER user = myPageService.selectUser(user_id);
		  	req.setAttribute("picture", user.getStored_name());
			req.setAttribute("list", list);
			req.setAttribute("user_id", user_id);
			req.getRequestDispatcher("/WEB-INF/views/myPage/myBoard.jsp").forward(req, resp);
		}
	}
}
