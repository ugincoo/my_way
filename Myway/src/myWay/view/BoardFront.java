package myWay.view;

import java.util.ArrayList;
import java.util.Scanner;

import myWay.controller.BoardController;
import myWay.dto.RecomendDto;

public class BoardFront {
	//싱글톤 생성
	//내부 객체 만들기 
	private static BoardFront bf = new BoardFront();
	//빈생성자 
	private BoardFront(){}
	//외부에서 호출 가능한 내부객체 반환 메소드 [getInstance]
	public static BoardFront getInstance () {return bf;}
	
	Scanner scanner = new Scanner(System.in);
	
	// 커뮤니티 선택
	public void boardIndex() {//boardIndex s
		while(true) {
			System.out.println(" •┈┈┈＊┈┈┈┈＊ 추천게시물 ＊┈┈┈┈＊┈┈┈• ");
			System.out.println(" •┈┈┈                           ┈┈┈• ");
			System.out.println(" •┈┈┈＊┈┈┈┈＊┈┈┈┈＊┈┈┈┈＊┈┈┈┈＊┈┈┈• ");
			System.out.print("1.상세보기 2.주문하기 3.뒤로가기 : ");
			int select = scanner.nextInt();
			if( select == 1) {boardList(); break;}
			else if( select == 2) {break;}
			else if( select == 3) {break;}
			else {System.out.println(" 다시 선택해주세요.");continue;}
		}
	}// boardIndex e
	
	//게시물출력
	public void boardList(){//void s
		System.out.println(" =================== 상세 게시물 =================== ");
		System.out.printf("%3s \t %10s \t %10s \t %10s \n","번호","제목","조회수","내용");
		// ArrayList 저장
		ArrayList<RecomendDto> result = BoardController.getInstance().boardlist();
		//반복문 돌리기
		for(int i = 0 ; i<result.size();i++) {// for s
			System.out.printf("%3s \t %10s \t %10s \t %10s \n",
				result.get(i).getRecomNo(),result.get(i).getRecomTitle(),
				result.get(i).getRecomView(),result.get(i).getRecomContent() );
			//상세게시물 번호 입력
			System.out.print("1.상세보기 2.뒤로가기 : ");
			int select1 = scanner.nextInt();
			if( select1 == 1) {	
				System.out.print("상세내용 볼 번호 : ");
				int select = scanner.nextInt();
				System.out.printf(" =================== %d 번게시물 =================== \n",(i+1) );
				System.out.println("제목 : " + result.get(i).getRecomTitle());
				System.out.println("내용 : " + result.get(i).getRecomContent());}
			else if ( select1 == 2) {boardIndex();}

		} //for문 e
		//댓글보기
		
		
		
	}//void e
	
}
