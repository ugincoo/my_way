package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import myWay.controller.BoardController;
import myWay.dto.BcommendDto;
import myWay.dto.RecomendDto;

public class BoardDao {
	//싱글톤 생성
		private static BoardDao bdao = new BoardDao();
		//어디서든 사용 가능한 변환 메소드  [getInstance]
		public static BoardDao getInstance() {return bdao;}
		
		// 1.필드
		private Connection con;			// 1. 연결된 DB구현객체를 가지고 있는 인터페이스
		private PreparedStatement ps;	// 2. 연결된 SQL 조작 [+매개변수 가능]
		private ResultSet rs;			// 3. 실행된 SQL 결과 인터페이스
		
		// DB 연동
			public BoardDao() {//빈 생성자 s
				try{
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myway","root","1234");
					System.out.println("연동성공!");
				}
				catch (Exception e) {System.out.println("연동 실패 : " + e);}
			}// 빈 생성자 e
			
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
		
		// 댓글 출력
		public ArrayList<BcommendDto> commentList(){
			//여러개 게시판 저장을 위한 리스트 선언
			ArrayList<BcommendDto> commentList = new ArrayList<>();
			//1. SQL 작성
			String sql = "select * from bcommend";
			//2.연결된 DB에 작성된 SQL 대입
			try {ps = con.prepareStatement(sql);
			//3. SQL 조작[매개변수 없으면 생략]		
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
		
	
		
}
/*
	//1. SQL 작성
	//2.연결된 DB에 작성된 SQL 대입
	//3. SQL 조작[매개변수 없으면 생략]
	//4. SQL 실행
	//5. SQL 결과
*/