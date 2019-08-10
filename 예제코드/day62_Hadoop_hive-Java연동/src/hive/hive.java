package hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class hive {

	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = DriverManager.getConnection("jdbc:hive2://70.12.114.212:10000/default", "", "");
        // 뒤에 공백은 아이디랑 비밀번호 (id : root / pwd : 111111)
		Statement stmt = conn.createStatement();
		//ResultSet rs = stmt.executeQuery("SELECT * FROM hdi"); // SQL 질의
		ResultSet rs = stmt.executeQuery("select * from airline_delay where delayYear='2008' LIMIT 2"); // SQL 질의
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		conn.close();
		System.out.println("Success....");
	}

}

