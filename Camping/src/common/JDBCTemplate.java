package common;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// JDBC에 싱글톤을 적용

public class JDBCTemplate {
	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//DB연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "1234";
	
	//private 생성자
	private JDBCTemplate() {}
	

	//DB 연결 객체
	private static Connection conn = null;
	
	
	//Connection객체 반환 - 싱글톤 패턴 적용
	public static Connection getConnection() {
		
		//한번 생성된 객체를 유지한다.
		if (conn == null) {
			try {
				//드라이버 로드
				Class.forName(DRIVER);
				
				//DB 연결
				conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				
				//DML(INSERT, DELETE) 수행한 후 자동으로 COMMIT 할 것이냐?
				//setAutoCommit의 기본값은 true
				//COMMIT은 수동으로 직접 제어 해야한다. 자동으로 COMMIT 되는것은 좋지 않다
				conn.setAutoCommit(false);
				
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return conn;
	}
	
	//Connection 객체 닫기
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {conn.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//PreparedStatement 닫기
	public static void close(PreparedStatement ps) {
		try {
			if (ps != null && !ps.isClosed()) {ps.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ResultSet 닫기
	public static void close(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {rs.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Statement 닫기
	public static void close(Statement st) {
		try {
			if (st != null && !st.isClosed()) {st.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	//commit 수행
	public static void commit(Connection conn) {
		try {
			if (conn!=null && !conn.isClosed()) {conn.commit();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//rollback 수행
	public static void rollback(Connection conn) {
		try {
			if (conn!=null && !conn.isClosed()) {conn.rollback();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
