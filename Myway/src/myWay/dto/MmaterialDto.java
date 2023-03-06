package myWay.dto;

public class MmaterialDto {
	// 필드
	int categoryNo;
	
	// 생성자
	public MmaterialDto() { }
	public MmaterialDto(int categoryNo) {
		super();
		this.categoryNo = categoryNo;
	}

	// 메소드
	@Override
	public String toString() {
		return "MmaterialDto [categoryNo=" + categoryNo + "]";
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

}
