package myWay.controller;

import java.util.ArrayList;

import myWay.dao.OderDao;
import myWay.dto.DmaterialDto;

public class OderController {
	private static OderController oc = new OderController();
	public OderController() {}
	
	public static OderController getInstance() {
		return oc;
	}
	
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
	public boolean returnPOrderDto() {
		boolean result = false;
		
		ArrayList<DmaterialDto> dto = new ArrayList<>();
		
		for(int i = 0; i < cartList.size(); i++) {
			// 카테고리당 1개씩만 선택하니까 카테고리 수를 나눈 것이 한 주문 단위
			if((i != 0 )&&(i%returnCategoryCount() == 0)) {  
				System.out.println(dto);
				System.out.println(1);
				result = OderDao.getInstance().inputPOrder(dto);
			}
			
			dto.add(cartList.get(i));
			System.out.println(dto);
		}
		return result;
	}
}
