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


@WebServlet("/shopping/review")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BoardService boardService = new BoardServiceImpl();
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("확인");
	
	String hid=req.getParameter("hid");
	System.out.println(hid);
	req.setAttribute("hid", hid);
	req.getRequestDispatcher("/WEB-INF/views/shopping/review.jsp").forward(req, resp);
	
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("post확인");
	
	req.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html;charset=utf-8");
	
	String title = req.getParameter("title");
	String content = req.getParameter("content");
	String category = req.getParameter("category");
	String prodid=req.getParameter("prodid");
	
	System.out.println("prodid" + prodid);
	System.out.println("title : " + title);
	System.out.println("content : " + content);
	System.out.println("category : " + category);
	
	HttpSession session = req.getSession();
	String loginid = (String)session.getAttribute("loginid");	
	
	
	Board board = new Board();
	board.setTitle(title);
	board.setContent(content);
	board.setBoardCate(category);
	board.setUser_id(loginid);
	board.setId(prodid);
	boardService.applyReview(board);
	resp.sendRedirect("http://localhost:8088/shopping/detail?prodno=" + prodid +"#review");
//	req.getRequestDispatcher("/WEB-INF/views/shopping/detail?prodno="+prodid+"#review").forward(req,resp);
	}


}
