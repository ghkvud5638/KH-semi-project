package admin.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.dao.face.CampRevDao;
import admin.dto.TB_REV;
import common.JDBCTemplate;

public class CampRevDaoImpl implements CampRevDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	@Override
	public List<TB_REV> SelectRevList(Connection connection) {
		String sql="";
		sql+="SELECT R.CAMP_NAME, R.USER_ID, R.NAME, R.PEOPLE, TO_CHAR(R.FIRST,'yyyy-MM-dd') FIRST,TO_CHAR(R.LAST,'yyyy-MM-dd') LAST,R.CARNUM,R.TEL,R.TOTAL,R.PAYMENTDATE,C.CAMP_ID FROM TB_REV R, CAMP_INFO C";
		sql+=" WHERE C.CAMP_NAME = R.CAMP_NAME";
		sql+=" ORDER BY PAYMENTDATE";
		List<TB_REV> revList = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				TB_REV revInfo = new TB_REV();
				revInfo.setCamp_id(rs.getString("CAMP_ID"));
				revInfo.setCamp_name(rs.getString("CAMP_NAME"));
				revInfo.setName(rs.getString("NAME"));
				revInfo.setPeople(rs.getInt("PEOPLE"));
				revInfo.setFirst(rs.getString("FIRST"));
				revInfo.setLast(rs.getString("LAST"));
				revInfo.setCarnum(rs.getString("CARNUM"));
				revInfo.setTel(rs.getString("TEL"));
				revInfo.setTotal(rs.getInt("TOTAL"));
				revInfo.setPaymentdate(rs.getString("PAYMENTDATE"));
				revList.add(revInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);;
		}

		return revList;
	}
	@Override
	public List<TB_REV> SelectRevListByMonth(Connection connection, String firstDate) {
		System.out.println(firstDate);
		String sql="";
		sql+="SELECT CAMP_NAME,USER_ID,NAME,PEOPLE,TO_CHAR(FIRST,'yyyy-MM-dd') FIRST,TO_CHAR(LAST,'yyyy-MM-dd') LAST,CARNUM,TEL,TOTAL,PAYMENTDATE FROM tb_rev";
		sql+=" WHERE trunc(to_date(?, 'yy/mm/dd')) BETWEEN trunc(first) AND trunc(last)";
		
		
		List<TB_REV> revList = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, firstDate);

			rs = ps.executeQuery();
			while(rs.next()){
				TB_REV revInfo = new TB_REV();
//				revInfo.setCamp_id(rs.getString("CAMP_ID"));
				revInfo.setCamp_name(rs.getString("CAMP_NAME"));
				revInfo.setName(rs.getString("NAME"));
				revInfo.setPeople(rs.getInt("PEOPLE"));
				revInfo.setFirst(rs.getString("FIRST"));
				revInfo.setLast(rs.getString("LAST"));
				revInfo.setCarnum(rs.getString("CARNUM"));
				revInfo.setTel(rs.getString("TEL"));
				revInfo.setTotal(rs.getInt("TOTAL"));
				revInfo.setPaymentdate(rs.getString("PAYMENTDATE"));
				revList.add(revInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);;
		}

		return revList;
	}
}



