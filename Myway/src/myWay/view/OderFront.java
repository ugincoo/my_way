package myWay.view;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import myWay.controller.CouponController;
import myWay.controller.MemberController;
import myWay.controller.OderController;
import myWay.dto.CouponDto;
import myWay.dto.DmaterialDto;
import myWay.dto.PorderDto;
import myWay.dto.orderListDto;

public class OderFront {
	private Scanner scanner = new Scanner(System.in);
	
	private static OderFront orderFront = new OderFront();
	public OderFront() {}
	
	public static OderFront getInstance() {
		return orderFront;
	}
	
	
	
	// 종류마다 재료 리스트 출력
	public int printMaterialList(int categoryNo) {
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
		return materialList.size();
		
	}
	
	// 2. 주문하기 [카트에 넣는]
	public void order() {
		try {
			System.out.println("--------------------주문--------------------");
			
			ArrayList<Integer> inCartNoArr = new ArrayList<>();
			
			for(int i = 0; i < OderController.getInstance().returnCategoryCount(); i++) {
//				int []souNo = null;
				
				int maxSize = printMaterialList(i+1);
				
				System.out.print("번호 선택 : ");
				
				int No = scanner.nextInt();
				
				if(No == 0 || No >= maxSize) {
					int materNo = OderController.getInstance().findMaterNo(maxSize, i+1);
					inCartNoArr.add(materNo);
				}else {
					int materNo = OderController.getInstance().findMaterNo(No, i+1);
					inCartNoArr.add(materNo);
				}

//				if(i+1 == 5) { //소스인 경우
//					System.out.print("[숫자만]소스를 종류 몇개 넣으실건가요 ? ");
//					int souFor = scanner.nextInt();
//					souNo = new int[souFor];
//					
//					for(int j = 0; j < souFor; j++) {
//						System.out.print(j+1 + "번째 소스 번호 선택 : ");
//						souNo[j] = scanner.nextInt();
//					}
//					for(int j = 0; j < souNo.length; j++) {
//						int materNo = OderController.getInstance().findMaterNo(souNo[j], i+1);
//						inCartNoArr.add(materNo);
//					}
//				}else {
//				}
				
			}
			System.out.print("장바구니에 담겠습니까 ? [1. Yes 2. No] : ");
			int answer = scanner.nextInt();
			
			if(answer == 1) {
				OderController.getInstance().inCartMaterials(inCartNoArr);
			}else if(answer == 2) {
				return;
			}
			viewCartList();
		}catch(Exception e) {
			System.out.println("잘못입력하셨습니다.");
		}
	}
	
	//3. 장바구니 목록 확인
	public void viewCartList() {
		ArrayList<DmaterialDto> dto = OderController.getInstance().returnCartList();
		int price = 0;
		
		System.out.println("-------------------현재 장바구니-------------------");
		for(int i = 0; i < dto.size(); i++) {
			if(i != 0 && i%OderController.getInstance().returnCategoryCount() == 0) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			String cName = OderController.getInstance().findCategoryName(dto.get(i).getCategoryNo());
			System.out.printf("%s : %s \t %d원\n", cName, dto.get(i).getMaterName(), dto.get(i).getMaterPrice());
			price += dto.get(i).getMaterPrice();
		}
		System.out.println("--------------------------------------------");
		System.out.println("장바구니 총 금액 " + price);
		
		System.out.println("1. 결제하기 2. 쿠폰 결제하기  3. 뒤로가기");
		
		int answer = scanner.nextInt();
		
		if(answer == 1) {
			purchase(0);
			
		}
		else if(answer == 2) {
			int useCouponPrice = couponList(price);
			purchase(useCouponPrice);
		}else if(answer == 3) {
			
		}
	}
	
	// 2-1 or 3-1 결제하기
	public void purchase(int price) {
		OderController.getInstance().returnPOrderDto();
		ArrayList<PorderDto> result = OderController.getInstance().purchase();
		
		if(result != null) {
			System.out.println("결제 완료되었습니다.");
			printOrderPaper(result, price);
		}else {
			System.out.println("결제 실패하였습니다.");
		}
	}
	
