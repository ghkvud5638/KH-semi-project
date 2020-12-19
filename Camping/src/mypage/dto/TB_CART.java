package mypage.dto;

public class TB_CART {
	private String cart_id;
	private String prod_id;
	private String user_id;
	private String prod_name;
	private int cnt;
	private String img_path;
	
	public String getCart_id() {
		return cart_id;
	}
	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	@Override
	public String toString() {
		return "TB_CART [cart_id=" + cart_id + ", prod_id=" + prod_id + ", user_id=" + user_id + ", prod_name="
				+ prod_name + ", cnt=" + cnt + ", img_path=" + img_path + "]";
	}
	
	
	
	
	
}
