package admin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import admin.common.JDBCTemplate;
import admin.dao.face.TbProductDao;
import admin.dto.TB_PROD;
import common.Paging;

public class TbProductDaoImpl implements TbProductDao {

	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public int insertProd(Connection conn, TB_PROD prodInfo, String saveDirectory) {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(now.getTime());

		String sql="";

	    sql += "INSERT INTO TB_PROD (PROD_ID, CATE_ID, PROD_NAME,PROD_PRICE,PROD_NUM ,PROD_PICTURETITLE ,PROD_PICTUREDETAIL, PROD_DATE) VALUES";
	    sql += " (PROD_ID_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?)";
		int res = 0;
		
		System.out.println("아이디: "+prodInfo.getProd_id());
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prodInfo.getCate_id());
			ps.setString(2, prodInfo.getProd_name());
			ps.setInt(3, prodInfo.getProd_price());
			ps.setInt(4, prodInfo.getProd_num());
			ps.setString(5, saveDirectory+prodInfo.getProd_picturetitle());
			ps.setString(6, saveDirectory+prodInfo.getProd_picturedetail());
			ps.setString(7, today);
			res = ps.executeUpdate();
			
			System.out.println(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}

	@Override
	public TB_PROD selectProdByID(Connection conn, String prod_id) {
		//SQL 작성
		String sql="";
		sql += "SELECT * FROM TB_PROD";
		sql += " WHERE PROD_ID = ?";
		TB_PROD prod = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prod_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				prod = new TB_PROD();
				prod.setCate_id(rs.getString("CATE_ID"));
				prod.setProd_name(rs.getString("PROD_NAME"));
				prod.setProd_price(rs.getInt("PROD_PRICE"));
				prod.setProd_num(rs.getInt("PROD_NUM"));
				prod.setProd_picturetitle(rs.getString("PROD_PICTURETITLE"));
				prod.setProd_picturedetail(rs.getString("PROD_PICTUREDETAIL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//자원반납
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
			return prod;
	}
	
	@Override
	public int updateProd(Connection conn, TB_PROD prodInfo, String prod_id, String save, int chk1, int chk2) {
		String sql="";
		sql += "UPDATE TB_PROD SET";
		sql += " PROD_NAME=?, PROD_PRICE=?, PROD_NUM=?, PROD_PICTURETITLE=?, PROD_PICTUREDETAIL=?, CATE_ID=?";
		sql += " WHERE PROD_ID = ?";
		int res = 0;
		
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prodInfo.getProd_name());
			ps.setInt(2, prodInfo.getProd_price());
			ps.setInt(3, prodInfo.getProd_num());
			if( chk1 == 0) {
				ps.setString(4, save+prodInfo.getProd_picturetitle());
			} else {
				ps.setString(4, prodInfo.getProd_picturetitle());
			}
			if( chk2 == 0 ) {
				ps.setString(5, save+prodInfo.getProd_picturedetail());
			} else {
				ps.setString(5, prodInfo.getProd_picturedetail());
				
			}
			ps.setString(6, prodInfo.getCate_id());
			ps.setString(7, prod_id);
			res = ps.executeUpdate();
			
			System.out.println(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	@Override
	public int removeProd(Connection conn, String prod_id) {
		int result = 0;
		
		try {
			String sql="";
			sql += "SELECT * FROM TB_PROD";
			sql += " WHERE PROD_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, prod_id);
			rs = ps.executeQuery();

			while(rs.next()) {
				sql = "";
				sql += "DELETE FROM TB_PROD";
				sql += " WHERE PROD_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, prod_id);
				result = ps.executeUpdate();
			
			System.out.println(rs);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
	@Override
	public List<TB_PROD> selectProdBoardList(Connection conn,Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
		sql += "		SELECT * ";
		sql += "		FROM TB_PROD";
		sql += "		ORDER BY prod_date";
		sql += "	) B";
		sql += " ) TB_PROD";
		sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
		
		//조회 결과를 저장할 List
		List<TB_PROD> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				TB_PROD b = new TB_PROD();
				
				b.setProd_id( rs.getString("prod_id") );
				b.setProd_name( rs.getString("prod_name") );
				b.setProd_price( rs.getInt("prod_price") );
				b.setProd_num( rs.getInt("prod_num") );
				b.setProd_picturetitle( rs.getString("prod_picturetitle") );
				b.setProd_picturedetail( rs.getString("prod_picturedetail") );
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public List<TB_PROD> searchProd(Connection conn, Paging paging, String cate_id, String search) {
		System.out.println(search);
		
		String sql = "";
//		if(search.equals("")) {
//			sql += "SELECT * FROM (";
//			sql += "	SELECT * FROM (SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
//			sql += "		SELECT * FROM TB_PROD";		
//			sql += "		ORDER BY prod_id DESC";
//			sql += "	) B";
//			sql += " ) TB_PROD WHERE cate_id = ?)";
//			sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
//		} else 
			if(cate_id.equals("all")) {
			sql += "SELECT * FROM (";
			sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
			sql += "		SELECT * FROM TB_PROD";		
			sql += "			Where prod_name like ?";
			sql += "		ORDER BY prod_date";
			sql += "	) B";
			sql += " ) TB_PROD";
			sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
		} else {
				sql += "SELECT * FROM (";
				sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
				sql += "		SELECT * FROM TB_PROD";		
				sql += "			Where prod_name like ?";
				sql += " 		AND CATE_ID = ?";
				sql += "		ORDER BY prod_date) B";
				sql += " ) TB_PROD ";
				sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
		}
		//조회 결과를 저장할 List
		List<TB_PROD> list = new ArrayList<>();
		String se = "%" + search + "%";
		
		try {
			ps = conn.prepareStatement(sql);
//			if(search.equals("")) {
//				ps.setString(1, cate_id);
//				ps.setInt(2, paging.getStartNo());
//				ps.setInt(3, paging.getEndNo());
//				
//			}else
				if(cate_id.equals("all")) {

				ps.setString(1, se);
				ps.setInt(2, paging.getStartNo());
				ps.setInt(3, paging.getEndNo());
			} else {
				System.out.println("작동했쓰십니다");
				System.out.println(cate_id);
				ps.setString(1, se);
				ps.setString(2, cate_id);
				ps.setInt(3, paging.getStartNo());
				ps.setInt(4, paging.getEndNo());
				
			}
			
			rs = ps.executeQuery();

			
			while(rs.next()) {
				TB_PROD b = new TB_PROD();
				
				b.setProd_id( rs.getString("prod_id") );
				b.setProd_name( rs.getString("prod_name") );
				b.setProd_price( rs.getInt("prod_price") );
				b.setProd_num( rs.getInt("prod_num") );
				b.setProd_picturetitle( rs.getString("prod_picturetitle") );
				b.setProd_picturedetail( rs.getString("prod_picturedetail") );
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM TB_PROD";
		
		//결과 저장할 변수
		int totalCount = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				totalCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return totalCount;
	}
	
	@Override
	public int selectSearchAll(Connection conn, String cate_id, String search) {
		//SQL 작성
		String sql = "";
		String se ="";
//		if(search.equals("")) {
//			sql += "SELECT count(*) FROM TB_PROD";
//			sql += " Where cate_id = ?";
//			
//		}else 
			if(cate_id.equals("all")) {
			System.out.println(search);
			sql += "SELECT count(*) FROM TB_PROD Where prod_name like ?";
			se = "%" + search + "%";

		} else {
			sql += "SELECT count(*) FROM ";
			sql += " TB_PROD Where prod_name like ?";
			sql += " AND cate_id = ?";
			se = "%" + search + "%";

			
		}
		//결과 저장할 변수
		int totalCount = 0;
		
		try {

			ps = conn.prepareStatement(sql); //SQL수행 객체
//			if(search.equals("")) {
//				ps.setString(1, cate_id);
//				
//			}else 
				if(cate_id.equals("all")) {
			ps.setString(1, se);
			} else {
				ps.setString(1, se);
				ps.setString(2, cate_id);
			}
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				totalCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return totalCount;
	}
	
}