package admin.dao.face;

import java.sql.Connection;
import java.util.List;

import admin.dto.TB_REV;

public interface CampRevDao {

	List<TB_REV> SelectRevList(Connection connection);

	List<TB_REV> SelectRevListByMonth(Connection connection, String firstDate);

}
