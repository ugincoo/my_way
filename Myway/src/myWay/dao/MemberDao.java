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


public class MemberDao extends DB연동 {
	
	//싱글톤
	private static MemberDao memberDao = new MemberDao();
	public MemberDao() {
		// TODO Auto-generated constructor stub
	}
	public static MemberDao getInstance() {
		 return memberDao;
	}
	
	// 회원가입
	public boolean signup (String memberId, int memberPw, String memberphone, String membername) {
		try {
			int result = checkUserId(memberId);
			
			if (result==0) {
				// 회원가입 완료
				String sql="insert into member( member_Id, member_Pw, member_phone,member_name )values(?,?,?,?);";	
					try {
						ps=con.prepareStatement(sql);
						ps.setString(1, memberId);
						ps.setInt( 2 , memberPw );
						ps.setString(3, memberphone);
						ps.setString(4, membername);
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
								// String sql = "INSERT INTO MEMBER(MEMBER_ID,MEMBER_PW) VALUES(? ,?)";	
								
								// ps = con.prepareStatement(sql);
								// ps.setString(1,memberId );
								// ps.setInt(2, memberPw);
								// ps.executeUpdate();
								
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
			 ps=con.prepareStatement(sql);
			 
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
			ps=con.prepareStatement(sql);
			
			ps.setString(1, memberId );
			
			ps.setInt(2,memberPw );
			
			rs = ps.executeQuery();

			while(rs.next()) {
				MemberDto dto = new MemberDto(rs.getInt(1),rs.getString(2),rs.getInt(3));
				
					
					return dto;
				
			}
			return null;
	
			
		}catch (Exception e) {
			System.out.println("checklogin error"+e);
			return null;
			
		}
	}
	
	//비밀번호수정
	public int update(int memberNo, int newpassword ) {
		
		String sql = "update member set member_pw=? where member_no=?;";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, newpassword );
			ps.setInt(2, memberNo);
			ps.executeUpdate(); 
			
			return 0;//비밀번호수정성공
			
		}catch (Exception e) {
			return 1;
		}
		
	}
	
	
	
	//회원탈퇴 //삭제는 select로 한번 조회 후 가능이였음..후
	public boolean delete(String memberId, int memberpw) {
		// 삭제대상 회원 정보 조회 쿼리
		String sql1 = "SELECT *"
					+ "  FROM MEMBER"
					+ " WHERE member_id = ?"
					+ "   AND member_pw = ?";
		
		// 조회된 회원 정보 삭제 쿼리
		String sql2 = "delete from member where member_id=? and member_pw=?";
		
		try {
				
			ps = con.prepareStatement(sql1);
			ps.setString(1, memberId);
			ps.setInt(2, memberpw);
				
			rs = ps.executeQuery();
				
			if(rs.next()) {
				// 회원 정보가 있는 경우
				System.out.println("삭제할 회원 정보를 찾았습니다. 해당 회원 정보를 삭제하겠습니다.");
				
				// 삭제 로직 시작
				ps = con.prepareStatement(sql2);
				ps.setString(1, memberId);
				ps.setInt(2, memberpw);
				
				ps.executeUpdate();
				
				return true;
			} else {
				// 회원 정보가 없는 경우
				System.out.println("삭제할 회원 정보가 없습니다. 다시 입력해주시길 바랍니다.");
				
				return false;
			}
				
		}catch (Exception e) {
			System.out.println("DB오류:"+e);
			
		}	
		return false;
	}
	
	
	//아이디찾기 * 아직미완성
	
	public String findId(int memberNo ) {
		
		String sql = "select member_id from member where member_no;";
			
				try {
					ps=con.prepareStatement(sql);
					ps.setInt(1, memberNo);
					rs=ps.executeQuery(); 
					
					if(rs.next() ) {
						ps = con.prepareStatement(sql);
						ps.setLong(1, memberNo);
						
							return MemberDto.getInstance().getMemberId();
					}
				
					
				}catch (Exception e) {
					return null;
				}
				return null;
		
	}
	
	//아이디찾기 유효성검사
	public MemberDto checknamephone(String membername,String memberphone) {
		MemberDto dto = new MemberDto();
		
		String sql = "select * from member where member_name =? and member_phone =?";
	
		try {			
			ps=con.prepareStatement(sql);
		
			ps.setString(1, membername );
		
			ps.setString(2,memberphone );
		
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new MemberDto(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
			}
			
			return dto;
			
		} catch (Exception e) {
			return dto;
		}
	}

	
	public ArrayList<MemberDto> Allprint() {
		ArrayList<MemberDto> memberList = new ArrayList<>();

		
		String sql = "select* from member;";
		
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				MemberDto dto = new MemberDto(
						rs.getInt(1),
						rs.getString(2), 
						rs.getInt(3), 
						rs.getString(4),
						rs.getString(5));
				memberList.add(dto);
			}
		}catch (Exception e) {

		}
		return memberList;
	}
	
}//class e
