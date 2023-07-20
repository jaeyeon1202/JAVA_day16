package day16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//db에 자바 연결해서 사용하기
// 파일입출력처름 스트림(?)을 만들어서 사용
//우리는 오라클을 사용하기 때문에 오라클에서 제공하는 관련 라이브러리를 사용


class DB{
	Connection con; //DB연결 객체
	PreparedStatement ps; //sql 명령어 전송객체
	ResultSet rs;//명령어 진행 후 결과 저장 객체
	public DB() {
		//최초로 호출해햐 하며 오라클 명령어를 사용할 수 있게 만들어줌.
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
	}
	
	public void getList() {
		String sql = "select * from newst";
		try {
			ps = con.prepareStatement(sql); //명령어를 전송하는 전송 객체를 얻어옴
			rs = ps.executeQuery(); //select * from newst 전송
			System.out.println(" === 회원정보 === ");
			while(rs.next()) {
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
				System.out.println("======");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}
	
}

public class MainClass01 {
	public static void main(String[] args) {
		
		DB db = new DB();
		db.getList();
	}
}
