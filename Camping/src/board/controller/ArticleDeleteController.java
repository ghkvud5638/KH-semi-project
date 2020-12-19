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

import com.google.gson.Gson;

import board.service.face.BoardService;
import board.service.impl.BoardServiceImpl;


@WebServlet("/board/delete")
public class ArticleDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardservice = new BoardServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardListDetailController [POST]");
		int boardno = Integer.parseInt(req.getParameter("boardNo"));
		System.out.println("boardno : "+boardno);
		
		boolean res = boardservice.deleteArticle(boardno);
		System.out.println("res : "+res);
		
		Map<String, Object> map = new HashMap<>();
		Gson gson = new Gson();
		
		if (res) {
			try {
				PrintWriter out = resp.getWriter();
				map.put("msg", "ok");
				map.put("redirectUrl", "/board/community");
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
