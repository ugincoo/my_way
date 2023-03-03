package myWay.controller;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import myWay.dto.*;
import myWay.dao.*;
import myWay.controller.*;
import myWay.view.*;

public class OderController {
	private static OderController oc = new OderController();
	public OderController() {}
	
	public static OderController getInstance() {
		return oc;
	}
	
	public int orderNumber = 1; //주문 번호 [프로그램 재실행할때마다 1부터]
	public String formatDateTime = null; // DB가 이리저리 얽혀있어서 주문 시간을 빼줌..
	//차피 결제할때마다 함수를 호출하므로 해당 formatDateTime은 같은 값이 나올 수없음.
	
	ArrayList<DmaterialDto> cartList = new ArrayList<>();
	
	//카테고리 개수 반환
	public int returnCategoryCount() {
		return OderDao.getInstance().returnCategoryCount();
	}
	
	// 재료마다 리스트 출력
	public ArrayList<DmaterialDto> printMaterialList(int categoryNo){
		return OderDao.getInstance().printMaterials(categoryNo);
	}
	
	//재료 No로 해당 재료 객체 찾기 [모든 정보]
	public DmaterialDto returnMaterialInfo(int materNo) {
		return OderDao.getInstance().returnMaterialInfo(materNo);
	}
	//카테고리 이름 찾기
	public String findCategoryName(int cNo) {
		String cName = null;
		
		if(cNo == 1) {
			cName = "빵";
		}else if(cNo == 2) {
			cName = "치즈";
		}else if(cNo == 3) {
			cName = "메인재료";
		}else if(cNo == 4) {
			cName = "채소";
		}else if(cNo == 5) {
			cName = "소스";
		}else if(cNo == 6) {
			cName = "음료";
		}
		
		return cName;
	}
	
	//카트에 재료 담기
	public void inCartMaterials(ArrayList<Integer> inCartNoArr) {
		
		for(int i = 0; i < inCartNoArr.size(); i++) {
			cartList.add(returnMaterialInfo(inCartNoArr.get(i)));
		}

	}
	
	//카트 출력
	public ArrayList<DmaterialDto> returnCartList(){
		return cartList;
	}
	
	//리스트 번호로 입력받고 해당 재료를 반환하는 함수
	public int findMaterNo(int no, int categoryNo) {
		return OderDao.getInstance().findMaterNo(no, categoryNo);
	}
	
	//pOrder에 담기전에 주문 한개 단위로 전달하기
	public void returnPOrderDto() {
		
		ArrayList<DmaterialDto> dto = new ArrayList<>();
		
		for(int i = 0; i < cartList.size(); i++) {
			// 카테고리당 1개씩만 선택하니까 카테고리 수를 나눈 것이 한 주문 단위
			if((i != 0 )&&((i+1)%returnCategoryCount() == 0)) {  
				dto.add(cartList.get(i));
				PorderDto pOrderDto = returnPorderDto(dto);
				int totalPrice = findOneSetPrice(pOrderDto);
				OderDao.getInstance().inputPOrder(pOrderDto, totalPrice);
				dto.clear(); //넣었으면 앞부분 비워주기
			}else {
				dto.add(cartList.get(i));
				
			}
		}
	}
	//주문 한개에 있는 정보마다 해당 가격 받아오는 함수 
	public int findOneSetPrice(PorderDto pOrderDto) {
		ArrayList<Integer> materNoList = new ArrayList<>();
		
		//total가격만 구하면 되는거라 순서는 상관X
		materNoList.add(pOrderDto.getBreadNo());
		materNoList.add(pOrderDto.getCheNo());
		materNoList.add(pOrderDto.getDrinkNo());
		materNoList.add(pOrderDto.getMeatNo());
		materNoList.add(pOrderDto.getVegNo());
		materNoList.add(pOrderDto.getSourceNo());
		
		int totalPrice = 0;
		
		for(int i = 0; i< materNoList.size(); i++) {
			totalPrice += OderDao.getInstance().returnMaterPrice(materNoList.get(i));
		}
		return totalPrice;
		
	}
	
	
	//pOrder 반환하는 함수
	public PorderDto returnPorderDto(ArrayList<DmaterialDto> dto) {
		PorderDto pOrderDto = new PorderDto(
				MemberController.getInstance().dto().getMemberNo(),
				dto.get(0).getMaterNo(),
				dto.get(1).getMaterNo(),
				dto.get(2).getMaterNo(),
				dto.get(3).getMaterNo(), 
				dto.get(4).getMaterNo(),
				dto.get(5).getMaterNo());
		
		return pOrderDto;
	}
	
	
	//최종 결제!
	public ArrayList<PorderDto> purchase() {
		ArrayList<Integer> pOrderList = OderDao.getInstance().returnPOrderNo();
	
		Timestamp dateTime = new Timestamp(System.currentTimeMillis());
		for(int i = 0; i < pOrderList.size(); i++) {
			int price = OderDao.getInstance().returnPorderPrice(pOrderList.get(i));
			OderDao.getInstance().purchase(pOrderList.get(i), price, dateTime);
			returnNowDate(pOrderList.get(i));
		}
		
		minusStock(cartList); //장바구니 비우기 전에 재고 줄이기 위해 전달
		cartList.clear(); // 결제가 완료되었으면 해당 장바구니를 비워준다.
		
		ArrayList<PorderDto> dto = returnOrderPaper(pOrderList);
		return dto;
	}
	
	//재고 줄이기
	public void minusStock(ArrayList<DmaterialDto> orderList) {
		ArrayList<Integer> messageNo = new ArrayList<>();
		for(int i = 0; i < orderList.size(); i++) {
			messageNo.add(OderDao.getInstance().minusStock(orderList.get(i).getMaterNo()));
		}
		sendMessage(messageNo);
		
	}
	
	//재고 관련해서 message를 관리자에게 보내기
	public void sendMessage(ArrayList<Integer> messageNo) {
		for(int i = 0; i < messageNo.size(); i++) {
			if(messageNo.get(i) >= 0) {
				StockController.getInstance().noticeMessageList.add(messageNo.get(i) + "번의 제품 재고 부족으로 10개 채워넣었습니다.");
			}
		}
	}
	
	//현재 시간 형식
	public String returnNowDate(int pOrderNo) {
		Timestamp dateTime = OderDao.getInstance().returnDateTime(pOrderNo);
		formatDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(dateTime);
		return formatDateTime;
	}
	
	//영수증 출력
	public ArrayList<PorderDto> returnOrderPaper(ArrayList<Integer> pOrderList) {
		ArrayList<PorderDto> orderList = new ArrayList<>();
		
		for(int i = 0; i < pOrderList.size(); i++) {
			orderList.add(OderDao.getInstance().returnOrderInfo(pOrderList.get(i)));
		}
		
		return orderList;
	}
	
	//로그인한 사용자의 주문 내역 확인 하기 
	public ArrayList<orderListDto> viewOrderList(){
		return OderDao.getInstance().viewOrderList();
	}
	
	//이름 반환 메소드
	
	
}
