package project;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardSelectExample {
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
				"SELECT bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata " +
				"FROM boards " +
				"WHERE bwriter=?";
			
			//PreparedStatement ��� �� �� ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter");
			
			//SQL �� ���� ��, ResultSet�� ���� ������ �б�
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {		
				//������ ���� �а� Board ��ü ����
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBfilename(rs.getString("bfilename"));
				board.setBfiledata(rs.getBlob("bfiledata"));
				
				//�ֿܼ� ���
				System.out.println(board);
				
				//���Ϸ� ����
				Blob blob = board.getBfiledata();
				if(blob != null) {
					InputStream is = blob.getBinaryStream();
					OutputStream os = new FileOutputStream("C:/Temp/" + board.getBfilename());
					is.transferTo(os);
					os.flush();
					os.close();
					is.close();
				}
			}
			rs.close();
			
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