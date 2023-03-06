package myWay.dto;

public class PorderDto {
	// 필드
	private int porderNo;
	private int memberNo;
    
	private int breadNo;
	private int drinkNo;
	private int vegNo;
	private int cheNo;
	private int sourceNo;
	private int meatNo;
	
	// 생성자
	public PorderDto() { }
	public PorderDto(int memberNo, int breadNo, int drinkNo, int vegNo, int cheNo, int sourceNo,
			int meatNo) {
		super();
		this.memberNo = memberNo;
		this.breadNo = breadNo;
		this.drinkNo = drinkNo;
		this.vegNo = vegNo;
		this.cheNo = cheNo;
		this.sourceNo = sourceNo;
		this.meatNo = meatNo;
	}
	
	
	
	
	
	public PorderDto(int porderNo, int memberNo, int breadNo, int drinkNo, int vegNo, int cheNo, int sourceNo,
			int meatNo) {
		super();
		this.porderNo = porderNo;
		this.memberNo = memberNo;
		this.breadNo = breadNo;
		this.drinkNo = drinkNo;
		this.vegNo = vegNo;
		this.cheNo = cheNo;
		this.sourceNo = sourceNo;
		this.meatNo = meatNo;
	}
	
	// 메소드
	@Override
	public String toString() {
		return "PorderDto [porderNo=" + porderNo + ", memberNo=" + memberNo + ", breadNo=" + breadNo + ", drinkNo="
				+ drinkNo + ", vegNo=" + vegNo + ", cheNo=" + cheNo + ", sourceNo=" + sourceNo + ", meatNo=" + meatNo
				+ "]";
	}
	public int getPorderNo() {
		return porderNo;
	}
	public void setPorderNo(int porderNo) {
		this.porderNo = porderNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getBreadNo() {
		return breadNo;
	}
	public void setBreadNo(int breadNo) {
		this.breadNo = breadNo;
	}
	public int getDrinkNo() {
		return drinkNo;
	}
	public void setDrinkNo(int drinkNo) {
		this.drinkNo = drinkNo;
	}
	public int getVegNo() {
		return vegNo;
	}
	public void setVegNo(int vegNo) {
		this.vegNo = vegNo;
	}
	public int getCheNo() {
		return cheNo;
	}
	public void setCheNo(int cheNo) {
		this.cheNo = cheNo;
	}
	public int getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(int sourceNo) {
		this.sourceNo = sourceNo;
	}
	public int getMeatNo() {
		return meatNo;
	}
	public void setMeatNo(int meatNo) {
		this.meatNo = meatNo;
	}
	
	
}
