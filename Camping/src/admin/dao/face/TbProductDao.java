package admin.dao.face;

import java.sql.Connection;
import java.util.List;

import admin.dto.TB_PROD;
import common.Paging;

public interface TbProductDao {


	public int insertProd(Connection conn, TB_PROD prodInfo, String saveDirectory);

	public List<TB_PROD> selectProdBoardList(Connection conn, Paging paging);

	public TB_PROD selectProdByID(Connection conn, String prod_id);

	public int updateProd(Connection conn, TB_PROD prodInfo, String prod_id, String save, int chk1, int chk2);

	public int removeProd(Connection conn, String prod_id);

	public int selectCntAll(Connection conn);

	public List<TB_PROD> searchProd(Connection conn, Paging paging, String cate_id, String search);

	public int selectSearchAll(Connection conn, String cate_id, String search);





	

}
