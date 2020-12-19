package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dto.TB_REV;
import admin.service.face.CampRevService;
import admin.service.impl.CampRevServiceImpl;


@WebServlet("/admin/campmanager")
public class RevListAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CampRevService campRevService = new CampRevServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("RevListAdminController [GET]");
		List<TB_REV> revList = campRevService.getRevList();
		req.setAttribute("revList", revList);
		req.getRequestDispatcher("/WEB-INF/views/admin/camp/campRevList.jsp").forward(req, resp);
	}
}
