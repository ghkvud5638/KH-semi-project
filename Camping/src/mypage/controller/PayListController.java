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

import mypage.dto.TB_PAY;
import mypage.dto.TB_REV;
import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/payList")
public class PayListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService mypageService = new myPageServiceImpl();
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
			System.out.println("PayListController [GET]");
			TB_USER user = mypageService.selectUser(user_id);
			req.setAttribute("picture", user.getStored_name());
			//
			List<TB_PAY> payList = mypageService.getPayList(user_id);
			List<TB_REV> revList = mypageService.getRevList(user_id);
			System.out.println(payList);
			req.setAttribute("payList", payList);
			req.setAttribute("RevList", revList);
			req.getRequestDispatcher("/WEB-INF/views/myPage/payList.jsp").forward(req, resp);
		}
		
	}
}
