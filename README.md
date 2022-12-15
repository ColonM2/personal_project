# 개인 프로젝트

```
** 오라클 이클립스 연결

try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			//연결하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE",
					"java",
					"oracle"
					);

```
###### 실행결과
![연결 결과](img/connect.png)
-----------------------------------------------------------------------------------------------------------
```
** 오라클 이클립스 연결

	//매개변수화된 SQL 문 작성
			String sql = "" +
				"INSERT INTO users(userid, username, userpassword, userage, useremail)" + "VALUES(?, ?, ?, ?, ?)";
			
			//PreparedStatement 얻기 및 값 지정
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter2");
			pstmt.setString(2, "한겨울");
			pstmt.setString(3, "12345");
			pstmt.setInt(4, 25);
			pstmt.setString(5, "winter@mycompany.com");
			
			//SQL 문 실행
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수: " + rows);

```
###### 실행결과
![실행 결과](img/Insert.png)
-----------------------------------------------------------------------------------------------------------
