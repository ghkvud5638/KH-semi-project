package mypage.controller;

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

import mypage.dto.TB_ASK_BOARD;
import mypage.dto.TB_COMMENT;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

//1:1문의 게시글 등록
@WebServlet("/myPage/askarticleapply")
public class AskArticleApplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService  mypageService = new myPageServiceImpl();
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("AskToAdminController [POST]");
	  		req.setCharacterEncoding("UTF-8");
	  		resp.setContentType("text/html;charset=utf-8");
			HttpSession session = req.getSession();
		  	String user_id = session.getAttribute("loginid").toString(); //현재 세션에 로그인 되어있는 아이디
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String category = req.getParameter("category");
//			System.out.println("user_id : "+user_id);
//			System.out.println(title+" ,"+content);
//			System.out.println(category);
			TB_ASK_BOARD askBoard = new TB_ASK_BOARD();
			askBoard.setBoard_cate(category);
			askBoard.setTitle(title);
			askBoard.setContent(content);
			askBoard.setUser_id(user_id);
			mypageService.insertAsk(askBoard);
			resp.sendRedirect("/myPage/ask");
	}			
}
