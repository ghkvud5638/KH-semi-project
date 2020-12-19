package board.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dto.Board;
import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;

@WebServlet("/board/apply")
public class BoardApplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService  boardservice = new BoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/board/apply  [GET] 접속");		
		req.getRequestDispatcher("/WEB-INF/views/board/boardApply.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/apply [POST] 게시글 작성 버튼 클릭");
		
		req.setCharacterEncoding("UTF-8");
  		resp.setContentType("text/html;charset=utf-8");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String category = req.getParameter("category");
		String vid = req.getParameter("vid");
		HttpSession session = req.getSession();
		String user_id = (String)session.getAttribute("loginid");
		
		System.out.println("user_id : "+user_id);
		System.out.println("title : "+title);
		System.out.println("content : "+content);
		System.out.println("category : "+category);
		System.out.println("vid : "+vid);
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setUser_id(user_id);
		board.setBoardCate(category);
		board.setVid_url(vid);
		
		boardservice.applyArticle(board);
		
		resp.sendRedirect("/board/community");
	}
	
	
	
}
