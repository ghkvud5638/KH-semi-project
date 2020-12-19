package camping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import camping.dao.ReservationDAO;
import camping.dto.Rev;

public class ReservationDAOimpl implements ReservationDAO{
	@Override
	public void rev_inser(Connection conn, Rev r) {
		PreparedStatement ps=null;
		//체크용 print
		
		String sql="";
		sql+="INSERT INTO TB_REV(CAMP_NAME,USER_ID,PEOPLE,FIRST,LAST,CARNUM,TEL,NAME,TOTAL,PAYMENTDATE)";
		sql+=" VALUES(?,?,?,?,?,?,?,?,?,to_CHAR(sysdate,'yyyy/MM/dd'))";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,r.getCAMP_NAME());
			ps.setString(2, r.getUserid());
			ps.setInt(3, r.getPeople());
			ps.setString(4, r.getFirst());
			ps.setString(5,r.getLast());
			ps.setString(6, r.getCarNum());
			ps.setString(7, r.getTel());
			ps.setString(8, r.getName());
			ps.setInt(9, r.getTotal());
			ps.executeUpdate();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
