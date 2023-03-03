package myWay.dao;

import java.util.ArrayList;

import myWay.dto.SalesDto;

public class SalesDao extends DB연동{
	
	//싱글톤
	private static SalesDao salesDao = new SalesDao();
	public static SalesDao getInstance() { return salesDao; }

	// 생성자
	private SalesDao() { }
	
	// 월 매출 출력 or 일 매출 출력
	public ArrayList<SalesDto> printSales() {
		ArrayList<SalesDto> salesDB = new ArrayList<>();
		String sql = "select pSearch.mater_no, pSearch.mater_name, count(pSearch.mater_no), mater_price*count(pSearch.mater_no)"
				+ "		from (select  ph.purchase_date, d.mater_name, d.mater_no, d.mater_price, po.drink_no, po.veg_no, po.bread_no, po.che_no, po.source_no, po.meat_no"
				+ "				from Porder po, purchase ph, dMaterials d"
				+ "				where po.porder_no = ph.porder_no) pSearch"
				+ "		where  pSearch.purchase_date like ?  and (pSearch.mater_no = pSearch.bread_no or"
				+ "										pSearch.mater_no = pSearch.che_no or"
				+ "                                     pSearch.mater_no = pSearch.drink_no or"
				+ "                                     pSearch.mater_no = pSearch.veg_no or"
				+ "										pSearch.mater_no = pSearch.meat_no or"
				+ "										pSearch.mater_no = pSearch.source_no)"
				+ "		Group by pSearch.mater_no";
		
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, "%2023-03%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SalesDto dto = new SalesDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4));
				
				salesDB.add(dto);
			}
			
			return salesDB;
			
		}catch(Exception e) {
			System.err.println(" DB 오류 : "+ e );
			return null;
		}
	}
	
	// 달력 일 매출
	public int findPrice(String date) {
		
		String sql = "select sum(purchase_price) as total_Dsales from  purchase where purchase_date like ?";
		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+date+"%");
			rs = ps.executeQuery();
			rs.next();
			
			return rs.getInt(1);
			
		}catch (Exception e) { System.out.println(" DB 오류 : "+ e );}
		
		return 0;
	}
	
}
