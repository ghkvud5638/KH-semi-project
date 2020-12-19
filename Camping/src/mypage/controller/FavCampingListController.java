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

import mypage.dto.CAMP_INFO;
import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/favcamp")
public class FavCampingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService myPageService = new myPageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FavCampingListController [GET]");		
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
			TB_USER user = myPageService.selectUser(user_id);
			req.setAttribute("picture", user.getStored_name());
			req.setAttribute("user_id", user_id);
			List<CAMP_INFO> camp = myPageService.selectJJ(user_id);
			req.setAttribute("camplist", camp);
			req.getRequestDispatcher("/WEB-INF/views/myPage/favoriteCamp.jsp").forward(req, resp);
		}
	}
}
