package admin.dao.face;

import java.sql.Connection;
import java.util.List;

import admin.dto.TB_USER;
import common.Paging;

public interface UserManageDao {

   int selectCntAll(Connection connection);

   List<TB_USER> userList(Connection conn, Paging paging);

   TB_USER selectUserByID(Connection conn, String user_id);

   int removeUser(Connection conn, String user_id);

   int modifyUser(Connection conn, TB_USER userInfo, String user_id, String save, int chk1);

}