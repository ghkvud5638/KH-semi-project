package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dto.Board;
import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;
import common.Paging;


@WebServlet("/admin/board")
public class BoardListAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardservice = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	  	if(session.getAttribute("loginid") == null) {//로그인 되어있는지 확인
 			resp.sendRedirect("/user/login");
	  		} else {
	  			String grade = (String)session.getAttribute("grade");//세션에서 등급 불러옴
	  			System.out.println(grade);
	  	  
	  		if(("admin").equals(grade)) {//등급이 관리자일 경우에만 관리자페이지 들어갈 수 있음
	  			System.out.println("BoardListAdminCommunityController [GET]");

	  			Paging paging = boardservice.getAllBoardPaging(req); // 전체 게시글 수, 현재 페이지, 화면에 보이는 시작&끝 페이지 번호 등을 포함한 페이징 객체생성
	  			req.setAttribute("paging", paging); 
	  			
	  			List<Board> list = boardservice.getAllBoardList(paging); // 개수에 맞는 게시글의 수 불러옴
	  			System.out.println(list);
	  			
	  			req.setAttribute("list", list);
	  			req.getRequestDispatcher("/WEB-INF/views/admin/board/boardlist.jsp")
	  			.forward(req, resp);
	  			} else {
	  			resp.sendRedirect("/myPage/main");
	  		}
	  	}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardListAdminCommunityController [POST]");
		String category = req.getParameter("cate");
		String search = req.getParameter("search");
		System.out.println(search);

//		if("all".equals(category)) {
//			Paging paging = boardservice.getAllBoardPaging(req, search);
//			req.setAttribute("paging", paging); 
//			
//			list = boardservice.getAllBoardList(paging, search);
//			
//		} else {
	    System.out.println(category);

		Paging paging = boardservice.getSearchBoardPaging(req, category, search); // 전체 게시글 수, 현재 페이지, 화면에 보이는 시작&끝 페이지 번호 등을 포함한 페이징 객체생성
		req.setAttribute("paging", paging); 
		
		List<Board> list = boardservice.getSearchBoardList(paging, category, search); // 개수에 맞는 게시글의 수 불러옴
		System.out.println(list);
//		}
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/admin/board/boardlist.jsp")
		.forward(req, resp);
	}
	
}
