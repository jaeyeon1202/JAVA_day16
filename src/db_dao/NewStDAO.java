package db_dao;
//데이터베이스에 접근하는코드만 dao가 관리한다.
//데이터베이스에 접근

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db_common.DBConnect;
import db_dto.NewStDTO;

public class NewStDAO {
	
	Connection con; //DB연결 객체
	PreparedStatement ps; //sql명령어 전송 객체
	ResultSet rs; //결과 저장 객체
	
	public NewStDAO() { //동일한 데이터베이스를 사용하고 있으니 dbconnect를 통해서 얻어온다.
		con = DBConnect.getConnect();
	}
	
	public ArrayList<NewStDTO> getList() {
		
		ArrayList<NewStDTO> list = new ArrayList<>();
		
		String sql = "select * from newst";
		try {
			ps = con.prepareStatement(sql); //명령어를 전송하는 전송 객체를 얻어옴
			rs = ps.executeQuery(); //select * from newst 전송
			//System.out.println(" === 회원정보 === ");
			while(rs.next()) {
				NewStDTO dto = new NewStDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				
				list.add(dto);
				
				//System.out.println(rs.getString("id"));
				//System.out.println(rs.getString("name"));
				//System.out.println(rs.getInt("age"));
				//System.out.println("======");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	public NewStDTO search(String id) {
		NewStDTO dto = null;
		//"select * from newst where id='아이디'";
		String sql = "select * from newst where id='"+id+"'";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) { //while(rs.next())
				dto = new NewStDTO(rs.getString("id"),
						rs.getString("name"), rs.getInt("age"));
				/*
				dto = new NewStDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				*/
				//System.out.println("id: "+rs.getString("id"));
				//System.out.println("name: "+rs.getString("name"));
				//System.out.println("age: "+rs.getInt("age"));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("dto: "+dto);
		return dto;
	}
	
	public int insert(NewStDTO dto) {
		//insert into newst values('aaa','aaa',30);
		//String sql = "insert into newst values('"+id+"','"+name+"',"+age+")";
		String sql = "insert into newst values(?, ?, ?)"; //?:나중에 내가 값을 채우겠다.
		int result =0;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getId()); //set: 물음표 자리에 값을 채워 넣겠다.
			ps.setString(2, dto.getName());// (물음표자리 순번, 저장할것)
			ps.setInt(3, dto.getAge());
			
			/*
			 Query: select에서만 Query를 사용한다. 결과값이 ResultSet
			 Update: select를 제외한 모든 명령어에서 사용한다.
			 * */
			
			//ps.executeUpdate(); 리턴값: int형 
			result=ps.executeUpdate();
			//ps.executeQuery(); //리턴값: resultSet ->배열형태
			//return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int delete(String id) {
		int result =0 ;
		
		//String sql = "delete from newst where id='"+id+"'";
		String sql = "delete from newst where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			result= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 return result;
	}
	
	public int modify(NewStDTO d) {
	      String sql =
	            "update newst set name =?,age=? where id =?";
	      int result =0;
	      try {
	         ps= con.prepareStatement(sql);
	         ps.setString(1,d.getName());
	         ps.setInt(2,d.getAge());
	         ps.setString(3,d.getId());
	         result = ps.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return result;
	   }
	
	
	
	
}//class





































