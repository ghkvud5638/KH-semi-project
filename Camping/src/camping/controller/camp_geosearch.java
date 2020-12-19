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
 * Servlet implementation class camp_geosearch
 */
@WebServlet("/camp/search2")
public class camp_geosearch extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private CampingService campService=new CampingServiceimpl();
    
   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
//      resp.setContentType("application/x-json; charset=UTF-8");
//      req.setCharacterEncoding("utf-8");
//      Paging paging=campService.getPaging(req);
//      String addr=req.getParameter("addr");
//       List<Camp> camp=new ArrayList<Camp>();
//       camp=campService.S_list(addr,paging);
//       System.out.println(camp);
//       req.setAttribute("list", camp);
//       req.setAttribute("paging",paging);
//       req.setAttribute("addr", addr);
//       req.getRequestDispatcher("/WEB-INF/views/camp/list.jsp").forward(req, resp);
//       Map<String,Object> map=new HashMap<>();
//       Gson gson=new Gson();
//       map.put("list",camp);
//       PrintWriter out=resp.getWriter();
//       String json=gson.toJson(map);
//       out.print(json);
//       out.flush();
//       out.close();
   
    }
    @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("application/x-json; charset=UTF-8");
      req.setCharacterEncoding("utf-8");
      Paging paging=campService.getPaging(req);
      String addr=req.getParameter("addr");
      List<Camp> camp=new ArrayList<Camp>();
      if("null".equals(addr)) {
         System.out.println("널소환");
         camp=campService.camplist(paging);
      }else {
         System.out.println("널 값 아닌거 소환");
          camp=campService.S_list(addr,paging);
          
      }
       req.setAttribute("list", camp);
       req.setAttribute("paging",paging);
       req.setAttribute("addr", addr);
       req.getRequestDispatcher("/WEB-INF/views/camp/list.jsp").forward(req, resp);
       
       
    }
}