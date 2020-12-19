package admin.service.face;

import java.util.List;

import admin.dto.TB_REV;

public interface CampRevService {

	List<TB_REV> getRevList();

	List<TB_REV> getRevListByDate(String firstDate);
	
	
}
