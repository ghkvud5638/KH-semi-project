package mypage.controller;

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

import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/deleteCart")
public class CartListDeleteController extends HttpServlet {
	private myPageService myPageService = new myPageServiceImpl();
	private static final long serialVersionUID = 1L;
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("CartListDeleteController [POST]");
			String cartId =  req.getParameter("cartId");
//			System.out.println(cartId);
			boolean res = myPageService.deleteCart(cartId);
			Map<String, Object> map = new HashMap<>();
			Gson gson = new Gson();
			if (res) {
				try {
					PrintWriter out = resp.getWriter();
					map.put("msg", "ok");
					map.put("redirectUrl", "/myPage/cartlist");
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
