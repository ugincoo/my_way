package myWay.dto;

public class SalesDto {
	//필드
	private int materNo;
	private String materName;
	private int materSalesCount;
	private int materSalesPrice;
	
	//생성자
	
	public SalesDto() {}
	
	public SalesDto(int materNo, String materName, int materSalesCount, int materSalesPrice) {
		this.materNo = materNo;
		this.materName = materName;
		this.materSalesCount = materSalesCount;
		this.materSalesPrice = materSalesPrice;
	}
	
	
	//메소드
	public int getMaterNo() {
		return materNo;
	}

	public void setMaterNo(int materNo) {
		this.materNo = materNo;
	}

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}

	public int getMaterSalesCount() {
		return materSalesCount;
	}

	public void setMaterSalesCount(int materSalesCount) {
		this.materSalesCount = materSalesCount;
	}

	public int getMaterSalesPrice() {
		return materSalesPrice;
	}

	public void setMaterSalesPrice(int materSalesPrice) {
		this.materSalesPrice = materSalesPrice;
	}

	@Override
	public String toString() {
		return "SalseDto [materNo=" + materNo + ", materName=" + materName + ", materSalesCount=" + materSalesCount
				+ ", materSalesPrice=" + materSalesPrice + "]";
	}
	
	
}
