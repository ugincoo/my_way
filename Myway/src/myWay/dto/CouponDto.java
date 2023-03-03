package myWay.dto;


public class CouponDto {
	// 싱글톤
	private static CouponDto coupondto = new CouponDto();
	public static CouponDto getInstance() {
		return coupondto;
	}	
	
	//필드
	int cpNo;			//쿠폰번호
	String cpName;		//쿠폰종류(이름)
	int cpPrice;		//쿠폰할인가격
	
	

	public CouponDto() {

	}



	public CouponDto(int cpNo, String cpName, int cpPrice) {
		super();
		this.cpNo = cpNo;
		this.cpName = cpName;
		this.cpPrice = cpPrice;
	}

	@Override
	public String toString() {
		return "CouponDto [cpNo=" + cpNo + ", cpName=" + cpName + ", cpPrice=" + cpPrice + "]";
	}

	public static CouponDto getCoupondto() {
		return coupondto;
	}

	public static void setCoupondto(CouponDto coupondto) {
		CouponDto.coupondto = coupondto;
	}

	public int getCpNo() {
		return cpNo;
	}

	public void setCpNo(int cpNo) {
		this.cpNo = cpNo;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public int getCpPrice() {
		return cpPrice;
	}

	public void setCpPrice(int cpPrice) {
		this.cpPrice = cpPrice;
	}


	
	
	
}
