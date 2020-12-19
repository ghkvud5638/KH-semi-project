package mypage.dto;

public class CAMP_INFO {
	private String addr;
	private int camp_id;
	private String addr_do;
	private String camp_name;
	private String url;
	private String induty;
	private Double longitude;
	private Double latitude;
	private String facil;
	private String addr_sigungu;
	private String tel;
	private String intro;
	private String picture;
	@Override
	public String toString() {
		return "CAMP_INFO [addr=" + addr + ", camp_id=" + camp_id + ", addr_do=" + addr_do + ", camp_name=" + camp_name
				+ ", url=" + url + ", induty=" + induty + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", facil=" + facil + ", addr_sigungu=" + addr_sigungu + ", tel=" + tel + ", intro=" + intro
				+ ", picture=" + picture + "]";
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getCamp_id() {
		return camp_id;
	}
	public void setCamp_id(int camp_id) {
		this.camp_id = camp_id;
	}
	public String getAddr_do() {
		return addr_do;
	}
	public void setAddr_do(String addr_do) {
		this.addr_do = addr_do;
	}
	public String getCamp_name() {
		return camp_name;
	}
	public void setCamp_name(String camp_name) {
		this.camp_name = camp_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInduty() {
		return induty;
	}
	public void setInduty(String induty) {
		this.induty = induty;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getFacil() {
		return facil;
	}
	public void setFacil(String facil) {
		this.facil = facil;
	}
	public String getAddr_sigungu() {
		return addr_sigungu;
	}
	public void setAddr_sigungu(String addr_sigungu) {
		this.addr_sigungu = addr_sigungu;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
