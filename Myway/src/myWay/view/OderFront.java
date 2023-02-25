package myWay.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.midi.Sequence;

import myWay.controller.MemberController;
import myWay.controller.OderController;
import myWay.dto.DmaterialDto;
import myWay.dto.PorderDto;

public class OderFront {
	private Scanner scanner = new Scanner(System.in);
	
	private static OderFront orderFront = new OderFront();
	public OderFront() {}
	
	public static OderFront getInstance() {
		return orderFront;
	}
	public void login() {
		System.out.print("아이디 : ");
		String id = scanner.next();
		
		System.out.print("비밀번호 : ");
		int pw = scanner.nextInt();
		
		if(MemberController.getInstance().logIn(id, pw) != -1) {
			index();
		}
	}
	
	//메인 페이지
	public void index() {
		try {
			while(true) {
				System.out.print("1.커뮤니티 2. 주문하기 3.장바구니목록확인 4.로그아웃 : ");
				int choice = scanner.nextInt();
				
				if(choice == 1) {
					
				}else if(choice == 2) { //2. 주문하기
					order();
					
				}else if(choice == 3) { //3. 장바구니
					viewCartList();
					
				}else if(choice == 4) { //4. 로그아웃
					return;
				}
			}
		}catch(InputMismatchException e) {
			System.out.println("숫자로 입력해주세요.");
			scanner = new Scanner(System.in);
		}
	}
	
	// 종류마다 재료 리스트 출력
	public void printMaterialList(int categoryNo) {
		ArrayList<DmaterialDto> materialList = OderController.getInstance().printMaterialList(categoryNo);
		// 카테고리별 이름 찾기 
		int cNo = materialList.get(1).getCategoryNo();
		String cName = OderController.getInstance().findCategoryName(cNo);
		
		System.out.printf("----------------%s 종류----------------\n", cName);
		for(int i = 0; i < materialList.size(); i++) {
			System.out.printf("%d  %s  %d \n",
					i+1,
					materialList.get(i).getMaterName(),
					materialList.get(i).getMaterPrice()); 
		}
		
	}
	
	// 2. 주문하기 [카트에 넣는]
	public void order() {
		System.out.println("--------------------주문--------------------");
		
		ArrayList<Integer> inCartNoArr = new ArrayList<>();
		
		for(int i = 0; i < OderController.getInstance().returnCategoryCount(); i++) {
//			int []souNo = null;
			printMaterialList(i+1);
			
			System.out.print("번호 선택 : ");
			int No = scanner.nextInt();
			int materNo = OderController.getInstance().findMaterNo(No, i+1);
			inCartNoArr.add(materNo);

//			if(i+1 == 5) { //소스인 경우
//				System.out.print("[숫자만]소스를 종류 몇개 넣으실건가요 ? ");
//				int souFor = scanner.nextInt();
//				souNo = new int[souFor];
//				
//				for(int j = 0; j < souFor; j++) {
//					System.out.print(j+1 + "번째 소스 번호 선택 : ");
//					souNo[j] = scanner.nextInt();
//				}
//				for(int j = 0; j < souNo.length; j++) {
//					int materNo = OderController.getInstance().findMaterNo(souNo[j], i+1);
//					inCartNoArr.add(materNo);
//				}
//			}else {
//			}
			
		}
		System.out.print("장바구니에 담겠습니까 ? [1. Yes 2. No] : ");
		int answer = scanner.nextInt();
		
		if(answer == 1) {
			OderController.getInstance().inCartMaterials(inCartNoArr);
		}else if(answer == 2) {
			return;
		}
		viewCartList();
	}
	
	//3. 장바구니 목록 확인
	public void viewCartList() {
		ArrayList<DmaterialDto> dto = OderController.getInstance().returnCartList();
		System.out.println("-------------------현재 장바구니---------------");
		for(int i = 0; i < dto.size(); i++) {
			String cName = OderController.getInstance().findCategoryName(dto.get(i).getCategoryNo());
			System.out.printf("%s : %s \t %d원\n", cName, dto.get(i).getMaterName(), dto.get(i).getMaterPrice());
		}
		System.out.println("-----------------------------------------");
		
		System.out.println("1. 결제하기 2. 뒤로가기");
		
		int answer = scanner.nextInt();
		
		if(answer == 1) {
			purchase();
		}else if(answer == 2) {
			return;
		}
	}
	
	// 2-1 or 3-1 결제하기
	public void purchase() {
		OderController.getInstance().returnPOrderDto();
	}
}
