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

public class RecomendDao extends DB연동 {
		//싱글톤 생성
		private static RecomendDao rd = new RecomendDao();
		//어디서든 사용 가능한 변환 메소드  [getInstance]
		public static RecomendDao getInstance() {return rd;}
		
		public RecomendDao() {
			// TODO Auto-generated constructor stub
		}
		
		// 추천게시판 출력
		public ArrayList<RecomendDto> recomendpage(){
			// 리스트 선언
			ArrayList<RecomendDto> recomendList = new ArrayList<>();
			// SQL
			String sql = "select * from recommend";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while( rs.next() ) {
					RecomendDto dto = new RecomendDto( rs.getInt(1), rs.getString(2), rs.getInt(3) , rs.getString(4));
					recomendList.add(dto);
				}
				return recomendList;
			}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); } 
			return null;
		}
		
		// 추천메뉴 등록
		public boolean recomendSignup( RecomendDto dto ) {
			String sql = "insert into recommend ( recom_title , recom_content ) values ( ? , ? )";
			try {
				ps =con.prepareStatement(sql);
				
				ps.setString(1, dto.getRecomTitle() );
				ps.setString(2, dto.getRecomContent() );
				
				ps.executeUpdate();
				
				return true;
			}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); } 
			return false;
		}
		
		// 추천메뉴 수정
		public boolean recomendUpdate( int recomendNo , String udate, String chage ) {
			String sql = "update recommend set "+udate+" = ? where recom_no = ?";
			try {
				ps = con.prepareStatement(sql);
				
				ps.setString(1, chage);
				ps.setInt(2, recomendNo);
				
				ps.executeUpdate();
				
				return true;
			}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); }
			return false;
		}
		
		// 추천메뉴 삭제
		public boolean recomendDelete( int recomendNo ) {
			String sql = "delete from recommend where recom_no = ?";
			try {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1, recomendNo);
				
				ps.executeUpdate();
				
				return true;
			}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); }
			return false;
		}
	
}