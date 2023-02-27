package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import myWay.dto.MemberDto;



public class MemberDao {
	
	private static Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//싱글톤
	private static MemberDao dao = new MemberDao();
	
	
	public static MemberDao getInstance() {
		return dao;
	}
	
	public MemberDao() {
		try {
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myway", "root", "1234");
			
		}catch(SQLException e) {
			System.out.println("DB연동 실패 : " + e.getMessage());
		}
	}
	
	// * 회원 정보를 모두 받아오는 함수 + 로그인
	public ArrayList<MemberDto> returnMemberDB(){
		ArrayList<MemberDto> memberDB = new ArrayList<>();
		
		String sql = "select * from member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDto dto = new MemberDto(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getInt(3));
				
				memberDB.add(dto);
			}
			return memberDB;
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
