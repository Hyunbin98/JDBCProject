package Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class as_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3307/databasetest?serverTimezone=UTC";
		
		try {
			Class.forName(jdbc_driver).newInstance();
			Connection con = DriverManager.getConnection(jdbc_url, "root", "000855");
			Statement st = (Statement) con.createStatement();
			String sql =  "CREATE TABLE `databasetest`.`addressbook` (\r\n" + 
					"  `id` INT NOT NULL,\r\n" + 
					"  `name` VARCHAR(45) NULL,\r\n" + 
					"  `tel` VARCHAR(45) NULL,\r\n" + 
					"  `email` VARCHAR(45) NULL,\r\n" + 
					"  `address` VARCHAR(45) NULL,\r\n" + 
					"  PRIMARY KEY (`id`))";
			st.executeUpdate(sql);
			//st를 이용하여 테이블 생성 
			
			String sql_pr1 = "INSERT INTO `databasetest`.`addressbook` VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pr1 = con.prepareStatement(sql_pr1);
			pr1.setInt(1, 1);
			pr1.setString(2, "moon");
			pr1.setString(3, "010");
			pr1.setString(4, "a@naver.com"); //email의 도메인을 @naver.com으로 UPDATE 수행
			pr1.setString(5, "a");
			pr1.executeUpdate(); // 업데이트
			
			pr1.setInt(1, 2);
			pr1.setString(2, "hyun");
			pr1.setString(3, "001");
			pr1.setString(4, "b@naver.com");//email의 도메인을 @naver.com으로 UPDATE 수행
			pr1.setString(5, "b");
			pr1.executeUpdate(); // 업데이트
			
			pr1.setInt(1, 3);
			pr1.setString(2, "bin");
			pr1.setString(3, "000");
			pr1.setString(4, "c@naver.com");//email의 도메인을 @naver.com으로 UPDATE 수행
			pr1.setString(5, "c");
			pr1.executeUpdate(); // 업데이트
			
			pr1.setInt(1, 4);
			pr1.setString(2, "don");
			pr1.setString(3, "111");
			pr1.setString(4, "d@naver.com");//email의 도메인을 @naver.com으로 UPDATE 수행
			pr1.setString(5, "d");
			pr1.executeUpdate(); // 업데이트
			
			pr1.setInt(1, 5);
			pr1.setString(2, "stop");
			pr1.setString(3, "010");
			pr1.setString(4, "e@naver.com");//email의 도메인을 @naver.com으로 UPDATE 수행
			pr1.setString(5, "e");
			pr1.executeUpdate(); // 업데이트 , 총 5열 생성 및 업데이트
			
			String delSql_1 = "DELETE FROM `databasetest`.`addressbook` WHERE (`id` = '4');\r\n";	
			st.executeUpdate(delSql_1);
			String delsql_2 = "DELETE FROM `databasetest`.`addressbook` WHERE (`id` = '5');\r\n";
			st.executeUpdate(delsql_2);
			//id 4,5 행(레코드) 삭제
			
			ResultSet rs = ((java.sql.Statement) pr1).executeQuery("SELECT * FROM databasetest.addressbook");
			//테이블 조회
			
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String address = rs.getString("address");
				System.out.printf("id:  %d, userName: %s, tel: %s, email: %s, address: %s"
						+ "\n", id, name, tel, email, address);
			}

			
			rs.close();
			pr1.close();
			st.close();
			con.close();	
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}