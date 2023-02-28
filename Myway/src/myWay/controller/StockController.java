package myWay.controller;

import java.util.ArrayList;

import myWay.dao.StockDao;
import myWay.dto.DmaterialDto;

public class StockController {
	
	// 싱글톤
	private static StockController stockController = new StockController();
	private StockController() {}
	public static StockController getInstance() { return stockController; }
	
	// 재료현황페이지
	public ArrayList<DmaterialDto> Materials( int categoryNo ){
		return StockDao.getInstance().Materials( categoryNo );
	}
	
	// 재료 등록
	public boolean materialSignup( int categoryNo , String materName , int materprice , int materStock ) {
		// 객체화
		DmaterialDto dto = new DmaterialDto( categoryNo, 0,materName, materStock, materprice);
		// DB 저장 후 반환
		return StockDao.getInstance().materialSignup( dto );
	}
	
	// 재료명 수정
	public boolean materNameUpdate( int materNo , String materName ) {
		 return StockDao.getInstance().materNameUpdate(materNo, materName);
	}
	
	// 재료가격 수정
	public boolean materPriceUpdate( int materNo , int materPrice ) {
		 return StockDao.getInstance().materPriceUpdate(materNo, materPrice);
	}
	
	// 재료재고 수정
	public boolean materStockUpdate( int materNo , int materStock ) {
		 return StockDao.getInstance().materStockUpdate(materNo, materStock);
	}
	
	// 재료 삭제
	public boolean materialDelete( int materNo ) {
		return StockDao.getInstance().materialDelete( materNo );
	}

}
