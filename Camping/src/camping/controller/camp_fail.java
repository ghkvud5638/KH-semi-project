package camping.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class camp_fail
 */
@WebServlet("/camp/fail")
public class camp_fail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("페일 두겟");
    	req.getRequestDispatcher("/WEB-INF/views/camp/fail.jsp").forward(req,resp);
    }

}
