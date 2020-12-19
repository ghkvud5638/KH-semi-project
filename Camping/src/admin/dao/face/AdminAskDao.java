package admin.dao.face;

import java.sql.Connection;
import java.util.List;

import admin.dto.TB_ASK_BOARD;
import common.Paging;

public interface AdminAskDao {

	int paging(Connection conn);

	List<TB_ASK_BOARD> askBoardList(Connection conn, Paging paging);

}
