package admin.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import admin.dto.TB_PROD;
import common.Paging;

public interface TbProductService {

	public void insertProd(TB_PROD prodInfo, String saveDirectory);

	public List<TB_PROD> selectProdBoardList(Paging paging);

	public TB_PROD selectProdInfo(String prod_id);

	public void updateProd(TB_PROD prodInfo, String prod_id, String save, int chk1, int chk2);

	public boolean removeProd(String prod_id);

	public Paging getPaging(HttpServletRequest req);

	public List<TB_PROD> searchProd(Paging paging, String cate_id, String search);

	public Paging getSearchPaging(HttpServletRequest req, String cate_id, String search);





}
