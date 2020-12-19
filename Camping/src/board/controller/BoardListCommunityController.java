package board.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dto.Board;
import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;
import common.Paging;

@WebServlet("/board/community")
public class BoardListCommunityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardservice = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardListCommunityController [GET]");
	    String uri = req.getRequestURI();
	    String category = uri.split("/board/")[1];
	    System.out.println(category);

		Paging paging = boardservice.getBoardPaging(req, category); // 전체 게시글 수, 현재 페이지, 화면에 보이는 시작&끝 페이지 번호 등을 포함한 페이징 객체생성
		req.setAttribute("paging", paging); 
		
		List<Board> list = boardservice.getBoardList(paging, category); // 개수에 맞는 게시글의 수 불러옴
		System.out.println(list);
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/board/boardlist.jsp")
		.forward(req, resp);
	}
}
