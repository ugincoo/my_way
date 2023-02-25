package myWay.dto;

public class DmaterialDto extends MmaterialDto  {
	// 필드
	int materNo;
	String materName;
	int  materStock;
	int materPrice;
	
	// 생성자
	public DmaterialDto() { }
	
	public DmaterialDto(int materNo) {
		this.materNo = materNo;
	}
	
	public DmaterialDto(int categoryNo, int materNo, String materName, int materStock, int materPrice) {
		this.categoryNo = categoryNo;
		this.materNo = materNo;
		this.materName = materName;
		this.materStock = materStock;
		this.materPrice = materPrice;
	}
	
	

	// 메소드
	@Override
	public String toString() {
		return "DmaterialDto [materNo=" + materNo + ", materName=" + materName + ", materStock=" + materStock
				+ ", materPrice=" + materPrice + ", categoryNo=" + categoryNo + "]";
	}
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
	public int getMaterStock() {
		return materStock;
	}
	public void setMaterStock(int materStock) {
		this.materStock = materStock;
	}
	public int getMaterPrice() {
		return materPrice;
	}
	public void setMaterPrice(int materPrice) {
		this.materPrice = materPrice;
	}
	
	
}
