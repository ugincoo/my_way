package myWay.dto;

public class BcommendDto {
	// 필드
	int bcommNo;
    String bcommContent;
    int memberNo;
    int recomNo; 
    
	// 생성자
	public BcommendDto() { }
	public BcommendDto(int bcommNo, String bcommContent, int memberNo, int recomNo) {
		super();
		this.bcommNo = bcommNo;
		this.bcommContent = bcommContent;
		this.memberNo = memberNo;
		this.recomNo = recomNo;
	}

	// 메소드
	@Override
	public String toString() {
		return "BcommendDto [bcommNo=" + bcommNo + ", bcommContent=" + bcommContent + ", memberNo=" + memberNo
				+ ", recomNo=" + recomNo + "]";
	}
	public int getBcommNo() {
		return bcommNo;
	}
	public void setBcommNo(int bcommNo) {
		this.bcommNo = bcommNo;
	}
	public String getBcommContent() {
		return bcommContent;
	}
	public void setBcommContent(String bcommContent) {
		this.bcommContent = bcommContent;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getRecomNo() {
		return recomNo;
	}
	public void setRecomNo(int recomNo) {
		this.recomNo = recomNo;
	}
	
	
}
