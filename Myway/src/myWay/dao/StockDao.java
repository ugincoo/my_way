package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import myWay.dto.DmaterialDto;

public class StockDao {
	
	// 싱글톤
	private static StockDao stockDao = new StockDao();
	public static StockDao getInstance() { return stockDao; }
	
	// 필드
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	// 생성자 연동
	private StockDao() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myway" , "root" , "1234" );
		}catch( Exception e ) { System.out.println( e.getMessage() ); }
	}
	
	// 재료현황페이지
	public ArrayList<DmaterialDto> Materials( int categoryNo ) {
		// 리스트 선언
		ArrayList<DmaterialDto> materialsList = new ArrayList<>();
		// SQL
		String sql = "select * from dMaterials";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				if( rs.getInt(1) == categoryNo ) { // 입력받은 카테고리와 일치하면
					DmaterialDto dto = new DmaterialDto( rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
					materialsList.add(dto);
				}
			}
			return materialsList;
		}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); }
		return null;
	}
	
	// 재료 등록
	public boolean materialSignup( DmaterialDto dto ) {
		String sql = "insert into dMaterials ( category_no, mater_name , mater_stock , mater_price ) values ( ? , ? , ? , ? )";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt( 1, dto.getMaterNo() );
			ps.setString( 2, dto.getMaterName() );
			ps.setInt( 3, dto.getMaterStock() );
			ps.setInt(4, dto.getMaterPrice() );
			
			ps.executeUpdate();
			
			return true;
		}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); }
		return false;
	}
	
	// 재료명 수정
	public boolean materNameUpdate( int materNo , String materName ) {
		String sql = "update dMaterials set mater_name = ? where mater_no = ?";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, materName);
			ps.setInt(2, materNo);
			
			ps.executeUpdate();
			
			return true;
		}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); }
		return false;
	}
	
	// 재료가격 수정
	public boolean materPriceUpdate( int materNo , int materPrice ) {
		String sql = "update dMaterials set mater_price = ? where mater_no = ?";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, materPrice);
			ps.setInt(2, materNo);
			
			ps.executeUpdate();
			
			return true;
		}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); }
		return false;
	}
	
	// 재료재고 수정
	public boolean materStockUpdate( int materNo , int materStock ) {
		String sql = "update dMaterials set mater_stock = ? where mater_no = ?";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, materStock);
			ps.setInt(2, materNo);
			
			ps.executeUpdate();
			
			return true;
		}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); }
		return false;
	}
	
	// 재료 삭제
	public boolean materialDelete( int materNo ) {
		String sql = "delete from dMaterials where mater_no = ?";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, materNo);
			
			ps.executeUpdate();
			
			return true;
		}catch( Exception e ) { System.out.println(" DB 오류 : "+ e ); }
	
		return false;
	}
	
	
}
