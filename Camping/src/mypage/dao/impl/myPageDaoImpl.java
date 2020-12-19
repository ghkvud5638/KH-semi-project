package mypage.dao.impl;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.AskPaging;
import common.CartPaging;
import common.JDBCTemplate;
import common.Paging;
import mypage.dao.face.myPageDao;
import mypage.dto.Board;
import mypage.dto.CAMP_INFO;
import mypage.dto.TB_ASK_BOARD;
import mypage.dto.TB_CART;
import mypage.dto.TB_COMMENT;
import mypage.dto.TB_PAY;
import mypage.dto.TB_RECOMMENT;
import mypage.dto.TB_REV;
import mypage.dto.TB_USER;

public class myPageDaoImpl implements myPageDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * 찜한 캠핑장 테이블에서 캠핑장 ID 추출 후, 캠핑장 테이블에서 필요한 컬럼 조회 -> 조인
	 */
	@Override
	public List<CAMP_INFO> selectJJcamp(Connection conn, String user_id) {
		PreparedStatement ps = null; // SQL 수행 객체
		ResultSet rs = null; // 조회결과 객체
		String sql=""; 
		sql+= "SELECT C.* FROM TB_JJIM J, CAMP_INFO C";
		sql +=" WHERE J.CAMP_ID = C.CAMP_ID AND J.USER_ID=?";
		List<CAMP_INFO> camplist = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				CAMP_INFO campinfo = new CAMP_INFO();
				campinfo.setPicture(rs.getString("PICTURE"));
				campinfo.setCamp_name(rs.getString("CAMP_NAME"));
				campinfo.setTel(rs.getString("TEL"));
				campinfo.setAddr(rs.getString("ADDR"));
				campinfo.setCamp_id(rs.getInt("CAMP_ID"));
				camplist.add(campinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return camplist;
	}
	//
	@Override
	public TB_ASK_BOARD selectAskArticleByBoardno(Connection connection, String boardno) {
		PreparedStatement ps = null; //SQL 수행 객체
		ResultSet rs = null; //조회 결과 객체
		//SQL 작성
		String sql="";
		sql += "SELECT * FROM TB_ASK_BOARD";
		sql += " WHERE BOARDNO = ?";
		TB_ASK_BOARD askBoard = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,boardno);
			rs = ps.executeQuery();
			while (rs.next()) {
				askBoard = new TB_ASK_BOARD();
				askBoard.setBoardno(rs.getString("BOARDNO"));
				askBoard.setTitle(rs.getString("TITLE"));
				askBoard.setContent(rs.getString("CONTENT"));
				askBoard.setUser_id(rs.getString("USER_ID"));
				askBoard.setBoard_cate(rs.getString("BOARD_CATE"));
				askBoard.setWrittendate(rs.getDate("WRITTENDATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return askBoard;
	}
	
	/**
	 * 1:1문의 댓글 작성
	 */
	@Override
	public int insertAskComment(Connection connection, TB_COMMENT comment) {
		PreparedStatement ps = null;
		int res = 0;
		String sql="";
		sql+="INSERT INTO TB_COMMENT(COMMENT_ID,BOARDNO,USER_ID,CONTENT)";
		sql+=" VALUES(SEQ_COMMENT.NEXTVAL,?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, comment.getBoardno());
			ps.setString(2, comment.getUser_id());
			ps.setString(3, comment.getContent());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	//1:1문의 게시글 댓글 조회
	@Override
	public List<TB_COMMENT> selectAskCommentList(Connection connection, String boardno) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		sql += "SELECT COMMENT_ID,BOARDNO,USER_ID,TO_CHAR(COMMENT_DATE,'YYYY-MM-DD HH24:MI:SS')COMMENT_DATE,CONTENT FROM TB_COMMENT";
		sql += " WHERE BOARDNO = ?";
		sql += " ORDER BY TO_NUMBER(COMMENT_ID) DESC";
		List<TB_COMMENT> commentList = new ArrayList<>(); 
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,boardno);
			rs = ps.executeQuery();
			while (rs.next()) {
				TB_COMMENT comment = new TB_COMMENT();
				comment.setBoardno(rs.getString("BOARDNO"));
				comment.setUser_id(rs.getString("USER_ID"));
				comment.setComment_date(rs.getString("COMMENT_DATE"));
				comment.setContent(rs.getString("CONTENT"));
				comment.setComment_id(rs.getString("COMMENT_ID"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return commentList;
	}
	@Override
	public int deleteAskComment(Connection connection, String commentId) {
		PreparedStatement ps = null;
		String sql ="";
		sql += "DELETE FROM TB_COMMENT";
		sql += " WHERE COMMENT_ID=?";
		int result = 0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, commentId);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;

	}
	//로그인한 회원의 개인정보 불러오기
	@Override
	public TB_USER selectUserByID(Connection conn , String user_id) {
		PreparedStatement ps = null; //SQL 수행 객체
		ResultSet rs = null; //조회 결과 객체
		//SQL 작성
		String sql="";
		sql += "SELECT * FROM TB_USER";
		sql += " WHERE USER_ID = ?";
		TB_USER user = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new TB_USER();
				user.setUser_id(rs.getString("USER_ID"));
				user.setUser_name(rs.getString("USER_NAME"));
				user.setNicname(rs.getString("NICNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setStored_name(rs.getString("STORED_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//자원반납
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
			return user;
	}
	//회원정보 수정
	@Override
	public int update(Connection conn, TB_USER user) {
		PreparedStatement ps = null; //SQL 수행 객체
		String sql="";
		sql += "UPDATE TB_USER SET";
		sql += " USER_NAME=?,NICNAME=?,EMAIL=?";
		sql += " WHERE USER_ID=?";
		int res = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getNicname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getUser_id());
			res = ps.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	//회원 사진 파일
	@Override
	public int insertFile(Connection connection, TB_USER up) {

		String sql ="";
		sql += "UPDATE TB_USER SET";
		sql += " ORIGIN_NAME=? , STORED_NAME=?";
		sql += " WHERE USER_ID=?";
		int result = 0;

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, up.getOrigin_name());
			ps.setString(2, up.getStored_name());
			ps.setString(3, up.getUser_id());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//1:1문의 게시글 등록
	@Override
	public int insertAskArticle(Connection connection, TB_ASK_BOARD askBoard) {
		String sql = "";
		sql+="INSERT INTO TB_ASK_BOARD(BOARDNO,TITLE,CONTENT,USER_ID,BOARD_CATE)";
		sql+=" VALUES(SEQ_ASK_BOARD.NEXTVAL,?,?,?,?)";
		int result = 0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, askBoard.getTitle());
			ps.setString(2, askBoard.getContent());
			ps.setString(3, askBoard.getUser_id());
			ps.setString(4, askBoard.getBoard_cate());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
	
	
	@Override
	public int deleteArticle(Connection conn, int boardno) {
		PreparedStatement ps = null;
		String sql ="";
		sql += "DELETE FROM BOARD";
		sql += " WHERE BOARDNO=?";
		
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardno);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	@Override
	public int deleteMemberByUserId(Connection conn, String userId) {
		PreparedStatement ps = null;
		String sql ="";
		sql += "DELETE FROM TB_USER";
		sql += " WHERE USER_ID=?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	@Override
	public int deleteJJcamp(Connection conn, String campId) {
		System.out.println("campId  Dao : "+campId);
		PreparedStatement ps = null;
		String sql ="";
		sql+="DELETE FROM TB_JJIM";
		sql+=" WHERE CAMP_ID=?";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,campId);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		System.out.println("dao result : "+result);
		return result;
	}
	
	
	
//	@Override
//	public List<Board> selectList(Connection connection) {
//		String sql = "";
//		sql += "SELECT * FROM board";
//		sql += " ORDER BY boardno DESC";
//		
//		//조회 결과를 저장할 List
//		List<Board> list = new ArrayList<>();
//		
//		try {
//			ps = connection.prepareStatement(sql);
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				Board board = new Board();
//				
//				board.setBoardno( rs.getString("boardno") );
//				board.setTitle( rs.getString("title") );
//				board.setId( rs.getString("id") );
//				board.setContent( rs.getString("content") );
//				board.setHit( rs.getInt("hit") );
//				board.setWrittendate( rs.getDate("writtendate") );
//				
//				list.add(board);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//		
//		return list;
//	}

	//회원이 작성한 게시글 페이징 카운트
	@Override
	public int selectCntAll(Connection connection) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM board";
		
		//결과 저장할 변수
		int totalCount = 0;
		
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				totalCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return totalCount;
		
	}
	
	@Override
	public int selectAskBoardCntAll(Connection connection) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM TB_ASK_BOARD";
		//결과 저장할 변수
		int totalCount = 0;
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				totalCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		//최종 결과 반환
		return totalCount;
	}

	
	@Override
	public List<Board> selectList(Connection connection, Paging paging , String user_id) {
		System.out.println("BoardDaoImpl에서의 아이디"+user_id);
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
		sql += "		SELECT";
		sql += "			board_no, title, writer_id, board_cate";
		sql += "			, content, hit, writter_date";
		sql += "		FROM board WHERE writer_id=?";
		sql += "		ORDER BY TO_NUMBER(board_no) DESC"; //숫자형으로 변환한 뒤 역순으로 출력, 게시글 번호가 큰게 최근의 게시글이니까
		sql += "	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
		
		//결과 저장할 List
		List<Board> boardList = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			System.out.println(user_id);
			ps.setString(1, user_id);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
//			System.out.println(paging.getStartNo());
//			System.out.println(paging.getEndNo());
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			//조회 결과 처리
			while(rs.next()) {
//				System.out.println("here");
				Board b = new Board(); //결과값 저장 객체
				//결과값 한 행 처리
				b.setBoardno( rs.getString("board_no") );
				b.setTitle( rs.getString("title") );
				b.setUser_id( rs.getString("writer_id") );
				b.setContent( rs.getString("content") );
				b.setHit( rs.getInt("hit") );
				b.setWrittendate( rs.getDate("writer_date") );
				b.setBoardCate( rs.getString("board_cate") );
				//리스트에 결과값 저장
				boardList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return boardList;
	}

	//회원이 작성한 1:1문의 게시글 조회
	@Override
	public List<TB_ASK_BOARD> selectAskBoardList(Connection connection, AskPaging askPaging, String user_id) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
		sql += "		SELECT";
		sql += "			boardno, title, user_id";
		sql += "			, content, writtendate, board_cate";
		sql += "		FROM TB_ASK_BOARD WHERE user_id=?";
		sql += "		ORDER BY TO_NUMBER(boardno) DESC"; //숫자형으로 변환한 뒤 역순으로 출력, 게시글 번호가 큰게 최근의 게시글이니까
		sql += "	) B";
		sql += " ) TB_ASK_BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?"; 
		
		List<TB_ASK_BOARD> boardList = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setInt(2, askPaging.getStartNo());
			ps.setInt(3, askPaging.getEndNo());
			rs = ps.executeQuery(); 
			while(rs.next()) {
				TB_ASK_BOARD askBoard = new TB_ASK_BOARD();
				askBoard.setBoardno(rs.getString("BOARDNO"));
				askBoard.setTitle(rs.getString("TITLE"));
				askBoard.setContent(rs.getString("CONTENT"));
				askBoard.setWrittendate(rs.getDate("writtendate"));
				askBoard.setBoard_cate(rs.getString("board_cate"));
				askBoard.setUser_id(rs.getString("user_id"));
				
				boardList.add(askBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return boardList;
	}
	
	//장바구니 페이징 카운트
	@Override
	public int selectCartCntAll(Connection connection) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM TB_CART";
		//결과 저장할 변수
		int totalCount = 0;
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			//조회 결과 처리
			while(rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		//최종 결과 반환
		return totalCount;
	}
	//장바구니 리스트 조회
	@Override
	public List<TB_CART> selectCartList(Connection connection, CartPaging paging, String user_id) {
		System.out.println("BoardDaoImpl에서의 아이디"+user_id);
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
		sql += "		SELECT";
		sql += "			C.CART_ID, C.PROD_ID, C.USER_ID, C.CNT, P.PROD_NAME, P.PROD_PICTURETITLE";
		sql += "		FROM TB_CART C, TB_PROD P WHERE user_id=? AND P.PROD_ID = C.PROD_ID";
		sql += "		ORDER BY C.CART_ID DESC";
		sql += "	) B";
		sql += " ) C";
		sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
		//결과 저장할 List
		List<TB_CART> cartList = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			System.out.println(user_id);
			ps.setString(1, user_id);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
//			System.out.println(paging.getStartNo());
//			System.out.println(paging.getEndNo());
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			//조회 결과 처리
			while(rs.next()) {
				System.out.println("here");
				TB_CART b = new TB_CART(); //결과값 저장 객체
				//결과값 한 행 처리
				b.setCart_id(rs.getString("CART_ID"));
				b.setProd_id(rs.getString("PROD_ID"));
				b.setUser_id(rs.getString("USER_ID"));
				b.setProd_name(rs.getString("PROD_NAME"));
				b.setImg_path(rs.getString("PROD_PICTURETITLE"));
				b.setCnt(rs.getInt("CNT"));
				System.out.println(rs.getString("PROD_ID"));
				//리스트에 결과값 저장
				cartList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		//최종 결과 반환
		return cartList;
	}
	//장바구니 삭제
	@Override
	public int deleteCartList(Connection conn, String cartId) {
		PreparedStatement ps = null;
		String sql ="";
		sql += "DELETE FROM TB_CART";
		sql += " WHERE CART_ID=?";
		
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cartId);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	//
	@Override
	public int deleteAskArticle(Connection connection, String boardno) {
		PreparedStatement ps = null;
		String sql ="";
		sql += "DELETE FROM TB_ASK_BOARD";
		sql += " WHERE BOARDNO=?";
		
		int result = 0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, boardno);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	//결제 내역 조회
	@Override
	public List<TB_PAY> selectPayList(Connection connection, String user_id) {
		String sql = "";
		sql+="SELECT PA.PAY_ID,PA.ORDER_ID,PA.USER_ID,PA.PROD_ID,TO_CHAR(PA.PAY_DATE,'yyyymmddhh24miss') PAY_DATE,PA.PROD_CNT,PA.TOTALPRICE,PR.PROD_NAME,PR.PROD_PICTURETITLE ";
		sql+=" FROM TB_PAY PA ,TB_PROD PR";
		sql+=" WHERE PA.USER_ID=? AND PA.PROD_ID = PR.PROD_ID";
		List<TB_PAY> payList = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				TB_PAY pay = new TB_PAY();
				pay.setPay_id(rs.getString("PAY_ID"));
				pay.setOrder_id(rs.getString("ORDER_ID"));
				pay.setUser_id(rs.getString("USER_ID"));
				pay.setProd_id(rs.getString("PROD_ID"));
				pay.setPay_date(rs.getString("PAY_DATE"));
				pay.setOrder_id(rs.getString("ORDER_ID"));
				pay.setProd_cnt(rs.getInt("PROD_CNT"));
				pay.setTotalprice(rs.getInt("TOTALPRICE"));
				pay.setProd_name(rs.getString("PROD_NAME"));
				pay.setProd_picturetitle(rs.getString("PROD_PICTURETITLE"));
				payList.add(pay);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return payList;
	}

	//메인에 상위 8개 캠핑장
	@Override
	public List<CAMP_INFO> selectTopCamp(Connection connection) {
		String sql="";
		sql+="SELECT CAMP_ID,CAMP_NAME,PICTURE,INDUTY,INTRO";
		sql+="	FROM (";
		sql+="		SELECT L.CAMP_ID, COUNT(L.CAMP_ID),C.CAMP_NAME,C.PICTURE,C.INDUTY,TO_CHAR(C.INTRO) INTRO FROM TB_LIKE L, CAMP_INFO C";
		sql+="			WHERE L.CAMP_ID = C.CAMP_ID";
		sql+="			GROUP BY L.CAMP_ID,C.CAMP_NAME,C.PICTURE,C.INDUTY,TO_CHAR(C.INTRO) ORDER BY COUNT(CAMP_ID) DESC";
		sql+="	)T";
		sql+=" WHERE ROWNUM <= 8";  
		List<CAMP_INFO> campList = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				CAMP_INFO camp = new CAMP_INFO();
				camp.setCamp_id(rs.getInt("CAMP_ID"));
				camp.setCamp_name(rs.getString("CAMP_NAME"));
				camp.setPicture(rs.getString("PICTURE"));
				camp.setInduty(rs.getString("INDUTY"));
				camp.setIntro(rs.getString("INTRO"));
				campList.add(camp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return campList;
	}
	//최신순 5개 게시글 조회하여 메인페이지에 뿌리기
	@Override
	public List<Board> selectMainBoardList(Connection connection, String category) {
		   String sql = "";
		      sql += "SELECT * FROM (";
		      sql += "   SELECT rownum rnum, B.* FROM (";
		      sql += "      SELECT";
		      sql += "         boardno, title, user_id";
		      sql += "          , board_cate, hit";
		      sql += "      FROM board WHERE board_cate=?";
		      sql += "      ORDER BY to_char(boardno, '0000') DESC";
		      sql += "   ) B";
		      sql += " ) BOARD";
		      sql += " WHERE rnum BETWEEN 1 AND 5";
		      List<Board> boardList = new ArrayList<>();
		      try {
				ps = connection.prepareStatement(sql);
				ps.setString(1, category);
				rs = ps.executeQuery();
				while(rs.next()) {
		            Board b = new Board(); //결과값 저장 객체
		            //결과값 한 행 처리
		            b.setBoardno( rs.getString("boardno") );
		            b.setTitle( rs.getString("title") );
		            b.setUser_id( rs.getString("user_id") );
		            b.setBoardCate( rs.getString("board_cate"));
		            b.setHit( rs.getInt("hit"));
		            //리스트에 결과값 저장
		            boardList.add(b);
		        }
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
		         JDBCTemplate.close(rs);
		         JDBCTemplate.close(ps);
		      }   		      
		return boardList;
	}
	//
	@Override
	public List<TB_REV> selectRevList(Connection connection, String user_id) {
		String sql="";
		sql+="SELECT R.* ,C.CAMP_ID FROM TB_REV R, CAMP_INFO C";
		sql+=" WHERE R.USER_ID=? AND C.CAMP_NAME = R.CAMP_NAME";
		sql+=" ORDER BY PAYMENTDATE";
		List<TB_REV> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				TB_REV revinfo = new TB_REV();
				revinfo.setCamp_id(rs.getString("CAMP_ID"));
				revinfo.setCamp_name(rs.getString("CAMP_NAME"));
				revinfo.setName(rs.getString("NAME"));
				revinfo.setPeople(rs.getInt("PEOPLE"));
				revinfo.setFirst(rs.getString("FIRST"));
				revinfo.setLast(rs.getString("LAST"));
				revinfo.setCarnum(rs.getString("CARNUM"));
				revinfo.setTel(rs.getString("TEL"));
				revinfo.setTotal(rs.getInt("TOTAL"));
				revinfo.setPaymentdate(rs.getString("PAYMENTDATE"));
				list.add(revinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);;
		}
		return list;
	}	
	// 결제 내역 날짜별로 조회
	@Override
	public List<TB_PAY> selectPayListByDate(Connection connection,String user_id,String SecondDate,String firstDate) {
		String sql = "";
		System.out.println("DAO first, last : "+firstDate+" ,"+SecondDate);
		sql+="SELECT PA.PAY_ID,PA.ORDER_ID,PA.USER_ID,PA.PROD_ID,TO_CHAR(PA.PAY_DATE,'yyyymmddhh24miss') PAY_DATE,PA.PROD_CNT,PA.TOTALPRICE,PR.PROD_NAME,PR.PROD_PICTURETITLE";
		sql+=" FROM TB_PAY PA, TB_PROD PR";
		sql+=" WHERE PA.PAY_DATE BETWEEN ? AND ?";
		sql+=" AND PA.USER_ID=? AND PA.PROD_ID = PR.PROD_ID";
		sql+=" ORDER BY PAY_ID DESC";
		List<TB_PAY> payList = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, firstDate);
			ps.setString(2, SecondDate);
			ps.setString(3, user_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				TB_PAY pay = new TB_PAY();
				pay.setPay_id(rs.getString("PAY_ID"));
				pay.setOrder_id(rs.getString("ORDER_ID"));
				pay.setUser_id(rs.getString("USER_ID"));
				pay.setProd_id(rs.getString("PROD_ID"));
				pay.setPay_date(rs.getString("PAY_DATE"));
				pay.setOrder_id(rs.getString("ORDER_ID"));
				pay.setProd_cnt(rs.getInt("PROD_CNT"));
				pay.setTotalprice(rs.getInt("TOTALPRICE"));
				pay.setProd_name(rs.getString("PROD_NAME"));
				pay.setProd_picturetitle(rs.getString("PROD_PICTURETITLE"));
				payList.add(pay);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return payList;
	}
	//캠핑장 예약 내역 날짜별 조회
	@Override
	public List<TB_REV> selectRevListByDate(Connection connection, String user_id, String secondDate, String firstDate) {
		System.out.println("DAO first, last : "+firstDate+" ,"+secondDate);
		String sql = "";
		sql+="SELECT R.*,I.CAMP_ID";
		sql+=" FROM TB_REV R, CAMP_INFO I";
		sql+=" WHERE R.PAYMENTDATE BETWEEN ? AND ?";
		sql+=" AND R.USER_ID=? AND R.CAMP_NAME=I.CAMP_NAME";
		List<TB_REV> revList = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, firstDate);
			ps.setString(2, secondDate);
			ps.setString(3, user_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				TB_REV revinfo = new TB_REV();
				revinfo.setCamp_id(rs.getString("CAMP_ID"));
				revinfo.setCamp_name(rs.getString("CAMP_NAME"));
				revinfo.setName(rs.getString("NAME"));
				revinfo.setPeople(rs.getInt("PEOPLE"));
				revinfo.setFirst(rs.getString("FIRST"));
				revinfo.setLast(rs.getString("LAST"));
				revinfo.setCarnum(rs.getString("CARNUM"));
				revinfo.setTel(rs.getString("TEL"));
				revinfo.setTotal(rs.getInt("TOTAL"));
				revinfo.setPaymentdate(rs.getString("PAYMENTDATE"));
				revList.add(revinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return revList;
	}
	
	
	
	
	
	   @Override
	   public int updateComment(Connection connection, TB_COMMENT com, String commentId) {
	      String sql="";
	      sql += "UPDATE TB_COMMENT SET";
	      sql += " CONTENT = ?";
	      sql += " WHERE COMMENT_ID = ?";
	      int res = 0;
	      
	      
	      try {
	         ps = connection.prepareStatement(sql);
	         ps.setString(1, com.getContent());
	         ps.setString(2, commentId);
	         res = ps.executeUpdate();
	         
	         System.out.println(res);
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         JDBCTemplate.close(ps);
	      }
	      return res;
	   }
	   
	   @Override
	   public int insertReComment(Connection connection, TB_RECOMMENT recomment) {
	      PreparedStatement ps = null;
	      int res = 0;
	      String sql="";
	      sql+="INSERT INTO TB_RECOMMENT(COMMENT_ID,BOARDNO,USER_ID,CONTENT,COMMENT_PARENT)";
	      sql+=" VALUES(SEQ_RECOMMENT.NEXTVAL,?,?,?,?)";
	      try {
	         ps = connection.prepareStatement(sql);
	         ps.setString(1, recomment.getBoardno());
	         ps.setString(2, recomment.getUser_id());
	         ps.setString(3, recomment.getContent());
	         ps.setString(4, recomment.getComment_parent());
	         res = ps.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(ps);
	      }
	      return res;
	   }
	   
	   @Override
	   public List<TB_RECOMMENT> selectReCommentList(Connection connection, String boardno) {
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String sql="";
	      sql += "SELECT * FROM TB_RECOMMENT";
	      sql += " WHERE BOARDNO = ?";
	      sql += " ORDER BY TO_NUMBER(COMMENT_ID)";
	      List<TB_RECOMMENT> recommentList = new ArrayList<>(); 
	      try {
	         ps = connection.prepareStatement(sql);
	         ps.setString(1,boardno);
	         rs = ps.executeQuery();
	         while (rs.next()) {
	            TB_RECOMMENT recomment = new TB_RECOMMENT();
	            recomment.setBoardno(rs.getString("BOARDNO"));
	            recomment.setUser_id(rs.getString("USER_ID"));
	            recomment.setComment_date(rs.getString("COMMENT_DATE"));
	            recomment.setContent(rs.getString("CONTENT"));
	            recomment.setComment_id(rs.getString("COMMENT_ID"));
	            recomment.setComment_parent(rs.getString("COMMENT_PARENT"));
	            recommentList.add(recomment);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         JDBCTemplate.close(rs);
	         JDBCTemplate.close(ps);
	      }
	      return recommentList;
	   }
	   
	   @Override
	   public int updateReComment(Connection connection, TB_RECOMMENT recomment, String recommentId) {
	      String sql="";
	      sql += "UPDATE TB_RECOMMENT SET";
	      sql += " CONTENT = ?";
	      sql += " WHERE COMMENT_ID = ?";
	      int res = 0;
	      
	      
	      try {
	         ps = connection.prepareStatement(sql);
	         ps.setString(1, recomment.getContent());
	         ps.setString(2, recommentId);
	         res = ps.executeUpdate();
	         
	         System.out.println(res);
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         JDBCTemplate.close(ps);
	      }
	      return res;
	   }
	   
	   @Override
	   public int deleteReComment(Connection connection, String recommentId) {
	      PreparedStatement ps = null;
	      String sql ="";
	      sql += "DELETE FROM TB_RECOMMENT";
	      sql += " WHERE COMMENT_ID=?";
	      int result = 0;
	      try {
	         ps = connection.prepareStatement(sql);
	         ps.setString(1, recommentId);
	         result = ps.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(ps);
	      }
	      return result;
	   }
	
	
	
}
