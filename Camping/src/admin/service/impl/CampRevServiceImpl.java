package admin.service.impl;

import java.util.List;

import admin.dao.face.CampRevDao;
import admin.dao.impl.CampRevDaoImpl;
import admin.dto.TB_REV;
import admin.service.face.CampRevService;
import common.JDBCTemplate;

public class CampRevServiceImpl implements CampRevService{
	private CampRevDao campRevDao = new CampRevDaoImpl();
	@Override
	public List<TB_REV> getRevList() {
		List<TB_REV> list =  campRevDao.SelectRevList(JDBCTemplate.getConnection());
		return list;
	}
	@Override
	public List<TB_REV> getRevListByDate(String firstDate) {
		List<TB_REV> list =  campRevDao.SelectRevListByMonth(JDBCTemplate.getConnection(),firstDate);
		return list;
	}
}
