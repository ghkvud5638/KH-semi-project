package shopping.dto;

import java.sql.Date;

public class TB_PROD {
	 private String prod_id;   
     private String order_id;   
     private String cate_id;  
     private String prod_name;   
     private int prod_price;  
     private int prod_num;   
     private Date prod_date; 
     private String prod_picturetitle;
     private String prod_picturedetail;
	
    @Override
	public String toString() {
		return "TB_PROD [prod_id=" + prod_id + ", order_id=" + order_id + ", cate_id=" + cate_id + ", prod_name="
				+ prod_name + ", prod_price=" + prod_price + ", prod_num=" + prod_num + ", prod_date=" + prod_date
				+ ", prod_picturetitle=" + prod_picturetitle + ", prod_picturedetail=" + prod_picturedetail + "]";
	}
	
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	public Date getProd_date() {
		return prod_date;
	}
	public void setProd_date(Date prod_date) {
		this.prod_date = prod_date;
	}
	public String getProd_picturetitle() {
		return prod_picturetitle;
	}
	public void setProd_picturetitle(String prod_picturetitle) {
		this.prod_picturetitle = prod_picturetitle;
	}
	public String getProd_picturedetail() {
		return prod_picturedetail;
	}
	public void setProd_picturedetail(String prod_picturedetail) {
		this.prod_picturedetail = prod_picturedetail;
	}
}
