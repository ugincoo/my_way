package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import myWay.controller.MemberController;
import myWay.dto.DmaterialDto;
import myWay.dto.PorderDto;

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
			System.out.println(MemberController.getInstance().getLogSeasion());
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
	
	//각각의 재료의 가격을 반환하는 함수
	public int returnMaterPrice(int materNo) {
		String sql = "select * from dMaterials where mater_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, materNo);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			return rs.getInt(5); //가격
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}
	
	
	// 2. pOrder에 담기 
	public void inputPOrder(PorderDto pOrderDto, int totalPrice) {

		String sql = "insert into porder (member_no, bread_no, che_no, meat_no, veg_no, source_no, drink_no, price)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pOrderDto.getMemberNo());
			pstmt.setInt(2, pOrderDto.getBreadNo());
			pstmt.setInt(3, pOrderDto.getCheNo());
			pstmt.setInt(4, pOrderDto.getMeatNo());
			pstmt.setInt(5, pOrderDto.getVegNo());
			pstmt.setInt(6, pOrderDto.getSourceNo());
			pstmt.setInt(7, pOrderDto.getDrinkNo());
			pstmt.setInt(8, totalPrice);
			
			pstmt.executeUpdate();

		}catch(SQLException e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	// pOrder 테이블의 pOrder_no을 반환하기
	public ArrayList<Integer> returnPOrderNo() {
		//지금 로그인한 멤버의 정보와 결제가 아직 되지 않은 걸 찾아서 결제를 하려고함.
		String sql = "select * from porder where member_no = ? && o_status = ?";
		ArrayList<Integer> pOrderNoList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, MemberController.getInstance().getLogSeasion().getMemberNo());
			pstmt.setInt(2, 0);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pOrderNoList.add(rs.getInt(1));
			}
			
			return pOrderNoList;
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	//pOrderNo마다 가격받아오기
	public int returnPorderPrice(int pOrderNo) {
		String sql = "select * from porder where porder_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pOrderNo);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			return rs.getInt(10);
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}
	
	
	//최종 결제
	public boolean purchase(int pOrderNo, int price, Timestamp dateTime) {
		String sql = "insert into purchase (porder_no, purchase_price, purcahse_date)"
				+ "values (?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pOrderNo);
			pstmt.setInt(2, price);
			pstmt.setTimestamp(3, dateTime);
			
			pstmt.executeUpdate();
			
			changepOrderStatus(pOrderNo);
			
			return true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//결제되었으면 해당 pOrder의 status를 1로 바꾸기
	public void changepOrderStatus(int pOrderNo) {
		String sql = "update porder set o_status = ? where porder_no = ?";	
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, pOrderNo);
			
			pstmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//결제완료되었으면 해당 재료의 재고를 줄이기
	public void minusStock(int materNo) {
		String sql = "update dMaterials set mater_stock = mater_stock-1 where mater_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, materNo);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//영수증에 필요한 정보 반환
	public PorderDto returnOrderInfo(int pOrderNo){
		String sql = "select * from porder where porder_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pOrderNo);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			PorderDto dto = new PorderDto(
					rs.getInt(1), 
					rs.getInt(2), 
					rs.getInt(3), 
					rs.getInt(4), 
					rs.getInt(5), 
					rs.getInt(6), 
					rs.getInt(7));
			
			return dto;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}
