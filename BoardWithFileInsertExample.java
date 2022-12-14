package project;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardWithFileInsertExample {
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
			
			//�Ű�����ȭ�� SQL �� �ۼ�
			String sql = "" +
				"INSERT INTO boards (bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata) " +
				"VALUES (SEQ_BNO.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?)";
			
			//PreparedStatement ��� �� �� ����
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
			pstmt.setString(1, "������ ��");
			pstmt.setString(2, "�Թڴ��� ������.");
			pstmt.setString(3, "winter");
			pstmt.setString(4, "snow.jpg");
			pstmt.setBlob(5, new FileInputStream("src/project/snow.jpg"));
			
			//SQL �� ����
			int rows = pstmt.executeUpdate();
			System.out.println("����� �� ��: " + rows);
			
			//bno �� ���
			if(rows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					int bno = rs.getInt(1);
					System.out.println("����� bno: " + bno);
				}
				rs.close();
			}
			
			//PreparedStatement �ݱ�
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try { 
					//���� ����
					conn.close(); 
				} catch (SQLException e) {}
			}
		}
	}
}