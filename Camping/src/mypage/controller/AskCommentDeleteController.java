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

//댓글 삭제, comment.jsp
@WebServlet("/myPage/askcommentdelete")
public class AskCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService  mypageService = new myPageServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("AskCommentController [POST]");
		String commentId = req.getParameter("commentId");
		boolean res = mypageService.deleteComment(commentId);
		Map<String, Object> map = new HashMap<>();
		Gson gson = new Gson();
		if (res) {
			try {
				PrintWriter out = resp.getWriter();
				map.put("msg", "ok");
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
