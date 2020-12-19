package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mypage.dto.TB_COMMENT;
import mypage.dto.TB_RECOMMENT;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

/**
 * Servlet implementation class AskCommentController
 */
@WebServlet("/myPage/comment")
public class AskCommentController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private myPageService  mypageService = new myPageServiceImpl();

   @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         
         System.out.println("AskCommentController");
         String param = req.getParameter("boardno");
         String boardno = null;
         if( param != null && !"".equals(param)) {
            boardno = param;
         }
         //boardno 받아야됨
         System.out.println(boardno);
         List<TB_COMMENT> commentList = mypageService.getCommentList(boardno); 
         List<TB_RECOMMENT> recommentList = mypageService.getReCommentList(boardno); 
         System.out.println(commentList);
         System.out.println(recommentList);
         req.setAttribute("commentList", commentList);
         req.setAttribute("recommentList", recommentList);
//         int size = 10*req.getParameter("cmtsize");
         System.out.println("댓글개수가몇개야" + commentList.size());
         System.out.println("답글개수가몇개야" + recommentList.size());
         
         int cmtsize = 10;
         if(cmtsize < commentList.size()) {
         req.setAttribute("cmtsize", cmtsize);
         } else {
         cmtsize = commentList.size();
         req.setAttribute("cmtsize", cmtsize);
            
         }
         req.getRequestDispatcher("/WEB-INF/views/myPage/comment.jsp").forward(req, resp);
      }
   
   //댓글 삭제, comment.jsp
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("AskCommentController [POST]");
      String commentId = req.getParameter("commentId");
      String up = (String)req.getParameter("up");
      System.out.println(up);
      boolean res = false;
      if("true".equals(up)) {
         System.out.println("이제 수정할거야");
            String comment = req.getParameter("comment");
            TB_COMMENT com = new TB_COMMENT();
            com.setContent(comment);
            res = mypageService.updateComment(commentId, com);
            req.setAttribute("content", com.getContent());
            System.out.println(req.getAttribute("content"));
            System.out.println("무슨값이들어갈까" +comment);

         } else {
            res = mypageService.deleteComment(commentId);
         }
      Map<String, Object> map = new HashMap<>();
      Gson gson = new Gson();
      if (res) {
         try {
            PrintWriter out = resp.getWriter();
            map.put("msg", "ok");
            resp.setContentType("text/html; charset=utf-8"); 
            String json = gson.toJson(map);
            out.print(json);
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