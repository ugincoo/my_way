package myWay.controller;

import java.util.ArrayList;

import myWay.dao.BoardDao;
import myWay.dao.RecomendDao;
import myWay.dto.BcommendDto;
import myWay.dto.RecomendDto;

public class BoardController {
	//싱글톤 생성
	//내부 객체 만들기 
	private static BoardController bc = new BoardController();
	// 빈 생성자
	private BoardController(){}	
	//외부에서 호출 가능한 내부객체 반환 메소드 생성 [getInstance]
	public  static BoardController getInstance() {return bc;}
		
	//추천게시물 
	public ArrayList<RecomendDto> boardlist(){
		//1. 모든 게시물을 호출 하는 dao 메소드 호출하여 결과 얻기
			ArrayList<RecomendDto> result = BoardDao.getInstance().boardlist();
		//2. 결과 반환
			return result;
	}
	
	//댓글 출력
	public ArrayList<BcommendDto> commentList(){
		//1. 모든 게시물을 호출 하는 dao 메소드 호출하여 결과 얻기
		ArrayList<BcommendDto> result = BoardDao .getInstance().commentList();
		//2. 결과 반환
		return result;
	}
	
	public boolean comment(String bcommContent, int memberNo, int recomNo ) {
		return BoardDao.getInstance().comment(bcommContent, 0, 0);
				
	}
}
