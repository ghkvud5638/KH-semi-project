package camping.service.impl;

import java.sql.Connection;

import camping.dao.ReservationDAO;
import camping.dao.impl.ReservationDAOimpl;
import camping.dto.Rev;
import camping.service.ReservationService;
import common.JDBCTemplate;

public class ReservationServiceimpl implements ReservationService  {
	ReservationDAO reseR=new ReservationDAOimpl();
	Connection conn=JDBCTemplate.getConnection();
	
	@Override
	public void rev_insert(Rev r) {
		reseR.rev_inser(conn,r);
	}
}
