package camping.controller;

import java.io.IOException;
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
 * Servlet implementation class camp_search
 */
@WebServlet("/camp/search")
public class camp_search extends HttpServlet {
   private static final long serialVersionUID = 1L;
        private CampingService campService=new CampingServiceimpl();
        
        
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           req.setCharacterEncoding("utf-8");
           String addr=req.getParameter("addr");
           }
        
        
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           req.setCharacterEncoding("utf-8");
           
           Paging paging=campService.getPaging(req);
           List<Camp> camp=campService.camplist(paging);
           List<String> camp_g=campService.campgeolist();

           req.setAttribute("paging",paging);
           req.setAttribute("geolist", camp_g);
           req.setAttribute("list", camp);
           req.getRequestDispatcher("/WEB-INF/views/camp/search.jsp").forward(req, resp);
           }
        

}