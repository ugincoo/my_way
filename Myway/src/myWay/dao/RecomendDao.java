package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import myWay.controller.RecomendController;

public class RecomendDao {
	//싱글톤 생성
	private static RecomendDao rd = new RecomendDao();
	//어디서든 사용 가능한 변환 메소드  [getInstance]
	public static RecomendDao getInstance() {return rd;}
	

	// 1.필드
	private Connection con;			// 1. 연결된 DB구현객체를 가지고 있는 인터페이스
	private PreparedStatement ps;	// 2. 연결된 SQL 조작 [+매개변수 가능]
	private ResultSet rs;			// 3. 실행된 SQL 결과 인터페이스
	
	// DB 연동
	public RecomendDao() {//빈 생성자 s
		try {
			con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/myway","root","1234");
			System.out.println("[DB연동 성공!]");
		}catch (Exception e) {
			System.out.println("연동실패 사유 : " + e); 
		}		
	}// 빈 생성자 e
	
	
}
