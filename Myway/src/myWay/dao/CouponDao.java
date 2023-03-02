package myWay.dao;

import java.util.ArrayList;

import myWay.dto.CouponDto;

public class CouponDao extends DB연동{
	// 싱글톤
	private static CouponDao coupondao = new CouponDao();
	public static CouponDao getInstance() {	return coupondao; }
	
	
	public ArrayList<CouponDto> couponList(){
		ArrayList<CouponDto>couponList = new ArrayList<>();
		
		String sql= "select * from coupon";
		
		try {
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery(sql);
			while( rs.next()) {
				CouponDto dto = new CouponDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3)
						);
				couponList.add(dto);
			}
	
		}catch (Exception e) {
			System.out.println("DB연동실패:"+e);
		}
		
		return couponList;
		
	}
	
}
