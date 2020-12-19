package shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.dto.Board;
import shopping.service.face.BoardService;
import shopping.service.impl.BoardServiceImpl;


@WebServlet("/shopping/QA")
public class QAController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("-----------------");
		System.out.println("/shopping/QA");
		String prodid = req.getParameter("prodid");
		System.out.println("나 작동 되나"+prodid);
				
		req.setAttribute("prodid", prodid);
		
		req.getRequestDispatcher("/WEB-INF/views/shopping/QA.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post확인");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//id 가져오기
		HttpSession session = req.getSession();
		String loginid = (String)session.getAttribute("loginid");
		System.out.println("loginid : " + loginid);
		
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String category = req.getParameter("category");
		String prodid=req.getParameter("prodid");
		
		System.out.println("title : " + title + ", content : " + content + ", category : " + category + ", prodid : " + prodid);
		
		
		//Board 객체 만들기
		Board board = new Board();
		
		board.setTitle(title);
		board.setContent(content);
		board.setBoardCate(category);
		board.setUser_id(loginid);
		board.setId(prodid);
		
		boardService.applyQA(board);
		
		resp.sendRedirect("http://localhost:8088/shopping/detail?prodno=" + prodid + "#QAtitle");
	}
}
