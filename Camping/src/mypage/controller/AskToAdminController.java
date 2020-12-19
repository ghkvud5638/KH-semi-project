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

import common.AskPaging;
import mypage.dto.TB_ASK_BOARD;
import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/ask")
public class AskToAdminController extends HttpServlet {
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
				System.out.println("AskToAdminController [GET]");
			  	String user_id = session.getAttribute("loginid").toString(); //현재 세션에 로그인 되어있는 아이디
			  	System.out.println("ask page userId : "+user_id);
				//
			  	TB_USER user = myPageService.selectUser(user_id);
			  	req.setAttribute("picture", user.getStored_name());  
			  	req.setAttribute("user_id", user_id);
			  	//
			  	AskPaging askPaging = myPageService.getAskPaging(req);
			  	req.setAttribute("askPaging", askPaging);
			  	//
			  	List<TB_ASK_BOARD> askBoardList = myPageService.getAskBoardList(askPaging, user_id);
			  	System.out.println(askBoardList);
			  	//
			  	req.setAttribute("list", askBoardList);
				req.getRequestDispatcher("/WEB-INF/views/myPage/ask.jsp").forward(req, resp);
			}
		}
}
