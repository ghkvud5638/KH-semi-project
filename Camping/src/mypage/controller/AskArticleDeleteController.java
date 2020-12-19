package mypage.controller;

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

import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;


@WebServlet("/myPage/askdelete")
public class AskArticleDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private myPageService mypageService = new myPageServiceImpl(); 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AskArticleDeleteController [POST]");
		
		String boardno = req.getParameter("boardno");
//		System.out.println("boardno : "+boardno);
		
		boolean res = mypageService.deleteAsk(boardno);
		Map<String, Object> map = new HashMap<>();
		Gson gson = new Gson();
		
		if (res) {
			try {
				PrintWriter out = resp.getWriter();
				map.put("msg", "ok");
				map.put("redirectUrl", "/myPage/ask");
				resp.setContentType("text/html; charset=utf-8"); 
				String json = gson.toJson(map);
				out.print(json);
				out.flush();
				out.close();
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			PrintWriter out = resp.getWriter();
			map.put("msg", "NotOk");
			resp.setContentType("text/html; charset=utf-8"); 
			String json = gson.toJson(map);
			out.print(json);
			out.flush();
			out.close();
		}
	}
}
