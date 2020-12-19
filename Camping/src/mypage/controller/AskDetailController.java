package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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
import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/askdetail")
public class AskDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService  mypageService = new myPageServiceImpl();
	
	//1:1문의 게시글 조회, ask.jsp
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("AskDetailController [GET]");
			HttpSession session = req.getSession();
			String userId = session.getAttribute("loginid").toString();
			String param = req.getParameter("boardno");
			String boardno = null;
			if( param != null && !"".equals(param)) {
				boardno = param;
			}
//			System.out.println(boardno);
			TB_ASK_BOARD askBoard = mypageService.getAskArticle(boardno);
			System.out.println("AskDetailController askBoard :  "+askBoard);
			req.setAttribute("loginid", userId);
			req.setAttribute("askBoard", askBoard);
			TB_USER user = mypageService.selectUser(userId);
			req.setAttribute("picture", user.getStored_name());
			//댓글 조회
			//1:1문의 게시글 번호 가져와서 댓글을 번호순으로 뿌려줌,시분초까지 보이도록 수정
			//조회값이 널일 경우 처리 jsp에서
			List<TB_COMMENT> commentList = mypageService.getCommentList(boardno); 
			System.out.println(commentList);
			req.setAttribute("commentList", commentList);
			req.getRequestDispatcher("/WEB-INF/views/myPage/askDetail.jsp").forward(req, resp);
		}
	
}
