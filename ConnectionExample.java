package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionExample {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//JDBC Driver ���
			Class.forName("oracle.jdbc.OracleDriver");

			//�����ϱ�
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE",
					"java",
					"oracle"
					);
			
			System.out.println("���� ����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//���� ����
					conn.close();
					System.out.println("���� ����");
				} catch (SQLException e) {}
			}
		}
	}
}		