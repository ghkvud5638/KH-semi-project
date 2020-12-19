package mypage.dto;

//import java.sql.Date;

public class TB_COMMENT {
	 private String comment_id;
	 private String boardno;
	 private String user_id;
	 private String comment_date;
	 private String comment_parent;
	 private String content;
	@Override
	public String toString() {
		return "TB_COMMENT [comment_id=" + comment_id + ", boardno=" + boardno + ", user_id=" + user_id
				+ ", comment_date=" + comment_date + ", comment_parent=" + comment_parent + ", content=" + content
				+ "]";
	}
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getBoardno() {
		return boardno;
	}
	public void setBoardno(String boardno) {
		this.boardno = boardno;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_parent() {
		return comment_parent;
	}
	public void setComment_parent(String comment_parent) {
		this.comment_parent = comment_parent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}