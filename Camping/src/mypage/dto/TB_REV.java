package mypage.dto;

public class TB_REV {
	private String camp_name;
	private String user_id;
	private String name;
	private int people;
	private String first;
	private String last;
	private String carnum;
	private String tel;
	private int total;
	private String paymentdate;
	private String camp_id;
	@Override
	public String toString() {
		return "TB_REV [camp_name=" + camp_name + ", user_id=" + user_id + ", name=" + name + ", people=" + people
				+ ", first=" + first + ", last=" + last + ", carnum=" + carnum + ", tel=" + tel + ", total=" + total
				+ ", paymentdate=" + paymentdate + ", camp_id=" + camp_id + "]";
	}
	public String getCamp_name() {
		return camp_name;
	}
	public void setCamp_name(String camp_name) {
		this.camp_name = camp_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}
	public String getCamp_id() {
		return camp_id;
	}
	public void setCamp_id(String camp_id) {
		this.camp_id = camp_id;
	}
}
