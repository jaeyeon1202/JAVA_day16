package db_common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static Connection con;
	public static Connection getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//오타, 이것과 관련된 기능을 로드하지 않아서
			System.out.println("드라이브 로드 성공!");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url,"java","1234");
			//오라클 데이터베이스에 연결하여 연결된 객체를 얻어온다.
			System.out.println("DB연결 성공");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //드라이브 로드
		return con;
	}
}
