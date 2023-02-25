package myWay;

import myWay.view.OderFront;

public class Start {
	
	public static int orderNumber = 0;
	
	public static void main(String[] args) {
		OderFront.getInstance().login();
	}
}
