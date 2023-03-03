package myWay.view;

import java.util.ArrayList;
import java.util.Scanner;

import myWay.controller.BoardController;
import myWay.dao.RecomendDao;
import myWay.dto.BcommendDto;
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
		while(true) { // 추천게시물 3개 해야함
			System.out.println(" ┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 🌭 커뮤니티 🌭 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑  ");
			boardPrintRecent();
			System.out.print("1.추천목록 2.주문하기 3.장바구니목록확인 4. 주문내역 5.뒤로가기 : ");
			System.out.println();
			int select = scanner.nextInt();
			if( select == 1) {boardList();}
			else if( select == 2) {OderFront.getInstance().order();}
			else if( select == 3) {OderFront.getInstance().viewCartList();}
			else if( select == 4) {OderFront.getInstance().viewOrderList();}
			else if( select == 5) {return;}
			else {System.out.println(" 다시 선택해주세요.");boardIndex();}
		}
	}// boardIndex e
	
	//게시물출력
	public void boardList(){//void s
		System.out.println(" ┍━━━━━━━━━━━━━━━━━━━━━━━ (ノ◕ヮ◕)ノ* ・゚✧ 직원 추천 조합 ✧゚・ *ヽ(◕ヮ◕ヽ) ━━━━━━━━━━━━━━━━━━━━━━━┑ ");
		System.out.printf("%3s \t %10s \t %10s \t %10s \n","번호","제목","조회수","내용");
		// ArrayList 저장
		ArrayList<RecomendDto> result = BoardController.getInstance().boardlist();;
		
		//반복문 돌리기
		for(int i = 0 ; i<result.size();i++) {// for s
			System.out.printf("%3s \t %10s \t %10s \t %10s \n",
				result.get(i).getRecomNo(),result.get(i).getRecomTitle(),
				result.get(i).getRecomView(),result.get(i).getRecomContent() );

		} //for문 e		
		System.out.print("1.게시물 보기 2.뒤로가기 : ");
		int select = scanner.nextInt();
		if( select == 1) {	board();}
		else if ( select == 2) {boardIndex();}
		else {System.out.println("없는 메뉴입니다.\n다시 선택해주세요 :)");boardList();}
	}//void e
	
	//상세게시물
	public void board(){
		//
		System.out.print("확인 할 게시물 번호를 입력해주세요 :)  >> ");
		int boardno = scanner.nextInt();
		
		// ArrayList 저장
		ArrayList<RecomendDto> result1 = BoardController.getInstance().boardlist();	//게시물 ArrayList
		ArrayList<BcommendDto> result2 = BoardController.getInstance().commentList(boardno); //댓글 ArrayList
		
		BoardController.getInstance().view(boardno); //조회수 증가
		
		while(true) {
		
			System.out.printf(" •┈┈┈＊┈┈┈┈＊┈┈┈＊┈┈┈┈＊ %d 번게시물 ＊┈┈┈┈＊┈┈┈＊┈┈┈┈＊┈┈┈•  \n",(boardno) );
			System.out.println("제목 : " + result1.get(boardno-1).getRecomTitle());
			System.out.println("내용 : " + result1.get(boardno-1).getRecomContent());
			System.out.println("조회수 : " + result1.get(boardno-1).getRecomView());
			
			System.out.println("댓글번호 \t 내용 \t 회원번호");
			
			for(int j = 0 ; j <result2.size();j++) {
				System.out.println(result2.get(j).getBcommNo()+"\t"+result2.get(j).getBcommContent()+"\t"+result2.get(j).getMemberNo());	
			}
			
			
			//다음 출력 선택
			System.out.print("1.댓글달기 2.댓글삭제 3.뒤로가기 : ");
			int select = scanner.nextInt();
			if( select == 1) {	comment(boardno);}
			else if ( select == 2) {delete();}
			else if ( select == 3) {boardList();}
			}
		
	}//board e	
	
	//게시물 3개 출력
	public void boardPrintRecent() {
		
		ArrayList<RecomendDto> blist = BoardController.getInstance().boardPrintRecent();
		System.out.printf("  %5s\t%10s\t%5s\t%10s \n","번호","제목","조회수","내용");
		for(RecomendDto dto : blist) {
			System.out.printf(" %5s\t%10s\t%5s\t%10s \n",
					dto.getRecomNo(),dto.getRecomTitle(),dto.getRecomView(),dto.getRecomContent());
		}
		System.out.println();
		System.out.println(" ┕━━━━━━━━━━━━━"+Front.FONT_YELLOW+"😊"+Front.RESET+"━━"+Front.FONT_GREEN+"🌭"+Front.RESET+"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"+Front.FONT_GREEN+"🌭"+Front.RESET+"━━"+Front.FONT_YELLOW+"😊"+Front.RESET+"━━"+Front.FONT_GREEN+"🌭"+Front.RESET+"━━"+Front.FONT_YELLOW+"😊"+Front.RESET+"━━┙ ");
	}	
	
	//댓글 작성
	public void comment(int boardno) {
		System.out.println("댓글 작성 : ");	String bcommContent = scanner.next();
		//입력 받은 데이터 컨트롤에 전달
		boolean result = BoardController.getInstance().comment(bcommContent, boardno);
		if(result) {System.out.println("댓글이 등록 되었습니다."); board();}
		else {System.out.println("댓글 등록을 실패하였습니다.");}
				
	}//comment e
	
	//댓글삭제
	public void delete() {
		System.out.println("삭제 할 댓글 번호 : ");	int bcommNo = scanner.nextInt();
		//입력 받은 데이터 컨트롤에 전달
		boolean result = BoardController.getInstance().delete(bcommNo);
		if(result) {System.out.println("댓글이 삭제 되었습니다."); board();}
		else {System.out.println("댓글 삭제를 실패하였습니다.");}
	}
}
