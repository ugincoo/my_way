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


public class MemberDao {
	// Database 연결
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	//싱글톤
	private static MemberDao memberDao = new MemberDao();
	public MemberDao() {
		 try {
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myway","root","kimy172418");
		 }catch (Exception e) { 
			 System.out.println("Db연결 실패 ::: " + e); 
		 }
	}
	public static MemberDao getInstance() {
		 return memberDao;
	}
	
	// 회원가입
	public boolean signup (String memberId, int memberPw) {
		try {
				int result = checkUserId(memberId);
			
			if (result==0) {
				// 회원가입 완료
				String sql="insert into member( member_Id, member_Pw )values(?,?);";	
					try {
						ps=conn.prepareStatement(sql);
						ps.setString(1, memberId);
						ps.setInt( 2 , memberPw );
						ps.executeUpdate(); 
						
						return true;
						
					}catch (Exception e) {
						System.out.println("DB오류"+e);
					}

			} else if (result==1) {
				// 회원가입 불가 (중복되는 아이디)
				System.out.println("중복되는 아이디입니다.");
				return false;
			} else {
				System.out.println("MemberDao.java checkUserId 오류 해당 확인해볼 것");
				return false;
			}
		} catch (Exception e) {
			System.out.println("MemberDao.java signup 오류");
		}
		return true;
	}
	 
	 

	// 로그인 
	public MemberDto login(String memberId,int memberPw ) {

				try {
						MemberDto result = checklogin(memberId, memberPw);
							// 경우의수 1.DB의 저장된 ID,PW가 일치해야함.. /2. ID 나 PW 둘 중 하나라도 안맞는 경우 
							if(result !=null) {//로그인성공
								String sql = "INSERT INTO MEMBER(MEMBER_ID,MEMBER_PW) VALUES(? ,?)";	
								
								ps = conn.prepareStatement(sql);
								ps.setString(1,memberId );
								ps.setInt(2, memberPw);
								ps.executeUpdate();
								
								return result;
								
							}else { //로그인실패
								System.out.println("id와 pw가 일치하지 않습니다");					
								return result;
								
							}
							
				}catch (Exception e) {
						return null;
					
				}	
		

	}//login e	
	
	
	
	
	
	
	
	// 회원정보 유효성 검사 (ID 중복체크) 기능 <-- 얘는 회원가입때 쓸 예정 집에 db없어서 일단 노확인
	// return 0 (중복없음 회원가입 가능), 1 (중복있음 회원가입 불가)
	public int checkUserId (String memberId) {
		int result = 0;
		String sql =  "SELECT *  FROM member WHERE member_id = ?";
		 
		 try {
			 ps=conn.prepareStatement(sql);
			 
			 ps.setString(1, memberId);
			 
			 rs = ps.executeQuery();
			 
			 
			 if(rs.next() == false) { 
				 return 0; }
			 else { 
				 return 1; }
		
			
		 } catch (Exception e) {
			System.out.println("checkUserId error"+e);
		 }
		 
		 return result;	// 0 (중복없음 회원가입 가능), 1 (중복있음 회원가입 불가)
	 }
	
	
////로그인 유효성검사 
	public MemberDto checklogin( String memberId, int memberPw) {
		
		
		String sql = "select * from member where member_id =? and member_pw =?";
	
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, memberId );
			
			ps.setInt(2,memberPw );
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberDto dto = 
						new MemberDto(
								rs.getInt(1),
								rs.getString(2),
								rs.getInt(3) 
								
								);
				
					
					return dto;
				
			}
			return null;
	
			
		}catch (Exception e) {
			System.out.println("checklogin error"+e);
			return null;
			
		}
	}


	
	
	
	
}//class e
