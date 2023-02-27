package myWay.controller;

import java.util.ArrayList;

import myWay.dao.RecomendDao;
import myWay.dto.RecomendDto;


public class RecomendController {
		//싱글톤 생성
		//내부 객체 만들기 
		private static RecomendController rc = new RecomendController();
		// 빈 생성자
		private RecomendController(){}
		//외부에서 호출 가능한 내부객체 반환 메소드 생성 [getInstance]
		public  static RecomendController getInstance() {return rc;}
		
		// 추천게시판 출력
		public ArrayList<RecomendDto> recomendpage(){
			return RecomendDao.getInstance().recomendpage();
		}
		
		// 추천메뉴 등록
		public boolean recomendSignup( String recomTitle , String recomContent ) {
			// 객체화
			RecomendDto dto = new RecomendDto(0, recomTitle, 0, recomContent);
			return RecomendDao.getInstance().recomendSignup( dto );
		}
		
		// 추천메뉴 수정
		public boolean recomendUpdate( int recomendNo , String udate, String recomchage ) {
			return RecomendDao.getInstance().recomendUpdate(recomendNo , udate, recomchage);
		}
		
		// 추천메뉴 삭제
		public boolean recomendDelete( int recomendNo ) {
			return RecomendDao.getInstance().recomendDelete(recomendNo);
		}
	//싱글톤 생성
	//내부 객체 만들기 
	private static RecomendController rc = new RecomendController();
	// 빈 생성자
	private RecomendController(){}
	//외부에서 호출 가능한 내부객체 반환 메소드 생성 [getInstance]
	public  static RecomendController getInstance() {return rc;}
}
