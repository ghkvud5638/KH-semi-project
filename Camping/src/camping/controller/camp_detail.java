package camping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import camping.dto.Camp;
import camping.service.CampingService;
import camping.service.impl.CampingServiceimpl;
@WebServlet("/camp/detail")
public class camp_detail extends HttpServlet {    
	private static final long serialVersionUID = 1L;
	CampingService campService = new CampingServiceimpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("loginid") == null) {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('로그인 해주세요.'); location.href='/user/login';</script>");
			writer.close();
			resp.sendRedirect("/user/login");
			return;
		} else {
			String userid = session.getAttribute("loginid").toString();
			String id = req.getParameter("camp_id");
			String j=req.getParameter("j");
			int camp_id = Integer.parseInt(id);
			Camp camp = new Camp();
			camp = campService.select_id(camp_id);
			List<String> image = campService.select_id_image(camp_id);
			int result = campService.selectlike(camp_id);
			String count = Integer.toString(result);
			int jjim = campService.selectJJim(camp_id, userid);
			String msg = "";
			if (jjim == 0) {
				msg = "♡ 하기";
			} else if (jjim == 1) {
				msg = "♥ 찜";
			}
			req.setAttribute("j", j);
			req.setAttribute("msg", msg); // 찜 버튼값
			req.setAttribute("count", count); // 추천 수
			req.setAttribute("image", image); // 이미지 리스트
			req.setAttribute("camp", camp); // 캠프장 정보
			req.getRequestDispatcher("/WEB-INF/views/camp/detail.jsp").forward(req, resp);
		}
	
	}
}
