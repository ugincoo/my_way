package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDao {
	// Database 연결
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	//싱글톤
	private static MemberDao memberDao = new MemberDao();
	public MemberDao() {
		 try {
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day16","root","1234");
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
			
			if (result == 0) {
				// 회원가입 완료
				String sql="insert into member( memberId,memberPw )values(?,?);";	
					try {
						ps=conn.prepareStatement(sql);
						ps.setString(1, memberId);
						ps.setInt( 2 , memberPw );
						ps.executeUpdate(); 
						
						return true;
						
					}catch (Exception e) {
						System.out.println("DB오류"+e);
					}

			} else if (result == 1) {
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
	}
	 
	 
	 
	// 로그인 
	public boolean login( ) {
		
	}
	
	
	
	
	
	
	
	
	// 회원정보 유효성 검사 (ID 중복체크) 기능 <-- 얘는 회원가입때 쓸 예정 집에 db없어서 일단 노확인
	// return 0 (중복없음 회원가입 가능), 1 (중복있음 회원가입 불가)
	public int checkUserId (String memberId) {
		int result = 0;
		String sql =  "SELECT IFNULL(member_id, 0, 1) AS member_id"
					+ "  FROM member"
					+ " WHERE member_id = ?";
		 
		 try {
			 ps=conn.prepareStatement(sql);
			 
			 ps.setString(1, memberId);
			 
			 rs = ps.executeQuery();
			 
			 System.out.println("MemberDao checkUserId 결과값 ::: " + rs.getInt(1));
			 
			 result = rs.getInt(1);
			 
		 } catch (Exception e) {
			System.out.println("checkUserId error");
		 }
		 
		 return result;	// 0 (중복없음 회원가입 가능), 1 (중복있음 회원가입 불가)
	 }
}
