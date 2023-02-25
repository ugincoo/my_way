package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import myWay.dto.DmaterialDto;

public class OderDao {
private static OderDao oderDao = new OderDao();
	
	
	public static OderDao getInstance() {
		return oderDao;
	}
	
	private static Connection conn;
	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 생성자
	private OderDao() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myway", "root", "1234");
//			System.out.println("연결 성공");
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 카테고리 크기 반환
	public int returnCategoryCount() {
		String sql = "select * from mMaterials";
		
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				count++;
			}
			
			return count;
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}
	
	//카테고리 마다 리스트 번호로 재료 고유 번호 찾기 
	public int findMaterNo(int no, int category) {
		String sql = "select row_number() over(order by mater_no asc) as num, mater_no, category_no, mater_name, mater_price from dMaterials where category_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, category);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getRow() == no) {
					return rs.getInt(2);
				}
			}
			return -1; // 못찾을 경우
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return -2; //DB에러
		}
	}

	// 재료마다 목록 출력하기 
	public ArrayList<DmaterialDto> printMaterials(int categoryNo){
		ArrayList<DmaterialDto> materialList = new ArrayList<>();
		
		String sql = "select * from dMaterials where category_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, categoryNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DmaterialDto dto = new DmaterialDto(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3), 
						rs.getInt(4),
						rs.getInt(5));
				
				materialList.add(dto);
			}
			return materialList;
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	// 재료 번호로 해당 재료의 정보를 반환
	public DmaterialDto returnMaterialInfo(int materNo) {
		String sql = "select * from dMaterials where mater_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, materNo);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			DmaterialDto dto = new DmaterialDto(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getInt(5));
			
			return dto;
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	
	// 2. 주문하기 
	
}
