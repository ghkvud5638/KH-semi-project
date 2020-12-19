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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import board.dto.Board;
import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;

@WebServlet("/board/detail")
public class BoardListDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardservice = new BoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardListDetailController [GET]");
		HttpSession session = req.getSession();
		
		if (session.getAttribute("loginid")==null) {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter(); 
			writer.println("<script>alert('로그인 해주세요.'); location.href='/user/login';</script>"); 
			writer.close();
			resp.sendRedirect("/user/login");
			return;
		}else {
			String userId = session.getAttribute("loginid").toString();
	//		System.out.println("BoardListDetailController  userId : " + userId);
			
			String param = req.getParameter("boardno");
			
			String boardno = null;
			//param의 값이 널값이 아니면서 빈 문자열이 아닐 때
			if( param != null && !"".equals(param)) {
				boardno = param; //형변환
			}
					
			Board board = new Board();
			board.setBoardno(boardno);
			
			Board detail = boardservice.selectBoardDetail(board);
			System.out.println("BoardListDetailController --> detail"+ detail);
			req.setAttribute("loginid", userId);
			req.setAttribute("detail", detail);
			
			req.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp")
			.forward(req, resp);

		}
	}
}
