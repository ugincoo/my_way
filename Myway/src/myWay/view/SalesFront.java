package myWay.view;

import java.util.ArrayList;

import myWay.controller.SalesController;
import myWay.dto.SalesDto;

public class SalesFront {
	
	// 싱글톤
	private static SalesFront salesFront = new SalesFront();
	private SalesFront() {}
	public static SalesFront getInstance() { return salesFront; }
	
	
	//매출 현황
	public void printCurrentSales(String dateTime) {
		//매개변수로 해당 월이나 
		ArrayList<SalesDto> salesDB = SalesController.getInstance().printSales();
		System.out.println("번호\t 이름 \t 수량 \t 매출액" );
		for(int i = 0; i <salesDB.size(); i++) {
			System.out.printf("%d \t %s \t %d \t %d\n", 
					salesDB.get(i).getMaterNo(),
					salesDB.get(i).getMaterName(),
					salesDB.get(i).getMaterSalesCount(),
					salesDB.get(i).getMaterSalesPrice());
		}
		
	}
}
