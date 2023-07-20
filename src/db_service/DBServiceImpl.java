package db_service;

import java.util.ArrayList;
import java.util.Scanner;

import db_dao.NewStDAO;
import db_dto.NewStDTO;

//연산담당
public class DBServiceImpl implements DBService {
	NewStDAO dao;
	
	public DBServiceImpl() {
		dao = new NewStDAO();
	}
	
	public void display() {
		Scanner input = new Scanner(System.in);
		String id, name;
		int num,age;
		while(true) {
			System.out.println("1.모든 데이터 확인\n2.검색데이터확인\n3.데이터추가");
			System.out.println("4.데이터수정\n5.데이터삭제\n6.종료");
			num=input.nextInt();
			switch(num) {
			case 1: 
				ArrayList<NewStDTO> list = getList();
				System.out.println(" === 회원정보 === ");
				System.out.println("id\tname\tage");
				System.out.println("================");
				for(NewStDTO d: list) {
					System.out.print(d.getId()+"\t");
					System.out.print(d.getName()+"\t");
					System.out.println(d.getAge()+"\t");
					System.out.println("------------------");
				}
				
				break;
			case 2:
				System.out.println("검색 id 입력:>>");
				id=input.next();
				NewStDTO dto = search(id);
				if(dto==null) {
					System.out.println("존재하지 않는 회원입니다.");
				}else {
					System.out.println("id: "+dto.getId());
					System.out.println("name: "+dto.getName());
					System.out.println("age: "+dto.getAge());
				}
				break;
			case 3: 
				while(true) {//중복아이디 판별
					System.out.println("추가할 id 입력>>");
					id=input.next();
					NewStDTO d = search(id);
					if(d == null) {
						System.out.println("존재하는 id입니다.");
						break;
					}
					System.out.println("존재하는 id 입니다.");
				}
				System.out.println("추가할 이름 입력>>");
				name=input.next();
				System.out.println("추가할 나이 입력>>");
				age=input.nextInt();
				
				int re = insert(id,name,age);
				if(re==1) {
					System.out.println("회원가입성공 !! ");
				}else {
					System.out.println("문제가 발생했습니다.");
				}
				
				break; 
			case 4: 
	            while(true) {
	               System.out.print("수정할 id 입력: ");
	               id = input.next();
	               NewStDTO d = search(id);
	               if(d != null) {
	                  break;
	               }
	               System.out.println("존재하지 않는 id 입니다.");
	            }
	            
	            System.out.print("수정할 이름 입력: ");
	            name = input.next();
	            System.out.print("수정할 나이 입력: ");
	            age = input.nextInt();
	            
	            NewStDTO d = new NewStDTO(id,name,age);
	            int re1 = modify(d);
	            
	            if(re1==1) {
	               System.out.println("수정 성공!");
	            }else {
	               System.out.println("문제 발생");
	            }
	            break;
			case 5:
				System.out.println("삭제할 id 입력>>");
				id=input.next();
				int result = delete(id);
				if(result ==1) {
					System.out.println("삭제성공!");
				}else {
					System.out.println("문제발생");
				}
				break;
			case 6: 
				return;
			}//switch
		}//while
	}//method
	
	public int modify(NewStDTO d) {
	      return dao.modify(d);
	 }

	public int delete(String id) {
		
		return dao.delete(id);
	}
	
	public ArrayList<NewStDTO> getList() { //부모와 반환타입이 같아야 함!
		ArrayList<NewStDTO> list =  dao.getList();
		return list;
	}
	
	public NewStDTO search(String id) { //데이터베이스에서 아이디 조회
		NewStDTO dto = dao.search(id);
		return dto;
		
	}
	
	public int insert(String id, String name, int age) {
		
		//NewStDTO dto = new NewStDTO();
		//dto.setId(id);
		//dto.setName(name);
		//dto.setAge(age);
		//밑에꺼 1줄과 위의 4줄은 같은 의미
		NewStDTO dto = new NewStDTO(id,name,age);
		
		return dao.insert(dto);
	}
	
	
	
}//class
































