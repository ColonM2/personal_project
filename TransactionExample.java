package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionExample {
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
			
			//Ʈ����� ���� ----------------------------------------------------
				//�ڵ� Ŀ�� ��� ����
				conn.setAutoCommit(false);
				
				//��� �۾�
				String sql1 = "UPDATE accounts SET balance=balance-? WHERE ano=?";
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1,  10000);
				pstmt1.setString(2, "111-111-1111");
				int rows1 = pstmt1.executeUpdate();
				if(rows1 == 0) throw new Exception("��ݵ��� �ʾ���");
				pstmt1.close();
				
				//�Ա� �۾�
				String sql2 = "UPDATE accounts SET balance=balance+? WHERE ano=?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1,  10000);
				pstmt2.setString(2, "222-222-2222");
				int rows2 = pstmt2.executeUpdate();
				if(rows2 == 0) throw new Exception("�Աݵ��� �ʾ���");
				pstmt2.close();
			
				//���� Ŀ�� -> ��� ���� ó��
				conn.commit();
				System.out.println("���� ��ü ����");	
			//Ʈ����� ���� ----------------------------------------------------
		} catch (Exception e) {
			try { 
				//���� �ѹ� -> ��� ���� ó��
				conn.rollback();
			} catch (SQLException e1) {}
			System.out.println("���� ��ü ����");
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					//������� �ڵ� Ŀ�� ��� �ѱ�
					conn.setAutoCommit(true);
					//���� ����
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}