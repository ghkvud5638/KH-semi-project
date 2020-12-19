package shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.dto.Board;
import shopping.service.face.BoardService;
import shopping.service.impl.BoardServiceImpl;

@WebServlet("/shopping/QAdetail")
public class QADetailController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   BoardService boardService = new BoardServiceImpl();

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String boardno = req.getParameter("boardno");
      System.out.println("boardno : " + boardno);
      // 조회하기(boardno를 이용)
      Board reviewInfo = boardService.selectByQABoardno(boardno);

      System.out.println("reviewInfo : " + reviewInfo);
      System.out.println(reviewInfo);
      req.setAttribute("reviewInfo", reviewInfo);

      req.getRequestDispatcher("/WEB-INF/views/shopping/QAdetail.jsp").forward(req, resp);

   }
}