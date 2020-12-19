package user.service.face;

import user.dto.TB_USER;

public interface UserService {

	public void insert(TB_USER up);

	/**
	 * 로그인 버튼 눌리면 db에서 해당 아이디가 있는지 조회
	 * @return 해당 아이디 객체
	 */
	public TB_USER selectUser(String id, String pw);

	/**
	 * 회원가입 시 id 중복 검사 
	 * @param id 입력된 아이디
	 * @return boolean값, 중복값 있을 시 false(0)
	 */
	public boolean duplicateIdCheck(String id);

	public int loginCheck(String userid, String userpw);

	
	/**
	 * 관리자 id 체크
	 * @param userid
	 * @return
	 */
	public String adminCheck(String userid);
	
	
	/**
	 * 아이디찾기
	 * @param name - 입력된 이름
	 * @param email - 입력된 이메일
	 * @param phone - 입력된 휴대폰번호
	 * @return  해당 아이디 
	 */
	public TB_USER searchId(String name, String email, String phone);

	
	/**
	 * 비밀번호 찾기
	 * @param id - 입력된 아이디
	 * @param name - 입력된 이름
	 * @param email - 입력된 이메일
	 * @param phone - 입력된 휴대폰 번호
	 * @return - 해당 사용자의 비밀번호
	 */
	public TB_USER searchPw(String id, String name, String email, String phone);
}
