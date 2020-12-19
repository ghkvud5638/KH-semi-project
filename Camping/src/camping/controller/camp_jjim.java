package camping.controller;

import java.io.IOException;
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
 * Servlet implementation class camp_jjim
 */
@WebServlet("/camp/jjim")
public class camp_jjim extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CampingService campService=new CampingServiceimpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String camp = req.getParameter("camp");
		HttpSession session = req.getSession();
	    String id = session.getAttribute("loginid").toString();
	    int result=campService.jjim(id, camp);
		String a="";
		String b="";
		if(result==0) {
			a="이미 찜하셨습니다.";
			b="♥ 찜";
		}else if(result==1) {
			a="찜 완료되었습니다.";
			b="♡ 찜하기";
		}
		resp.setContentType("application/json; charset=UTF-8");
		Map map = new HashMap();
		map.put("a",a);
		map.put("b",b);
		resp.getWriter().println(new Gson().toJson(map));
	}

}
