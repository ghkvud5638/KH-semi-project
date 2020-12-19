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
import mypage.dto.TB_RECOMMENT;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/recomment")
public class ReCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService  mypageService = new myPageServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("RECController [POST]");
			boolean res = false;

			String recommentContent = req.getParameter("recomment");
			String boardno = req.getParameter("boardno");
			String recommentId = req.getParameter("recommentId");
			String up = (String)req.getParameter("up");
			HttpSession session = req.getSession();
			String userId = session.getAttribute("loginid").toString();
			System.out.println(boardno);
			TB_RECOMMENT recomment = new TB_RECOMMENT();
				if("update".equals(up)) {
				System.out.println("이제 수정할거야");
					String comment = req.getParameter("recomment");
					System.out.println(comment);
					recomment.setContent(comment);
					res = mypageService.updateReComment(recommentId, recomment);
					req.setAttribute("recontent", recomment.getContent());
					System.out.println(req.getAttribute("recontent"));
					System.out.println("무슨값이들어갈까" +comment);

				} else if("remove".equals(up)) {
					res = mypageService.deleteReComment(recommentId);
				} else {
					recomment.setBoardno(boardno);
					recomment.setComment_parent(recommentId);
					recomment.setContent(recommentContent);
					recomment.setUser_id(userId);
					res = mypageService.insertReComment(recomment);
					
				}
			
			Map<String, Object> map = new HashMap<>();
			Gson gson = new Gson();
			if (res) {
				try {
					PrintWriter out = resp.getWriter();
					map.put("msg", "ok");
					map.put("redirectUrl", "/myPage/askdetail");
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
