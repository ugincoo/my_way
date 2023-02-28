package myWay.dto;

public class MemberDto {
	// 필드
	int 	memberNo;	// 회원일련번호
    String 	memberId;	// 회원 ID
    int 	memberPw;	// 회원 PW
    String	memberphone;// 회원 phone , 수정
    String	membername;	// 회원 name , 수정
    

	// 생성자
	public MemberDto() { }
	public MemberDto(int memberNo, String memberId, int memberPw) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
	}

	//수정) 풀생성자
	public MemberDto(int memberNo, String memberId, int memberPw, String memberphone, String membername) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberphone = memberphone;
		this.membername = membername;
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
	public String getMemberphone() {
		return memberphone;
	}
	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public int getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(int memberPw) {
		this.memberPw = memberPw;
	}
	
	
	
}
