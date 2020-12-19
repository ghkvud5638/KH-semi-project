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

import mypage.dto.TB_PAY;
import mypage.dto.TB_REV;
import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/paySearch")
public class PayListMonthSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService mypageService = new myPageServiceImpl(); 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("PayListMonthSearchController [POST]");
		HttpSession session = req.getSession();
	    String user_id = session.getAttribute("loginid").toString();
		String FirstDate = req.getParameter("FirstDate");
		String SecondDate = req.getParameter("SecondDate");
		TB_USER user = mypageService.selectUser(user_id);
		System.out.println("FirstDate : "+FirstDate);
		System.out.println("SecondDate : "+SecondDate);
		List<TB_PAY> payList = mypageService.getPayListByDate(user_id,SecondDate,FirstDate);
		List<TB_REV> RevList = mypageService.getRevListByDate(user_id,SecondDate,FirstDate);
		
		System.out.println("payList : "+payList);
		req.setAttribute("picture", user.getStored_name());
		req.setAttribute("payList", payList);
		req.setAttribute("RevList", RevList);
		req.getRequestDispatcher("/WEB-INF/views/myPage/payList.jsp").forward(req, resp);
	}
}
