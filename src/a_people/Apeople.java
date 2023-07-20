package a_people;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db_common.DBConnect;

public class Apeople {
   PreparedStatement ps;
   ResultSet rs;
   
   public void display() {
      Connection con = DBConnect.getConnect();
      String sql = "select * from person";
      try {
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         System.out.println("===== person =====");
         while(rs.next()) {
            System.out.println("num : "+rs.getInt("num"));
            System.out.println("name : "+rs.getString("NAME"));
            System.out.println("birth : "+rs.getString("BIRTH"));
            System.out.println("tel : "+rs.getString("TEL"));
            System.out.println("------------------");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}