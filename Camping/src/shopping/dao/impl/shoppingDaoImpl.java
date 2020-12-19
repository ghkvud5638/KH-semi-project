package shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.CartPaging;
import common.JDBCTemplate;
import common.Paging;
import shopping.dao.face.shoppingDao;
import shopping.dto.Board;
import shopping.dto.TB_CART;
import shopping.dto.TB_PROD;

public class shoppingDaoImpl implements shoppingDao {
	

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<TB_PROD> selectAllProd(Connection conn, Paging paging) {
		System.out.println("myPageDao - selectLittle() 호출");
		PreparedStatement ps = null; // SQL 수행 객체
		ResultSet rs = null; // 조회결과 객체
		String sql = "";		
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
		sql += "		SELECT";
		sql += "			PROD_NAME, PROD_PICTURETITLE";
		sql += "			, PROD_PRICE,PROD_ID";
		sql += "		FROM TB_PROD";
		sql += "	) B";
		sql += " ) TB_PROD";
		sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
		
				
		List<TB_PROD> prodlist = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			System.out.println("StartNo : "+paging.getStartNo());
			System.out.println("EndNo : "+paging.getEndNo());
			
			rs = ps.executeQuery(); // SQL 수행 및 결과 반환
			while (rs.next()) {
				TB_PROD prodinfo = new TB_PROD();
//				prodinfo.setProd_id(rs.getString("PROD_ID"));
				prodinfo.setProd_name(rs.getString("PROD_NAME"));
				prodinfo.setProd_picturetitle(rs.getString("PROD_PICTURETITLE"));
				prodinfo.setProd_price(rs.getInt("PROD_PRICE"));
				prodinfo.setProd_id(rs.getString("PROD_ID"));
				prodlist.add(prodinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return prodlist;
	}
	
	
	@Override
	public TB_PROD selectByProdId(Connection conn, TB_PROD prod) {
		PreparedStatement ps = null; // SQL 수행 객체
		ResultSet rs = null; // 조회결과 객체
		String sql = "";
		sql +="SELECT PROD_NAME,PROD_PICTURETITLE,PROD_PICTUREDETAIL,PROD_PRICE,PROD_ID FROM TB_PROD";
		sql +=" WHERE PROD_ID=?";
		TB_PROD prodinfo = new TB_PROD();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prod.getProd_id());
			
			rs = ps.executeQuery(); // SQL 수행 및 결과 반환
			while (rs.next()) {
				prodinfo.setProd_name(rs.getString("PROD_NAME"));
				prodinfo.setProd_picturetitle(rs.getString("PROD_PICTURETITLE"));
				prodinfo.setProd_picturedetail(rs.getString("PROD_PICTUREDETAIL"));
				prodinfo.setProd_price(rs.getInt("PROD_PRICE"));
				prodinfo.setProd_id(rs.getString("PROD_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return prodinfo;
	}
	
	@Override
	public int insertCart(Connection conn, TB_CART cart) {
		PreparedStatement ps = null; // SQL 수행 객체
		String sql="";
		sql +="INSERT INTO TB_CART";
		sql +=" VALUES(TB_CART_SEQ.NEXTVAL,?,?,?)";
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cart.getProd_id());
			ps.setString(2, cart.getUser_id());
			ps.setInt(3, cart.getCnt());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	
	/**
	 * 전체 상품 리스트 페이징
	 * 
	 */
	@Override
	public int selectCntAll(Connection connection) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM TB_PROD";
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
		return totalCount;
	}
	
	
	

	//----------------------------------------------------
	//---사이드 메뉴
	//1. 텐트/타프
		@Override
		public List<TB_PROD> tentList(Connection conn, Paging paging1) {
			//SQL 작성
					String sql = "";
					sql += "SELECT * FROM (";
					sql += "	SELECT rownum rnum, B.* FROM (";
					sql += "		SELECT";
					sql += "			PROD_PICTURETITLE, PROD_ID, PROD_NAME, PROD_PRICE";
					sql += "		FROM TB_PROD";
					sql += " 		WHERE PROD_ID LIKE 't0%'";
					sql += "		ORDER BY PROD_ID DESC";
					sql += "	) B";
					sql += " ) TB_PROD";
					sql += " WHERE rnum BETWEEN ? AND ?"; //start, end number
							
					//리스트 결과 저장할 LIST
					List<TB_PROD> tentList = new ArrayList<TB_PROD>();
					
					try {
						ps = conn.prepareStatement(sql);
						
						ps.setInt(1, paging1.getStartNo());
						ps.setInt(2, paging1.getEndNo());
						
						//결과 저장
						rs = ps.executeQuery();
						
						while(rs.next()) {
							//결과값 저장 
							TB_PROD p = new TB_PROD();
							
							p.setProd_picturetitle(rs.getString("prod_picturetitle"));
							p.setProd_id(rs.getString("prod_id"));
							p.setProd_name(rs.getString("prod_name"));
							p.setProd_price(rs.getInt("prod_price"));
							
							tentList.add(p);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						JDBCTemplate.close(rs);
						JDBCTemplate.close(ps);
					}
					
							
					return tentList;
		}


		@Override
		public int selectCntAll1(Connection conn) {
			//SQL 작성
			String sql = "";
			sql += "SELECT count(*) FROM TB_PROD";
			sql += " WHERE PROD_ID LIKE 't0%'";
			
			//결과 저장할 변수
			int totalCount1 = 0;
			
			
			try {
				//SQL 수행객체, 결과 저장
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					totalCount1 = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			return totalCount1;
			
			
		}

	//2. 침낭
		@Override
		public List<TB_PROD> sleepingbagList(Connection conn, Paging paging2) {
			//SQL 작성
			String sql = "";
			sql += "SELECT * FROM (";
			sql += "	SELECT rownum rnum, B.* FROM (";
			sql += "		SELECT";
			sql += "			PROD_PICTURETITLE, PROD_ID, PROD_NAME, PROD_PRICE";
			sql += "		FROM TB_PROD";
			sql += " 		WHERE PROD_ID LIKE 's%'";
			sql += "		ORDER BY PROD_ID DESC";
			sql += "	) B";
			sql += " ) TB_PROD";
			sql += " WHERE rnum BETWEEN ? AND ?"; //start, end number
					
			//리스트 결과 저장할 LIST
			List<TB_PROD> sleepingbagList = new ArrayList<TB_PROD>();
			
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, paging2.getStartNo());
				ps.setInt(2, paging2.getEndNo());
				
				//결과 저장
				rs = ps.executeQuery();
				
				while(rs.next()) {
					//결과값 저장 
					TB_PROD p = new TB_PROD();
					
					p.setProd_picturetitle(rs.getString("prod_picturetitle"));
					p.setProd_id(rs.getString("prod_id"));
					p.setProd_name(rs.getString("prod_name"));
					p.setProd_price(rs.getInt("prod_price"));
					
					sleepingbagList.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			
					
			return sleepingbagList;
		}

		
		@Override
		public int selectCntAll2(Connection conn) {
			//SQL 작성
			String sql = "";
			sql += "SELECT count(*) FROM TB_PROD";
			sql += " WHERE PROD_ID LIKE 's%'";
			
			//결과 저장할 변수
			int totalCount2 = 0;
			
			
			try {
				//SQL 수행객체, 결과 저장
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					totalCount2 = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			return totalCount2;
			
		}


	//3. 테이블/의자
		@Override
		public List<TB_PROD> tableList(Connection conn, Paging paging3) {
			//SQL 작성
					String sql = "";
					sql += "SELECT * FROM (";
					sql += "	SELECT rownum rnum, B.* FROM (";
					sql += "		SELECT";
					sql += "			PROD_PICTURETITLE, PROD_ID, PROD_NAME, PROD_PRICE";
					sql += "		FROM TB_PROD";
					sql += " 		WHERE PROD_ID LIKE 'tc%'";
					sql += "		ORDER BY PROD_ID DESC";
					sql += "	) B";
					sql += " ) TB_PROD";
					sql += " WHERE rnum BETWEEN ? AND ?"; //start, end number
							
					//리스트 결과 저장할 LIST
					List<TB_PROD> tableList = new ArrayList<TB_PROD>();
					
					try {
						ps = conn.prepareStatement(sql);
						
						ps.setInt(1, paging3.getStartNo());
						ps.setInt(2, paging3.getEndNo());
						
						//결과 저장
						rs = ps.executeQuery();
						
						while(rs.next()) {
							//결과값 저장 
							TB_PROD p = new TB_PROD();
							
							p.setProd_picturetitle(rs.getString("prod_picturetitle"));
							p.setProd_id(rs.getString("prod_id"));
							p.setProd_name(rs.getString("prod_name"));
							p.setProd_price(rs.getInt("prod_price"));
							
							tableList.add(p);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						JDBCTemplate.close(rs);
						JDBCTemplate.close(ps);
					}
					
							
					return tableList;
		}

		@Override
		public int selectCntAll3(Connection conn) {
			//SQL 작성
			String sql = "";
			sql += "SELECT count(*) FROM TB_PROD";
			sql += " WHERE PROD_ID LIKE 'tc%'";
			
			//결과 저장할 변수
			int totalCount3 = 0;
			
			
			try {
				//SQL 수행객체, 결과 저장
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					totalCount3 = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			return totalCount3;
			
		}


	//4. 취사용품
		@Override
		public List<TB_PROD> cookingList(Connection conn, Paging paging4) {
			//SQL 작성
					String sql = "";
					sql += "SELECT * FROM (";
					sql += "	SELECT rownum rnum, B.* FROM (";
					sql += "		SELECT";
					sql += "			PROD_PICTURETITLE, PROD_ID, PROD_NAME, PROD_PRICE";
					sql += "		FROM TB_PROD";
					sql += " 		WHERE PROD_ID LIKE 'c%'";
					sql += "		ORDER BY PROD_ID DESC";
					sql += "	) B";
					sql += " ) TB_PROD";
					sql += " WHERE rnum BETWEEN ? AND ?"; //start, end number
							
					//리스트 결과 저장할 LIST
					List<TB_PROD> cookingList = new ArrayList<TB_PROD>();
					
					try {
						ps = conn.prepareStatement(sql);
						
						ps.setInt(1, paging4.getStartNo());
						ps.setInt(2, paging4.getEndNo());
						
						//결과 저장
						rs = ps.executeQuery();
						
						while(rs.next()) {
							//결과값 저장 
							TB_PROD p = new TB_PROD();
							
							p.setProd_picturetitle(rs.getString("prod_picturetitle"));
							p.setProd_id(rs.getString("prod_id"));
							p.setProd_name(rs.getString("prod_name"));
							p.setProd_price(rs.getInt("prod_price"));
							
							cookingList.add(p);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						JDBCTemplate.close(rs);
						JDBCTemplate.close(ps);
					}
					
							
					return cookingList;
		}


		@Override
		public int selectCntAll4(Connection conn) {
			//SQL 작성
			String sql = "";
			sql += "SELECT count(*) FROM TB_PROD";
			sql += " WHERE PROD_ID LIKE 'c%'";
			
			//결과 저장할 변수
			int totalCount4 = 0;
			
			
			try {
				//SQL 수행객체, 결과 저장
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					totalCount4 = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			return totalCount4;

		}


		@Override
		public List<Board> selectReview(Connection conn, String prodno) {
			//sql 작성
			System.out.println("shopping dao prodno : " + prodno);
			String sql = "";
			sql += "SELECT * FROM board";
			sql += " WHERE ID = ? and BOARD_CATE='review'";
			sql += " ORDER BY boardno DESC";
			
			List<Board> reviewList = new ArrayList<Board>();
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, prodno);
				
				rs = ps.executeQuery();
				while(rs.next()) {
					Board rList = new Board();
					
					rList.setBoardno(rs.getString("BOARDNO"));
					rList.setWrittendate(rs.getDate("Writtendate"));
					rList.setTitle(rs.getString("title"));
					rList.setContent(rs.getString("content"));
					rList.setUser_id(rs.getString("user_id"));
					
					reviewList.add(rList);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			
			
			return reviewList;
		}


		@Override
		public List<Board> selectQa(Connection conn, String prodno) {
			//sql 작성
			String sql = "";
			sql += "SELECT * FROM board";
			sql += " WHERE ID = ? and BOARD_CATE='Q&A'";
			sql += " ORDER BY boardno DESC";
			
			List<Board> qaList = new ArrayList<Board>();
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, prodno);
				
				rs = ps.executeQuery();
				while(rs.next()) {
					Board qList = new Board();
					
					qList.setBoardno(rs.getString("BOARDNO"));
					qList.setWrittendate(rs.getDate("Writtendate"));
					qList.setTitle(rs.getString("title"));
					qList.setContent(rs.getString("content"));
					qList.setUser_id(rs.getString("user_id"));
					
					qaList.add(qList);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			return qaList;
		}
	
		
		@Override
		public List<TB_PROD> selectProdListBySort(Connection conn,Paging paging, String sort) {
			List<TB_PROD> prodlist = new ArrayList<>();
			String sql="";
			if ("DESC".equals(sort)) {
				System.out.println("dao sort : "+sort);
				sql += "SELECT * FROM (";
				sql += " SELECT rownum rnum, B.* FROM (";  				
				sql += "		SELECT PROD_NAME, PROD_PICTURETITLE,PROD_PRICE,PROD_ID FROM TB_PROD";
				sql += " 		ORDER BY PROD_PRICE DESC";
				sql += "	) B";
				sql += " ) TB_PROD";
				sql += " WHERE rnum BETWEEN ? AND ?"; 
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1,paging.getStartNo());
					ps.setInt(2,paging.getEndNo());
					rs = ps.executeQuery();
					while(rs.next()) {
						TB_PROD prod = new TB_PROD();
//						prodinfo.setProd_id(rs.getString("PROD_ID"));
						prod.setProd_name(rs.getString("PROD_NAME"));
						prod.setProd_picturetitle(rs.getString("PROD_PICTURETITLE"));
						prod.setProd_price(rs.getInt("PROD_PRICE"));
						prod.setProd_id(rs.getString("PROD_ID"));
						prodlist.add(prod);
						System.out.println("dao list : " + prodlist);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(ps);
				}
			}
			
			if ("ASC".equals(sort)) {
				System.out.println("dao sort : "+sort);
				sql += "SELECT * FROM (";
				sql += " SELECT rownum rnum, B.* FROM(";  				
				sql += "		SELECT PROD_NAME, PROD_PICTURETITLE, PROD_PRICE,PROD_ID FROM TB_PROD";
				sql += " 		ORDER BY PROD_PRICE ASC";
				sql += "	) B";
				sql += " ) TB_PROD";
				sql += " WHERE rnum BETWEEN ? AND ?"; 
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1,paging.getStartNo());
					ps.setInt(2,paging.getEndNo());
					rs = ps.executeQuery();
					while(rs.next()) {
						TB_PROD prod = new TB_PROD();
//						prodinfo.setProd_id(rs.getString("PROD_ID"));
						prod.setProd_name(rs.getString("PROD_NAME"));
						prod.setProd_picturetitle(rs.getString("PROD_PICTURETITLE"));
						prod.setProd_price(rs.getInt("PROD_PRICE"));
						prod.setProd_id(rs.getString("PROD_ID"));
						prodlist.add(prod);
						System.out.println("dao list : " + prodlist);

					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(ps);
				}
			}
			
			return prodlist;
		}
}
