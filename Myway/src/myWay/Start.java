package myWay;

import myWay.view.Front;
import myWay.view.MemberFront;


public class Start {
	public static void main(String[] args) {
		Front.getInstance().hi();
		MemberFront.getInstance().index();
	}
}
