# 김정현 개인 프로젝트

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

![연결 결과](link.png)
