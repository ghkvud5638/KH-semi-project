package admin.dao.face;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import admin.common.Paging;

public interface TbSalesDao {

	int selectCntAll(Connection conn);

	List<HashMap<String, Object>> selectSalesList(Connection conn, Paging paging);

	List<HashMap<String, Object>> SalesListDate(Connection conn, Paging paging, String start, String end);

}
