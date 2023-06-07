package myWay.view;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import myWay.controller.SalesController;
import myWay.dto.SalesDto;

public class SalesFront {
	
	// 싱글톤
	private static SalesFront salesFront = new SalesFront();
	private SalesFront() {}
	public static SalesFront getInstance() { return salesFront; }
	
	// 달력 필드
	Calendar cal = Calendar.getInstance();
	int year = cal.get( Calendar.YEAR );
	int month = cal.get( Calendar.MONTH )+1;
	int day = cal.get( Calendar.DAY_OF_MONTH );
	
	// 달력 출력
	public void Calendar( int choice ) {
		
		if( choice == -1 ) {
			month--;
			if( month < 1 ) { month = 12; year--; }
		}
		else if( choice == 1 ) {
			month++;
			if( month > 12 ) { month = 1; year++; }
		}
		
		System.out.printf("==================================================== %d 년 %d 월 ====================================================\n" , year , month );
		System.out.println("\t일\t\t월\t\t화\t\t수\t\t목\t\t금\t\t토\t");
		cal.set(year, month-1, 1); //  현재 연도/월 의 1일 날짜 형식으로 변경
		int sweek = cal.get( Calendar.DAY_OF_WEEK ); // 해당 월의 시작일
		int eday = cal.getActualMaximum( Calendar.DAY_OF_MONTH ); // 해당 월의 마지막일
		
		for( int i = 1; i<sweek ; i++ ) { System.out.print("\t\t"); } // 해당 월 1일 전까지 공백으로 채우기
		
		for( int i = 1; i<= eday ; i++ ) {
			if( i < 10 ) { System.out.print("\t0"+i); }
			else { System.out.print("\t"+i); }
			if( i < 10 && month < 10 ) {
				System.out.print( " [ "+SalesController.getInstance().findPrice(year+"-"+"0"+month+"-"+"0"+i)+" ] ");
			}else if(i >= 10 && month < 10) {
				System.out.print( " [ "+SalesController.getInstance().findPrice(year+"-"+"0"+month+"-"+i)+" ] ");
			}else if(i < 10 && month >= 10) {
				System.out.print( " [ "+SalesController.getInstance().findPrice(year+"-"+month+"-"+"0"+i)+" ] ");
			}else { System.out.print( "[ "+SalesController.getInstance().findPrice(year+"-"+month+"-"+i)+" ] "); }
			if( sweek%7 == 0 ) { System.out.println(); }
			sweek++;
		}
		System.out.println("\n=====================================================================================================================");
		
		}
		

	// 매출 현황[월]
	public void printMCurrentSales() {
	    System.out.println("=================== " + year + "년 " + month + "월 매출 " + "===================");
	    // 매개변수로 해당 월
	    ArrayList<SalesDto> salesDB = SalesController.getInstance().printSales(returnDate());
	    System.out.printf("%2s%15s \t %7s \t %3s \n", "no", "재료명", "수량", "매출액");
	    // 현재 월보다 작은 경우에만 출력
	    if (month < LocalDate.now().getMonthValue()) {
	        for (int i = 0; i <= salesDB.size(); i++) {
	            System.out.printf("%2d%15s \t %7d \t %5d \n",
	                    salesDB.get(i).getMaterNo(),
	                    salesDB.get(i).getMaterName(),
	                    salesDB.get(i).getMaterSalesCount(),
	                    salesDB.get(i).getMaterSalesPrice());
	        }
	    } else {
	        System.out.println("해당 월의 매출 현황은 아직 확인할 수 없습니다.");
	    }
	}


	//매출 현황[일]
	public void printDCurrentSales(int day) {
		String searchDate = returnDate();
		
		if(day < 10) {
			searchDate += "0" + day;
		}else {
			searchDate += day;
		}
		System.out.println("================ " + year + "년 " + month + "월 "+ day + "일 매출 ================" );
		//매개변수로 해당 일 
		ArrayList<SalesDto> salesDB = SalesController.getInstance().printSales(searchDate);
		System.out.printf("%2s%15s \t %7s \t %3s \n" , "no" , "재료명" , "수량" , "매출액" );
		for(int i = 0; i <salesDB.size(); i++) {
			System.out.printf("%2d%15s \t %7d \t %5d \n",
					salesDB.get(i).getMaterNo(),
					salesDB.get(i).getMaterName(),
					salesDB.get(i).getMaterSalesCount(),
					salesDB.get(i).getMaterSalesPrice());
		}
	}
	//date String으로 반환
	public String returnDate() {
		String date = year + "-";
		
		if(month < 10 ) {
			date += "0"+ month + "-";
		}else {
			date += month + "-";
		}
		
		return date;
	}
}
