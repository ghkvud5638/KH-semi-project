package camping.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import camping.service.CampingService;
import camping.service.impl.CampingServiceimpl;


@WebServlet("/camp/rev")
public class camp_rev extends HttpServlet {
	CampingService campService = new CampingServiceimpl();
	private static final long serialVersionUID = 1L;
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userid = session.getAttribute("loginid").toString();
		String camp_name=req.getParameter("hid-campid");
		String startdate=req.getParameter("start-date");
		String lastdate=req.getParameter("last-date");
		SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date f=fm.parse(startdate);
			Date e=fm.parse(lastdate);
			long d=e.getTime()-f.getTime();
			long t=d/(24*60*60*1000);
			String day=Long.toString(t);
			String people=req.getParameter("people");
			System.out.println(camp_name + "/" + startdate + "/" + lastdate + "/" + people);
			req.setAttribute("day", day);
			req.setAttribute("camp_name", camp_name);
			req.setAttribute("startdate", startdate);
			req.setAttribute("lastdate",lastdate);
			req.setAttribute("people", people);
			req.getRequestDispatcher("/WEB-INF/views/camp/rev.jsp").forward(req, resp);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}   

}
