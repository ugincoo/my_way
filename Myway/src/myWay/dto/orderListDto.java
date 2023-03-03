package myWay.dto;

public class orderListDto {
	/*필드*/
	private int breadNo;
	private int cheName;
	private int meatName;
	private int vegName;
	private int sourceName;
	private int drinkName;
	
	private int purchasePrice;
	
	private String orderDate;
	
	/*생성자*/
	public orderListDto() {}

	public orderListDto(int breadNo, int cheName, int meatName, int vegName, int sourceName, int drinkName,
			int purchasePrice, String orderDate) {
		this.breadNo = breadNo;
		this.cheName = cheName;
		this.meatName = meatName;
		this.vegName = vegName;
		this.sourceName = sourceName;
		this.drinkName = drinkName;
		this.purchasePrice = purchasePrice;
		this.orderDate = orderDate;
	}
	
	/*메소드*/
	public int getBreadNo() {
		return breadNo;
	}

	public void setBreadNo(int breadNo) {
		this.breadNo = breadNo;
	}

	public int getCheName() {
		return cheName;
	}

	public void setCheName(int cheName) {
		this.cheName = cheName;
	}

	public int getMeatName() {
		return meatName;
	}

	public void setMeatName(int meatName) {
		this.meatName = meatName;
	}

	public int getVegName() {
		return vegName;
	}

	public void setVegName(int vegName) {
		this.vegName = vegName;
	}

	public int getSourceName() {
		return sourceName;
	}

	public void setSourceName(int sourceName) {
		this.sourceName = sourceName;
	}

	public int getDrinkName() {
		return drinkName;
	}

	public void setDrinkName(int drinkName) {
		this.drinkName = drinkName;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
}
