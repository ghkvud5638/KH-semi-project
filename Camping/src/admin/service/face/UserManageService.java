package admin.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import admin.dto.TB_USER;
import common.Paging;

public interface UserManageService {

   Paging getPaging(HttpServletRequest req);

   List<TB_USER> userList(Paging paging);

   TB_USER selectUserInfo(String user_id);

   boolean removeUser(String user_id);

   void modifyUser(TB_USER userInfo, String user_id, String save, int chk1);

}