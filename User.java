package project;

import lombok.Data;

@Data //Constructor, Getter, Setter, hashCode(), equals(), toString() �ڵ� ����
public class User {
	private String userId;
	private String userName;
	private String userPassword;
	private int userAge;
	private String userEmail;
}