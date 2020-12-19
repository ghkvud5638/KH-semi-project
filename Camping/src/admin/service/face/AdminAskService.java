package admin.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import admin.dto.TB_ASK_BOARD;
import common.Paging;

public interface AdminAskService{

	Paging getPaging(HttpServletRequest req);

	List<TB_ASK_BOARD> askBoardList(Paging paging);

}
