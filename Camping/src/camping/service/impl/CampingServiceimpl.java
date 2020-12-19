package camping.service.impl;

import java.sql.Connection;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import camping.controller.Paging;
import camping.dao.CampingDAO;
import camping.dao.impl.CampingDAOimpl;
import camping.dto.Camp;
import camping.service.CampingService;
import common.JDBCTemplate;

public class CampingServiceimpl implements CampingService{
   CampingDAO campDAO=new CampingDAOimpl();
   Connection conn=JDBCTemplate.getConnection();
   @Override
   public List<Camp> camplist(Paging paging) {
      List<Camp> camp=campDAO.list(conn,paging);
      return camp;
   }
   @Override
   public List<String> campgeolist() {
      List<String> geo=campDAO.geolist(conn);
      return geo;
   }
   @Override
   public List<Camp> S_list(String addr,Paging paging) {
      List<Camp> S_camp=campDAO.S_list(conn,addr,paging);
      return S_camp;
   }
   @Override
   public List<Camp> selectlist_all(String address, String induty, String keyword) {
   
      if("0".equals(induty) && "0".equals(keyword)) {
         List<Camp> Selectcamp=campDAO.Selectlist_add(conn,address);
         return Selectcamp;
      }
      if("0".equals(address) && "0".equals(keyword)) {
         List<Camp> Selectcamp=campDAO.Selectlist_in(conn,induty);
         return Selectcamp;
      }
      if("0".equals(address) && "0".equals(induty)) {
         List<Camp> Selectcamp=campDAO.Selectlist_key(conn,keyword);
         return Selectcamp;
      }
      if("0".equals(address)) {
         List<Camp> Selectcamp=campDAO.Selectlist_inkey(conn,induty,keyword);   
         return Selectcamp;
      }
      if("0".equals(induty)) {
         List<Camp> Selectcamp=campDAO.Selectlist_addkey(conn,address,keyword);
         return Selectcamp;
      }
      if("0".equals(keyword)){
         List<Camp> Selectcamp=campDAO.Selectlist_addin(conn,address,induty);
         return Selectcamp;
      }
      List<Camp> Selectcamp=campDAO.Selectlist_all(conn,address,induty,keyword);
      return Selectcamp;
   }
   @Override
   public Camp select_id(int id) {
      Camp camp=campDAO.select_id(conn,id);
      return camp;
   }
   @Override
   public List<String> select_id_image(int camp_id) {
      List<String> image=campDAO.select_id_image(camp_id);
      return image;
   }
   @Override
   public int insertlike(String id, String camp) {
      int result=campDAO.insertlike(conn,id,camp);
      return result;
      
   }
   @Override
   public int likecount(String camp) {
      int count=campDAO.likecount(conn,camp);
      return count;
   }
   @Override
   public int selectlike(int camp_id) {
      int count=campDAO.selectlike(conn,camp_id);
      return count;
   }
   @Override
   public int jjim(String id, String camp) {
      int result=campDAO.jjim(conn,id,camp);
      return result;
   }
   @Override
   public int selectJJim(int camp_id, String userid) {
      int result=campDAO.jjim(conn,camp_id,userid);
      return result;
   }
   @Override
   public Paging getPaging(HttpServletRequest req) {
      //전달파라미터 curPage를 파싱한다
      String param = req.getParameter("curPage");
      int curPage = 0;
      if( param!=null && !"".equals(param) ) {
         curPage = Integer.parseInt(param);
      }
      int totalCount = campDAO.selectCntAll(JDBCTemplate.getConnection());
      
      //Paging 객체 생성
      Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
      //계산된 Paging 객체 반환
      return paging;
   }

}