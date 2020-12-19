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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import mypage.dto.TB_COMMENT;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

//댓글 등록
@WebServlet("/myPage/commentapply")
public class AskCommentApplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService  mypageService = new myPageServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AskDetailController [POST]");
		String commentContent = req.getParameter("commentContent");
		String boardno = req.getParameter("boardno");
		HttpSession session = req.getSession();
		String userId = session.getAttribute("loginid").toString();
//		System.out.println(commentContent);
//		System.out.println(boardno);
//		System.out.println(userId);
		TB_COMMENT comment = new TB_COMMENT();
		comment.setBoardno(boardno);
		comment.setContent(commentContent);
		comment.setUser_id(userId);
		boolean res = mypageService.insertComment(comment);
		Map<String, Object> map = new HashMap<>();
		Gson gson = new Gson();
		if (res) {
			try {
				PrintWriter out = resp.getWriter();
				map.put("msg", "ok");
//				map.put("redirectUrl", "/myPage/askdetail");
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
