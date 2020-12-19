package camping.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import camping.controller.Paging;
import camping.dto.Camp;

public interface CampingService {
   public List<Camp> camplist(Paging paging);

   public List<String> campgeolist();

   public List<Camp> S_list(String addr,Paging paging);

   public List<Camp> selectlist_all(String address, String induty, String keyword);

   public Camp select_id(int id);

   public List<String> select_id_image(int camp_id);

   public int insertlike(String id, String camp);

   public int likecount(String camp);

   public int selectlike(int camp_id);

   public int jjim(String id, String camp);

   public int selectJJim(int camp_id, String userid);

   public Paging getPaging(HttpServletRequest req);
   
}