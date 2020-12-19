package mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.dto.Board;
import mypage.dto.CAMP_INFO;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService mypageService = new myPageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MainController [GET]");
		List<CAMP_INFO> topCampList = mypageService.getTopCamp();
		System.out.println("topCampList : "+ topCampList);
		
	    List<Board> CommunityList = mypageService.getMainBoardList("community");
	    List<Board> AssignmentList = mypageService.getMainBoardList("assignment"); 
	    System.out.println("CommunityList : "+CommunityList);
	    System.out.println("AssignmentList : "+AssignmentList);
		
	    req.setAttribute("CommunityList", CommunityList);
	    req.setAttribute("AssignmentList", AssignmentList);
		req.setAttribute("topCampList", topCampList);
		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);

	}
}
