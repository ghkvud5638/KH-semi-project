package user.dto;

public class TB_USER {

	private String user_id;
	private String user_name;
	private String gender;
	private String pw;
	private String phone;
	private String email;
	private String addr1;
	private String addr2;
	private String grade;
	private String nicname;
	private String join_date;
	private String picture;
	private String origin_name;
	private String stored_name;
	@Override
	public String toString() {
		return "TB_USER [user_id=" + user_id + ", user_name=" + user_name + ", gender=" + gender + ", pw=" + pw
				+ ", phone=" + phone + ", email=" + email + ", addr1=" + addr1 + ", addr2=" + addr2 + ", grade=" + grade
				+ ", nicname=" + nicname + ", join_date=" + join_date + ", picture=" + picture + ", origin_name="
				+ origin_name + ", stored_name=" + stored_name + "]";
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getNicname() {
		return nicname;
	}
	public void setNicname(String nicname) {
		this.nicname = nicname;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public String getStored_name() {
		return stored_name;
	}
	public void setStored_name(String stored_name) {
		this.stored_name = stored_name;
	}
}
