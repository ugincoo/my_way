package myWay.dto;

public class MemberDto {
	// 필드
	int memberNo;
    String memberId;
    int memberPw;
    
	// 생성자
	public MemberDto() { }
	public MemberDto(int memberNo, String memberId, int memberPw) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
	}

	// 메소드
	@Override
	public String toString() {
		return "MemberDto [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + "]";
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(int memberPw) {
		this.memberPw = memberPw;
	}
	
	
}
