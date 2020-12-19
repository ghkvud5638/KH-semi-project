package admin.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import admin.common.JDBCTemplate;
import admin.dao.face.UserManageDao;
import admin.dao.impl.UserManageDaoImpl;
import admin.dto.TB_USER;
import admin.service.face.UserManageService;
import common.Paging;

public class UserManageServiceImpl implements UserManageService {
   
   private UserManageDao userDao = new UserManageDaoImpl();
   
   @Override
   public Paging getPaging(HttpServletRequest req) {

      //전달파라미터 curPage를 파싱한다
      String param = req.getParameter("curPage");
      int curPage = 0;
      if( param!=null && !"".equals(param) ) {
         curPage = Integer.parseInt(param);
      }
      
      //Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
      int totalCount = userDao.selectCntAll(JDBCTemplate.getConnection());
      
      //Paging 객체 생성
      Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
      
      //계산된 Paging 객체 반환
      return paging;
   }
   
   @Override
   public List<TB_USER> userList(Paging paging) {
      Connection conn = JDBCTemplate.getConnection();
      
      List<TB_USER> b = userDao.userList(conn, paging); 

      return b;
   }
   
   @Override
   public TB_USER selectUserInfo(String user_id) {
      Connection conn = JDBCTemplate.getConnection();
      TB_USER user = userDao.selectUserByID(conn,user_id); 
      return user;
   }
   
   @Override
   public boolean removeUser(String user_id) {
      Connection conn = JDBCTemplate.getConnection();
      boolean result=false;

      int res = userDao.removeUser(conn, user_id); 
      if (res > 0) {
         System.out.println("커밋완료");
         JDBCTemplate.commit(conn);
         result=true;

      }else {
         System.out.println("커밋안됨");
         JDBCTemplate.rollback(conn);
         result=false;

      }
      
      return result;
   }
   
   @Override
   public void modifyUser(TB_USER userInfo, String user_id, String save, int chk1) {
      Connection conn = JDBCTemplate.getConnection();

      int res = userDao.modifyUser(conn, userInfo, user_id, save, chk1); 
      if (res == 1) {
         System.out.println("커밋완료");
         JDBCTemplate.commit(conn);
      }else {
         System.out.println("커밋안됨");
         JDBCTemplate.rollback(conn);
      }      
   }

}