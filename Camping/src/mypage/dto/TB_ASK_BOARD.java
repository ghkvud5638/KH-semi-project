package mypage.dto;

import java.sql.Date;

public class TB_ASK_BOARD {
	private String boardno;
	private String title;
	private String content;
	private String user_id;
	private Date writtendate;
	private String board_cate;
	@Override
	public String toString() {
		return "TB_ASK_BOARD [boardno=" + boardno + ", title=" + title + ", content=" + content + ", user_id=" + user_id
				+ ", writtendate=" + writtendate + ", board_cate=" + board_cate + "]";
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getWrittendate() {
		return writtendate;
	}
	public void setWrittendate(Date writtendate) {
		this.writtendate = writtendate;
	}
	public String getBoard_cate() {
		return board_cate;
	}
	public void setBoard_cate(String board_cate) {
		this.board_cate = board_cate;
	}
}
