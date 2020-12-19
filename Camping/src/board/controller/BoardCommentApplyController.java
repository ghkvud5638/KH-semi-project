package board.controller;

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

import board.dto.TB_BOARD_COMMENT;
import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;
import oracle.jdbc.driver.T4CXAConnection;

@WebServlet("/board/commentapply")
public class BoardCommentApplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("BoardCommentApplyController [POST]");
			String commentContent = req.getParameter("commentContent");
			String boardno = req.getParameter("boardno");
			HttpSession session = req.getSession();
			String userId = session.getAttribute("loginid").toString();
			System.out.println("commentContent : "+commentContent);
			System.out.println("boardno : "+boardno);
			TB_BOARD_COMMENT comment = new TB_BOARD_COMMENT();
			comment.setBoardno(boardno);
			comment.setContent(commentContent);
			comment.setUser_id(userId);
			boolean res = boardService.insertComment(comment); // 댓글 인서트하는 메소드
			Map<String, Object> map = new HashMap<>();
			Gson gson = new Gson();
			if (res) {
				try {
					PrintWriter out = resp.getWriter();
					map.put("msg", "ok");
//					map.put("redirectUrl", "/board/detail");
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
