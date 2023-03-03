package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import myWay.dto.*;
import myWay.dao.*;
import myWay.controller.*;
import myWay.view.*;

public class BoardDao extends DB연동{
	//싱글톤 생성
		private static BoardDao bdao = new BoardDao();
		//어디서든 사용 가능한 변환 메소드  [getInstance]
		public static BoardDao getInstance() {return bdao;}
		private BoardDao () {}
	
		// 추천 게시물	
		public ArrayList<RecomendDto> boardlist(){
			//여러개 게시판 저장을 위한 리스트 선언
			ArrayList<RecomendDto> boardList = new ArrayList<>();
			//1. SQL 작성
			String sql = "select * from recommend";
			//2.연결된 DB에 작성된 SQL 대입
			try {ps = con.prepareStatement(sql);
			//3. SQL 조작[매개변수 없으면 생략]		
			//4. SQL 실행
				rs = ps.executeQuery();
			//5. SQL 결과
				while(rs.next()) {
					//레코드1개 -> 객체화 1개 ->[rs.get~~ (필드순서번호)]
					RecomendDto rdto = new RecomendDto(
							rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4) );
					boardList.add(rdto);
				}return boardList;
			}catch (Exception e) {System.out.println("연동실패 사유 : " + e); }	
			return null;
		}
		
		//게시물출력
		public ArrayList<RecomendDto> boardPrintRecent() {
			
			ArrayList<RecomendDto> blist = new ArrayList<>();
			
			String sql = " select * from recommend limit 3";
			try {
				ps = con.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()) {
					RecomendDto dto = new RecomendDto(
							rs.getInt(1),
							rs.getString(2), 
							rs.getInt(3), 
							rs.getString(4));
					blist.add(dto);
				}
			}catch (Exception e) {System.out.println(e);}
			return blist;
		}
		
		
		
		
		//조회수 증가 
		public void view( int recomNo) {
			
			//1. SQL 작성
			String sql ="update recommend set recom_view = recom_view+1 where recom_no =?";
			//2.연결된 DB에 작성된 SQL 대입
			try {ps = con.prepareStatement(sql);
			//3. SQL 조작[매개변수 없으면 생략]
			ps.setInt(1, recomNo);
			//4. SQL 실행
			ps.executeUpdate();
			//5. SQL 결과
			}catch (Exception e) {System.out.println("연동실패 사유 : " + e); }		
			
		}
		
		
		// 댓글 출력
		public ArrayList<BcommendDto> commentList(int bcommNo){
			//여러개 게시판 저장을 위한 리스트 선언
			ArrayList<BcommendDto> commentList = new ArrayList<>();
			//1. SQL 작성
			String sql = "select * from bcommend where recom_no = ?";
			//2.연결된 DB에 작성된 SQL 대입
			try {ps = con.prepareStatement(sql);
			//3. SQL 조작[매개변수 없으면 생략]	
			ps.setInt(1, bcommNo);
			//4. SQL 실행
			rs = ps.executeQuery();
			//5. SQL 결과
				while(rs.next()) {
					//레코드1개 -> 객체화 1개 ->[rs.get~~ (필드순서번호)]
					BcommendDto bdto = new BcommendDto(
							rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4) );
					commentList.add(bdto);
				}return commentList;
			}catch (Exception e) {System.out.println("연동실패 사유 : " + e); }	
			return null;
		}
		//댓글 작성
		public boolean comment(String bcommContent, int memberNo, int recomNo) {
			// 리스트 선언
			
			//1. SQL 작성
			String sql = "insert into bcommend (bcomm_content,member_no,recom_no) values (?,?,?)";
			//2.연결된 DB에 작성된 SQL 대입
			try {ps = con.prepareStatement(sql);
			//3. SQL 조작[매개변수 없으면 생략]
				ps.setString(1, bcommContent);
				ps.setInt(2,memberNo);
				ps.setInt(3,recomNo);
				
			//4. SQL 실행
				ps.executeUpdate();
				return true;
			//5. SQL 결과
			}catch (Exception e) {System.out.println("연동 실패 : " + e);}
				return false;		
		}
		// 댓글 삭제 [ 인수 특정번호 댓글 삭제/  반환 t/f ]
		public boolean delete(int bcommNo) {
			//1. SQL 작성
			String sql = "delete from bcommend where bcomm_no = ?";
			//2.연결된 DB에 작성된 SQL 대입
			try {
				ps = con.prepareStatement(sql);
						
			//3. SQL 조작[매개변수 없으면 생략]
				ps.setInt(1, bcommNo);
			//4. SQL 실행
				ps.executeUpdate();
				return true;
			//5. SQL 결과
			}catch (Exception e) {System.out.println("연동 실패 : " + e);}
			return false;
		}
	
	//댓글삭제 유효성 검사
				
}
/*
	//1. SQL 작성
	//2.연결된 DB에 작성된 SQL 대입
	//3. SQL 조작[매개변수 없으면 생략]
	//4. SQL 실행
	//5. SQL 결과
*/