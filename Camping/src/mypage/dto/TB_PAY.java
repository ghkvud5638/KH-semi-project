package mypage.dto;

public class TB_PAY {
	private String pay_id; 
	private String order_id;
	private String user_id;
	private String prod_id;
	private String pay_date;
	private int prod_cnt;
	private int totalprice;
	private String prod_name;
	private String prod_picturetitle;
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public int getProd_cnt() {
		return prod_cnt;
	}
	public void setProd_cnt(int prod_cnt) {
		this.prod_cnt = prod_cnt;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_picturetitle() {
		return prod_picturetitle;
	}
	public void setProd_picturetitle(String prod_picturetitle) {
		this.prod_picturetitle = prod_picturetitle;
	}
	@Override
	public String toString() {
		return "TB_PAY [pay_id=" + pay_id + ", order_id=" + order_id + ", user_id=" + user_id + ", prod_id=" + prod_id
				+ ", pay_date=" + pay_date + ", prod_cnt=" + prod_cnt + ", totalprice=" + totalprice + ", prod_name="
				+ prod_name + ", prod_picturetitle=" + prod_picturetitle + "]";
	}
}
