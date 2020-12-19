package camping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import camping.controller.Paging;
import camping.dao.CampingDAO;
import camping.dto.Camp;
import common.JDBCTemplate;


public class CampingDAOimpl implements CampingDAO {
   private static String getTagValue(String tag, Element ele) {

        NodeList nodeList = ele.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nodeList.item(0);

        if(nValue == null) {

            return null;

        }

        return nValue.getNodeValue();

    }// getTagValue
   @Override
   public List<Camp> list(Connection conn,Paging paging) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      List<Camp> list=new ArrayList<>();
      String sql="";
      sql += "SELECT * FROM (SELECT rownum rnum, B.* FROM (SELECT * FROM CAMP_INFO) B) TB_PROD WHERE rnum BETWEEN ? AND ? "; 

      try {
         //SQL 수행 객체 생성
      
         ps=conn.prepareStatement(sql);
         ps.setInt(1, paging.getStartNo());
        ps.setInt(2, paging.getEndNo());
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         while( rs.next() ) {
            Camp camp=new Camp();
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
            System.out.println(camp);
            
            list.add(camp);
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return list;
   }

   @Override
   public List<String> geolist(Connection conn) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      
      String sql="";
      sql+="Select distinct(ADDR_DO) from camp_info";
      List<String> sido=new ArrayList<String>();
      try {
         //SQL 수행 객체 생성
         ps=conn.prepareStatement(sql);
         
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         while( rs.next() ) {
            
            sido.add(rs.getString("ADDR_DO"));
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return sido;
   }

   @Override
   public List<Camp> S_list(Connection conn,String addr,Paging paging) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      
      String sql="";
      sql += "SELECT * FROM (SELECT rownum rnum, B.* FROM (SELECT * FROM CAMP_INFO where ADDR_DO=?) B) TB_PROD WHERE rnum BETWEEN ? AND ? "; 

      
      List<Camp> S_list=new ArrayList<>();
      try {
         //SQL 수행 객체 생성
        
         ps=conn.prepareStatement(sql);
         ps.setString(1, addr);
         ps.setInt(2, paging.getStartNo());
         ps.setInt(3, paging.getEndNo());
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         while( rs.next() ) {
            Camp camp=new Camp();
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
            
            S_list.add(camp);
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return S_list;
   }

   @Override
   public List<Camp> Selectlist_all(Connection conn, String address, String induty, String keyword) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      String ADDR=address;
      String indu="%"+induty+"%";
      String key="%"+keyword+"%";
      String sql="";
      List<Camp> Select_All=new ArrayList<>();
      sql+="Select * from CAMP_INFO";
      sql+=" where ADDR_DO =? and INDUTY like ? and (ADDR like ? or CAMP_NAME like ? or FACIL like ? or INDUTY like ?)"; 
      try {
         //SQL 수행 객체 생성
         ps=conn.prepareStatement(sql);
         ps.setString(1, ADDR);
         ps.setString(2, indu);
         ps.setString(3, key);
         ps.setString(4, key);
         ps.setString(5, key);
         ps.setString(6, key);
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         
         
         while( rs.next() ) {
            Camp camp=new Camp();
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
            
            Select_All.add(camp);
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      
      }
      System.out.println("전부다");
      return Select_All;
   }

   @Override
   public Camp select_id(Connection conn, int id) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      
      String sql="";
      sql+="Select * from camp_info";
      sql+=" where CAMP_ID=?";
      Camp camp=new Camp();
      try {
         //SQL 수행 객체 생성
         ps=conn.prepareStatement(sql);
         ps.setInt(1, id);
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         
         while( rs.next() ) {
            
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return camp;
      
   }

   @Override
   public List<String> select_id_image(int camp_id) {
      String serviceKey = "Fl5mf246v9aBCpcBdkO2vSR6ZcKdXrDzHJOtukP6oQz7QVlDpYEG8B0NXJ6HxSh0TV%2B2%2FPOmoGaPqrE2TxrecA%3D%3D";

        int page =1; //페이지 초기값
        List<String> imagelist=new ArrayList<>();
        try {

            while(true) {         
                //parsing할 url 지정 

                String url = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/imageList?"

                        +  "serviceKey=" + serviceKey  // 인증키
                        + "&MobileOS=ETC" + "&MobileApp=AppTest&" + "contentId=" +camp_id;

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

                DocumentBuilder dBuilder  = dbFactory.newDocumentBuilder();

                Document doc = dBuilder.parse(url);
                //root tag

                doc.getDocumentElement().normalize();           

                // parsing tag

                NodeList nodeList = doc.getElementsByTagName("item"); //리스트 단위
                for(int temp =0; temp<nodeList.getLength(); temp++) {//크기만큼
                   
         
                   Node nNode = nodeList.item(temp);  
                    if(nNode.getNodeType()==Node.ELEMENT_NODE) {

                        Element element = (Element) nNode;
                        imagelist.add(getTagValue("imageUrl",element));
                        
                    }//if

                }//for

                page += 1;

                if(page > 12) {

                    break;

                }
               
            }//while 

        } catch (Exception e) {

            e.printStackTrace();

        }
      
      return imagelist;
   }
   @Override
   public int insertlike(Connection conn, String id, String camp) {
      PreparedStatement ps=null;
      int result=0;
      //체크용 print
      
      String sql="";
      sql+="INSERT INTO TB_LIKE(USER_ID,CAMP_ID,COUNT_LIKE)";
      sql+=" SELECT ?,?,1 FROM DUAL A WHERE NOT EXISTS (SELECT 0 FROM TB_LIKE WHERE USER_ID=? and CAMP_ID=?)";
      
      try {
         ps=conn.prepareStatement(sql);
         ps.setString(1,id);
         ps.setString(2,camp);
         ps.setString(3,id);
         ps.setString(4,camp);
         result=ps.executeUpdate();
         conn.commit();
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return result;
   }
   
   @Override
   public int likecount(Connection conn,String camp) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      int result=0;
      String sql="";
      sql+="Select count(CAMP_ID) count from TB_LIKE";
      sql+=" where CAMP_ID=?";

      try {
         ps=conn.prepareStatement(sql);
         ps.setString(1, camp);
         
         rs=ps.executeQuery();

         conn.commit();

         if(rs.next()){
               result = rs.getInt("count");
            }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return result;
   }
   @Override
   public int selectlike(Connection conn, int camp_id) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      int result=0;
      String sql="";
      sql+="Select count(CAMP_ID) count from TB_LIKE";
      sql+=" where CAMP_ID=?";

      try {
         ps=conn.prepareStatement(sql);
         ps.setInt(1, camp_id);
         
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();

         conn.commit();

         if(rs.next()){
               result = rs.getInt("count");
            }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return result;
   }
   @Override
   public int jjim(Connection conn,String id, String camp) {
      PreparedStatement ps=null;
      int result=0;
      //체크용 print
      
      String sql="";
      sql+="INSERT INTO TB_JJIM(USER_ID,CAMP_ID)";
      sql+=" SELECT ?,? FROM DUAL A WHERE NOT EXISTS (SELECT 0 FROM TB_JJIM WHERE USER_ID=? and CAMP_ID=?)";
      
      try {
         ps=conn.prepareStatement(sql);
         ps.setString(1,id);
         ps.setString(2,camp);
         ps.setString(3,id);
         ps.setString(4,camp);
         result=ps.executeUpdate();
         conn.commit();
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return result;
   }
   @Override
   public int jjim(Connection conn, int camp_id, String userid) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      int result=0;
      String sql="";
      sql+="Select count(CAMP_ID) count from TB_JJIM";
      sql+=" where CAMP_ID=? and USER_ID=?";

      try {
         ps=conn.prepareStatement(sql);
         ps.setInt(1, camp_id);
         ps.setString(2, userid);
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();

         conn.commit();

         if(rs.next()){
               result = rs.getInt("count");
            }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return result;
   }
   @Override
   public List<Camp> Selectlist_key(Connection conn, String keyword) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      String key="%"+keyword+"%";
      String sql="";
      List<Camp> Select_All=new ArrayList<>();
      sql+="Select * from CAMP_INFO";
      sql+=" where ADDR like ? or CAMP_NAME like ? or FACIL like ? or INDUTY like ?"; 
      try {
         //SQL 수행 객체 생성
         ps=conn.prepareStatement(sql);
         ps.setString(1, key);
         ps.setString(2, key);
         ps.setString(3, key);
         ps.setString(4, key);
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         
         
         while( rs.next() ) {
            Camp camp=new Camp();
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
            
            Select_All.add(camp);
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      
      }
      System.out.println("키워드만");
      return Select_All;
   }
   @Override
   public List<Camp> Selectlist_inkey(Connection conn, String induty, String keyword) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      String key="%"+keyword+"%";
      String sql="";
      List<Camp> Select_All=new ArrayList<>();
      sql+="Select * from CAMP_INFO";
      sql+=" where induty like ? and (ADDR like ? or CAMP_NAME like ? or FACIL like ?)";
      try {
         //SQL 수행 객체 생성
         ps=conn.prepareStatement(sql);
         ps.setString(1, induty );
         ps.setString(2, key);
         ps.setString(3, key);
         ps.setString(4, key);
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         
         
         while( rs.next() ) {
            Camp camp=new Camp();
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
            
            Select_All.add(camp);
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      
      }
      System.out.println("indu랑 키워드");
      return Select_All;
   }
   @Override
   public List<Camp> Selectlist_addkey(Connection conn, String address, String keyword) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      String sql="";
      String key="%"+keyword+"%";
      List<Camp> Select_All=new ArrayList<>();
      sql+="Select * from CAMP_INFO";
      sql+=" where ADDR_DO=? and (INDUTY like ? or CAMP_NAME like ? or FACIL like ?)"; 
      try {
         //SQL 수행 객체 생성
         ps=conn.prepareStatement(sql);
         ps.setString(1, address);
         ps.setString(2, key);
         ps.setString(3, key);
         ps.setString(4, key);
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         
         
         while( rs.next() ) {
            Camp camp=new Camp();
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
            
            Select_All.add(camp);
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      
      }
      System.out.println("address랑 키워드");
      return Select_All;
   }
   @Override
   public List<Camp> Selectlist_addin(Connection conn, String address, String induty) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      String sql="";
      List<Camp> Select_All=new ArrayList<>();
      sql+="Select * from CAMP_INFO";
      sql+=" where ADDR_DO=? and INDUTY like ?"; 
      try {
         //SQL 수행 객체 생성
         ps=conn.prepareStatement(sql);
         ps.setString(1, address );
         ps.setString(2, induty);
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         
         while( rs.next() ) {
            Camp camp=new Camp();
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
            
            Select_All.add(camp);
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      
      }
      System.out.println("address랑 indu만");      
      return Select_All;
   }
   @Override
   public List<Camp> Selectlist_add(Connection conn, String address) {
      PreparedStatement ps=null;
      ResultSet rs=null;
      String ADDR=address;
      String sql="";
      List<Camp> Select_All=new ArrayList<>();
      sql+="Select * from CAMP_INFO";
      sql+=" where ADDR_DO=?"; 
      try {
         //SQL 수행 객체 생성
         ps=conn.prepareStatement(sql);
         ps.setString(1, ADDR );   
         //SQL 수행 및 결과 반환
         rs=ps.executeQuery();
         
         while( rs.next() ) {
            Camp camp=new Camp();
            camp.setADDR(rs.getString("ADDR"));
            camp.setADDR_DO(rs.getString("ADDR_DO"));
            camp.setCAMP_ID(rs.getInt("CAMP_ID"));
            camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
            camp.setURL(rs.getString("URL"));
            camp.setINDUTY(rs.getString("INDUTY"));
            camp.setLONGITUDE(rs.getString("LONGITUDE"));
            camp.setLATITUDE(rs.getString("LATITUDE"));
            camp.setFACIL(rs.getString("FACIL"));
            camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
            camp.setTEL(rs.getString("TEL"));
            camp.setINTRO(rs.getString("INTRO"));
            
            Select_All.add(camp);
         }
      }catch (SQLException e) {
      }finally {
         try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      System.out.println("address만");
      return Select_All;
   }
   @Override
   public List<Camp> Selectlist_in(Connection conn, String induty) {
         
         PreparedStatement ps=null;
         ResultSet rs=null;
         String sql="";
         List<Camp> Select_All=new ArrayList<>();   
            sql+="Select * from CAMP_INFO";
            sql+=" where INDUTY like ?"; 
            try {
               //SQL 수행 객체 생성
               ps=conn.prepareStatement(sql);
               ps.setString(1, induty );   
               //SQL 수행 및 결과 반환
               rs=ps.executeQuery();
               System.out.println("이프 6번");
               
               while( rs.next() ) {
                  Camp camp=new Camp();
                  camp.setADDR(rs.getString("ADDR"));
                  camp.setADDR_DO(rs.getString("ADDR_DO"));
                  camp.setCAMP_ID(rs.getInt("CAMP_ID"));
                  camp.setCAMP_NAME(rs.getString("CAMP_NAME"));
                  camp.setURL(rs.getString("URL"));
                  camp.setINDUTY(rs.getString("INDUTY"));
                  camp.setLONGITUDE(rs.getString("LONGITUDE"));
                  camp.setLATITUDE(rs.getString("LATITUDE"));
                  camp.setFACIL(rs.getString("FACIL"));
                  camp.setADDR_SIGUNGU(rs.getString("ADDR_SIGUNGU"));
                  camp.setTEL(rs.getString("TEL"));
                  camp.setINTRO(rs.getString("INTRO"));
                  
                  Select_All.add(camp);
               }
            }catch (SQLException e) {
            }finally {
               try {
                  if(rs!=null) rs.close();
                  if(ps!=null) ps.close();
               } catch (SQLException e) {
                  e.printStackTrace();
               }
            }
            System.out.println("induty만");
            return Select_All;
   }
   @Override
   public int selectCntAll(Connection connection) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      //SQL 작성
      String sql = "";
      sql += "SELECT count(*) FROM camp_info";
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
}
   