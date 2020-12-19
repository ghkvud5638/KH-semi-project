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

@WebServlet("/myPage/deleteArticle")
public class BoardListDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private myPageService myPageService = new myPageServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardListDeleteController [POST]");
		int boardno = Integer.parseInt(req.getParameter("boardno"));
		System.out.println(boardno);
		boolean res = myPageService.deleteArticle(boardno);
		Map<String, Object> map = new HashMap<>();
		Gson gson = new Gson();
		if (res) {
			try {
				//삭제 성공
				PrintWriter out = resp.getWriter();
				map.put("msg", "ok");
				map.put("redirectUrl", "/myPage/myBoard");
				resp.setContentType("text/html; charset=utf-8"); 
				String json = gson.toJson(map);
				out.print(json); // 변환한 데이터 넘기기
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
