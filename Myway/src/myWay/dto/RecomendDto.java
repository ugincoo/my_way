package myWay.dto;

public class RecomendDto {
	// 필드
	private int recomNo;
	private String recomTitle;
	private int recomView;
	private String recomContent;
    
	// 생성자
	public RecomendDto() { }
	public RecomendDto(int recomNo, String recomTitle, int recomView, String recomContent) {
		super();
		this.recomNo = recomNo;
		this.recomTitle = recomTitle;
		this.recomView = recomView;
		this.recomContent = recomContent;
	}

	
	// 메소드
	@Override
	public String toString() {
		return "RecomendDto [recomNo=" + recomNo + ", recomTitle=" + recomTitle + ", recomView=" + recomView
				+ ", recomContent=" + recomContent + "]";
	}
	public int getRecomNo() {
		return recomNo;
	}
	public void setRecomNo(int recomNo) {
		this.recomNo = recomNo;
	}
	public String getRecomTitle() {
		return recomTitle;
	}
	public void setRecomTitle(String recomTitle) {
		this.recomTitle = recomTitle;
	}
	public int getRecomView() {
		return recomView;
	}
	public void setRecomView(int recomView) {
		this.recomView = recomView;
	}
	public String getRecomContent() {
		return recomContent;
	}
	public void setRecomContent(String recomContent) {
		this.recomContent = recomContent;
	}
}
