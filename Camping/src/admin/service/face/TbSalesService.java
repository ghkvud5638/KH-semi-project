package admin.service.face;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import admin.common.Paging;

public interface TbSalesService {

	List<HashMap<String, Object>> selectSalesList(Paging paging);

	Paging getPaging(HttpServletRequest req);

	List<HashMap<String, Object>> SalesListDate(Paging paging, String start, String end);

}
