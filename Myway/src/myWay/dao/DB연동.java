package myWay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB연동 {

	// 1. JDBC 인터페이스 3개
		public Connection con;			// DB연동 인터페이스
		public PreparedStatement ps;	// SQL 조작 인터페이스
		public ResultSet rs;			// SQL 결과 조작 인터페이스
		
		public DB연동() {
			try {	// DB연동
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardDB", "root", "1234");
			}catch(Exception e) { System.out.println( e ); } 
		}
}
