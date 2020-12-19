package camping.dao;

import java.sql.Connection;
import java.util.List;

import camping.controller.Paging;
import camping.dto.Camp;

public interface CampingDAO {

   public List<Camp> list(Connection conn,Paging paging);

   public List<String> geolist(Connection conn);

   public List<Camp> S_list(Connection conn,String addr,Paging paging);

   public List<Camp> Selectlist_all(Connection conn, String address, String induty, String keyword);

   public Camp select_id(Connection conn, int id);

   public List<String> select_id_image(int camp_id);

   public int insertlike(Connection conn, String id, String camp);

   public int likecount(Connection conn,String camp);

   public int selectlike(Connection conn, int camp_id);

   public int jjim(Connection conn,String id, String camp);

   public int jjim(Connection conn, int camp_id, String userid);

   public List<Camp> Selectlist_key(Connection conn, String keyword);

   public List<Camp> Selectlist_inkey(Connection conn, String induty, String keyword);

   public List<Camp> Selectlist_addkey(Connection conn, String address, String keyword);

   public List<Camp> Selectlist_addin(Connection conn, String address, String induty);

   public List<Camp> Selectlist_add(Connection conn, String address);

   public List<Camp> Selectlist_in(Connection conn, String induty);

   public int selectCntAll(Connection connection);

   

}