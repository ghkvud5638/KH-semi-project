package camping.controller;

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

import camping.service.CampingService;
import camping.service.impl.CampingServiceimpl;

/**
 * Servlet implementation class camp_like
 */
@WebServlet("/camp/like")
public class camp_like extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CampingService campService=new CampingServiceimpl();
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	     if (session.getAttribute("loginid")==null) {
	         resp.setContentType("text/html; charset=UTF-8");
	         PrintWriter writer = resp.getWriter(); 
	         writer.println("<script>alert('로그인 해주세요.'); location.href='/user/login';</script>"); 
	         writer.close();
	         resp.sendRedirect("/user/login");
	         return;
	      }else {
	    	  String id = session.getAttribute("loginid").toString();
	    	  String camp = req.getParameter("camp");
	     int result=campService.insertlike(id, camp);
		String msg = "";
		if(result==0) {
			msg="이미 추천하셨습니다.";
			
		}else if(result==1) {
			msg="감사합니다. 추천완료되었습니다.";
		}
		int count=campService.likecount(camp);
		String cnt=Integer.toString(count);
		
		//setAttri는 포워딩 , jsp 전용
//		req.setAttribute("cnt",cnt);
//		resp.setContentType("application/x-json; charset=UTF-8");
//		req.getRequestDispatcher("/WEB-INF/views/camp/detail.jsp")
//		.forward(req, resp);

		resp.setContentType("application/json; charset=UTF-8");
		
		//json으로 보내기
//		resp.getWriter().println("{\"msg\":\""+msg+"\", \"cnt\":"+cnt+"}");
		
		//맵 처리
		Map map = new HashMap();
		map.put("msg", msg);
		map.put("cnt", cnt);
		resp.getWriter().println(new Gson().toJson(map));
	      }
		
	}

}
