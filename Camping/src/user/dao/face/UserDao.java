package user.dao.face;

import java.sql.Connection;

import user.dto.TB_USER;

public interface UserDao {

	public int insertUser(Connection conn, TB_USER up);

	/**
	 * 회원가입시 id 중복체크
	 * @param conn
	 * @param up
	 * @return
	 */
	public boolean check(Connection conn, TB_USER up);

	
	public TB_USER selectByID(Connection conn, String id, String pw);

	public boolean checkDuplicateId(Connection conn, String id);

	public int checkLoginIdPw(Connection conn, String userid, String userpw);
	
	public String adminCheck(Connection conn, String userid);
	

	public TB_USER searchId(Connection conn, String name, String email, String phone);

	public TB_USER searchPw(Connection conn, String id, String name, String email, String phone);
}
