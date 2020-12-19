package camping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import camping.dto.Camp;
import camping.service.CampingService;
import camping.service.impl.CampingServiceimpl;


/**
 * Servlet implementation class camp_selectsearch
 */
@WebServlet("/camp/search3")
public class camp_selectsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CampingService campService=new CampingServiceimpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/x-json; charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String address= req.getParameter("local");
		String induty=req.getParameter("induty");
		String keyword=req.getParameter("keyword");
		if ("".equals(keyword)){
			keyword="0";
		}
			
		if(!"0".equals(keyword) || !"0".equals(induty) || !"0".equals(address)) {
			List<Camp> camp=new ArrayList<Camp>();
			camp=campService.selectlist_all(address,induty,keyword);
			System.out.println("camp : "+camp);
			req.setAttribute("list2", camp);
	 		req.getRequestDispatcher("/WEB-INF/views/camp/select_list.jsp").forward(req, resp);
		}else if("0".equals(keyword) && "0".equals(induty) && "0".equals(address)) {
			req.getRequestDispatcher("/WEB-INF/views/camp/null_list.jsp").forward(req, resp);
			
		}
		
	}

}
