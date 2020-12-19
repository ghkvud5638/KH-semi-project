package camping.dto;


public class Rev {
	private String CAMP_NAME;
	private int people;
	private String tel;
	private int total;
	private String name;
	private String CarNum;
	private String first;
	private String last;
	private String userid;
	public String getCAMP_NAME() {
		return CAMP_NAME;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "Rev [CAMP_NAME=" + CAMP_NAME + ", people=" + people + ", tel=" + tel + ", total=" + total + ", name="
				+ name + ", CarNum=" + CarNum + ", first=" + first + ", last=" + last + ", userid=" + userid + "]";
	}
	public void setCAMP_NAME(String cAMP_NAME) {
		CAMP_NAME = cAMP_NAME;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarNum() {
		return CarNum;
	}
	public void setCarNum(String carNum) {
		CarNum = carNum;
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
	
}
