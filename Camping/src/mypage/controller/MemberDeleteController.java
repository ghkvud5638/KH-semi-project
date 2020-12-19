package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/myPage/memberdelete")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService mypageservice = new myPageServiceImpl();
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("MemberDeleteController [POST]");
			HttpSession session = req.getSession();
		    String userId = session.getAttribute("loginid").toString(); 
			System.out.println("userId : "+userId);
			boolean res = mypageservice.deleteMember(userId);
			if (res) {
		  		session = req.getSession();
		  		session.invalidate();
				resp.sendRedirect("/myPage/main");
			}else {
				System.out.println("계정 삭제 실패");
			}
		}
}
