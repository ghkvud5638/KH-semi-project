package admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.service.face.TbSalesService;
import admin.service.impl.TbSalesServiceImpl;
import admin.common.Paging;


@WebServlet("/admin/sales")
public class TbProdSalesController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private TbSalesService tbsales = new TbSalesServiceImpl();
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
        if(session.getAttribute("loginid") == null) {//로그인 되어있는지 확인
          resp.sendRedirect("/user/login");
           } else {
              String grade = (String)session.getAttribute("grade");//세션에서 등급 불러옴
              System.out.println(grade);
          
           if(("admin").equals(grade)) {//등급이 관리자일 경우에만 관리자페이지 들어갈 수 있음
              Paging paging = tbsales.getPaging(req);
              req.setAttribute("paging", paging);
              
              Calendar now = Calendar.getInstance();
              Calendar st = Calendar.getInstance();
              st.set(Calendar.YEAR , now.get(Calendar.YEAR));
              st.set(Calendar.MONTH , now.get(Calendar.MONTH));
              st.set(Calendar.DATE , now.getActualMinimum(Calendar.DATE));
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
              
              String start = sdf.format(st.getTime());
              String end = sdf.format(now.getTime());
              
              System.out.println("내날짜는" + start);
              System.out.println("내날짜는" + end);
              
              List<HashMap<String, Object>> list = tbsales.SalesListDate(paging, start, end);
              
              req.setAttribute("list", list);
              req.setAttribute("start", start);
              req.setAttribute("end", end);
              
              
              System.out.println(start);
              System.out.println(end);
              
              System.out.println(list);
              req.getRequestDispatcher("/WEB-INF/views/admin/sales/sales.jsp").forward(req, resp);
              } else {
              resp.sendRedirect("/myPage/main");
           }
        }   
      
      
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println(req.getParameter("startdate"));
      System.out.println(req.getParameter("enddate"));
      
   
      Paging paging = tbsales.getPaging(req);
      String start = req.getParameter("startdate");
      String end = req.getParameter("enddate");
      req.setAttribute("paging", paging);
      
      
      List<HashMap<String, Object>> list = tbsales.SalesListDate(paging, start, end);
      System.out.println(start);
      System.out.println(end);
      
      
      req.setAttribute("list", list);
      req.setAttribute("start", start);
      req.setAttribute("end", end);
      System.out.println(list);
      req.getRequestDispatcher("/WEB-INF/views/admin/sales/sales.jsp").forward(req, resp);
   }

}