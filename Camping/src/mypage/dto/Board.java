package mypage.dto;

import java.util.Date;

public class Board {
	private String boardno;
	private String title;
	private String id;
	private String content;
	private int hit;
	private Date writtendate;
	private String user_id;
	private String boardCate;
	@Override
	public String toString() {
		return "Board [boardno=" + boardno + ", title=" + title + ", id=" + id + ", content=" + content + ", hit=" + hit
				+ ", writtendate=" + writtendate + ", user_id=" + user_id + ", boardCate=" + boardCate + "]";
	}
	public String getBoardno() {
		return boardno;
	}
	public void setBoardno(String boardno) {
		this.boardno = boardno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getWrittendate() {
		return writtendate;
	}
	public void setWrittendate(Date writtendate) {
		this.writtendate = writtendate;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBoardCate() {
		return boardCate;
	}
	public void setBoardCate(String boardCate) {
		this.boardCate = boardCate;
	}
}
