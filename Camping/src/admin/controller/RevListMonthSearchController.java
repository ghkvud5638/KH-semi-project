package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.face.CampRevService;
import admin.service.impl.CampRevServiceImpl;
import admin.dto.TB_REV;

@WebServlet("/admin/revSearch")
public class RevListMonthSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CampRevService campRevService = new CampRevServiceImpl();	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String FirstDate = req.getParameter("FirstDate");
		System.out.println("FirstDate : "+FirstDate);
		List<TB_REV> RevList = campRevService.getRevListByDate(FirstDate);
		req.setAttribute("revList", RevList);
		req.getRequestDispatcher("/WEB-INF/views/admin/camp/campRevList.jsp").forward(req, resp);
	}
}
