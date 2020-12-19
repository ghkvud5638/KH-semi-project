package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.TB_BOARD_COMMENT;
import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;
//댓글 조회
@WebServlet("/board/comment")
public class BoardCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardSerivce = new BoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		System.out.println("BoardCommentController [GET]");
		String param = req.getParameter("boardno");
		String boardno = null;
		if( param != null && !"".equals(param)) {
			boardno = param;
		}
		System.out.println(boardno);
		List<TB_BOARD_COMMENT> commentList = boardSerivce.getCommentList(boardno);
		System.out.println("commentList : "+commentList);
		req.setAttribute("commentList", commentList);
		req.getRequestDispatcher("/WEB-INF/views/board/comment.jsp").forward(req, resp);
	}
	

}
