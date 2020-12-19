package shopping.service.face;

import shopping.dto.Board;

public interface BoardService {

	/**
	 * 리뷰 내용을 적용한다
	 * 
	 * @param board - board 내용
	 */
	public void applyReview(Board board);

	/**
	 * 
	 * QA내용을 적용한다
	 * @param board - board 내용 값
	 */
	public void applyQA(Board board);
	
	/**
	    * 리뷰 디테일 조회하기
	    * 
	    * @param boardno - 게시판번호를 이용해서 조회
	    * @return
	    */
	   public Board selectByBoardno(String boardno);

	   public Board selectByQABoardno(String boardno);
	

}
