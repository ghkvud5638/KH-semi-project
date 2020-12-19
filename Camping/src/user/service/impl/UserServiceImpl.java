package user.service.impl;

import java.sql.Connection;

import common.JDBCTemplate;
import user.dao.face.UserDao;
import user.dao.impl.UserDaoImpl;
import user.dto.TB_USER;
import user.service.face.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userdao = new UserDaoImpl();
	
	@Override
	public void insert(TB_USER up) {
		Connection conn = JDBCTemplate.getConnection();
		
		//userid 중복 검사해서 true(같은게 있으면) 나오면 밑의 코드 수행하지 말자 
//		boolean ch = userdao.check(conn,up);
		userdao.check(conn,up);
//		System.out.println("here ch : "+ch);
		
//		if (!ch) {
			//user 정보 db 삽입
			int res = userdao.insertUser(conn, up);
			if (res > 0) {
				System.out.println("커밋완료");
				JDBCTemplate.commit(conn);
			}else {
				System.out.println("커밋안됨");
				JDBCTemplate.rollback(conn);
			}
//		}
		
//		return !ch;
		
	}
	@Override
	public TB_USER selectUser(String id, String pw) {
		Connection conn = JDBCTemplate.getConnection();

		TB_USER user = userdao.selectByID(conn,id,pw); 

		return user;
	}
	
	/**
	 * 회원가입시 아이디 중복 검사
	 */
	@Override
	public boolean duplicateIdCheck(String id) {
		Connection conn = JDBCTemplate.getConnection();
		boolean res = userdao.checkDuplicateId(conn, id);
		
		return res;
	}
	
	@Override
	public int loginCheck(String userid, String userpw) {
		Connection conn = JDBCTemplate.getConnection();
		int res = userdao.checkLoginIdPw(conn, userid, userpw);
		
		return res;
	}
	
	

	@Override
	public String adminCheck(String userid) {
		Connection conn = JDBCTemplate.getConnection();
		String res = userdao.adminCheck(conn, userid);
		
		return res;
	}

	
	
	@Override
	public TB_USER searchId(String name, String email, String phone) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		TB_USER user = userdao.searchId(conn, name, email, phone);
		
		System.out.println(" user : " + user);
		
		return user;
	}
	
	@Override
	public TB_USER searchPw(String id, String name, String email, String phone) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		TB_USER user = userdao.searchPw(conn, id, name, email, phone);
		
		System.out.println(" user : " + user);
		
		return user;
	}
	

}
