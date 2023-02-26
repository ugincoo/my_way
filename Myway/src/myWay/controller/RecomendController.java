package myWay.controller;


public class RecomendController {
	//싱글톤 생성
	//내부 객체 만들기 
	private static RecomendController rc = new RecomendController();
	// 빈 생성자
	private RecomendController(){}
	//외부에서 호출 가능한 내부객체 반환 메소드 생성 [getInstance]
	public  static RecomendController getInstance() {return rc;}
}
