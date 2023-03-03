package myWay.view;

import java.util.ArrayList;
import java.util.Scanner;

import myWay.controller.CouponController;
import myWay.controller.OderController;
import myWay.dto.CouponDto;

public class CouponFront {
	// 싱글톤
	private static CouponFront couponfront = new CouponFront();
	public static CouponFront getInstance() {
		return couponfront;
	}
	
	Scanner scanner = new Scanner(System.in);
	

	

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
				while(true) {	
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
					}	
					return totalPrice;
				}
	}//couponList e

	

	
}
