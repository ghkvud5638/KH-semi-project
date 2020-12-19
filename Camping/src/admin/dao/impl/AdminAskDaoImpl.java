package admin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.common.JDBCTemplate;
import admin.dao.face.AdminAskDao;
import admin.dto.TB_ASK_BOARD;
import common.Paging;

public class AdminAskDaoImpl implements AdminAskDao {

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int paging(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM TB_ASK_BOARD";
		
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
	public List<TB_ASK_BOARD> askBoardList(Connection conn, Paging paging) {
		System.out.println("askboardlist 다오 작동");
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
		sql += "		SELECT * ";
		sql += "		FROM TB_ASK_BOARD";
		sql += "		ORDER BY TO_NUMBER(BOARDNO) DESC";
		sql += "	) B";
		sql += " ) TB_ASK_BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
		
		//조회 결과를 저장할 List
		List<TB_ASK_BOARD> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
		
			rs = ps.executeQuery();
			while(rs.next()) {
				TB_ASK_BOARD b = new TB_ASK_BOARD();
				b.setBoardno( rs.getString("Boardno") );
				b.setTitle( rs.getString("Title") );
				b.setContent( rs.getString("Content") );
				b.setUser_id( rs.getString("User_id") );
				b.setWrittendate( rs.getDate("Writtendate") );
				b.setBoard_cate( rs.getString("Board_cate") );
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
	
}
