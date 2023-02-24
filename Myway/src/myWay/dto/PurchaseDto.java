package myWay.dto;

public class PurchaseDto {
	// 필드
	int purchaseNo;
    int porderNo;
    int purchasePrice;
    
    // 생성자
    public PurchaseDto() { }
	public PurchaseDto(int purchaseNo, int porderNo, int purchasePrice) {
		super();
		this.purchaseNo = purchaseNo;
		this.porderNo = porderNo;
		this.purchasePrice = purchasePrice;
	}

	// 메소드
	@Override
	public String toString() {
		return "PurchaseDto [purchaseNo=" + purchaseNo + ", porderNo=" + porderNo + ", purchasePrice=" + purchasePrice
				+ "]";
	}
	public int getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public int getPorderNo() {
		return porderNo;
	}
	public void setPorderNo(int porderNo) {
		this.porderNo = porderNo;
	}
	public int getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
    
	

}
