package camping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import camping.dto.Rev;
import camping.service.ReservationService;
import camping.service.impl.ReservationServiceimpl;

/**
 * Servlet implementation class camp_success
 */
@WebServlet("/camp/success")
public class camp_success extends HttpServlet {
	ReservationService rev = new ReservationServiceimpl();
	private static final long serialVersionUID = 1L;
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/camp/success.jsp").forward(req, resp);;
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
			String camp_name = req.getParameter("camp_name");
			String p = req.getParameter("people");
			String first = req.getParameter("first");
			String last = req.getParameter("last");
			String price = req.getParameter("total");
			String carnum = req.getParameter("carnum");
			String tel = req.getParameter("tel");
			String name = req.getParameter("name");
			int total = Integer.parseInt(price);
			int people = Integer.parseInt(p);
			Rev r = new Rev();
			r.setCAMP_NAME(camp_name);
			r.setPeople(people);
			r.setFirst(first);
			r.setLast(last);
			r.setTotal(total);
			r.setCarNum(carnum);
			r.setTel(tel);
			r.setName(name);
			r.setUserid(userid);
			rev.rev_insert(r);
		}

	}

}
