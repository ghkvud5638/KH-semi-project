package camping.dao;

import java.sql.Connection;

import camping.dto.Rev;

public interface ReservationDAO {
	void rev_inser(Connection conn, Rev r);
}
