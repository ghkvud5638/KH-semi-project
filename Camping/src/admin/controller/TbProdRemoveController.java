package admin.controller;

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

import admin.service.face.TbProductService;
import admin.service.impl.TbProductServiceImpl;


@WebServlet("/admin/prodremove")
public class TbProdRemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TbProductService tbprod = new TbProductServiceImpl();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	  	if(session.getAttribute("loginid") == null) {//로그인 되어있는지 확인
 			resp.sendRedirect("/user/login");
	  		} else {
	  			String grade = (String)session.getAttribute("grade");//세션에서 등급 불러옴
	  			System.out.println(grade);
	  	  
	  		if(("admin").equals(grade)) {//등급이 관리자일 경우에만 관리자페이지 들어갈 수 있음
	  			System.out.println("작동");
	  			String prod_id = req.getParameter("prod_id");
	  			System.out.println(prod_id);			
	  			boolean res = tbprod.removeProd(prod_id);
	  			if (res) {
	  				resp.sendRedirect("/admin/prodlist");
	  			}
	  			} else {
	  			resp.sendRedirect("/myPage/main");
	  		}
	  	}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("작동");
		String prod_id = req.getParameter("prod_id");
		System.out.println(prod_id);			
		boolean res = tbprod.removeProd(prod_id);
		
		Map<String, Object> map = new HashMap<>();
		Gson gson = new Gson();
		
		if (res) {
			try {
				//삭제 성공
				PrintWriter out = resp.getWriter();
				map.put("msg", "ok");
				map.put("redirectUrl", "/admin/prodlist");
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