	//영수증 출력
	public void printOrderPaper(ArrayList<PorderDto> orderPaperList, int price) {
		if(orderPaperList.size() > 0) {
			
			System.out.println("-------------------영수증-------------------");
			System.out.printf("\t 주문번호[  %d  ] \t\n", OderController.getInstance().orderNumber);
			int totalPrice = 0;
		
			ArrayList<DmaterialDto> materialsList = new ArrayList<>();
			for(int i = 0; i < orderPaperList.size(); i++) {
				materialsList.add( OderController.getInstance().returnMaterialInfo(orderPaperList.get(i).getBreadNo()));
				materialsList.add( OderController.getInstance().returnMaterialInfo(orderPaperList.get(i).getCheNo()));
				materialsList.add( OderController.getInstance().returnMaterialInfo(orderPaperList.get(i).getDrinkNo()));
				materialsList.add( OderController.getInstance().returnMaterialInfo(orderPaperList.get(i).getMeatNo()));
				materialsList.add( OderController.getInstance().returnMaterialInfo(orderPaperList.get(i).getSourceNo()));
				materialsList.add( OderController.getInstance().returnMaterialInfo(orderPaperList.get(i).getVegNo()));
				
				totalPrice += OderController.getInstance().findOneSetPrice(orderPaperList.get(i));
				//totalPrice=couponList(totalPrice);	// 쿠폰적용을 위한 추가
			}
			
			System.out.printf("%s  %10s \t %8s\n" ,"번호", "재료 이름", "재료 가격");
			for(int i = 0; i < materialsList.size(); i++) {
				if(i != 0 && i%6 == 0) {
					System.out.println("------------------------");
				}
				System.out.printf("%d  %10s \t %15d\n", i+1, materialsList.get(i).getMaterName(), materialsList.get(i).getMaterPrice());
				
			}
			System.out.println();
			if(price == 0) {
				System.out.print("[총 금액은]  " + totalPrice+"\t");	
			}else {
				System.out.println("쿠폰 적용된 금액은 "+ price);
			}

			System.out.println(OderController.getInstance().formatDateTime);
			
			
			System.out.println();
			System.out.println("║▌│█║▌│ █║▌│█│║▌║║▌│█║▌│ █║▌│█│║▌║\r\n"
					+ "║▌│█║▌│ █║▌│█│║▌║║▌│█║▌│ █║▌│█│║▌║");
			
			OderController.getInstance().orderNumber++; //주문 번호 증가
	
		}else {
			System.out.println("결제할 목록이 없습니다.");
		}
	}
	
	//전체쿠폰조회
		public int couponList(int totalPrice) {
	        int totalPrice2=0;
	        totalPrice2 =totalPrice; 	
			System.out.println("보유 쿠폰 목록");
			System.out.printf("%3s \t %10s \t %10s\n","번호","쿠폰종류","할인금액");
			ArrayList<CouponDto> result =
						CouponController.getInstance().couponList();
			
			for( int i=0; i<result.size(); i++) {
				System.out.printf("%3s \t %10s \t %10s\n",
								result.get(i).getCpNo(),
								result.get(i).getCpName(),
								result.get(i).getCpPrice());
			}//for e
			
						System.out.println("사용하실 쿠폰 번호를 선택해주세요");
				
						int ch = scanner.nextInt();
						
						if(ch==1) {
							totalPrice2 -=1000;
							return totalPrice2;
							
							
						}else if(ch==2) {
							
							totalPrice2 -=2000;
							return totalPrice2;
							
						}else if(ch==3) {
							totalPrice2 -=3000;
							return totalPrice2;		
						}else if(ch==4) {
							return	totalPrice2; 
							
						}
						return totalPrice;
					
		}//couponList e





	
	//로그인한 사용자의 주문 내역 보여주기
	public void viewOrderList() {
		ArrayList<orderListDto> orderListDB = OderController.getInstance().viewOrderList();
		if(orderListDB.size() > 0) {
			System.out.println("--------------------------------------------------- 주문 내역 ---------------------------------------------------");
			System.out.printf("%2s %10s %10s %10s %10s %10s %10s  %10s \t  %10s\n", "번호", "빵", "치즈", "메인", "채소", "소스", "음료", "총 가격", "구매일");
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			for(int i = 0; i < orderListDB.size(); i++) {
				System.out.printf("%2d %10s %10s %10s %10s %10s %10s %10d원 \t %20s\n",
						i+1,
						OderController.getInstance().returnMaterialInfo(orderListDB.get(i).getBreadNo()).getMaterName(),
						OderController.getInstance().returnMaterialInfo(orderListDB.get(i).getCheName()).getMaterName(),
						OderController.getInstance().returnMaterialInfo(orderListDB.get(i).getMeatName()).getMaterName(),
						OderController.getInstance().returnMaterialInfo(orderListDB.get(i).getVegName()).getMaterName(),
						OderController.getInstance().returnMaterialInfo(orderListDB.get(i).getSourceName()).getMaterName(),
						OderController.getInstance().returnMaterialInfo(orderListDB.get(i).getDrinkName()).getMaterName(),
						orderListDB.get(i).getPurchasePrice(),
						orderListDB.get(i).getOrderDate());
			}
			System.out.println("--------------------------------------------------------------------------------------------------------------");
		}else {
			System.out.println("┌───────────────┐\n"
					+ "  주문 목록이 없습니다.\n"
					+ "└───────────────┘\n"
					+ "　　ᕱ ᕱ ||\n"
					+ "　 ( ･ω･ ||\n"
					+ "　 /　つΦ\n"
					+ "");
		}
	}
}
