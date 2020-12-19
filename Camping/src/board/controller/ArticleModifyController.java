package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.dto.Board;
import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;

@WebServlet("/board/modify")
public class ArticleModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardservice = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ArticleModifyController [GET]");
		
		String boardno = req.getParameter("boardno"); 
		System.out.println("ArticleModifyController[GET] : "+ boardno);
		
		//조회수 처리를 하면서 detail을 보는게 아니라,
		//단순히 boardno을 매개로 게시글의 데이터를 빼 올때,
		// -> 게시글 수정용
		Board board = new Board();
		board = boardservice.selectArticle(boardno);
		
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/articleModify.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ArticleModifyController [POST]");
		req.setCharacterEncoding("UTF-8");
  		resp.setContentType("text/html;charset=utf-8");
		String boardno = req.getParameter("boardno");
		String title = req.getParameter("title"); 
		String content = req.getParameter("content");
		System.out.println("boardno : "+boardno);
		System.out.println("title : "+title);
		System.out.println("content : "+content);
		
		Board board = new Board();
		board.setTitle(title);
		board.setBoardno(boardno);
		board.setContent(content);
		boolean res = boardservice.modifyArticle(board);
		if (res) {
			resp.sendRedirect("/board/detail?boardno="+boardno);
		}else {
			System.out.println("게시글 수정 실패.."); // 실패 페이지 jsp 만들까?
		}
	}
}
