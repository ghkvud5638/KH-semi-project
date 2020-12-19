package shopping.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.dto.Board;
import shopping.service.face.BoardService;
import shopping.service.impl.BoardServiceImpl;


@WebServlet("/shopping/reviewDetail")
public class ReviewDetailController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   BoardService boardService = new BoardServiceImpl();
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("---reviewdetailcontroller [get]---");
      HttpSession session = req.getSession();
      String userId = session.getAttribute("loginid").toString();
      System.out.println("BoardListDetailController  userId : " + userId);
      
      String boardno = req.getParameter("boardno");
      System.out.println("boardno : " + boardno);
      
      //조회하기(boardno를 이용)
      Board reviewInfo = boardService.selectByBoardno(boardno);

      System.out.println("reviewInfo : " + reviewInfo);
      
      req.setAttribute("reviewInfo", reviewInfo);

      req.getRequestDispatcher("/WEB-INF/views/shopping/reviewDetail.jsp").forward(req, resp);
   }
}